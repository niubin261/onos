/*
 * Copyright 2016-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.driver.handshaker;

/**
 * Default driver to fallback on if no other driver is available.
 */

import org.onosproject.floodlightpof.protocol.OFMessage;
import org.onosproject.floodlightpof.protocol.OFPhysicalPort;
import org.onosproject.pof.controller.driver.AbstractPofSwitch;

import java.util.Map;

public class DefaultPofSwitchHandshaker extends AbstractPofSwitch {

    private static final int LOWEST_PRIORITY = 0;

    @Override
    public Boolean supportNxRole() {
        return false;
    }

    @Override
    public void startDriverHandshake() {

    }

    @Override
    public void processDriverHandshakeMessage(OFMessage m) {}

    @Override
    public boolean isDriverHandshakeComplete() {
        return true;
    }

    @Override
    public Map<Integer, OFPhysicalPort> getPorts() {
/*        if (this.factory().getVersion() == org.projectfloodlight.openflow.protocol.OFVersion.OF_10) {
            return java.util.Collections.unmodifiableList(features.getPorts());
        } else {
            return Collections.unmodifiableList(
                    ports.stream().flatMap(p -> p.getEntries().stream())
                            .collect(tolist()));
        }*/
        return ports;
    }


}