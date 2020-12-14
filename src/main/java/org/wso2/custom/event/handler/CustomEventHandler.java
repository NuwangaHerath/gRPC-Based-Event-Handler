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

package org.wso2.custom.event.handler;

import org.wso2.custom.event.handler.internal.CustomEventHandlerComponent;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.application.common.model.User;
import org.wso2.carbon.identity.core.bean.context.MessageContext;
import org.wso2.carbon.identity.event.IdentityEventConstants;
import org.wso2.carbon.identity.event.IdentityEventException;
import org.wso2.carbon.identity.event.event.Event;
import org.wso2.carbon.identity.event.handler.AbstractEventHandler;
import org.wso2.carbon.user.core.UserCoreConstants;
import org.wso2.carbon.user.core.UserStoreManager;
import org.apache.commons.logging.Log;

import java.util.HashMap;
import java.util.Map;

public class CustomEventHandler extends AbstractEventHandler {

    private static Log log = LogFactory.getLog(org.wso2.custom.event.handler.CustomEventHandler.class);

    @Override
    public String getName() {

        return "customEvent";
    }

    @Override
    public int getPriority(MessageContext messageContext) {

        return 58;
    }

    @Override
    public void handleEvent(Event event) throws IdentityEventException {

        Map<String, Object> eventProperties = event.getEventProperties();
        String userName = (String) eventProperties.get(IdentityEventConstants.EventProperty.USER_NAME);
        String tenantDomain = (String) eventProperties.get(IdentityEventConstants.EventProperty.TENANT_DOMAIN);
        String userStoreDomain = (String) eventProperties.get(IdentityEventConstants.EventProperty.USER_STORE_DOMAIN);
        String eventName = event.getEventName();

        if (IdentityEventConstants.Event.PRE_ADD_USER.equals(eventName)) {
            log.info("PRE_ADD_USER from Custom Event Handler");
        }
        if (IdentityEventConstants.Event.POST_ADD_USER.equals(eventName)) {
            log.info("POST_ADD_EVENT from Custom Event Handler");
        }

    }

    private void triggerSampleEvent(User user, String eventName)
            throws IdentityEventException {

        HashMap<String, Object> properties = new HashMap<String, Object>();

        properties.put(IdentityEventConstants.EventProperty.USER_NAME, user.getUserName());
        properties.put(IdentityEventConstants.EventProperty.
                TENANT_DOMAIN, user.getTenantDomain());
        properties.put(IdentityEventConstants.EventProperty.
                USER_STORE_DOMAIN, user.getUserStoreDomain());

        Event sampleEvent = new Event(eventName, properties);
        try {
            new CustomEventHandlerComponent().getIdentityEventService().handleEvent(sampleEvent);
        } catch (IdentityEventException identityEventException) {
            identityEventException.printStackTrace();
        }
    }
}
