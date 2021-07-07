/*
 * Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.grpc.event.handler;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.bean.context.MessageContext;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.event.Event;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.grpc.event.handler.grpc.Service;
import org.wso2.grpc.event.handler.grpc.ServiceGrpc;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;

/**
 * GrpcEventHandler overrides methods of AbstractEventHandler using gRPC stubs.
 */
public class GrpcEventHandler extends AbstractEventHandler {

    private static Log log = LogFactory.getLog(GrpcEventHandler.class);
    private String grpcServerHost;
    private int grpcServerPort;
    private String handlerName;
    private int priority;
    private String certPath;
    private ManagedChannel channel;
    private ServiceGrpc.serviceBlockingStub clientStub;

    @Override
    public String getName() {

        return handlerName;
    }

    @Override
    public int getPriority(MessageContext messageContext) {

        return priority;
    }

    @Override
    public void handleEvent(Event event) throws IdentityEventException {

        Map<String, Object> eventProperties = event.getEventProperties();
        String eventName = event.getEventName();

        // Define event properties for create gRPC event message.
        Map<String, String> grpcMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : eventProperties.entrySet()) {
            if (entry.getValue() != null && entry.getValue().getClass().equals(String.class)) {
                grpcMap.put(entry.getKey(), entry.getValue().toString());
            }
        }

        // Define the gRPC event message
        Service.Event event1 = Service.Event.newBuilder().setEvent(eventName).putAllEventProperties(grpcMap).build();

        // Obtain log message from remote gRPC server.
        Service.Log remoteLog = clientStub.handleEvent(event1);
        // TODO: 2021-01-15 could be removed 
        log.info(remoteLog.getLog());
    }

    /**
     * init method initialize the handler's configurations.
     */
    public void init(String handlerName, int priority, String host, int port, String certPath) {

        this.handlerName = handlerName;
        this.priority = priority;
        this.grpcServerHost = host;
        this.grpcServerPort = port;
        this.certPath = certPath;

        // Obtains the certificate file.
        File clientCACertFile = new File(certPath);

        // Create the channel for gRPC server with server authentication SSL/TLS.
        try {
            this.channel = NettyChannelBuilder.forAddress(grpcServerHost, grpcServerPort)
                    .sslContext(GrpcSslContexts.forClient().trustManager(clientCACertFile).build())
                    .build();
        } catch (SSLException e) {
            log.error("Error occurred while verifying the SSL certificate : ", e);
        }

        // Create the gRPC client stub.
        this.clientStub = ServiceGrpc.newBlockingStub(channel);
    }
}
