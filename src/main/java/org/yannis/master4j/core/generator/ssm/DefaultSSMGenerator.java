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
package org.yannis.master4j.core.generator.ssm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.core.engine.Builder;
import org.yannis.master4j.core.engine.BuilderFactory;
import org.yannis.master4j.core.engine.ssm.impl.SSMBuilderImpl;
import org.yannis.master4j.model.Context;

public class DefaultSSMGenerator extends AbstractSSMGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultSSMGenerator.class);

    @Override
    public void generate(Context context) {

        LOGGER.info("[DefaultSSMGenerator]code generating...");

        Builder builder = null;
        try {
            BuilderFactory factory = new BuilderFactory(context);
            builder = factory.newInstance(SSMBuilderImpl.class);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        builder.build();
    }

}
