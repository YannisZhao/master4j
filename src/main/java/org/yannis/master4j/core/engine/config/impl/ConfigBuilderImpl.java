package org.yannis.master4j.core.engine.config.impl;

import org.yannis.master4j.core.engine.config.AbstractConfigBuilder;

public class ConfigBuilderImpl extends AbstractConfigBuilder {

	@Override
	public boolean buildConfiguration() {
		System.out.println("starting building configuration...");
		return false;
	}

	@Override
	public boolean build() {
		buildConfiguration();
		return false;
	}

}
