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
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContextBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.core.bean.context.MessageContext;
import org.wso2.carbon.identity.event.IdentityEventConfigBuilder;
import org.wso2.carbon.identity.event.IdentityEventConstants;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.bean.ModuleConfiguration;
import org.wso2.carbon.identity.event.event.Event;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.grpc.event.handler.grpc.Service;
import org.wso2.grpc.event.handler.grpc.serviceGrpc;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLException;

/**
 * GrpcEventHandler overrides methods of AbstractEventHandler using gRPC stubs.
 */
public class GrpcEventHandler extends AbstractEventHandler {

    private static Log log = LogFactory.getLog(GrpcEventHandler.class);
    private ModuleConfiguration grpcEventHandlerConfiguration;
    private String grpcServerHost;
    private String grpcServerPort;
    private ManagedChannel channel;
    private serviceGrpc.serviceBlockingStub clientStub;

    public GrpcEventHandler() {

        {
            try {
                this.grpcEventHandlerConfiguration = IdentityEventConfigBuilder.getInstance().getModuleConfigurations
                        ("grpcBasedEventHandler");
            } catch (IdentityEventException e) {
                log.info("IdentityEventException: ", e);
            }
        }

        // Obtain grpcServerHost and grpcServerPort from identity-event properties.
        this.grpcServerHost = grpcEventHandlerConfiguration.getModuleProperties()
                .getProperty("grpcBasedEventHandler.host");
        this.grpcServerPort = grpcEventHandlerConfiguration.getModuleProperties()
                .getProperty("grpcBasedEventHandler.port");

        // Create the channel for gRPC server with server authentication SSL/TLS.
        File clientCACertFile = new File("/home/nuwanga/wso2/custom-event-handler/src/main/java/org/wso2/grpc/event/handler/cert1/ca-cert.pem");
        try {
            this.channel = NettyChannelBuilder.forAddress(grpcServerHost, Integer.parseInt(grpcServerPort))
                    .sslContext(GrpcSslContexts.forClient().trustManager(clientCACertFile).build())
                    .build();
        } catch (SSLException e) {
            e.printStackTrace();
        }

        // Create the gRPC client stub.
        this.clientStub = serviceGrpc.newBlockingStub(channel);

    }

    @Override
    public String getName() {

        // Obtain handlerName from remote gRPC server.
        Service.HandlerName handlerName = clientStub.getName(Service.Empty.newBuilder().build());
        return handlerName.getName();
    }

    @Override
    public int getPriority(MessageContext messageContext) {

        // Obtain priority from remote gRPC server.
        Service.Priority priority = clientStub.getPriority(Service.MessageContext.newBuilder().build());
        return priority.getPriority();
    }

    @Override
    public void handleEvent(Event event) throws IdentityEventException {

        Map<String, Object> eventProperties = event.getEventProperties();
        String userName = (String) eventProperties.get(IdentityEventConstants.EventProperty.USER_NAME);
        String tenantDomain = (String) eventProperties.get(IdentityEventConstants.EventProperty.TENANT_DOMAIN);
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
        log.info(remoteLog.getLog());

    }
}
