/*
 * Copyright 2015-present Open Networking Laboratory
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

package org.onosproject.pof.controller.impl;


import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.onosproject.floodlightpof.protocol.OFMessage;
import org.onosproject.floodlightpof.protocol.factory.BasicFactory;
import org.onosproject.floodlightpof.protocol.factory.OFMessageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//import org.onosproject.floodlightpof.protocol.OFFactories;
//import org.onosproject.floodlightpof.protocol.OFMessageReader;


/**
 * Decode an pof message from a Channel, for use in a netty pipeline.
 */
public class OFMessageDecoder extends FrameDecoder {

    OFMessageFactory factory = new BasicFactory();
    protected static Logger log = LoggerFactory.getLogger(OFMessageDecoder.class);

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel,
            ChannelBuffer buffer) throws Exception {
        if (!channel.isConnected()) {
            // In testing, I see decode being called AFTER decode last.
            // This check avoids that from reading corrupted frames
            return null;
        }

        // Note that a single call to decode results in reading a single
        // OFMessage from the channel buffer, which is passed on to, and processed
        // by, the controller (in OFChannelHandler).
        // This is different from earlier behavior (with the original openflowj),
        // where we parsed all the messages in the buffer, before passing on
        // a list of the parsed messages to the controller.
        // The performance *may or may not* not be as good as before.

        //OFMessageReader<OFMessage> reader = OFFactories.getGenericReader();
        //OFMessage message = reader.readFrom(buffer);

        List<OFMessage> message = factory.parseOFMessage(buffer);
        if (message != null) {
            //TODO Nothing
        }
        return message;
    }

}
