package org.yannis.master4j.core.engine.support;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.entity.Field;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.FieldUtils;
import org.yannis.master4j.util.TemplateUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/7/25.
 */
public class DtoConstructor {
    public static void construct(final String domainPath, final ProjectConfig projectConfig, final TableMeta meta) {
        final String className = getClassName(meta);
        final List<Field> fields = FieldUtils.getFields(meta);
        Map<String,Object> root = new HashMap<String,Object>(){
            {
                put("package", projectConfig.getBasePackageName()+".dto");
                put("imports","");
                put("classDoc",meta.getComment());
                put("className", className);
                put("fields",fields);
                put("toStringBody","");
            }
        };

        TemplateUtils.process("/springmvc/class/Dto.ftl", root, domainPath + "/" + className + ".java");
    }

    private static String getClassName(TableMeta meta) {
        String tableName = meta.getTableName();
        if (meta.getPrefixName() != null) {
            tableName.replace(meta.getPrefixName(), "");
        }

        return ClassUtils.getCamelCaseName(tableName)+"DTO";
    }

}
