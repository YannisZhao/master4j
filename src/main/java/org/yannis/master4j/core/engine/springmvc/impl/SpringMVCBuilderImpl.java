package org.yannis.master4j.core.engine.springmvc.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.core.engine.springmvc.AbstractSpringMVCBuilder;
import org.yannis.master4j.core.engine.support.ControllerConstructor;
import org.yannis.master4j.core.engine.support.EntityConstructor;
import org.yannis.master4j.meta.TableMeta;
import org.yannis.master4j.util.FileUtils;

public class SpringMVCBuilderImpl extends AbstractSpringMVCBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMVCBuilderImpl.class);

	private String webModulePath;
	private String apiModulePath;
	private String implModulePath;
	private String srcRelativePath;
	private String testRelativePath;

	public void init(){
		DirConfig dirConfig = projectConfig.getDirConfig();

		webModulePath = dirConfig.getWebModulePath();
		apiModulePath = dirConfig.getApiModulePath();
		implModulePath = dirConfig.getImplModulePath();
		srcRelativePath = dirConfig.getSrcRelativePath();
		testRelativePath = dirConfig.getTestRelativePath();
	}

	@Override
	public boolean buildDomain() {
        if(LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building domain objects...");
        }

		String domainPath = apiModulePath + "/" + srcRelativePath + "/domain";
		FileUtils.mkdir(domainPath);

		for(TableMeta meta : dbMeta.getTableMetaList()) {
			// Construct domain bean
			EntityConstructor.construct(domainPath, projectConfig, meta);
		}

		return false;
	}

	@Override
	public boolean buildController() {
        if(LOGGER.isInfoEnabled()) {
            LOGGER.info("Starting building controllers...");
        }

        String controllerPath = webModulePath + "/" + srcRelativePath + "/controller";
        FileUtils.mkdir(controllerPath);

        for(TableMeta meta : dbMeta.getTableMetaList()) {
            // Construct Controller
            ControllerConstructor.construct(controllerPath, projectConfig, meta);
        }

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
	public boolean buildTest(){
		return false;
	}

	@Override
	public boolean build() {
        init();
		buildDomain();
		buildController();
		buildService();
		buildDao();
		buildTest();
		return false;
	}

}
