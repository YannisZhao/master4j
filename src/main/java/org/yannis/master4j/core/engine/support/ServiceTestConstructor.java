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
import org.yannis.master4j.model.Field;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.TemplateUtils;

public class ServiceTestConstructor {

    public static void construct(final Context context) {

        String testServicePath = (String) context.getAttribute("serviceTestPath");
        FileUtils.mkdirs(testServicePath);

        final ProjectConfig projectConfig = context.getProjectConfig();
        final String packageName = projectConfig.getBasePackageName() + ".service.impl";
        final String templateRoot = "/" + projectConfig.getCodeStyle().getTemplateRoot();

        List<BeanInfo> beanInfoList = context.getBeanInfoList();
        for (final BeanInfo beanInfo : beanInfoList) {
            final TableMeta tableMeta = beanInfo.getTableMeta();
            final String className = beanInfo.getTestServiceName();
            final List<Field> fields = beanInfo.getFieldList();
            Map<String, Object> root = new HashMap<String, Object>() {
                {
                    put("package", packageName);
                    put("basePackageName", projectConfig.getBasePackageName());
                    put("imports", "");
                    put("classDoc", tableMeta.getComment());
                    put("className", className);
                    put("tableName", tableMeta.getTableName());
                    put("fields", fields);
                    put("domainName", beanInfo.getEntityName());
                    put("dtoName", beanInfo.getDtoName());
                    put("serviceName", beanInfo.getServiceName());
                    put("serviceImplName", beanInfo.getServiceImplName());
                }
            };

            TemplateUtils.process(templateRoot + "/class/ServiceTest.ftl",
                root, testServicePath + "/" + className + ".java");
        }

    }

}
