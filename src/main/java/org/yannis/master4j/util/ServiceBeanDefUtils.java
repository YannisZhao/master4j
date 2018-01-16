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
package org.yannis.master4j.util;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;
import org.yannis.master4j.meta.TableMeta;

import java.util.List;

public class ServiceBeanDefUtils {
    public static String getDef(ProjectConfig projectConfig, DatabaseMeta dbMeta) {
        String basePackage = projectConfig.getBasePackageName();
        List<TableMeta> tblMetas = dbMeta.getTableMetaList();

        StringBuilder serviceDefs = new StringBuilder();

        for (TableMeta meta : tblMetas){
            String serviceShort = uncap(ClassUtils.getClassName(meta));
            String serviceFullName = basePackage+".service.impl."+ClassUtils.getClassName(meta);
            serviceDefs.append(String.format("\t<bean id=\"%s\" class=\"%s\" />\n", serviceShort, serviceFullName));
        }

        return serviceDefs.toString();
    }

    private static String uncap(String className) {
        return className.substring(0,1).toLowerCase()+className.substring(1);
    }
}