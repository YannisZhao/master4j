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
package org.yannis.master4j.config;

/**
 * Created by yannis on 6/13/16.
 */
public interface TemplateDir {

    String basePath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
    String staticBasePath = basePath + "/templates/static";
    String springmvcBasePath = basePath + "/templates/springmvc";
    String sshBasePath = basePath + "/templates/ssh";
    String ssmBasePath = basePath + "templates/ssm";

    String AddOnsPath = staticBasePath + "/add-ons";
    String cssPath = staticBasePath + "/css";
    String imagePath = staticBasePath + "/images";
    String jsPath = staticBasePath + "/js";
    String pagesPath = staticBasePath + "/pages";
}
