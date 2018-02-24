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
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.model.BeanInfo;
import org.yannis.master4j.model.Context;
import org.yannis.master4j.model.Field;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.TemplateUtils;

public class EntityConverterConstructor {

    public static void construct(final Context context) {

        String entityConverterPath = (String) context.getAttribute("entityConverterPath");
        FileUtils.mkdir(entityConverterPath);
        final ProjectConfig projectConfig = context.getProjectConfig();
        final String packageName = projectConfig.getBasePackageName() + ".converter";
        final String templateRoot = "/" + projectConfig.getCodeStyle().getTemplateRoot();

        List<BeanInfo> beanInfoList = context.getBeanInfoList();
        for (final BeanInfo beanInfo : beanInfoList) {
            final TableMeta tableMeta = beanInfo.getTableMeta();
            final String className = beanInfo.getEntityConverterName();
            final List<Field> fields = beanInfo.getFieldList();
            Map<String, Object> root = new HashMap<String, Object>() {
                {
                    put("package", packageName);
                    put("basePackageName", projectConfig.getBasePackageName());
                    put("imports", "");
                    put("classDoc", tableMeta.getComment());
                    put("className", className);
                    put("fields", fields);
                    put("dtoName", beanInfo.getDtoName());
                    put("entityName", beanInfo.getEntityName());
                }
            };

            TemplateUtils.process(templateRoot + "/class/EntityConverter.ftl", root,
                entityConverterPath + "/" + className + ".java");
        }

    }

}
