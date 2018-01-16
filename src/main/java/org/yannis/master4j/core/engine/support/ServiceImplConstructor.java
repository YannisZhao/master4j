/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Also with any question can email zhaoyjun0222@gmail.com
 */
package org.yannis.master4j.core.engine.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.entity.Field;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.FieldUtils;
import org.yannis.master4j.util.TemplateUtils;

/**
 * This class generally used to builder the implementation of the Service .java file
 */
public class ServiceImplConstructor {

    public static void construct(final String serviceImplPath, final ProjectConfig projectConfig,
        final TableMeta meta) {
        final String className = ClassUtils.getClassName(meta);
        Map<String, Object> root = new HashMap<String, Object>() {
            {
                put("package", projectConfig.getBasePackageName() + ".service.impl");
                put("basePackageName", projectConfig.getBasePackageName());
                put("imports", "");
                List<Field> fields = FieldUtils.getFields(meta);
                put("fields", fields);
                put("pks", FieldUtils.getPKList(fields));
                put("classDoc", meta.getComment());
                put("className", className);
                put("baseClassName", className.substring(0, className.indexOf("Impl")));
                put("daoName", className.substring(0, className.lastIndexOf("ServiceImpl")) + "Dao");
                put("converterName", className.substring(0, className.lastIndexOf("ServiceImpl")) + "Converter");
                put("dtoName", className.substring(0, className.lastIndexOf("ServiceImpl")));
                put("domainName", className.substring(0, className.lastIndexOf("ServiceImpl")) + "Entity");
            }
        };

        TemplateUtils.process("/springmvc/class/ServiceImpl.ftl", root, serviceImplPath + "/" + className + ".java");
    }

}
