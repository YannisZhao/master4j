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
package org.yannis.master4j.core.engine.config.constructor.springmvc;

import java.util.List;
import org.yannis.master4j.entity.Field;
import org.yannis.master4j.meta.DatabaseMeta;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.FieldUtils;
import org.yannis.master4j.util.FileUtils;

/**
 * This class generally used to builder the POM file
 */
public class MessageResourceConstructor {

    public static void construct(final String path, final DatabaseMeta dbMeta) {
        StringBuilder builder = new StringBuilder("");
        for (TableMeta meta : dbMeta.getTableMetaList()) {
            // Construct message resource

            String entityName = getClassName(meta).toLowerCase();

            builder.append("# " + entityName + "\n");

            List<Field> fields = FieldUtils.getFields(meta);
            for (Field field : fields) {
                if (!field.isNullable() && field.getDefaultValue() == null) {
                    String pv = String.format("%s.%s.%s = %s must be not null", entityName, field.getName(), "empty",
                        field.getName());
                    builder.append(pv + "\n");
                }
            }

            builder.append("\n");
        }
        FileUtils.newFile(path, builder.toString());
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName);
    }
}
