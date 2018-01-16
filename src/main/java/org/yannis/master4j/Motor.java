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
package org.yannis.master4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.GeneratorFactoryProvider;
import org.yannis.master4j.core.generator.Generator;
import org.yannis.master4j.meta.DatabaseMeta;
import org.yannis.master4j.util.DBHelper;
import org.yannis.master4j.util.DirectoryCreator;

public class Motor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Motor.class);

    private GeneratorFactoryProvider generatorFactoryProvider;

    public Motor(GeneratorFactoryProvider generatorFactoryProvider) {
        this.generatorFactoryProvider = generatorFactoryProvider;
    }

    public void fire(ProjectConfig projectConfig) {

        DirectoryCreator.createDirs(projectConfig.getDirConfig());

        DatabaseMeta dbMeta = new DBHelper(projectConfig.getDbConfig()).getDatabaseMeta();

        Generator classGenerator = generatorFactoryProvider.getClassGeneratorFactory().newInstance();
        Generator viewGenerator = generatorFactoryProvider.getViewGeneratorFactory().newInstance();
        Generator configGenerator = generatorFactoryProvider.getConfigGeneratorFactory().newInstance();

        LOGGER.info("Starting code generating...");

        configGenerator.generate(dbMeta, projectConfig);
        classGenerator.generate(dbMeta, projectConfig);
        viewGenerator.generate(dbMeta, projectConfig);

        LOGGER.info("Code generated successful");

    }

}
