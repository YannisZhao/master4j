package org.yannis.master4j;

import junit.framework.TestCase;
import org.yannis.master4j.config.DBConfig;
import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.generator.ConfigGeneratorFactory;
import org.yannis.master4j.core.generator.ViewGeneratorFactory;
import org.yannis.master4j.core.generator.springmvc.SpringMVCGeneratorFactory;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase
{
    public void test(){

        Motor motor = new Motor();
        motor.setClassGeneratorFactory(new SpringMVCGeneratorFactory());
        motor.setConfigGeneratorFactory(new ConfigGeneratorFactory());
        motor.setViewGeneratorFactory(new ViewGeneratorFactory());

        ProjectConfig projectConfig = new ProjectConfig();

        DBConfig dbConfig = new DBConfig();
        dbConfig.setDatabase("lianshang");
        dbConfig.setIp("10.21.11.111");
        dbConfig.setDriverPackage("com.mysql.jdbc.Driver");
        dbConfig.setUsername("lssite");
        dbConfig.setPassword("dftxbZik5ah3Zrv3");
        projectConfig.setDbConfig(dbConfig);

        projectConfig.setOutputPath("/home/yannis/development/stuff");
        projectConfig.setProjectName("ranstol");
        projectConfig.setBasePackageName("org.yannis.test");

        projectConfig.setDirConfig(new DirConfig(projectConfig.getOutputPath(),projectConfig.getProjectName(),projectConfig.getBasePackageName()));

        motor.fire(projectConfig);
    }
}
