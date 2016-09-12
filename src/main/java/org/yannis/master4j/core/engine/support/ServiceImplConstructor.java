package org.yannis.master4j.core.engine.support;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.TemplateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2016/6/24.
 */
public class ServiceImplConstructor {
    public static void construct(final String serviceImplPath, final ProjectConfig projectConfig, final TableMeta meta) {
        final String className = getClassName(meta);
        Map<String,Object> root = new HashMap<String,Object>(){
            {
                put("package", projectConfig.getBasePackageName()+".service.impl");
                put("basePackageName", projectConfig.getBasePackageName());
                put("imports","");
                put("classDoc",meta.getComment());
                put("className", className);
                put("baseClassName", className.substring(0,className.indexOf("Impl")));
                put("daoName", className.substring(0,className.lastIndexOf("ServiceImpl"))+"Dao");
                put("converterName", className.substring(0,className.lastIndexOf("ServiceImpl"))+"Converter");
                put("dtoName", className.substring(0,className.lastIndexOf("ServiceImpl"))+"DTO");
            }
        };

        TemplateUtils.process("/springmvc/class/ServiceImpl.ftl", root, serviceImplPath + "/" + className + ".java");
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName) + "ServiceImpl";
    }
}
