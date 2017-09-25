/*
 * Copyright 2017 original authors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */
package org.particleframework.aop.simple;


import org.particleframework.aop.Interceptor;
import org.particleframework.aop.InvocationContext;
import org.particleframework.aop.simple.Mutating;
import org.particleframework.inject.MutableArgumentValue;

import javax.inject.Singleton;

/**
 * @author Graeme Rocher
 * @since 1.0
 */
@Singleton
public class ArgMutatingInterceptor implements Interceptor {

    @Override
    public Object intercept(InvocationContext context) {
        Mutating m = context.getAnnotation(Mutating.class);
        MutableArgumentValue arg = (MutableArgumentValue) context.getParameters().get(m.value());
        if(arg != null) {
            Object value = arg.getValue();
            if(value instanceof Number) {
                arg.setValue(((Number)value).intValue()*2);
            }
            else {
                arg.setValue("changed");
            }
        }
        return context.proceed();
    }
}