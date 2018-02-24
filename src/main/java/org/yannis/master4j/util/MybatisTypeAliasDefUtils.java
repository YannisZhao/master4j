package org.yannis.master4j.util;

import java.util.List;
import org.yannis.master4j.model.BeanInfo;
import org.yannis.master4j.model.Context;

/**
 * @author Yannis Zhao
 * @date 2018/1/22
 * @since 1.0
 */
public final class MybatisTypeAliasDefUtils {


    public static String getDef(Context context) {

        StringBuilder typeAliasDefs = new StringBuilder();
        typeAliasDefs.append("\t<typeAliases>\n");

        List<BeanInfo> beanInfoList = context.getBeanInfoList();
        for (BeanInfo beanInfo : beanInfoList) {
            String entityName = beanInfo.getEntityName();
            String basePackage = context.getProjectConfig().getBasePackageName();
            String entityFullName = basePackage + ".entity." + entityName;
            typeAliasDefs.append(String.format("\t\t<typeAlias type=\"%s\" alias=\"%s\"/>\n",
                entityFullName, entityName));
        }
        typeAliasDefs.append("\t</typeAliases>\n");

        return typeAliasDefs.toString();
    }
}
