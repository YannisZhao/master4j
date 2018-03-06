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
package org.yannis.master4j.core.engine.ssm;

import org.yannis.master4j.core.engine.AbstractBuilder;
import org.yannis.master4j.model.Context;

public abstract class AbstractSSMBuilder extends AbstractBuilder {

    public AbstractSSMBuilder(Context context) {
        super(context);
    }

    public abstract void buildMeta();

    public abstract void buildController();

    public abstract void buildService();

    public abstract void buildServiceImpl();

    public abstract void buildBizService();

    public abstract void buildCacheService();

    public abstract void buildMapper();

    public abstract void buildMapperImpl();

    public abstract void buildTest();

}