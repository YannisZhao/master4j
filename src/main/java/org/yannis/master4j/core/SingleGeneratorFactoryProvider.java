package org.yannis.master4j.core;

import org.yannis.master4j.core.generator.config.ConfigGeneratorFactory;
import org.yannis.master4j.core.generator.springmvc.SpringMVCGeneratorFactory;
import org.yannis.master4j.core.generator.view.ViewGeneratorFactory;

/**
 * Generator factory provider
 *
 * @author Yannis Zhao, zhaoyjun0222@gmail.com Date: 2017-04-09
 */
public class SingleGeneratorFactoryProvider implements GeneratorFactoryProvider {

    private static GeneratorFactory springMVCGeneratorFactory = new SpringMVCGeneratorFactory();
    private static GeneratorFactory configGeneratorFactory = new ConfigGeneratorFactory();
    private static GeneratorFactory viewGeneratorFactory = new ViewGeneratorFactory();

    public GeneratorFactory getClassGeneratorFactory() {
        return springMVCGeneratorFactory;
    }

    public GeneratorFactory getConfigGeneratorFactory() {
        return configGeneratorFactory;
    }

    public GeneratorFactory getViewGeneratorFactory() {
        return viewGeneratorFactory;
    }
}
