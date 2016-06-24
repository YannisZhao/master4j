package org.yannis.master4j.core.engine.config.constructor.springmvc;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.util.TemplateUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell on 2016/6/24.
 */
public class PomConstructor {

    public void construct(final String targetPath, final ProjectConfig projectConfig) {
        Map<String,Object> root = new HashMap<String,Object>(){
            {
                put("group", "");
                put("module","");
                put("version","");
            }
        };

        TemplateUtils.process("/springmvc/class/ServiceImpl.ftl", root, targetPath + "/pom.xml");
    }

}
