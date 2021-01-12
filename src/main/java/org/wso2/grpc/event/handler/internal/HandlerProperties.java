/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

import java.io.File;

public class HandlerProperties {

    private String handlerName;
    private int priority;
    private String host;
    private int port;
    private File certFile;

    public HandlerProperties(String handlerName, String priority, String host, String port, String certPath) {

        this.handlerName = handlerName;
        this.priority = Integer.parseInt(priority);
        this.host = host;
        this.port = Integer.parseInt(port);
        this.certFile = new File(certPath);

    }

    public String getHandlerName() {

        return this.handlerName;
    }

    public int getPriority() {
        return this.priority;
    }

    public String getHost() {

        return this.host;
    }

    public int getPort() {

        return this.port;
    }

    public File getCertFile() {

        return this.certFile;
    }

}
