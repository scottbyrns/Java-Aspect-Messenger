package com.scottbyrns;

/**
 * Encapsulation of a message destined for a message handler.
 *
 * Copyright (C) 2012 by Scott Byrns
 * http://github.com/scottbyrns
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * <p/>
 * Created 7/17/12 2:51 PM
 */
public class Message
{
    public String channel;
    public Object data;

    /**
     * Copy the values of a message to a new instance.
     *
     * @param message The message to copy.
     * @return A new message instance.
     */
    public static Message copy(Message message) {
        return create(message.getChannel(), message.getData());
    }

    /**
     * Create a new message object.
     *
     * @param channel The information channel you want to send it along.
     * @param data The data contained within the message.
     *
     * @return A new message populated with your input values.
     */
    public static Message create(String channel, Object data) {
        Message outMessage = new Message();
        outMessage.setChannel(channel);
        outMessage.setData(data);
        return outMessage;
    }

    /**
     * Create a new message object.
     *
     * @param data The data contained within the message.
     * @return A new message populated with your data object.
     */
    public static Message create(Object data) {
        Message outMessage = new Message();
        outMessage.setChannel("PUBLIC");
        outMessage.setData(data);
        return outMessage;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }
}
