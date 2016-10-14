/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Also with any question can email zhaoyjun0222@gmail.com
 */
package org.yannis.master4j.util;

import org.yannis.master4j.config.DirConfig;

public class DirecotryCreator {
    public static void createDirs(DirConfig dirConfig) {
        System.out.println("start building directory...");
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
        FileUtils.mkdirs(dirConfig.getWebModulePath()+"/"+dirConfig.getSrcRelativePath()+"/web");
        FileUtils.mkdirs(dirConfig.getWebModulePath()+"/"+dirConfig.getTestRelativePath()+"/web");
        FileUtils.mkdirs(dirConfig.getWebModulePath()+"/"+dirConfig.getResourceRelativePath());
        FileUtils.mkdirs(dirConfig.getWebModulePath()+"/"+dirConfig.getTestResourceRelativePath());

        FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getSrcRelativePath()+"/api");
        //FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getTestRelativePath());
        FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getResourceRelativePath());
        //FileUtils.mkdirs(dirConfig.getApiModulePath()+"/"+dirConfig.getTestResourceRelativePath());

        FileUtils.mkdirs(dirConfig.getImplModulePath()+"/"+dirConfig.getSrcRelativePath());
        FileUtils.mkdirs(dirConfig.getImplModulePath()+"/"+dirConfig.getTestRelativePath());
        FileUtils.mkdirs(dirConfig.getImplModulePath()+"/"+dirConfig.getResourceRelativePath());
        FileUtils.mkdirs(dirConfig.getImplModulePath()+"/"+dirConfig.getTestResourceRelativePath());

    }
}
