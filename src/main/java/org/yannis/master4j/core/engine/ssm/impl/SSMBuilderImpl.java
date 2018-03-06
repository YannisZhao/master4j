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
package org.yannis.master4j.core.engine.ssm.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.core.engine.ssm.AbstractSSMBuilder;
import org.yannis.master4j.core.engine.support.BizServiceConstructor;
import org.yannis.master4j.core.engine.support.CacheServiceConstructor;
import org.yannis.master4j.core.engine.support.ControllerConstructor;
import org.yannis.master4j.core.engine.support.ControllerTestConstructor;
import org.yannis.master4j.core.engine.support.MapperConstructor;
import org.yannis.master4j.core.engine.support.MapperImplConstructor;
import org.yannis.master4j.core.engine.support.MapperTestConstructor;
import org.yannis.master4j.core.engine.support.ServiceConstructor;
import org.yannis.master4j.core.engine.support.ServiceImplConstructor;
import org.yannis.master4j.core.engine.support.ServiceTestConstructor;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.model.Context;
import org.yannis.master4j.util.FileUtils;
import org.yannis.master4j.util.TemplateUtils;

public class SSMBuilderImpl extends AbstractSSMBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSMBuilderImpl.class);

    public SSMBuilderImpl(Context context) {
        super(context);
    }

    @Override
    public void buildMeta() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building meta components...");
        }

        String metaPath = TemplateUtils.getTemplateBasePath() + "/ssm/meta/";

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
            String responseVoBeanPath = webModulePath + "/" + srcRelativePath + "/web/basis/ResponseVO.java";
            FileUtils.copyTo(metaPath + "ResponseVO.m4", responseVoBeanPath, new HashMap<String, String>() {{
                put("package", getProjectConfig().getBasePackageName() + ".web.basis");
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
            String baseServiceTestPath = implModulePath + "/" + testRelativePath + "/BaseTest.java";
            FileUtils.copyTo(metaPath + "BaseServiceTest.m4", baseServiceTestPath, new HashMap<String, String>() {{
                put("package", getProjectConfig().getBasePackageName());
                put("date", date);
            }});

            String paginationPath = apiModulePath + "/" + srcRelativePath + "/api/common/Pagination.java";
            FileUtils.copyTo(metaPath + "Pagination.m4", paginationPath, new HashMap<String, String>() {{
                put("package", getProjectConfig().getBasePackageName() + ".api.common");
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
    public void buildBizService() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building biz services...");
        }

        // Construct Biz Service
        BizServiceConstructor.construct(context);
    }

    @Override
    public void buildCacheService() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building cache services...");
        }

        // Construct Cache Service
        CacheServiceConstructor.construct(context);
    }

    @Override
    public void buildMapper() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building mappers...");
        }

        // Construct Dao Interface
        MapperConstructor.construct(context);

    }

    @Override
    public void buildMapperImpl() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building mappers xml...");
        }

        // Construct Mapper Impl
        MapperImplConstructor.construct(context);

    }

    @Override
    public void buildTest() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building tests...");
        }

        MapperTestConstructor.construct(context);

        String serviceTestPath = (String) context.getAttribute("serviceTestPath");
        FileUtils.mkdirs(serviceTestPath);

        // Construct Service Test
        ServiceTestConstructor.construct(context);

        String controllerTestPath = (String) context.getAttribute("controllerTestPath");
        FileUtils.mkdirs(controllerTestPath);

        for (TableMeta meta : getDatabaseMeta().getTableMetaList()) {
            // Construct Web Test
            ControllerTestConstructor.construct(context);
        }

    }

    @Override
    public void build() {
        buildMeta();
        buildEntity();
        buildForm();
        buildDto();
        buildVo();
        buildConverter();
        buildController();
        buildService();
        buildServiceImpl();
        buildBizService();
        buildCacheService();
        buildMapper();
        buildMapperImpl();
        buildTest();
    }

}
