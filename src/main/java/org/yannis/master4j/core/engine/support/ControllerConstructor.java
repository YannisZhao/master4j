package org.yannis.master4j.core.engine.support;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.entity.Field;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.FieldUtils;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.TemplateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yannis on 6/15/16.
 */
public class ControllerConstructor {
    public static void construct(final String controllerPath, final ProjectConfig projectConfig, final TableMeta meta) {
        final String className = getClassName(meta);
        Map<String,Object> root = new HashMap<String,Object>(){
            {
                put("package", projectConfig.getBasePackageName()+".domain");
                put("imports","");
                put("classDoc",meta.getComment());
                put("className", className);
            }
        };

        TemplateUtils.process("/springmvc/class/Controller.ftl", root, controllerPath + "/" + className + ".java");
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName)+"Controller";
    }

}