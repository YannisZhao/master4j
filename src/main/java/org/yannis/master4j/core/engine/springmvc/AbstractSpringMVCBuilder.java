package org.yannis.master4j.core.engine.springmvc;

import org.yannis.master4j.core.engine.AbstractBuilder;

public abstract class AbstractSpringMVCBuilder extends AbstractBuilder {

	public abstract boolean buildDomain();
	public abstract boolean buildForm();
	public abstract boolean buildDto();
	public abstract boolean buildVo();
	public abstract boolean buildConverter();
	public abstract boolean buildController();
	public abstract boolean buildService();
	public abstract boolean buildServiceImpl();
	public abstract boolean buildDao();
	public abstract boolean buildDaoImpl();
	public abstract boolean buildTest();

}
