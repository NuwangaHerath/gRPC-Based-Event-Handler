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
    private String grpcEventHandlerNames;
    private File certFile;
    private List<String> handlerNames;
    private List<HandlerProperties> handlerConfigs = new ArrayList<>();

    public void getHandlerNames() {

        try {
            this.grpcEventHandlerNames = IdentityEventConfigBuilder.getInstance().getModuleConfigurations
                    ("multiHandlers").getModuleProperties().getProperty("multiHandlers.handlerNames");
        } catch (IdentityEventException e) {
            log.info("Identity Event Exception", e);
        }
        this.handlerNames = Arrays.asList(grpcEventHandlerNames.split(","));
    }

    public void getHandlers() {

        Iterator<String> handlerNamesArray = handlerNames.listIterator();
        while (handlerNamesArray.hasNext()) {
            String handlerName = handlerNamesArray.next();
            ModuleConfiguration handlerConfiguration = null;
            try {
                handlerConfiguration = IdentityEventConfigBuilder.getInstance()
                        .getModuleConfigurations(handlerName);
            } catch (IdentityEventException e) {
                log.info("Identity Event Exception", e);
            }
            String priority = handlerConfiguration.getModuleProperties()
                    .getProperty(handlerName + ".priority");
            String host = handlerConfiguration.getModuleProperties().getProperty(handlerName + ".host");
            String port = handlerConfiguration.getModuleProperties().getProperty(handlerName + ".port");
            String certPath = handlerConfiguration.getModuleProperties().getProperty(handlerName + ".certPath");
            HandlerProperties handlerProperties = new HandlerProperties(handlerName, priority, host, port, certPath);
            this.handlerConfigs.add(handlerProperties);
        }
    }

    @Activate
    protected void activate(ComponentContext context) {

        // Create multiple event handler instances.
        this.getHandlerNames();
        this.getHandlers();

        Iterator<HandlerProperties> handlerConfigsArray = this.handlerConfigs.listIterator();
        while (handlerConfigsArray.hasNext()) {
            HandlerProperties handlerProperties = handlerConfigsArray.next();
            GrpcEventHandler eventHandler = new GrpcEventHandler();
            eventHandler.init(handlerProperties.getHandlerName(), handlerProperties.getPriority(), handlerProperties.getHost(), handlerProperties.getPort()
                    , handlerProperties.getCertFile());

            // Register the event handlers as an OSGI service.
            context.getBundleContext().registerService(
                    AbstractEventHandler.class.getName(), eventHandler, null);
            log.info("gRPC event handler is activated successfully - " + handlerProperties.getHost() + ":" + handlerProperties.getPort());
        }

    }

    @Deactivate
    protected void deactivate(ComponentContext context) {

        if (log.isDebugEnabled()) {
            log.debug("gRPC event handler is deactivated ");
        }
    }

}
