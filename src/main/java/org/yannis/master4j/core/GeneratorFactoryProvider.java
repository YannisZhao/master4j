package org.yannis.master4j.core;

/**
 * Generator factory provider base
 *
 * @author Yannis Zhao, zhaoyjun0222@gmail.com Date: 2017-04-09
 */
public interface GeneratorFactoryProvider {

    GeneratorFactory getClassGeneratorFactory();

    GeneratorFactory getConfigGeneratorFactory();

    GeneratorFactory getViewGeneratorFactory();

}
