package org.yannis.master4j.core.generator.config;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;

public abstract class AbstractConfigGenerator implements ConfigGenerator {

	@Override
	public abstract boolean generate(DatabaseMeta meta, ProjectConfig config);

}
