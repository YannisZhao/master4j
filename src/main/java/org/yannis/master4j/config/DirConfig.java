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
package org.yannis.master4j.config;

/**
 * Created by yannis on 6/13/16.
 */
public class DirConfig {

    private String basePath;
    private String webModulePath;
    private String apiModulePath;
    private String implModulePath;

    private String webinfPath;
    private String srcRelativePath;
    private String resourceRelativePath;
    private String testRelativePath;
    private String testResourceRelativePath;

    /*private String controllerModulePath;
    private String serviceModulePath;
    private String serviceImplModulePath;
    private String daoModulePath;
    private String entityModulerPath;
    private String voModulerPath;

    private String imageModulePath;
    private String jsModulePath;
    private String cssModulePath;
    private String addonModulePath;
    private String pageModulePath;*/


    public DirConfig(String savePath, String projectName, String basePackage) {
        basePath = savePath + "/" + projectName + "-parent";
        webModulePath = basePath + "/" + projectName + "-web";
        apiModulePath = basePath + "/" + projectName + "-api";
        implModulePath = basePath + "/" + projectName;

        String packagePath = basePackage.replaceAll("\\.", "/");
        webinfPath = webModulePath + "/src/main/webapp/WEB-INF";
        srcRelativePath = "src/main/java/" + packagePath;
        resourceRelativePath = "src/main/resources";
        testResourceRelativePath = "src/test/resources";
        testRelativePath = "src/test/java/" + packagePath;

        /*controllerModulePath = webModulePath + "/src/main/java/controller";
        serviceModulePath = apiModulePath + "/src/main/java//service";
        serviceImplModulePath = implModulePath + "/src/main/java//service";
        daoModulePath = implModulePath + "/src/main/java//dao";
        entityModulerPath = implModulePath + "/src/main/java//model";
        voModulerPath = webModulePath + "/src/main/java//vo";

        imageModulePath = webModulePath + "/static/iamges";
        jsModulePath = webModulePath + "/static/js";
        cssModulePath = webModulePath + "/static/css";
        addonModulePath = webModulePath + "/static/add-ons";
        pageModulePath = webModulePath + "/static/pages";*/

    }

    public String getBasePath() {
        return basePath;
    }

    public String getWebModulePath() {
        return webModulePath;
    }

    public String getApiModulePath() {
        return apiModulePath;
    }

    public String getImplModulePath() {
        return implModulePath;
    }

    public String getWebinfPath() {
        return webinfPath;
    }

    public String getSrcRelativePath() {
        return srcRelativePath;
    }

    public String getResourceRelativePath() {
        return resourceRelativePath;
    }

    public String getTestRelativePath() {
        return testRelativePath;
    }

    public String getTestResourceRelativePath() {
        return testResourceRelativePath;
    }

    public void setTestResourceRelativePath(String testResourceRelativePath) {
        this.testResourceRelativePath = testResourceRelativePath;
    }
}
