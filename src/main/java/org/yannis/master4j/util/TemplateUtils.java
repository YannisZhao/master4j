package org.yannis.master4j.util;

import java.util.Map;

/**
 * Created by yannis on 6/15/16.
 */
public class TemplateUtils {

    public static String getTemplateBasePath(){
        return Thread.currentThread().getContextClassLoader().getResource("springmvc").getPath();
    }

    public static String process(String template, Map<String, String> root) {
        return template;
    }
}
