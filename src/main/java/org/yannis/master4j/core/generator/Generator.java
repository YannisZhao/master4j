package org.yannis.master4j.core.generator;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;

public interface Generator {
	
	boolean generate(DatabaseMeta meta, ProjectConfig config);

}
