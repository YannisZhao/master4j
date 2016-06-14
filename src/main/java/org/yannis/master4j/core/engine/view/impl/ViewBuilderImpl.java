package org.yannis.master4j.core.engine.view.impl;

import org.yannis.master4j.core.engine.view.AbstractViewBuilder;

public class ViewBuilderImpl extends AbstractViewBuilder {

	@Override
	public boolean buildScripts() {
		System.out.println("starting building scripts...");
		return false;
	}

	@Override
	public boolean buildStyles() {
		System.out.println("starting building styles...");
		return false;
	}

	@Override
	public boolean buildPlugins() {
		System.out.println("starting building plugins...");
		return false;
	}

	@Override
	public boolean buildImages() {
		System.out.println("starting building images...");
		return false;
	}

	@Override
	public boolean build() {
		buildScripts();
		buildStyles();
		buildPlugins();
		buildImages();
		return false;
	}

}
