/**
 * Copyright © 2015 - 2025 Yannis Zhao (zhaoyjun0222@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yannis.master4j.core.engine.config.constructor;

import java.util.HashMap;
import java.util.Map;
import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.util.TemplateUtils;

/**
 * This class generally used to builder the POM file
 */
public class PomConstructor {

    public static void construct(final ProjectConfig projectConfig) {

        DirConfig dirConfig = projectConfig.getDirConfig();
        String templateRoot = "/" + projectConfig.getCodeStyle().getTemplateRoot();

        Map<String, Object> root = new HashMap<String, Object>() {
            {
                put("group", projectConfig.getBasePackageName());
                put("module", projectConfig.getProjectName());
                put("version", "1.0.0-SNAPSHOT");
            }
        };

        TemplateUtils.process(templateRoot + "/pom/parent.ftl", root, dirConfig.getBasePath() + "/pom.xml");
        TemplateUtils.process(templateRoot + "/pom/api.ftl", root, dirConfig.getApiModulePath() + "/pom.xml");
        TemplateUtils.process(templateRoot + "/pom/impl.ftl", root, dirConfig.getImplModulePath() + "/pom.xml");
        TemplateUtils.process(templateRoot + "/pom/web.ftl", root, dirConfig.getWebModulePath() + "/pom.xml");
    }

}
