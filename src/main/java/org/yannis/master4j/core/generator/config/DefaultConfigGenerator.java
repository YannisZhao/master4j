package org.yannis.master4j.core.generator.config;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.engine.Builder;
import org.yannis.master4j.core.engine.BuilderFacotry;
import org.yannis.master4j.core.engine.config.impl.DefaultSpringConfigBuilderImpl;
import org.yannis.master4j.meta.DatabaseMeta;

public class DefaultConfigGenerator extends AbstractConfigGenerator {

	@Override
	public boolean generate(DatabaseMeta meta, ProjectConfig config) {
		System.out.println("[DefaultConfigGenerator]code generating...");
		Builder builder = null;
		try {
			BuilderFacotry facotry = new BuilderFacotry(meta, config);
			builder = facotry.newInstance(DefaultSpringConfigBuilderImpl.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		builder.build();
		return false;
	}

}
