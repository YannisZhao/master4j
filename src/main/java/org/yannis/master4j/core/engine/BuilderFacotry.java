package org.yannis.master4j.core.engine;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;

public class BuilderFacotry {

	private DatabaseMeta dbMeta;
	private ProjectConfig config;

	public BuilderFacotry(DatabaseMeta dbMeta, ProjectConfig config){
		this.dbMeta = dbMeta;
		this.config = config;
	}
	
	public Builder newInstance(Class<? extends Builder> clazz) throws Throwable{
		try {
			Builder builder = clazz.newInstance();
			builder.setDbMeta(dbMeta);
			builder.setProjectConfig(config);
			return builder;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new Throwable(e);
		}
	}

}
