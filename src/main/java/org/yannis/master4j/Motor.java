package org.yannis.master4j;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.core.GeneratorFactory;
import org.yannis.master4j.core.generator.Generator;
import org.yannis.master4j.meta.DatabaseMeta;
import org.yannis.master4j.util.DBHelper;

public class Motor {

    private GeneratorFactory classGeneratorFactory;

    private GeneratorFactory viewGeneratorFactory;

    private GeneratorFactory configGeneratorFactory;

    public void fire(ProjectConfig projectConfig){
        DatabaseMeta dbMeta = new DBHelper(projectConfig.getDbConfig()).getDatabaseMeta();

        System.out.println(dbMeta);

        Generator springMVCGenerator = classGeneratorFactory.newInstance();
        Generator viewGenerator = viewGeneratorFactory.newInstance();
        Generator configGenerator = configGeneratorFactory.newInstance();

        springMVCGenerator.generate(dbMeta, projectConfig);
        viewGenerator.generate(dbMeta, projectConfig);
        configGenerator.generate(dbMeta, projectConfig);
    }

    public GeneratorFactory getClassGeneratorFactory() {
        return classGeneratorFactory;
    }

    public void setClassGeneratorFactory(GeneratorFactory classGeneratorFactory) {
        this.classGeneratorFactory = classGeneratorFactory;
    }

    public GeneratorFactory getViewGeneratorFactory() {
        return viewGeneratorFactory;
    }

    public void setViewGeneratorFactory(GeneratorFactory viewGeneratorFactory) {
        this.viewGeneratorFactory = viewGeneratorFactory;
    }

    public GeneratorFactory getConfigGeneratorFactory() {
        return configGeneratorFactory;
    }

    public void setConfigGeneratorFactory(GeneratorFactory configGeneratorFactory) {
        this.configGeneratorFactory = configGeneratorFactory;
    }

}
