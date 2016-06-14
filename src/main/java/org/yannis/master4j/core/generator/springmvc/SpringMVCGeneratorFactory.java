package org.yannis.master4j.core.generator.springmvc;

import org.yannis.master4j.core.generator.AbstractGeneratorFactory;
import org.yannis.master4j.core.generator.springmvc.DefaultSpringMVCGenerator;
import org.yannis.master4j.core.generator.springmvc.SpringMVCGenerator;

public class SpringMVCGeneratorFactory extends
		AbstractGeneratorFactory<SpringMVCGenerator> {

	@Override
	public SpringMVCGenerator newInstance() {
		return new DefaultSpringMVCGenerator();
	}

}
