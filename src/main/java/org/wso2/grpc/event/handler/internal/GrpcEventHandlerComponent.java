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

package org.wso2.grpc.event.handler.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.identity.event.IdentityEventConfigBuilder;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.bean.ModuleConfiguration;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.grpc.event.handler.GrpcEventHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @scr.component name="org.wso2.grpc.event.handler.internal.GrpcEventHandlerComponent" immediate="true"
 */
public class GrpcEventHandlerComponent {

    private static Log log = LogFactory.getLog(GrpcEventHandlerComponent.class);
    private ModuleConfiguration grpcEventHandlerConfiguration;
    private List<List<String>> servers = new ArrayList<>();
    private File certFile;

    public void getHandlerConfiguration() {

        try {
            this.grpcEventHandlerConfiguration = IdentityEventConfigBuilder.getInstance().getModuleConfigurations
                    ("multiHandlers");
        } catch (IdentityEventException e) {
            log.info("Identity Event Exception", e);
        }

        // Obtain certPath from identity-event properties.
        String certFilePath = grpcEventHandlerConfiguration.getModuleProperties()
                .getProperty("multiHandlers.certPath");

        // Obtain the CA certificate file.
        this.certFile = new File(certFilePath);
    }

    public void getServers() {

        // Obtains servers host and port configurations from identity-event properties.
        String serverConfigs = grpcEventHandlerConfiguration.getModuleProperties()
                .getProperty("multiHandlers.servers");
        List<String> serverList = Arrays.asList(serverConfigs.split(","));
        Iterator<String> serverListArray = serverList.listIterator();
        while (serverListArray.hasNext()) {
            List<String> serverInfo = Arrays.asList(serverListArray.next().split(String.valueOf('#')));
            servers.add(serverInfo);
        }
    }

    @Activate
    protected void activate(ComponentContext context) {

        // Create multiple event handler instances
        this.getHandlerConfiguration();
        this.getServers();
        Iterator<List<String>> serverList = servers.listIterator();
        while (serverList.hasNext()) {
            List<String> address = serverList.next();
            log.info(address.get(0) + ":" + address.get(1));
            GrpcEventHandler eventHandler = new GrpcEventHandler();
            eventHandler.init(address.get(0), address.get(1), certFile);

            // Register the event handlers as an OSGI service.
            context.getBundleContext().registerService(
                    AbstractEventHandler.class.getName(), eventHandler, null);
            log.info("gRPC event handler is activated successfully - " + address.get(0) + ":" + address.get(1));
        }

    }

    @Deactivate
    protected void deactivate(ComponentContext context) {

        if (log.isDebugEnabled()) {
            log.debug("gRPC event handler is deactivated ");
        }
    }

}
