package org.yannis.master4j.core.engine.springmvc.impl;

import org.yannis.master4j.core.engine.springmvc.AbstractSpringMVCBuilder;

public class SpringMVCBuilderImpl extends AbstractSpringMVCBuilder {

	@Override
	public boolean buildDomain() {
		System.out.println("starting building domain objects...");
		return false;
	}

	@Override
	public boolean buildController() {
		System.out.println("starting building controllers...");
		return false;
	}

	@Override
	public boolean buildService() {
		System.out.println("starting building services...");
		return false;
	}

	@Override
	public boolean buildDao() {
		System.out.println("starting building daos...");
		return false;
	}

	@Override
	public boolean build() {
		buildDomain();
		buildController();
		buildService();
		buildDao();
		return false;
	}

}
