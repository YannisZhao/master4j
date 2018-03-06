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

import java.util.HashMap;
import java.util.Map;

public class SqlTypeMapper {

    private static Map<String, String> mapper = new HashMap<String, String>() {
        {
            put("BIT", "Integer");
            put("TINYINT UNSIGNED", "Integer");
            put("TINYINT", "Integer");
            put("BOOL", "Boolean");
            put("SMALLINT", "Integer");
            put("SMALLINT UNSIGNED", "Integer");
            put("MEDIUMINT", "Integer");
            put("MEDIUMINT UNSIGNED", "Long");
            put("INT", "Integer");
            put("INTEGER UNSIGNED", "Long");
            put("BIGINT", "Long");
            put("BIGINT UNSIGNED", "Long");
            put("FLOAT", "Float");
            put("DOUBLE", "Double");
            put("DECIMAL", "BigDecimal");
            put("DATE", "Date");
            put("TIME", "Time");
            put("DATETIME", "Timestamp");
            put("TIMESTAMP", "Timestamp");
            put("BINARY", "byte[]");
            put("TINYBLOB", "byte[]");
            put("MEDIUMBLOB", "byte[]");
            put("BLOB", "byte[]");
            put("LONGBLOB", "byte[]");
            put("TEXT", "String");
            put("MEDIUMTEXT", "String");
            put("LONGTEXT", "String");
            put("ENUM", "String");
            put("SET", "String");
            put("VARCHAR", "String");
            put("CHAR", "String");
        }
    };

    public static String getJavaType(String columnType) {
        String type = mapper.get(columnType);
        if (type == null) {
            type = "String";
        }
        return type;
    }
}
