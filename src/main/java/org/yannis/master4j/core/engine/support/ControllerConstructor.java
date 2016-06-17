package org.yannis.master4j.core.engine.support;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.TemplateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yannis on 6/15/16.
 */
public class ControllerConstructor {
    public static void construct(String controllerPath, ProjectConfig projectConfig, TableMeta meta) {
        String templatePath = TemplateUtils.getTemplateBasePath() + "/class/Controller.ftl";

        String template = FileUtils.read(templatePath);
        final String className = getClassName(meta);
        Map<String,String> root = new HashMap<String,String>(){
            {
                put("className", className);
            }
        };
        FileUtils.newFile(controllerPath + "/" + className + ".java", TemplateUtils.process(template, root));
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName)+"Controller";
    }

}
