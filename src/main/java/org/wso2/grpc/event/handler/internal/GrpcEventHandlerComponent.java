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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @scr.component name="org.wso2.grpc.event.handler.internal.GrpcEventHandlerComponent" immediate="true"
 */
public class GrpcEventHandlerComponent {

    private static Log log = LogFactory.getLog(GrpcEventHandlerComponent.class);
    private Iterator<Object> grpcEventHandlerNames;
    private List<GrpcBasedHandlerProperties> handlerConfigs = new ArrayList<>();

    public void populateHandlerNames() {

        // Obtain gRPC based handler names from identity-event properties.
        try {
            this.grpcEventHandlerNames = IdentityEventConfigBuilder.getInstance()
                    .getModuleConfigurations("grpcHandler").getModuleProperties().values().iterator();
            if (log.isDebugEnabled()) {
                StringBuilder stringBuilder = new StringBuilder();
                while (grpcEventHandlerNames.hasNext()) {
                    stringBuilder.append(grpcEventHandlerNames.next() + "|");
                }
                log.debug("gRPC Handler names : " + stringBuilder.toString());
            }
        } catch (IdentityEventException e) {
            log.error("Error occurred while reading Identity Event properties for gRPC handler names.", e);
        }
    }

    public void populateHandlerConfigs() {

        // Obtain gRPC based handler configurations from identity-event properties.
        while (grpcEventHandlerNames.hasNext()) {
            String handlerName = String.valueOf(grpcEventHandlerNames.next());
            ModuleConfiguration handlerConfiguration = null;

            try {
                handlerConfiguration = IdentityEventConfigBuilder.getInstance()
                        .getModuleConfigurations(handlerName);
            } catch (IdentityEventException e) {
                log.error("Error occurred while reading Identity Event properties for gRPC handler configurations."
                        , e);
            }
            String priority = handlerConfiguration.getModuleProperties()
                    .getProperty(handlerName + ".priority");
            String host = handlerConfiguration.getModuleProperties().getProperty(handlerName + ".host");
            String port = handlerConfiguration.getModuleProperties().getProperty(handlerName + ".port");
            String certPath = handlerConfiguration.getModuleProperties().getProperty(handlerName + ".certPath");

            // Add gRPC based handler properties to handlerConfigs.
            GrpcBasedHandlerProperties grpcBasedHandlerProperties = new GrpcBasedHandlerProperties(handlerName,
                    priority,
                    host,
                    port,
                    certPath);
            this.handlerConfigs.add(grpcBasedHandlerProperties);
        }
    }

    @Activate
    protected void activate(ComponentContext context) {

        this.populateHandlerNames();
        this.populateHandlerConfigs();

        // Create multiple gRPC based event handler instances.
        Iterator<GrpcBasedHandlerProperties> handlerConfigsArray = this.handlerConfigs.listIterator();
        while (handlerConfigsArray.hasNext()) {
            GrpcBasedHandlerProperties grpcBasedHandlerProperties = handlerConfigsArray.next();
            GrpcEventHandler eventHandler = new GrpcEventHandler();
            eventHandler.init(grpcBasedHandlerProperties.getHandlerName(),
                    grpcBasedHandlerProperties.getPriority(),
                    grpcBasedHandlerProperties.getHost(),
                    grpcBasedHandlerProperties.getPort(),
                    grpcBasedHandlerProperties.getCertPath());

            // Register the gRPC based event handlers as an OSGI service.
            context.getBundleContext().registerService(
                    AbstractEventHandler.class.getName(), eventHandler, null);
            log.info(grpcBasedHandlerProperties.getHandlerName() + " is activated successfully.");
        }

    }

    @Deactivate
    protected void deactivate(ComponentContext context) {

        if (log.isDebugEnabled()) {
            log.debug("gRPC event handler is deactivated ");
        }
    }

}
