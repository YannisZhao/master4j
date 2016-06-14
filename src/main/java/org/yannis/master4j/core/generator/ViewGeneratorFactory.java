package org.yannis.master4j.core.generator;

import org.yannis.master4j.core.generator.view.DefaultViewGenerator;
import org.yannis.master4j.core.generator.view.ViewGenerator;

public class ViewGeneratorFactory extends AbstractGeneratorFactory<ViewGenerator> {

	@Override
	public ViewGenerator newInstance() {
		return new DefaultViewGenerator();
	}

}
