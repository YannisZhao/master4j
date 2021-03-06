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
package org.yannis.master4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.config.DirConfig;

public class DirectoryCreator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryCreator.class);

    public static void createDirs(DirConfig dirConfig) {

        LOGGER.info("start building project directory...");

        FileUtils.mkdir(dirConfig.getBasePath());

        FileUtils.mkdir(dirConfig.getWebModulePath());
        /*FileUtils.mkdirs(dirConfig.getControllerModulePath());
        FileUtils.mkdirs(dirConfig.getVoModulerPath());
        FileUtils.mkdir(dirConfig.getImageModulePath());
        FileUtils.mkdir(dirConfig.getJsModulePath());
        FileUtils.mkdir(dirConfig.getCssModulePath());
        FileUtils.mkdir(dirConfig.getAddonModulePath());
        FileUtils.mkdir(dirConfig.getPageModulePath());*/

        FileUtils.mkdir(dirConfig.getApiModulePath());
        //FileUtils.mkdir(dirConfig.getServiceModulePath());

        FileUtils.mkdir(dirConfig.getImplModulePath());
        /*FileUtils.mkdir(dirConfig.getEntityModulerPath());
        FileUtils.mkdir(dirConfig.getServiceImplModulePath());
        FileUtils.mkdir(dirConfig.getDaoModulePath());*/

        FileUtils.mkdirs(dirConfig.getWebinfPath());
        FileUtils.mkdirs(dirConfig.getWebModulePath() + "/" + dirConfig.getSrcRelativePath() + "/web");
        FileUtils.mkdirs(dirConfig.getWebModulePath() + "/" + dirConfig.getTestRelativePath() + "/web");
        FileUtils.mkdirs(dirConfig.getWebModulePath() + "/" + dirConfig.getResourceRelativePath());
        FileUtils.mkdirs(dirConfig.getWebModulePath() + "/" + dirConfig.getTestResourceRelativePath());

        FileUtils.mkdirs(dirConfig.getApiModulePath() + "/" + dirConfig.getSrcRelativePath() + "/api");
        //FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getTestRelativePath());
        //FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getResourceRelativePath());
        //FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getTestResourceRelativePath());

        FileUtils.mkdirs(dirConfig.getImplModulePath() + "/" + dirConfig.getSrcRelativePath());
        FileUtils.mkdirs(dirConfig.getImplModulePath() + "/" + dirConfig.getTestRelativePath());
        FileUtils.mkdirs(dirConfig.getImplModulePath() + "/" + dirConfig.getResourceRelativePath());
        FileUtils.mkdirs(dirConfig.getImplModulePath() + "/" + dirConfig.getTestResourceRelativePath());

    }
}
