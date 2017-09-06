/*
 * Copyright 2017-present Open Networking Foundation
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

package org.onosproject.net.pi.runtime;

import com.google.common.annotations.Beta;

/**
 * An action that can be executed as a consequence of a match in a match+action table of a protocol-independent
 * pipeline.
 */
@Beta
public interface PiTableAction {

    /**
     * Type of this action.
     *
     * @return a type
     */
    Type type();

    enum Type {
        /**
         * Simple action with runtime parameters set by the control plane.
         */
        ACTION,

        /**
         * Executes the action group specified by the given identifier.
         */
        ACTION_GROUP_ID,

        /**
         * Executes the action member group specified by the given identifier.
         */
        GROUP_MEMBER_ID
    }
}
