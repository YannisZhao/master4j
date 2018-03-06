/**
 * Copyright © 2015 - 2025 Yannis Zhao (zhaoyjun0222@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yannis.master4j.core.engine.support;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.model.BeanInfo;
import org.yannis.master4j.model.Context;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.ModelCreationUtils;
import org.yannis.master4j.util.TemplateUtils;

public class MapperTestConstructor {

    public static void construct(final Context context) {

        String mapperTestPath = (String) context.getAttribute("mapperTestPath");
        FileUtils.mkdir(mapperTestPath);
        final ProjectConfig projectConfig = context.getProjectConfig();
        final String packageName = projectConfig.getBasePackageName() + ".mapper";
        final String templateRoot = "/" + projectConfig.getCodeStyle().getTemplateRoot();

        List<BeanInfo> beanInfoList = context.getBeanInfoList();
        for (final BeanInfo beanInfo : beanInfoList) {
            final String className = beanInfo.getMapperName() + "Test";
            final TableMeta tableMeta = beanInfo.getTableMeta();

            Map<String, Object> root = new HashMap<String, Object>() {
                {
                    put("package", packageName);
                    put("basePackageName", projectConfig.getBasePackageName());
                    put("imports", "");
                    put("classDoc", tableMeta.getComment());
                    put("tableName", tableMeta.getTableName());
                    put("className", className);
                    put("entityName", beanInfo.getEntityName());
                    put("dtoName", beanInfo.getDtoName());
                    put("fields", beanInfo.getFieldList());
                    put("mapperName", beanInfo.getMapperName());
                    try {
                        put("mockBeanScript", ModelCreationUtils.generateScript(beanInfo));
                    } catch (Exception e) {
                        e.printStackTrace();
                        put("mockBeanScript", "");
                    }
                }
            };

            TemplateUtils
                .process(templateRoot + "/class/MapperTest.ftl", root, mapperTestPath + "/" + className + ".java");
        }

    }
}
