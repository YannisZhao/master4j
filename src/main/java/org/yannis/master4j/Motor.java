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

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.GeneratorFactory;
import org.yannis.master4j.core.generator.Generator;
import org.yannis.master4j.meta.DatabaseMeta;
import org.yannis.master4j.util.DBHelper;
import org.yannis.master4j.util.DirecotryCreator;

public class Motor {

    private GeneratorFactory classGeneratorFactory;

    private GeneratorFactory viewGeneratorFactory;

    private GeneratorFactory configGeneratorFactory;

    public void fire(ProjectConfig projectConfig){

        DirecotryCreator.createDirs(projectConfig.getDirConfig());

        DatabaseMeta dbMeta = new DBHelper(projectConfig.getDbConfig()).getDatabaseMeta();

        Generator springMVCGenerator = classGeneratorFactory.newInstance();
        Generator viewGenerator = viewGeneratorFactory.newInstance();
        Generator configGenerator = configGeneratorFactory.newInstance();

        springMVCGenerator.generate(dbMeta, projectConfig);
        viewGenerator.generate(dbMeta, projectConfig);
        configGenerator.generate(dbMeta, projectConfig);
    }

    public GeneratorFactory getClassGeneratorFactory() {
        return classGeneratorFactory;
    }

    public void setClassGeneratorFactory(GeneratorFactory classGeneratorFactory) {
        this.classGeneratorFactory = classGeneratorFactory;
    }

    public GeneratorFactory getViewGeneratorFactory() {
        return viewGeneratorFactory;
    }

    public void setViewGeneratorFactory(GeneratorFactory viewGeneratorFactory) {
        this.viewGeneratorFactory = viewGeneratorFactory;
    }

    public GeneratorFactory getConfigGeneratorFactory() {
        return configGeneratorFactory;
    }

    public void setConfigGeneratorFactory(GeneratorFactory configGeneratorFactory) {
        this.configGeneratorFactory = configGeneratorFactory;
    }

}
