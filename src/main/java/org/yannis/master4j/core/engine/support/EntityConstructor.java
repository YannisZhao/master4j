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
        final List<Field> fields = FieldUtils.getFields(meta);
        Map<String, Object> root = new HashMap<String, Object>() {
            {
                put("package", projectConfig.getBasePackageName() + ".domain");
                put("basePackageName", projectConfig.getBasePackageName());
                put("imports", FieldUtils.getImportList(fields));
                put("classDoc", meta.getComment());
                put("className", className);
                put("fields", fields);
            }
        };

        TemplateUtils.process("/springmvc/class/Domain.ftl", root, domainPath + "/" + className + ".java");
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName);
    }

}
