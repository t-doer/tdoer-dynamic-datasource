/*
 *
 *  Copyright 2017-2019 T-Doer (tdoer.com).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package com.tdoer.datasource;

import com.tdoer.datasource.config.MasterDatabaseConfig;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author conan (kly824968443@gmail.com)
 * @create 2019/10/12
 * @description
 */
@Aspect
@Slf4j
public class DynamicDataSourceAspect implements MethodInterceptor {

    @Autowired
    MasterDatabaseConfig masterDatabaseConfig;


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        //before
        DynamicDataSourceContextHolder.setDataSourceKey(masterDatabaseConfig.getName());
        //around
        Object result = methodInvocation.proceed();
        log.debug("switch datasource to {} in Method {}",
                DynamicDataSourceContextHolder.getDataSourceKey(), methodInvocation.getMethod().getDeclaringClass());
        //after
        DynamicDataSourceContextHolder.clearDataSourceKey();
        return result;
    }
}

