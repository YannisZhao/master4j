package org.yannis.master4j.core.engine.springmvc;

import org.yannis.master4j.core.engine.AbstractBuilder;

public abstract class AbstractSpringMVCBuilder extends AbstractBuilder {
	
	public abstract boolean buildDomain();
	public abstract boolean buildController();
	public abstract boolean buildService();
	public abstract boolean buildDao();

}
