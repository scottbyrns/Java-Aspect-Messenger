package com.scottbyrns;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Wrapper around a method and the execution context it will be invoked in.
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
 * Created 7/17/12 11:28 AM
 */
public class ScopedMethod
{
    private Object context;
    private Method method;
    private String channel;

    public ScopedMethod(Object context, Method method, String channel)
    {
        this.context = context;
        this.method = method;
        this.channel = channel;
    }

    /**
     * Invoke the method in its scope.
     */
    public void invoke(Message message) {
        try {

            boolean isVoidReturn = getMethod().getReturnType().getSimpleName().equals("void");
            boolean isOneArgument = getMethod().getParameterTypes().length == 1;
            if (isVoidReturn && isOneArgument) {
                boolean argumentIsMethod = method.getParameterTypes()[0].getSimpleName().equals(Message.class.getSimpleName());
                if (argumentIsMethod) {
                    try {
                        if (message.getChannel().equals("PUBLIC") || message.getChannel().equals(getChannel())) {
                            getMethod().invoke(getContext(), Message.copy(message));
                        }
                    }
                    catch (InvocationTargetException e) {
                        e.printStackTrace();
                        throw new Exception("Method: " + getContext().getClass().getSimpleName() + "#" + method.getName() + "(Message message) threw an internal exception preventing invocation.");
                    }
                }
                else {
                    new Exception("Method: " + method.getName() + " does not accept a Message object as an argument.").printStackTrace();
                }
            }
            else if (!isVoidReturn) {
                new Exception("Method: " + method.getName() + " is required to have a void return type.").printStackTrace();
            }
            else if (!isOneArgument) {
                new Exception("Method: " + method.getName() + " takes more than one argument.").printStackTrace();
            }
            else {
                new Exception("Method: " + method.getName() + " could not be called. Not sure why.").printStackTrace();
            }

        }
        catch (Throwable e) {
            e.printStackTrace();
            // Muck it.
        }
    }

    public Object getContext()
    {
        return context;
    }

    public void setContext(Object context)
    {
        this.context = context;
    }

    public Method getMethod()
    {
        return method;
    }

    public void setMethod(Method method)
    {
        this.method = method;
    }

    public String getChannel()
    {
        return channel;
    }

    public void setChannel(String channel)
    {
        this.channel = channel;
    }
}
