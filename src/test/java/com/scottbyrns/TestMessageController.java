package com.scottbyrns;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
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
 * Created 7/17/12 11:10 AM
 */
public class TestMessageController
{

    private boolean failed = true;

    @RegisterAsCallback(
            group = "Unit-Test"
    )
    public void handleMessage (Message message) {
        assertTrue((message.getData() instanceof SendMessageFromAnotherClass) || (message.getData() instanceof TestMessageController));
        failed = false;
    }

    @Test
    public void testRegisteringListenersOfClass() throws Exception
    {
        MessageController.registerListenersOfClass(getClass(), this);
        MessageController.sendMessage("Unit-Test", this);

        SendMessageFromAnotherClass.sendMessage();

        if (failed) {
            fail("The message did not reach the handler.");
        }
    }
}
