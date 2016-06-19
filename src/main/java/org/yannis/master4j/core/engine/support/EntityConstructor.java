package org.yannis.master4j.core.engine.support;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.entity.Field;
import org.yannis.master4j.meta.ColumnMeta;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.FieldUtils;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.TemplateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yannis on 6/15/16.
 */
public class EntityConstructor {
    public static void construct(final String domainPath, final ProjectConfig projectConfig, final TableMeta meta) {
        final String className = getClassName(meta);
        final List<Field> fields = getFields(meta);
        Map<String, Object> root = new HashMap<String, Object>() {
            {
                put("package", projectConfig.getBasePackageName() + ".domain");
                put("imports", getImportList(fields));
                put("classDoc", meta.getComment());
                put("className", className);
                put("fields", fields);
            }
        };

        TemplateUtils.process("/springmvc/class/Domain.ftl", root, domainPath + "/" + className + ".java");
    }

    private static String getImportList(List<Field> fields) {
        StringBuilder builder = new StringBuilder("");
        for (Field field : fields) {
            String type = field.getType();
            if (builder.indexOf(type) >= 0) {
                continue;
            }
            switch (type) {
                case "BigDecimal":
                    builder.append("import java.math.BigDecimal\n");
                    break;
                case "Date":
                    builder.append("import java.sql.Date\n");
                    break;
                case "Time":
                    builder.append("import java.sql.Time\n");
                    break;
                case "Timestamp":
                    builder.append("import java.sql.Timestamp\n");
                    break;
            }
        }

        return builder.toString();
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName);
    }

    private static List<Field> getFields(TableMeta meta) {
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

}
