package org.yannis.master4j.util;

import org.yannis.master4j.entity.Field;
import org.yannis.master4j.meta.ColumnMeta;
import org.yannis.master4j.meta.TableMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/6/18.
 */
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
            field.setSize(Integer.toString(columnMeta.getColumnSize()));
            field.setComment(columnMeta.getComment());
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

}
