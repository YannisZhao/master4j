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
 * Created by dell on 2016/6/24.
 */
public class DaoImplConstructor {
    public static void construct(final String daoPath, final ProjectConfig projectConfig, final TableMeta meta) {
        final String className = getClassName(meta);
        Map<String,Object> root = new HashMap<String,Object>(){
            {
                put("package", projectConfig.getBasePackageName()+".dao.impl");
                put("imports","");
                put("fields", FieldUtils.getFields(meta));
                put("classDoc",meta.getComment());
                put("className", className);
                put("baseClassName", className.substring(0,className.indexOf("Impl")));
                put("domainName", className.substring(0,className.lastIndexOf("DaoImpl")));
                put("dtoName", className.substring(0,className.lastIndexOf("DaoImpl"))+"DTO");
                put("convertName", className.substring(0,className.lastIndexOf("DaoImpl"))+"Converter");
            }
        };

        TemplateUtils.process("/springmvc/class/DaoImpl.ftl", root, daoPath + "/" + className + ".java");
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName) + "DaoImpl";
    }

}
