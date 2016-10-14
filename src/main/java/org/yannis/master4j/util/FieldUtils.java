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

import org.yannis.master4j.entity.Field;
import org.yannis.master4j.meta.ColumnMeta;
import org.yannis.master4j.meta.TableMeta;

import java.util.ArrayList;
import java.util.List;

public class FieldUtils {
    public static String getCamelCaseName(String name) {
        StringBuilder builder = new StringBuilder();

        boolean nextNeedSuperCase = false;
        for (int i = 0; i < name.length(); ++i) {

            char ch = name.charAt(i);
            if (i == 0) {
                builder.append(Character.toLowerCase(ch));
            } else {
                if ('_' == name.charAt(i)) {
                    nextNeedSuperCase = true;
                } else {
                    if (nextNeedSuperCase || Character.isUpperCase(ch)) {
                        builder.append(Character.toUpperCase(ch));
                    } else {
                        builder.append(Character.toLowerCase(ch));
                    }

                    nextNeedSuperCase = false;
                }
            }
        }

        return builder.toString();
    }

    public static List<Field> getFields(TableMeta meta) {
        List<Field> fields = new ArrayList<>();
        for (ColumnMeta columnMeta : meta.getColumnMetas()) {
            Field field = new Field();
            field.setName(FieldUtils.getCamelCaseName(columnMeta.getColumnName()));
            field.setType(columnMeta.getColumnType());
            field.setComparedDefaultValue(getTypeString(field));
            field.setSize(Integer.toString(columnMeta.getColumnSize()));
            field.setComment(columnMeta.getComment());
            field.setPrimary(columnMeta.isPrimary());
            fields.add(field);
        }
        return fields;
    }

    public static String getImportList(List<Field> fields) {
        StringBuilder builder = new StringBuilder("");
        for (Field field : fields) {
            String type = field.getType();
            if (builder.indexOf(type) >= 0) {
                continue;
            }
            switch (type) {
                case "BigDecimal":
                    builder.append("import java.math.BigDecimal;\n");
                    break;
                case "Date":
                    builder.append("import java.sql.Date;\n");
                    break;
                case "Time":
                    builder.append("import java.sql.Time;\n");
                    break;
                case "Timestamp":
                    builder.append("import java.sql.Timestamp;\n");
                    break;
            }
        }

        return builder.toString();
    }

    private static String getTypeString(Field field) {
        switch (field.getType()) {
            case "String":
                return "null";
            case "int":
                return "0";
            case "float":
                return "0";
            case "long":
                return "0";
            case "double":
                return "0";
            case "decimal":
                return "0";
            case "Timestamp":
                return null;
            case "Time":
                return null;
            case "Date":
                return null;
        }
        return null;
    }

    public static List<Field> getPKList(List<Field> fields) {
        List<Field> pkList = new ArrayList<>();
        for (Field field : fields){
            if(field.isPrimary()) {
                pkList.add(field);
            }
        }
        return pkList;
    }

}
