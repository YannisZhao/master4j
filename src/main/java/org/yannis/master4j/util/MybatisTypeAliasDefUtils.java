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
package org.yannis.master4j.util;

import java.util.List;
import org.yannis.master4j.model.BeanInfo;
import org.yannis.master4j.model.Context;

/**
 * @author Yannis Zhao
 * @date 2018/1/22
 * @since 1.0
 */
public final class MybatisTypeAliasDefUtils {


    public static String getDef(Context context) {

        StringBuilder typeAliasDefs = new StringBuilder();
        typeAliasDefs.append("\t<typeAliases>\n");

        List<BeanInfo> beanInfoList = context.getBeanInfoList();
        for (BeanInfo beanInfo : beanInfoList) {
            String entityName = beanInfo.getEntityName();
            String basePackage = context.getProjectConfig().getBasePackageName();
            String entityFullName = basePackage + ".entity." + entityName;
            typeAliasDefs.append(String.format("\t\t<typeAlias type=\"%s\" alias=\"%s\"/>\n",
                entityFullName, entityName));
        }
        typeAliasDefs.append("\t</typeAliases>\n");

        return typeAliasDefs.toString();
    }
}
