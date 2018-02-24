package org.yannis.master4j.core;

import org.yannis.master4j.core.generator.config.ConfigGeneratorFactory;
import org.yannis.master4j.core.generator.ssm.SSMGeneratorFactory;
import org.yannis.master4j.core.generator.view.ViewGeneratorFactory;

/**
 * @author Yannis Zhao
 * @date 2018/1/21
 * @since 1.0
 */
public class SSMGeneratorFactoryProvider implements GeneratorFactoryProvider {

    private static GeneratorFactory ssmGeneratorFactory = new SSMGeneratorFactory();
    private static GeneratorFactory configGeneratorFactory = new ConfigGeneratorFactory();
    private static GeneratorFactory viewGeneratorFactory = new ViewGeneratorFactory();

    @Override
    public GeneratorFactory getClassGeneratorFactory() {
        return ssmGeneratorFactory;
    }

    @Override
    public GeneratorFactory getConfigGeneratorFactory() {
        return configGeneratorFactory;
    }

    @Override
    public GeneratorFactory getViewGeneratorFactory() {
        return viewGeneratorFactory;
    }
}
