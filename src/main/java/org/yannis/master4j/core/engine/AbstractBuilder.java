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
package org.yannis.master4j.core.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.engine.support.DtoConstructor;
import org.yannis.master4j.core.engine.support.EntityConstructor;
import org.yannis.master4j.core.engine.support.EntityConverterConstructor;
import org.yannis.master4j.core.engine.support.FormConstructor;
import org.yannis.master4j.core.engine.support.VOConverterConstructor;
import org.yannis.master4j.core.engine.support.VoConstructor;
import org.yannis.master4j.meta.DatabaseMeta;
import org.yannis.master4j.model.Context;

/**
 * @author Yannis
 */
public abstract class AbstractBuilder implements Builder {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBuilder.class);

    protected Context context;

    protected String webModulePath;
    protected String apiModulePath;
    protected String implModulePath;
    protected String srcRelativePath;
    protected String resourceRelativePath;
    protected String testRelativePath;

    private ProjectConfig projectConfig;
    private DatabaseMeta databaseMeta;

    public AbstractBuilder(Context context) {
        this.context = context;
        init();
    }

    public void init() {
        this.projectConfig = context.getProjectConfig();
        this.databaseMeta = context.getDatabaseMeta();

        DirConfig dirConfig = projectConfig.getDirConfig();

        webModulePath = dirConfig.getWebModulePath();
        apiModulePath = dirConfig.getApiModulePath();
        implModulePath = dirConfig.getImplModulePath();
        srcRelativePath = dirConfig.getSrcRelativePath();
        resourceRelativePath = dirConfig.getResourceRelativePath();
        testRelativePath = dirConfig.getTestRelativePath();
    }

    public void buildEntity() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building domain objects...");
        }

        EntityConstructor.construct(context);

    }

    public void buildForm() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building form objects...");
        }

        // Construct form bean
        FormConstructor.construct(context);

    }

    public void buildDto() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building dto objects...");
        }

        // Construct dto bean
        DtoConstructor.construct(context);

    }

    public void buildVo() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building vo objects...");
        }

        // Construct vo bean
        VoConstructor.construct(context);

    }

    public void buildConverter() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building converter objects...");
        }

        VOConverterConstructor.construct(context);
        EntityConverterConstructor.construct(context);

    }

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    public DatabaseMeta getDatabaseMeta() {
        return databaseMeta;
    }

}
