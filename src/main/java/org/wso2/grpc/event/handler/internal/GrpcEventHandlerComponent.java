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
import org.opensaml.xmlsec.encryption.Public;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Deactivate;
import org.wso2.carbon.identity.event.IdentityEventConfigBuilder;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.bean.ModuleConfiguration;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.grpc.event.handler.GrpcEventHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @scr.component name="org.wso2.grpc.event.handler.internal.GrpcEventHandlerComponent" immediate="true"
 */
public class GrpcEventHandlerComponent {

    private static Log log = LogFactory.getLog(GrpcEventHandlerComponent.class);
    private ModuleConfiguration grpcEventHandlerConfiguration;
    private List<List<String>> servers = new ArrayList<>();

    public void getHandlerConfiguration() {

        try {
            this.grpcEventHandlerConfiguration = IdentityEventConfigBuilder.getInstance().getModuleConfigurations
                    ("grpcBasedEventHandler");
        } catch (IdentityEventException e) {
            log.info("Identity Event Exception", e);
        }
    }

    public void getServers(ModuleConfiguration handlerConfig) {

        String serverConfigs = grpcEventHandlerConfiguration.getModuleProperties()
                .getProperty("grpcBasedEventHandler.servers");
        List<String> serverList = Arrays.asList(serverConfigs.split(","));
        Iterator<String> serverListArray = serverList.listIterator();
        while (serverListArray.hasNext()) {
            List<String> serverInfo = Arrays.asList(serverListArray.next().split(String.valueOf('#')));
            servers.add(serverInfo);
        }
    }

    @Activate
    protected void activate(ComponentContext context) {

        GrpcEventHandler eventHandler = new GrpcEventHandler();
        // Register the custom listener as an OSGI service.
        context.getBundleContext().registerService(
                AbstractEventHandler.class.getName(), eventHandler, null);
        log.info("gRPC event handler is activated successfully.");
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {

        if (log.isDebugEnabled()) {
            log.debug("gRPC event handler is deactivated ");
        }
    }

}
