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

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author conan (kly824968443@gmail.com)
 * @create 2019/10/12
 * @description
 */
@Component
@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class MasterDatabaseConfig {

    private String driverClassName;

    private String url;

    private String username;

    private String password;

    private String name;

    public Map<String, Object> getProperties() {
        Map<String, Object> map = new HashMap<>();
        map.put("driverClassName", getDriverClassName());
        map.put("url", getUrl());
        map.put("username", getUsername());
        map.put("password", getPassword());
        return map;
    }
}

