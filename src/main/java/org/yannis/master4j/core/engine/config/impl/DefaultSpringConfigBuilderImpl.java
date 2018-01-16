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
package org.yannis.master4j.core.engine.config.impl;

import java.io.IOException;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.config.DBConfig;
import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.core.engine.config.AbstractConfigBuilder;
import org.yannis.master4j.core.engine.config.constructor.springmvc.MessageResourceConstructor;
import org.yannis.master4j.core.engine.config.constructor.springmvc.PomConstructor;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.ServiceBeanDefUtils;
import org.yannis.master4j.util.TemplateUtils;

public class DefaultSpringConfigBuilderImpl extends AbstractConfigBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSpringConfigBuilderImpl.class);

    @Override
    public boolean buildConfiguration() throws IOException {
        LOGGER.info("starting building configuration...");
        String metaPath = TemplateUtils.getTemplateBasePath() + "/springmvc/meta/";
        DirConfig dirConfig = projectConfig.getDirConfig();
        String resourceRelativePath = dirConfig.getResourceRelativePath();
        String testResourceRelativePath = dirConfig.getTestResourceRelativePath();
//		String srcRelativePath = dirConfig.getSrcRelativePath();
//		String testRelativePath = dirConfig.getTestRelativePath();

        // Build pom
        PomConstructor.construct(projectConfig);

        // Build web module configuration
        String webModulePath = dirConfig.getWebModulePath();
        //FileUtils.newFile(webModulePath +"/pom.xml","");
        String webinfPath = dirConfig.getWebinfPath();
        //FileUtils.newFile(webinfPath +"/web.xml","");
        FileUtils.mkdir(webinfPath + "/templates/springmvc/lib");
        FileUtils.mkdir(webinfPath + "/pages");
        FileUtils.mkdirs(webModulePath + "/" + resourceRelativePath + "/i18n/message");
        MessageResourceConstructor
            .construct(webModulePath + "/" + resourceRelativePath + "/i18n/message/messages.properties", dbMeta);
        FileUtils.mkdirs(webModulePath + "/" + resourceRelativePath + "/i18n/label");
        FileUtils.newFile(webModulePath + "/" + resourceRelativePath + "/i18n/label/labels.properties", "");

        FileUtils.copyTo(metaPath + "web.xml.m4", webinfPath + "/web.xml");
        //FileUtils.copyTo(metaPath+"spring-beans.m4", webModulePath+"/"+resourceRelativePath+"/spring-beans.xml");
        FileUtils
            .copyTo(metaPath + "spring-config.m4", webModulePath + "/" + resourceRelativePath + "/spring-config.xml",
                new HashMap<String, String>() {{
                    put("package", projectConfig.getBasePackageName() + ".web");
                }});
        FileUtils.copyTo(webModulePath + "/" + resourceRelativePath + "/spring-config.xml",
            webModulePath + "/" + testResourceRelativePath + "/spring-config.xml");
        //FileUtils.copyTo(metaPath+"db.m4", webModulePath+"/"+resourceRelativePath+"/db.properties");
        FileUtils.copyTo(metaPath + "log4j.m4", webModulePath + "/" + resourceRelativePath + "/log4j.properties",
            new HashMap<String, String>() {{
                put("file", projectConfig.getProjectName() + "_web.log");
            }});

        FileUtils.mkdir(webModulePath + "/src/main/webapp/static");
        FileUtils.mkdir(webModulePath + "/src/main/webapp/static/images");
        FileUtils.mkdir(webModulePath + "/src/main/webapp/static/js");
        FileUtils.mkdir(webModulePath + "/src/main/webapp/static/css");
        FileUtils.mkdir(webModulePath + "/src/main/webapp/static/add-ons");

        //Build service api module configuration
//		String apiModulePath = dirConfig.getApiModulePath();
//		//FileUtils.newFile(apiModulePath +"/pom.xml","");
//		FileUtils.mkdirs(apiModulePath+"/"+resourceRelativePath+"/i18n/message");
//		FileUtils.mkdirs(apiModulePath+"/"+resourceRelativePath+"/i18n/label");
//
//		FileUtils.newFile(apiModulePath+"/"+resourceRelativePath+"/spring-beans.xml","");
//		FileUtils.newFile(apiModulePath+"/"+resourceRelativePath+"/spring-context.xml","");
//		FileUtils.newFile(apiModulePath+"/"+resourceRelativePath+"/spring-config.xml","");
//		FileUtils.newFile(apiModulePath+"/"+resourceRelativePath+"/log4j.properties","");

        //Build service impl module configuration
        String implModulePath = dirConfig.getImplModulePath();
        //FileUtils.newFile(implModulePath +"/pom.xml","");
        //FileUtils.mkdirs(implModulePath+"/"+resourceRelativePath+"/i18n/message");
        //FileUtils.mkdirs(implModulePath+"/"+resourceRelativePath+"/i18n/label");

        FileUtils.copyTo(metaPath + "log4j.m4", implModulePath + "/" + resourceRelativePath + "/log4j.properties",
            new HashMap<String, String>() {{
                put("file", projectConfig.getProjectName() + "_biz.log");
            }});
        FileUtils.copyTo(implModulePath + "/" + resourceRelativePath + "/log4j.properties",
            implModulePath + "/" + testResourceRelativePath + "/log4j.properties");
        FileUtils
            .copyTo(metaPath + "spring-beans.m4", implModulePath + "/" + resourceRelativePath + "/spring-beans.xml",
                new HashMap<String, String>() {{
                    put("package", projectConfig.getBasePackageName() + "");
                }});
        FileUtils.copyTo(implModulePath + "/" + resourceRelativePath + "/spring-beans.xml",
            implModulePath + "/" + testResourceRelativePath + "/spring-beans.xml");
        FileUtils.copyTo(metaPath + "spring-config-service.m4",
            implModulePath + "/" + resourceRelativePath + "/spring-config.xml", new HashMap<String, String>() {{
                DBConfig dbConfig = projectConfig.getDbConfig();
                String url = dbConfig.getProtocalPrefix() + dbConfig.getIp() + ":" + dbConfig.getPort() + "/" + dbConfig
                    .getDatabase()
                    + "?useUnicode=true&amp;characterEncoding=" + dbConfig.getCharset()
                    + "&amp;zeroDateTimeBehavior=convertToNull";
                put("url", url);
                put("username", dbConfig.getUsername());
                put("password", dbConfig.getPassword());
                put("package", projectConfig.getBasePackageName() + "");
            }});
        FileUtils.copyTo(implModulePath + "/" + resourceRelativePath + "/spring-config.xml",
            implModulePath + "/" + testResourceRelativePath + "/spring-config.xml");

        final String serviceDefinitions = ServiceBeanDefUtils.getDef(projectConfig, dbMeta);
        FileUtils.copyTo(metaPath + "spring-services.m4",
            implModulePath + "/" + resourceRelativePath + "/spring-services.xml", new HashMap<String, String>() {{
                put("services", serviceDefinitions);
            }});
        FileUtils.copyTo(implModulePath + "/" + resourceRelativePath + "/spring-services.xml",
            implModulePath + "/" + testResourceRelativePath + "/spring-services.xml");

        return false;
    }

    @Override
    public boolean build() {
        try {
            return buildConfiguration();
        } catch (Exception e) {
            LOGGER.error("error build configure file(s)", e);
        }
        return false;
    }

}
