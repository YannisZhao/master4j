package org.yannis.master4j.core.engine.config.constructor.springmvc;

import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.util.TemplateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yannis on 2016/6/24.
 */
public class PomConstructor {

    public static void construct(final ProjectConfig projectConfig) {

        DirConfig dirConfig = projectConfig.getDirConfig();

        Map<String,Object> root = new HashMap<String,Object>(){
            {
                put("group", projectConfig.getBasePackageName());
                put("module",projectConfig.getProjectName());
                put("version","1.0.0-SNAPSHOT");
            }
        };

        TemplateUtils.process("/springmvc/pom/parent.ftl", root, dirConfig.getBasePath()+"/pom.xml");
        TemplateUtils.process("/springmvc/pom/api.ftl", root, dirConfig.getApiModulePath()+"/pom.xml");
        TemplateUtils.process("/springmvc/pom/impl.ftl", root, dirConfig.getImplModulePath()+"/pom.xml");
        TemplateUtils.process("/springmvc/pom/web.ftl", root, dirConfig.getWebModulePath()+"/pom.xml");
    }

}
