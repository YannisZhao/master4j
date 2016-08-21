package org.yannis.master4j.core.engine.config.impl;

import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.core.engine.config.AbstractConfigBuilder;
import org.yannis.master4j.core.engine.config.constructor.springmvc.PomConstructor;
import org.yannis.master4j.util.FileUtils;

public class DefaultSpringConfigBuilderImpl extends AbstractConfigBuilder {

	@Override
	public boolean buildConfiguration() {
		System.out.println("starting building configuration...");
		DirConfig dirConfig = projectConfig.getDirConfig();
		String resourceRelativePath = dirConfig.getResourceRelativePath();
//		String srcRelativePath = dirConfig.getSrcRelativePath();
//		String testRelativePath = dirConfig.getTestRelativePath();

		// Build pom
		PomConstructor.construct(projectConfig);

		// Build web module configuration
		String webModulePath = dirConfig.getWebModulePath();
		//FileUtils.newFile(webModulePath +"/pom.xml","");
		String webinfPath = dirConfig.getWebinfPath();
		FileUtils.newFile(webinfPath +"/web.xml","");
		FileUtils.mkdir(webinfPath+"/lib");
		FileUtils.mkdir(webinfPath+"/pages");
		FileUtils.mkdirs(webModulePath+"/"+resourceRelativePath+"/i18n/message");
		FileUtils.mkdirs(webModulePath+"/"+resourceRelativePath+"/i18n/label");

		FileUtils.newFile(webModulePath+"/"+resourceRelativePath+"/spring-beans.xml","");
		FileUtils.newFile(webModulePath+"/"+resourceRelativePath+"/spring-context.xml","");
		FileUtils.newFile(webModulePath+"/"+resourceRelativePath+"/spring-config.xml","");
		FileUtils.newFile(webModulePath+"/"+resourceRelativePath+"/log4j.properties","");

		FileUtils.mkdir(webModulePath+"/src/main/webapp/static");
		FileUtils.mkdir(webModulePath+"/src/main/webapp/static/images");
		FileUtils.mkdir(webModulePath+"/src/main/webapp/static/js");
		FileUtils.mkdir(webModulePath+"/src/main/webapp/static/css");
		FileUtils.mkdir(webModulePath+"/src/main/webapp/static/add-ons");

		//Build service api module configuration
		String apiModulePath = dirConfig.getApiModulePath();
		//FileUtils.newFile(apiModulePath +"/pom.xml","");
		FileUtils.mkdirs(apiModulePath+"/"+resourceRelativePath+"/i18n/message");
		FileUtils.mkdirs(apiModulePath+"/"+resourceRelativePath+"/i18n/label");

		FileUtils.newFile(apiModulePath+"/"+resourceRelativePath+"/spring-beans.xml","");
		FileUtils.newFile(apiModulePath+"/"+resourceRelativePath+"/spring-context.xml","");
		FileUtils.newFile(apiModulePath+"/"+resourceRelativePath+"/spring-config.xml","");
		FileUtils.newFile(apiModulePath+"/"+resourceRelativePath+"/log4j.properties","");

		//Build service impl module configuration
		String implModulePath = dirConfig.getImplModulePath();
		//FileUtils.newFile(implModulePath +"/pom.xml","");
		FileUtils.mkdirs(implModulePath+"/"+resourceRelativePath+"/i18n/message");
		FileUtils.mkdirs(implModulePath+"/"+resourceRelativePath+"/i18n/label");

		FileUtils.newFile(implModulePath+"/"+resourceRelativePath+"/spring-beans.xml","");
		FileUtils.newFile(implModulePath+"/"+resourceRelativePath+"/spring-context.xml","");
		FileUtils.newFile(implModulePath+"/"+resourceRelativePath+"/spring-config.xml","");
		FileUtils.newFile(implModulePath+"/"+resourceRelativePath+"/log4j.properties","");


		return false;
	}

	@Override
	public boolean build() {
		return buildConfiguration();
	}

}
