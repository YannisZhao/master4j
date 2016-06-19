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

    static {
       /* Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("log4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertyConfigurator.configure(props);*/
    }

    public void test(){

        Motor motor = new Motor();
        motor.setClassGeneratorFactory(new SpringMVCGeneratorFactory());
        motor.setConfigGeneratorFactory(new ConfigGeneratorFactory());
        motor.setViewGeneratorFactory(new ViewGeneratorFactory());

        ProjectConfig projectConfig = new ProjectConfig();

        DBConfig dbConfig = new DBConfig();
        dbConfig.setDatabase("zkorder");
        dbConfig.setIp("139.196.21.84");
        dbConfig.setDriverPackage("com.mysql.jdbc.Driver");
        dbConfig.setUsername("root");
        dbConfig.setPassword("5467354673");
        projectConfig.setDbConfig(dbConfig);

        projectConfig.setOutputPath("/home/yannis/Development");
        projectConfig.setProjectName("ranstol");
        projectConfig.setBasePackageName("org.yannis.test");

        projectConfig.setDirConfig(new DirConfig(projectConfig.getOutputPath(),projectConfig.getProjectName(),projectConfig.getBasePackageName()));

        motor.fire(projectConfig);
    }
}
