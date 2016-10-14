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
package org.yannis.master4j.core.generator.config;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.engine.Builder;
import org.yannis.master4j.core.engine.BuilderFacotry;
import org.yannis.master4j.core.engine.config.impl.DefaultSpringConfigBuilderImpl;
import org.yannis.master4j.meta.DatabaseMeta;

public class DefaultConfigGenerator extends AbstractConfigGenerator {

	@Override
	public boolean generate(DatabaseMeta meta, ProjectConfig config) {
		System.out.println("[DefaultConfigGenerator]code generating...");
		Builder builder = null;
		try {
			BuilderFacotry facotry = new BuilderFacotry(meta, config);
			builder = facotry.newInstance(DefaultSpringConfigBuilderImpl.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		builder.build();
		return false;
	}

}
