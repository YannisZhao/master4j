package org.yannis.master4j.core.generator;

import org.yannis.master4j.core.generator.config.ConfigGenerator;
import org.yannis.master4j.core.generator.config.DefaultConfigGenerator;

public class ConfigGeneratorFactory extends AbstractGeneratorFactory<ConfigGenerator> {

	@Override
	public ConfigGenerator newInstance() {
		return new DefaultConfigGenerator();
	}

}
