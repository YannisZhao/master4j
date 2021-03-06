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
package org.yannis.master4j.core;

import org.yannis.master4j.core.generator.config.ConfigGeneratorFactory;
import org.yannis.master4j.core.generator.ssm.SSMGeneratorFactory;
import org.yannis.master4j.core.generator.view.ViewGeneratorFactory;

/**
 * @author Yannis Zhao
 * @date 2018/1/21
 * @since 1.0
 */
public class SSMGeneratorFactoryProvider implements GeneratorFactoryProvider {

    private static GeneratorFactory ssmGeneratorFactory = new SSMGeneratorFactory();
    private static GeneratorFactory configGeneratorFactory = new ConfigGeneratorFactory();
    private static GeneratorFactory viewGeneratorFactory = new ViewGeneratorFactory();

    @Override
    public GeneratorFactory getClassGeneratorFactory() {
        return ssmGeneratorFactory;
    }

    @Override
    public GeneratorFactory getConfigGeneratorFactory() {
        return configGeneratorFactory;
    }

    @Override
    public GeneratorFactory getViewGeneratorFactory() {
        return viewGeneratorFactory;
    }
}
