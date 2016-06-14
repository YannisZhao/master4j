package org.yannis.master4j.config;

/**
 * Created by yannis on 6/13/16.
 */
public interface TemplateDir {

    String basePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();;
    String staticBasePath = basePath + "/static";
    String springmvcBasePath = basePath + "/springmvc";
    String sshBasePath = basePath + "/ssh";
    String ssmBasePath = basePath + "ssm";

    String AddOnsPath = staticBasePath + "/add-ons";
    String cssPath = staticBasePath + "/css";
    String imagePath = staticBasePath + "/images";
    String jsPath = staticBasePath + "/js";
    String pagesPath = staticBasePath + "/pages";
}