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
 * This class generally used to builder the Service .java file
 */
public class ServiceConstructor {

    public static void construct(final String servicePath, final ProjectConfig projectConfig, final TableMeta meta) {
        final String className = getClassName(meta);
        Map<String, Object> root = new HashMap<String, Object>() {
            {
                put("package", projectConfig.getBasePackageName() + ".api.service");
                put("basePackageName", projectConfig.getBasePackageName());
                List<Field> fields = FieldUtils.getFields(meta);
                put("fields", fields);
                put("pks", FieldUtils.getPKList(fields));
                put("imports", "");
                put("classDoc", meta.getComment());
                put("className", className);
                put("dtoName", className.substring(0, className.lastIndexOf("Service")));
            }
        };

        TemplateUtils.process("/springmvc/class/Service.ftl", root, servicePath + "/" + className + ".java");
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName) + "Service";
    }
}
