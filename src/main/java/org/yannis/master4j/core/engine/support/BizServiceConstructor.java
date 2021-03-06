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

/**
 * This class generally used to builder the implementation of the Service .java file
 */
public class BizServiceConstructor {

    public static void construct(final Context context) {
        String bizServicePath = (String) context.getAttribute("bizServicePath");
        FileUtils.mkdirs(bizServicePath);

        final ProjectConfig projectConfig = context.getProjectConfig();
        final String packageName = projectConfig.getBasePackageName() + ".service.biz";
        final String templateRoot = "/" + projectConfig.getCodeStyle().getTemplateRoot();

        List<BeanInfo> beanInfoList = context.getBeanInfoList();
        for (final BeanInfo beanInfo : beanInfoList) {
            final TableMeta tableMeta = beanInfo.getTableMeta();
            final String className = beanInfo.getBizServiceName();
            final List<Field> fields = beanInfo.getFieldList();
            Map<String, Object> root = new HashMap<String, Object>() {
                {
                    put("package", packageName);
                    put("basePackageName", projectConfig.getBasePackageName());
                    put("imports", "");
                    put("fields", fields);
                    put("pks", FieldUtils.getPKList(fields));
                    put("classDoc", tableMeta.getComment());
                    put("className", className);
                    put("baseClassName", beanInfo.getServiceName());
                    put("bizServiceName", beanInfo.getBizServiceName());
                    put("cacheServiceName", beanInfo.getCacheServiceName());
                    put("convertName", beanInfo.getEntityConverterName());
                    put("mapperName", beanInfo.getMapperName());
                    put("converterName", beanInfo.getEntityConverterName());
                    put("dtoName", beanInfo.getDtoName());
                    put("domainName", beanInfo.getEntityName());
                }
            };

            TemplateUtils.process(templateRoot + "/class/BizService.ftl",
                root, bizServicePath + "/" + className + ".java");
        }
    }

}
