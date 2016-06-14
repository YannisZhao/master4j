package org.yannis.master4j.core.generator.view;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.engine.Builder;
import org.yannis.master4j.core.engine.BuilderFacotry;
import org.yannis.master4j.core.engine.view.impl.ViewBuilderImpl;
import org.yannis.master4j.meta.DatabaseMeta;

public class DefaultViewGenerator extends AbstractViewGenerator {

	@Override
	public boolean generate(DatabaseMeta meta, ProjectConfig config) {
		System.out.println("[DefaultViewGenerator]code generating...");
		Builder builder = null;
		try {
			BuilderFacotry facotry = new BuilderFacotry(meta, config);
			builder = facotry.newInstance(ViewBuilderImpl.class);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		builder.build();
		return false;
	}

}
