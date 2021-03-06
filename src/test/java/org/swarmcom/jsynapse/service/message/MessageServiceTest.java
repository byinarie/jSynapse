/*
 * (C) Copyright 2015 eZuce Inc.
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
 *
*/
package org.swarmcom.jsynapse.service.message;

import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.swarmcom.jsynapse.TestBase;
import org.swarmcom.jsynapse.domain.Room;
import org.swarmcom.jsynapse.domain.Message;
import org.swarmcom.jsynapse.service.room.RoomService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.swarmcom.jsynapse.domain.Message.MessageSummary;
import static org.swarmcom.jsynapse.domain.Message.Messages;
import static org.swarmcom.jsynapse.domain.Message.MessageSummary.*;

public class MessageServiceTest extends TestBase {
    @Autowired
    MessageService messageService;

    @Autowired
    RoomService roomService;

    @Test
    public void getRoomMessages() {
        //create room
        Room room = new Room();
        room.setName("My First Test Room");
        roomService.createRoom(room);
        String roomId = room.getRoomId();
        //send message to room
        Message message = new Message();
        message.setMsgtype("room_creation");
        message.setBody("Body Test message");
        messageService.sendMessage(roomId, message);
        //get room messages
        Messages messages = messageService.getMessages(roomId);
        assertTrue(0 < messages.getChunk().size());
        MessageSummary summary = messages.getChunk().get(0);
        assertEquals("room_creation", summary.getContent().get(MSG_TYPE));
        assertEquals("Body Test message", summary.getContent().get(BODY));
    }
}
