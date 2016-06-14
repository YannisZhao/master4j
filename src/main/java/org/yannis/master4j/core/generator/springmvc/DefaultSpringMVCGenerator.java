package org.yannis.master4j.core.generator.springmvc;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.engine.Builder;
import org.yannis.master4j.core.engine.BuilderFacotry;
import org.yannis.master4j.core.engine.springmvc.impl.SpringMVCBuilderImpl;
import org.yannis.master4j.meta.DatabaseMeta;

public class DefaultSpringMVCGenerator extends AbstractSpringMVCGenerator {

	@Override
	public boolean generate(DatabaseMeta meta, ProjectConfig config) {
		System.out.println("[DefaultSpringMVCGenerator]code generating...");
		Builder builder = null;
		try {
			BuilderFacotry facotry = new BuilderFacotry(meta, config);
			builder = facotry.newInstance(SpringMVCBuilderImpl.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		builder.build();
		return false;
	}

}
