package org.yannis.master4j.util;

/**
 * Created by yannis on 6/15/16.
 */
public class TemplateUtils {

    public static String getTemplateBasePath(){
        return Thread.currentThread().getContextClassLoader().getResource("springmvc").getPath();
    }
}
