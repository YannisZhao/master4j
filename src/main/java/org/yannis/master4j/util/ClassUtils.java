package org.yannis.master4j.util;

/**
 * Created by yannis on 6/15/16.
 */
public class ClassUtils {
    public static String getCamelCaseName(String name) {
        StringBuilder builder = new StringBuilder();

        boolean nextNeedSuperCase = false;
        for (int i = 0; i < name.length(); ++i) {

            char ch = name.charAt(i);
            if (i == 0) {
                builder.append(Character.toUpperCase(ch));
            } else {
                if ('_' == name.charAt(i)) {
                    nextNeedSuperCase = true;
                } else {
                    if (nextNeedSuperCase || Character.isUpperCase(ch)) {
                        builder.append(Character.toUpperCase(ch));
                    } else {
                        builder.append(Character.toLowerCase(ch));
                    }

                    nextNeedSuperCase = false;
                }
            }
        }

        return builder.toString();
    }
}
