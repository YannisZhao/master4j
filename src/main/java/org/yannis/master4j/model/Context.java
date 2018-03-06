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
package org.yannis.master4j.model;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;

/**
 * @author Yannis Zhao
 * @date 2018/1/21
 * @since 1.0
 */
public final class Context {

    private ProjectConfig projectConfig;
    private DatabaseMeta databaseMeta;
    private List<BeanInfo> beanInfoList;
    private Map<String, Object> attributes = new ConcurrentHashMap<>();

    private Context() {
    }

    public static Context getContext() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {

        private static final Context INSTANCE = new Context();
    }

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    public Context setProjectConfig(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
        return this;
    }

    public DatabaseMeta getDatabaseMeta() {
        return databaseMeta;
    }

    public Context setDatabaseMeta(DatabaseMeta databaseMeta) {
        this.databaseMeta = databaseMeta;
        return this;
    }

    public List<BeanInfo> getBeanInfoList() {
        return beanInfoList;
    }

    public Context setBeanInfoList(List<BeanInfo> beanInfoList) {
        this.beanInfoList = beanInfoList;
        return this;
    }

    public Context addAttribute(String key, Object value) {
        this.attributes.put(key, value);
        return this;
    }

    public Object getAttribute(String key) {
        return this.attributes.get(key);
    }
}
