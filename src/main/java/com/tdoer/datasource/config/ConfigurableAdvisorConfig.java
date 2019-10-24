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

package com.tdoer.datasource.config;

import com.tdoer.datasource.DynamicDataSourceAspect;
import com.tdoer.datasource.TenantDynamicDataSourceAspect;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author conan (kly824968443@gmail.com)
 * @create 2019/10/23
 * @description
 */
@Configuration
public class ConfigurableAdvisorConfig {

    @Value("${master.datasource.pointcut:execution( * com.tdoer.datasource.mapper.tenant..*.*(..))}")
    private String masterPointcut;

    @Value("${tenant.datasource.pointcut:execution( * com.tdoer..mapper..*.*(..))}")
    private String tenantPointcut;

    @Bean
    public AspectJExpressionPointcutAdvisor masterConfigurabledvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(masterPointcut);
        advisor.setAdvice(dynamicDataSourceAspect());
        advisor.setOrder(2);
        return advisor;
    }

    @Bean
    public AspectJExpressionPointcutAdvisor tenantConfigurabledvisor() {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(tenantPointcut);
        advisor.setAdvice(tenantDynamicDataSourceAspect());
        advisor.setOrder(1);
        return advisor;
    }

    @Bean
    public DynamicDataSourceAspect dynamicDataSourceAspect() {
        return new DynamicDataSourceAspect();
    }

    @Bean
    public TenantDynamicDataSourceAspect tenantDynamicDataSourceAspect() {
        return new TenantDynamicDataSourceAspect();
    }
}
