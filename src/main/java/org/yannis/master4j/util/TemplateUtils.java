package org.yannis.master4j.util;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Created by yannis on 6/15/16.
 */
public class TemplateUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateUtils.class);
    private static final Configuration cfg = new Configuration();

    static {
        try {
            cfg.setDirectoryForTemplateLoading(new File(getTemplateBasePath()));
            cfg.setObjectWrapper(new DefaultObjectWrapper());
        } catch (IOException e) {
            LOGGER.info("Set template base path failed",e);
        }

        cfg.setObjectWrapper(new DefaultObjectWrapper());
    }

    public static String getTemplateBasePath() {
        return Thread.currentThread().getContextClassLoader().getResource("templates").getPath();
    }

    public static void process(String templatePath, Map<String, Object> root, String savePath) {

        try {
            Template template = cfg.getTemplate(templatePath);

            File output = new File(savePath);
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
