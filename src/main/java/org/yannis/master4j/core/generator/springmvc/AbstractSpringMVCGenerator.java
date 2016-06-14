package org.yannis.master4j.core.generator.springmvc;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;

public abstract class AbstractSpringMVCGenerator implements SpringMVCGenerator {

	@Override
	public abstract boolean generate(DatabaseMeta meta, ProjectConfig config);

}
