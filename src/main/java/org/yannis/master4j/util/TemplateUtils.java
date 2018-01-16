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
package org.yannis.master4j.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TemplateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateUtils.class);
    private static final Configuration cfg = new Configuration();

    static {
        try {
            cfg.setDirectoryForTemplateLoading(new File(getTemplateBasePath()));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
        } catch (IOException e) {
            LOGGER.info("Set template base path failed", e);
        }

        cfg.setObjectWrapper(new DefaultObjectWrapper());
    }

    private TemplateUtils() {
    }

    public static String getTemplateBasePath() {
        return Thread.currentThread().getContextClassLoader().getResource("templates").getPath();
    }

    public static void process(String templatePath, Map<String, Object> root, String savePath) {

        try {
            Template template = cfg.getTemplate(templatePath);

            File output = new File(savePath);
            if (!output.exists() && output.isFile()) {
                output.createNewFile();
            }
            Writer writer = new FileWriter(output);
            template.process(root, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
