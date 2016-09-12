package org.yannis.master4j.core.engine.support;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.entity.Field;
import org.yannis.master4j.meta.ColumnMeta;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.FieldUtils;
import org.yannis.master4j.util.TemplateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yannis on 9/12/16.
 */
public class EntityConverterConstructor {
    public static void construct(final String domainPath, final ProjectConfig projectConfig, final TableMeta meta) {
        final String className = getClassName(meta);
        final List<Field> fields = getFields(meta);
        Map<String,Object> root = new HashMap<String,Object>(){
            {
                put("package", projectConfig.getBasePackageName()+".converter");
                put("basePackageName", projectConfig.getBasePackageName());
                put("imports","");
                put("classDoc",meta.getComment());
                put("className", className);
                put("fields",fields);
                put("dtoName", className.substring(0,className.lastIndexOf("Converter"))+"DTO");
            }
        };

        TemplateUtils.process("/springmvc/class/EntityConverter.ftl", root, domainPath + "/" + className + ".java");
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName)+"Converter";
    }

    private static List<Field> getFields(TableMeta meta) {
        List<Field> fields = new ArrayList<>();
        for(ColumnMeta columnMeta : meta.getColumnMetas()){
            Field field = new Field();
            field.setName(FieldUtils.getCamelCaseName(columnMeta.getColumnName()));
            field.setType("int");
            field.setSize(Integer.toString(columnMeta.getColumnSize()));
            field.setComment(columnMeta.getComment());
            fields.add(field);
        }
        return fields;
    }
}
