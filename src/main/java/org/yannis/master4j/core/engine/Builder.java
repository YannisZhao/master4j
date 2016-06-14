package org.yannis.master4j.core.engine;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;

public interface Builder {
	
	boolean build();

	void setDbMeta(DatabaseMeta dbMeta);

	void setProjectConfig(ProjectConfig config);

}
