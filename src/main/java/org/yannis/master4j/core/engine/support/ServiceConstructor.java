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
public class ServiceConstructor {
    public static void construct(final String servicePath, final ProjectConfig projectConfig, final TableMeta meta) {
        final String className = getClassName(meta);
        Map<String,Object> root = new HashMap<String,Object>(){
            {
                put("package", projectConfig.getBasePackageName()+".api.service");
                put("basePackageName", projectConfig.getBasePackageName());
                put("imports","");
                put("classDoc",meta.getComment());
                put("className", className);
                put("dtoName", className.substring(0,className.lastIndexOf("Service"))+"DTO");
            }
        };

        TemplateUtils.process("/springmvc/class/Service.ftl", root, servicePath + "/" + className + ".java");
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName) + "Service";
    }
}
