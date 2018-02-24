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
import org.yannis.master4j.core.engine.config.constructor.MessageResourceConstructor;
import org.yannis.master4j.core.engine.config.constructor.PomConstructor;
import org.yannis.master4j.core.engine.support.DDLScriptConstructor;
import org.yannis.master4j.model.Context;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.MybatisTypeAliasDefUtils;
import org.yannis.master4j.util.ServiceBeanDefUtils;
import org.yannis.master4j.util.TemplateUtils;

public class DefaultSSMConfigBuilderImpl extends AbstractConfigBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSSMConfigBuilderImpl.class);

    public DefaultSSMConfigBuilderImpl(Context context) {
        super(context);
    }

    @Override
    public void buildConfiguration() throws IOException {
        LOGGER.info("starting building configuration...");
        String metaPath = TemplateUtils.getTemplateBasePath() + "/ssm/meta/";
        DirConfig dirConfig = getProjectConfig().getDirConfig();
        String resourceRelativePath = dirConfig.getResourceRelativePath();
        String testResourceRelativePath = dirConfig.getTestResourceRelativePath();
//		String srcRelativePath = dirConfig.getSrcRelativePath();
//		String testRelativePath = dirConfig.getTestRelativePath();

        // Build pom
        PomConstructor.construct(getProjectConfig());

        // Build web module configuration
        String webModulePath = dirConfig.getWebModulePath();
        //FileUtils.newFile(webModulePath +"/pom.xml","");
        String webinfPath = dirConfig.getWebinfPath();
        //FileUtils.newFile(webinfPath +"/web.xml","");
        FileUtils.mkdir(webinfPath + "/templates/ssm/lib");
        FileUtils.mkdir(webinfPath + "/pages");
        FileUtils.mkdirs(webModulePath + "/" + resourceRelativePath + "/i18n/message");
        MessageResourceConstructor
            .construct(webModulePath + "/" + resourceRelativePath + "/i18n/message/messages.properties",
                context);
        FileUtils.mkdirs(webModulePath + "/" + resourceRelativePath + "/i18n/label");
        FileUtils.newFile(webModulePath + "/" + resourceRelativePath + "/i18n/label/labels.properties", "");

        FileUtils.copyTo(metaPath + "web.xml.m4", webinfPath + "/web.xml");
        //FileUtils.copyTo(metaPath+"application-context.m4", webModulePath+"/"+resourceRelativePath+"/spring-beans.xml");
        FileUtils
            .copyTo(metaPath + "application-web.m4",
                webModulePath + "/" + resourceRelativePath + "/application-web.xml",
                new HashMap<String, String>() {{
                    put("package", getProjectConfig().getBasePackageName() + ".web");
                }});
        //FileUtils.copyTo(metaPath+"db.m4", webModulePath+"/"+resourceRelativePath+"/db.properties");
        FileUtils.copyTo(metaPath + "log4j.m4", webModulePath + "/" + resourceRelativePath + "/log4j.properties",
            new HashMap<String, String>() {{
                put("file", getProjectConfig().getProjectName() + "_web.log");
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
        //FileUtils.mkdirs(implModulePath+"/"+resourceRelativePath+"/i18n/message");
        //FileUtils.mkdirs(implModulePath+"/"+resourceRelativePath+"/i18n/label");

        FileUtils.copyTo(metaPath + "log4j.m4", implModulePath + "/" + resourceRelativePath + "/log/log4j.properties",
            new HashMap<String, String>() {{
                put("file", getProjectConfig().getProjectName() + ".log");
            }});
        FileUtils.mkdirs(implModulePath+"/"+testResourceRelativePath+"/log");
        FileUtils.copyTo(metaPath + "log4j-test.m4",
            implModulePath + "/" + testResourceRelativePath + "/log/log4j.properties");
        FileUtils
            .copyTo(metaPath + "application-context.m4",
                implModulePath + "/" + resourceRelativePath + "/application-context.xml",
                new HashMap<String, String>() {{
                    put("package", getProjectConfig().getBasePackageName() + "");
                }});
        FileUtils
            .copyTo(metaPath + "application-context-test.m4",
                implModulePath + "/" + testResourceRelativePath + "/application-context.xml",
                new HashMap<String, String>() {{
                    put("package", getProjectConfig().getBasePackageName() + "");
                }});
        FileUtils.copyTo(metaPath + "application-dao.m4",
            implModulePath + "/" + resourceRelativePath + "/application-dao.xml", new HashMap<String, String>() {{
                DBConfig dbConfig = getProjectConfig().getDbConfig();
                String url = dbConfig.getProtocalPrefix() + dbConfig.getIp() + ":" + dbConfig.getPort() + "/" + dbConfig
                    .getDatabase()
                    + "?useUnicode=true&amp;characterEncoding=" + dbConfig.getCharset()
                    + "&amp;zeroDateTimeBehavior=convertToNull";
                put("url", url);
                put("username", dbConfig.getUsername());
                put("password", dbConfig.getPassword());
                put("package", getProjectConfig().getBasePackageName() + "");
            }});

        final String serviceDefinitions = ServiceBeanDefUtils.getDef(context);
        FileUtils.copyTo(metaPath + "application-services.m4",
            implModulePath + "/" + resourceRelativePath + "/application-services.xml", new HashMap<String, String>() {{
                put("services", serviceDefinitions);
            }});
//        FileUtils.copyTo(implModulePath + "/" + resourceRelativePath + "/application-services.xml",
//            implModulePath + "/" + testResourceRelativePath + "/application-services.xml");

        FileUtils.copyTo(metaPath + "application-redis.m4",
            implModulePath + "/" + resourceRelativePath + "/application-redis.xml");

        final String typeAliasesDefinitions = MybatisTypeAliasDefUtils.getDef(context);
        FileUtils.copyTo(metaPath + "mybatis-config.m4",
            implModulePath + "/" + resourceRelativePath + "/mybatis/mybatis-config.xml",
            new HashMap<String, String>() {{
                put("typeAliases", typeAliasesDefinitions);
            }});
        FileUtils.copyTo(metaPath + "mybatis-config-test.m4",
            implModulePath + "/" + testResourceRelativePath + "/mybatis/mybatis-config.xml",
            new HashMap<String, String>() {{
                put("typeAliases", typeAliasesDefinitions);
            }});

        FileUtils.copyTo(metaPath + "application-h2.m4",
            implModulePath + "/" + testResourceRelativePath + "/h2/application-h2.xml",
            new HashMap<String, String>() {{
                put("package", getProjectConfig().getBasePackageName() + "");
            }});
        DDLScriptConstructor.construct(context);
    }

    @Override
    public void build() {
        try {
            buildConfiguration();
        } catch (Exception e) {
            LOGGER.error("error build configure file(s)", e);
        }
    }

}
