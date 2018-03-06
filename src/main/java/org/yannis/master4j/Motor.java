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
package org.yannis.master4j;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.GeneratorFactoryProvider;
import org.yannis.master4j.core.generator.Generator;
import org.yannis.master4j.meta.DatabaseMeta;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.model.BeanInfo;
import org.yannis.master4j.model.Context;
import org.yannis.master4j.util.ClassUtils;
import org.yannis.master4j.util.DBHelper;
import org.yannis.master4j.util.DirectoryCreator;
import org.yannis.master4j.util.FieldUtils;

public class Motor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Motor.class);

    private GeneratorFactoryProvider generatorFactoryProvider;

    public Motor(GeneratorFactoryProvider generatorFactoryProvider) {
        this.generatorFactoryProvider = generatorFactoryProvider;
    }

    public void fire(ProjectConfig projectConfig) {

        Context context = Context.getContext();
        context.setProjectConfig(projectConfig);

        DirectoryCreator.createDirs(projectConfig.getDirConfig());

        DatabaseMeta dbMeta = new DBHelper(projectConfig.getDbConfig()).getDatabaseMeta();
        if (dbMeta.getTableMetaList() == null || dbMeta.getTableMetaList().isEmpty()) {
            LOGGER.info("No tables detected, process exit.");
            return;
        }
        buildContext(dbMeta, context);

        Generator classGenerator = generatorFactoryProvider.getClassGeneratorFactory().newInstance();
        Generator viewGenerator = generatorFactoryProvider.getViewGeneratorFactory().newInstance();
        Generator configGenerator = generatorFactoryProvider.getConfigGeneratorFactory().newInstance();

        LOGGER.info("Starting code generating...");

        configGenerator.generate(context);
        classGenerator.generate(context);
        viewGenerator.generate(context);

        LOGGER.info("Code generated successfully");

    }

    private void buildContext(DatabaseMeta dbMeta, Context context) {
        context.setDatabaseMeta(dbMeta);
        List<TableMeta> tableMetaList = dbMeta.getTableMetaList();
        List<BeanInfo> beanInfos = new ArrayList<>(tableMetaList.size());
        for (TableMeta meta : tableMetaList) {
            BeanInfo beanInfo = buildBeanInfo(context, meta);
            beanInfos.add(beanInfo);
        }
        context.setBeanInfoList(beanInfos);
    }

    private BeanInfo buildBeanInfo(Context context, TableMeta meta) {

        String tableName = meta.getTableName();
        ProjectConfig projectConfig = context.getProjectConfig();
        final String tablePrefix = projectConfig.getDbConfig().getTablePrefix();
        if (tablePrefix != null && !"".equals(tablePrefix)) {
            if (tableName.startsWith(tablePrefix)) {
                tableName = tableName.substring(tableName.indexOf(tablePrefix) + tablePrefix.length());
            }
        }

        DirConfig dirConfig = projectConfig.getDirConfig();
        String webModulePath = dirConfig.getWebModulePath();
        String apiModulePath = dirConfig.getApiModulePath();
        String implModulePath = dirConfig.getImplModulePath();
        String srcRelativePath = dirConfig.getSrcRelativePath();
        String resourceRelativePath = dirConfig.getResourceRelativePath();
        String testRelativePath = dirConfig.getTestRelativePath();

        context.addAttribute("controllerPath", webModulePath + "/" + srcRelativePath + "/web/controller");
        context.addAttribute("servicePath", apiModulePath + "/" + srcRelativePath + "/api/service");
        context.addAttribute("serviceImplPath", implModulePath + "/" + srcRelativePath + "/service/impl");
        context.addAttribute("bizServicePath", implModulePath + "/" + srcRelativePath + "/service/biz");
        context.addAttribute("cacheServicePath", implModulePath + "/" + srcRelativePath + "/cache");
        context.addAttribute("daoPath", implModulePath + "/" + srcRelativePath + "/dao");
        context.addAttribute("daoImplPath", implModulePath + "/" + srcRelativePath + "/dao/impl");
        context.addAttribute("mapperPath", implModulePath + "/" + srcRelativePath + "/mapper");
        context.addAttribute("mapperImplPath", implModulePath + "/" + resourceRelativePath + "/mybatis/mapper");
        context.addAttribute("entityPath", implModulePath + "/" + srcRelativePath + "/entity");
        context.addAttribute("dtoPath", apiModulePath + "/" + srcRelativePath + "/api/dto");
        context.addAttribute("voPath", webModulePath + "/" + srcRelativePath + "/web/vo");
        context.addAttribute("formPath", webModulePath + "/" + srcRelativePath + "/web/form");
        context.addAttribute("entityConverterPath", implModulePath + "/" + srcRelativePath + "/converter");
        context.addAttribute("voConverterPath", webModulePath + "/" + srcRelativePath + "/web/converter");
        context.addAttribute("daoTestPath", implModulePath + "/" + testRelativePath + "/dao/impl");
        context.addAttribute("mapperTestPath", implModulePath + "/" + testRelativePath + "/mapper");
        context.addAttribute("serviceTestPath", implModulePath + "/" + testRelativePath + "/service/impl");
        context.addAttribute("controllerTestPath", webModulePath + "/" + testRelativePath + "/web/controller");

        String camelCaseName = ClassUtils.getCamelCaseName(tableName);
        return new BeanInfo()
            .setTableMeta(meta)
            .setFieldList(FieldUtils.getFields(meta))
            .setControllerName(camelCaseName + "Controller")
            .setServiceName(camelCaseName + "Service")
            .setServiceImplName(camelCaseName + "ServiceImpl")
            .setTestServiceName(camelCaseName + "ServiceTest")
            .setBizServiceName(camelCaseName + "BizService")
            .setCacheServiceName(camelCaseName + "CacheService")
            .setDaoName(camelCaseName + "Dao")
            .setDaoImplName(camelCaseName + "DaoImpl")
            .setTestDaoName(camelCaseName + "DaoTest")
            .setMapperName(camelCaseName + "Mapper")
            .setMapperImplName(camelCaseName + "Mapper")
            .setTestMapperName(camelCaseName + "MapperTest")
            .setEntityName(camelCaseName + "Entity")
            .setDtoName(camelCaseName)
            .setVoName(camelCaseName + "VO")
            .setFormName(camelCaseName + "Form")
            .setVoConverterName(camelCaseName + "Converter")
            .setEntityConverterName(camelCaseName + "Converter");
    }

}
