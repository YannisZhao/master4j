package org.yannis.master4j;

import java.util.Arrays;
import junit.framework.TestCase;
import org.yannis.master4j.config.DBConfig;
import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.GeneratorFactoryProvider;
import org.yannis.master4j.core.SSMGeneratorFactoryProvider;
import org.yannis.master4j.core.SingleGeneratorFactoryProvider;
import org.yannis.master4j.enums.CodeStyle;

/**
 * Unit test for simple App.
 */
public class AppTest
    extends TestCase {

    static {
       /* Properties props = new Properties();
        try {
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("log4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertyConfigurator.configure(props);*/
    }

    public void test() {

        ProjectConfig projectConfig = new ProjectConfig();

        /* configure database information */
        DBConfig dbConfig = new DBConfig();
        dbConfig.setDatabase("snail");
        dbConfig.setIp("127.0.0.1");
        dbConfig.setDriverPackage("com.mysql.jdbc.Driver");
        dbConfig.setUsername("root");
        dbConfig.setPassword("111111");
        dbConfig.setExcludeTables(Arrays.asList("sys_resource", "sys_role", "sys_user", "user_group"));
        projectConfig.setDbConfig(dbConfig);

        /* configure project & output path information */
        projectConfig.setOutputPath("/development/projects/master4j_gen");
        projectConfig.setProjectName("snail");
        projectConfig.setBasePackageName("org.yannis.snail");
        projectConfig.setCodeStyle(CodeStyle.SPRINGMVC);

        /* bootstrap the app */
        GeneratorFactoryProvider provider = null;
        switch (projectConfig.getCodeStyle()) {
            case SPRINGMVC:
                provider = new SingleGeneratorFactoryProvider();
                break;
            case SSM:
                provider = new SSMGeneratorFactoryProvider();
                break;
            case SSH:
                System.out.println("Not Support yet!");
                return;
        }
        Motor motor = new Motor(provider);
        motor.fire(projectConfig);
    }
}
