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
package org.yannis.master4j.core.generator.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.core.engine.Builder;
import org.yannis.master4j.core.engine.BuilderFactory;
import org.yannis.master4j.core.engine.view.impl.ViewBuilderImpl;
import org.yannis.master4j.model.Context;

public class DefaultViewGenerator extends AbstractViewGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultViewGenerator.class);

    @Override
    public void generate(Context context) {

        LOGGER.info("[DefaultViewGenerator]code generating...");

        Builder builder = null;
        try {
            BuilderFactory factory = new BuilderFactory(context);
            builder = factory.newInstance(ViewBuilderImpl.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        builder.build();
    }

}
