package org.yannis.master4j;

import junit.framework.TestCase;
import org.yannis.master4j.config.DBConfig;
import org.yannis.master4j.config.DirConfig;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.generator.ConfigGeneratorFactory;
import org.yannis.master4j.core.generator.ViewGeneratorFactory;
import org.yannis.master4j.core.generator.springmvc.SpringMVCGeneratorFactory;

import java.util.Arrays;

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

        /* configure database information */
        DBConfig dbConfig = new DBConfig();
        dbConfig.setDatabase("snail");
        dbConfig.setIp("127.0.0.1");
        dbConfig.setDriverPackage("com.mysql.jdbc.Driver");
        dbConfig.setUsername("root");
        dbConfig.setPassword("111111");
        dbConfig.setExcludeTables(Arrays.asList("sys_resource","sys_role","sys_user","user_group"));
        projectConfig.setDbConfig(dbConfig);

        /* configure project & output path information */
        projectConfig.setOutputPath("/home/yannis/Development");
        projectConfig.setProjectName("snail");
        projectConfig.setBasePackageName("org.yannis.snail");

        projectConfig.setDirConfig(new DirConfig(projectConfig.getOutputPath(),projectConfig.getProjectName(),projectConfig.getBasePackageName()));

        /* bootstrap the app */
        motor.fire(projectConfig);
    }
}
