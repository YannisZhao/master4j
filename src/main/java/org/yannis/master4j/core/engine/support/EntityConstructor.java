package org.yannis.master4j.core.engine.support;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.TemplateUtils;

/**
 * Created by yannis on 6/15/16.
 */
public class EntityConstructor {
    public static void construct(String domainPath, ProjectConfig projectConfig, TableMeta meta) {
        String templatePath = TemplateUtils.getTemplateBasePath() + "/springmvc/class/Domain.ftl";

        String template = FileUtils.read(templatePath);
        String className = getClassName(meta);
        FileUtils.newFile(domainPath + "/" + className + ".java", template);
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName);
    }

}
