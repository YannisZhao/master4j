package org.yannis.master4j.core.generator.view;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;

public abstract class AbstractViewGenerator implements ViewGenerator {

	@Override
	public abstract boolean generate(DatabaseMeta meta, ProjectConfig config);

}
