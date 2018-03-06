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
package org.yannis.master4j.core.engine.springmvc.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.core.engine.springmvc.AbstractSpringMVCBuilder;
import org.yannis.master4j.core.engine.support.ControllerConstructor;
import org.yannis.master4j.core.engine.support.ControllerTestConstructor;
import org.yannis.master4j.core.engine.support.DaoConstructor;
import org.yannis.master4j.core.engine.support.DaoImplConstructor;
import org.yannis.master4j.core.engine.support.DaoTestConstructor;
import org.yannis.master4j.core.engine.support.ServiceConstructor;
import org.yannis.master4j.core.engine.support.ServiceImplConstructor;
import org.yannis.master4j.core.engine.support.ServiceTestConstructor;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.model.Context;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.TemplateUtils;

public class SpringMVCBuilderImpl extends AbstractSpringMVCBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMVCBuilderImpl.class);

    public SpringMVCBuilderImpl(Context context) {
        super(context);
    }

    @Override
    public void buildMeta() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building meta components...");
        }

        String metaPath = TemplateUtils.getTemplateBasePath() + "/springmvc/meta/";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String date = sdf.format(new Date());

        try {
            // build base controller
            FileUtils.mkdirs(webModulePath + "/" + srcRelativePath + "/web/basis");
            String baseControllerPath = webModulePath + "/" + srcRelativePath + "/web/basis/BaseController.java";
            FileUtils.copyTo(metaPath + "BaseController.m4", baseControllerPath, new HashMap<String, String>() {{
                put("package", getProjectConfig().getBasePackageName() + ".web.basis");
                put("date", date);
            }});

            // builder base dao
            String baseDaoPath = implModulePath + "/" + srcRelativePath + "/dao/BaseDao.java";
            FileUtils.copyTo(metaPath + "BaseDao.m4", baseDaoPath, new HashMap<String, String>() {{
                put("package", getProjectConfig().getBasePackageName() + ".dao");
                put("date", date);
            }});

            // builder basis test of controller
            String baseControllerTestPath = webModulePath + "/" + testRelativePath + "/web/controller/BaseTest.java";
            FileUtils
                .copyTo(metaPath + "BaseControllerTest.m4", baseControllerTestPath, new HashMap<String, String>() {{
                    put("package", getProjectConfig().getBasePackageName() + ".web.controller");
                    put("date", date);
                }});

            // builder basis test of service
            String baseServiceTestPath = implModulePath + "/" + testRelativePath + "/service/BaseTest.java";
            FileUtils.copyTo(metaPath + "BaseServiceTest.m4", baseServiceTestPath, new HashMap<String, String>() {{
                put("package", getProjectConfig().getBasePackageName() + ".service");
                put("date", date);
            }});

            // builder basis test of dao
            String baseDaoTestPath = implModulePath + "/" + testRelativePath + "/dao/BaseTest.java";
            FileUtils.copyTo(metaPath + "BaseDaoTest.m4", baseDaoTestPath, new HashMap<String, String>() {{
                put("package", getProjectConfig().getBasePackageName() + ".dao");
                put("date", date);
            }});
        } catch (Exception e) {
            LOGGER.error("Construct meta components error", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void buildController() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building controllers...");
        }

        // Construct Controller
        ControllerConstructor.construct(context);

    }

    @Override
    public void buildService() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building services...");
        }

        // Construct Service Api
        ServiceConstructor.construct(context);

    }


    @Override
    public void buildServiceImpl() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building services impl...");
        }

        // Construct Service Api Impl
        ServiceImplConstructor.construct(context);

    }

    @Override
    public void buildDao() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building daos...");
        }

        // Construct Dao Interface
        DaoConstructor.construct(context);

    }

    @Override
    public void buildDaoImpl() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building dao impls...");
        }

        // Construct Dao Impl
        DaoImplConstructor.construct(context);

    }

    @Override
    public void buildTest() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building tests...");
        }

        String daoTestPath = implModulePath + "/" + testRelativePath + "/dao/impl";
        FileUtils.mkdirs(daoTestPath);

        // Construct Dao Test
        DaoTestConstructor.construct(context);

        String serviceTestPath = implModulePath + "/" + testRelativePath + "/service/impl";
        FileUtils.mkdirs(serviceTestPath);

        // Construct Service Test
        ServiceTestConstructor.construct(context);

        String controllerTestPath = webModulePath + "/" + testRelativePath + "/web/controller";
        FileUtils.mkdirs(controllerTestPath);

        for (TableMeta meta : getDatabaseMeta().getTableMetaList()) {
            // Construct Web Test
            ControllerTestConstructor.construct(context);
        }

    }

    @Override
    public void build() {
        init();
        buildMeta();
        buildEntity();
        buildForm();
        buildDto();
        buildVo();
        buildConverter();
        buildController();
        buildService();
        buildServiceImpl();
        buildDao();
        buildDaoImpl();
        buildTest();
    }

}
