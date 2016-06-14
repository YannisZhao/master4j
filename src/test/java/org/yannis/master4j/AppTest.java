package org.yannis.master4j;

import junit.framework.TestCase;
import org.yannis.master4j.config.DBConfig;
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
        projectConfig.setDbConfig(new DBConfig());

        motor.fire(projectConfig);
    }
}
