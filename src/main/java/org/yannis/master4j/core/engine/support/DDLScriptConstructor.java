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

import java.util.List;
import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.meta.TableMeta.Index;
import org.yannis.master4j.model.BeanInfo;
import org.yannis.master4j.model.Context;
import org.yannis.master4j.model.Field;
import org.yannis.master4j.util.FileUtils;

/**
 * This class generally used to builder the Controller .java file
 */
public class DDLScriptConstructor {

    public static void construct(final Context context) {

        final String templateRoot = "/" + context.getProjectConfig().getCodeStyle().getTemplateRoot();
        final List<BeanInfo> beanInfoList = context.getBeanInfoList();
        for (final BeanInfo beanInfo : beanInfoList) {
            final TableMeta tableMeta = beanInfo.getTableMeta();
            final List<Field> fields = beanInfo.getFieldList();
            String script = generateDDLScript(tableMeta, fields);
            DirConfig dirConfig = context.getProjectConfig().getDirConfig();
            String fileName = dirConfig.getImplModulePath() + "/" + dirConfig.getTestResourceRelativePath()
                + "/h2/" + tableMeta.getTableName() + ".sql";
            FileUtils.newFile(fileName, script);
        }
    }

    private static String generateDDLScript(TableMeta tableMeta, List<Field> fields) {
        StringBuilder script = new StringBuilder("CREATE TABLE `").append(tableMeta.getTableName()).append("` (\n");
        for (Field field : fields) {
            //`id` bigint(20) NOT NULL AUTO_INCREMENT,
            script.append("\t`").append(field.getName()).append("` ")
                .append(field.getColumnType()).append("(").append(field.getSize()).append(")")
                .append(field.isNullable() ? " NULL " : " NOT NULL")
                .append(field.isAutoIncrement() ? " AUTO_INCREMENT" : "")
                .append(field.getDefaultValue() != null ? " DEFAULT '" + field.getDefaultValue() + "'" : "")
                .append(",\n");
        }

        // primary key
        StringBuilder primaryStmt = new StringBuilder();
        Index primary = tableMeta.getPrimary();
        if (primary != null) {
            primaryStmt.append("PRIMARY KEY `").append(primary.getName()).append("` (");
            List<String> primaryColumns = primary.getColumns();
            for (String primaryColumn : primaryColumns) {
                primaryStmt.append("`").append(primaryColumn).append("`,");
            }
            primaryStmt.deleteCharAt(primaryStmt.length() - 1).append(")");
        }
        script.append("\t").append(primaryStmt);

        // unique key
        StringBuilder uniqueStmt = new StringBuilder();
        Index unique = tableMeta.getUnique();
        if (unique != null) {
            uniqueStmt.append("UNIQUE KEY `").append(unique.getName()).append("` (");
            List<String> uniqueColumns = unique.getColumns();
            for (String uniqueColumn : uniqueColumns) {
                uniqueStmt.append("`").append(uniqueColumn).append("`,");
            }
            uniqueStmt.deleteCharAt(uniqueStmt.length() - 1).append(")");
        }

        if (uniqueStmt.length() > 0) {
            if (primaryStmt.length() == 0) {
                script.append("\t").append(uniqueStmt);
            } else {
                script.append(",\n\t").append(uniqueStmt);
            }
        }

        // unique key
        StringBuilder normalStmt = new StringBuilder();
        Index normal = tableMeta.getNormal();
        if (normal != null) {
            normalStmt.append("INDEX `").append(normal.getName()).append("` (");
            List<String> normalColumns = normal.getColumns();
            for (String normalColumn : normalColumns) {
                normalStmt.append("`").append(normalColumn).append("`,");
            }
            normalStmt.deleteCharAt(normalStmt.length() - 1).append(")");
        }

        if (normalStmt.length() > 0) {
            if (primaryStmt.length() == 0 && uniqueStmt.length() == 0) {
                script.append("\t").append(normalStmt);
            } else {
                script.append(",\n\t").append(normalStmt);
            }
        }

        script.append("\n) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;");
        return script.toString();
    }

}
