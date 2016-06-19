package org.yannis.master4j.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yannis on 6/13/16.
 */
public class SqlTypeMapper {

    private static Map<String,String> mapper = new HashMap<String,String>() {
        {
            put("BIT", "String");
            put("TINYINT", "String");
            put("BOOL", "boolean");
            put("SMALLINT", "int");
            put("MEDIUMINT", "int");
            put("MEDIUMINT UNSIGNED", "long");
            put("INT", "int");
            put("INTEGER UNSIGNED", "long");
            put("BIGINT", "long");
            put("BIGINT UNSIGNED", "BigInteger");
            put("FLOAT", "float");
            put("DOUBLE", "double");
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

    public static String getType(String columnType) {
        String type = mapper.get(columnType);
        if(type == null){
            type = "String";
        }
        return type;
    }
}
