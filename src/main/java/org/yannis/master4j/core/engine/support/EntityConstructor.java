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
import org.yannis.master4j.util.FieldUtils;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.TemplateUtils;

public class EntityConstructor {

    public static void construct(final Context context) {
        String entityPath = (String) context.getAttribute("entityPath");
        FileUtils.mkdir(entityPath);
        final ProjectConfig projectConfig = context.getProjectConfig();
        final String packageName = projectConfig.getBasePackageName() + ".entity";
        final String templateRoot = "/" + projectConfig.getCodeStyle().getTemplateRoot();

        List<BeanInfo> beanInfoList = context.getBeanInfoList();
        for (BeanInfo beanInfo : beanInfoList) {
            final String className = beanInfo.getEntityName();
            final List<Field> fields = beanInfo.getFieldList();
            final TableMeta tableMeta = beanInfo.getTableMeta();
            Map<String, Object> root = new HashMap<String, Object>(5) {
                {
                    put("package", packageName);
                    put("imports", FieldUtils.getImportList(fields));
                    put("classDoc", tableMeta.getComment());
                    put("className", className);
                    put("fields", fields);
                }
            };

            TemplateUtils.process(templateRoot + "/class/Entity.ftl", root, entityPath + "/" + className + ".java");
        }
    }

}
