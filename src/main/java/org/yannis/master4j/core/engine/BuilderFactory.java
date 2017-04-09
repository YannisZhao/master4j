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
package org.yannis.master4j.core.engine;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;

public class BuilderFactory {

	private DatabaseMeta dbMeta;
	private ProjectConfig config;

	public BuilderFactory(DatabaseMeta dbMeta, ProjectConfig config){
		this.dbMeta = dbMeta;
		this.config = config;
	}
	
	public Builder newInstance(Class<? extends Builder> clazz) throws Throwable{
		try {
			Builder builder = clazz.newInstance();
			builder.setDbMeta(dbMeta);
			builder.setProjectConfig(config);
			return builder;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new Throwable(e);
		}
	}

}
