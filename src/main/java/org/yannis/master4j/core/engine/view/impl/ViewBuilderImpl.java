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
package org.yannis.master4j.core.engine.view.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.core.engine.view.AbstractViewBuilder;
import org.yannis.master4j.model.Context;

public class ViewBuilderImpl extends AbstractViewBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewBuilderImpl.class);

    public ViewBuilderImpl(Context context) {
        super(context);
    }

    @Override
    public boolean buildScripts() {
        LOGGER.info("starting building scripts...");
        return false;
    }

    @Override
    public boolean buildStyles() {
        LOGGER.info("starting building styles...");
        return false;
    }

    @Override
    public boolean buildPlugins() {
        LOGGER.info("starting building plugins...");
        return false;
    }

    @Override
    public boolean buildImages() {
        LOGGER.info("starting building images...");
        return false;
    }

    @Override
    public void build() {
        buildScripts();
        buildStyles();
        buildPlugins();
        buildImages();
    }

}
