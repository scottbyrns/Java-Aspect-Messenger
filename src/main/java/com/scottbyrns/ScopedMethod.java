package com.scottbyrns;

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

    public ScopedMethod(Object context, Method method)
    {
        this.context = context;
        this.method = method;
    }

    /**
     * Invoke the method in its scope.
     */
    public void invoke() {
        try {
            getMethod().invoke(getContext());
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
}
