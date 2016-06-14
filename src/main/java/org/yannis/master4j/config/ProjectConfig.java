package org.yannis.master4j.config;

/**
 * Created by yannis on 6/13/16.
 */
public class ProjectConfig {

    private String outputPath;

    private DBConfig dbConfig;

    private DirConfig dirConfig;

    private String groupName;

    private String projectName;

    private String basePackageName;

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public DBConfig getDbConfig() {
        return dbConfig;
    }

    public void setDbConfig(DBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public DirConfig getDirConfig() {
        return dirConfig;
    }

    public void setDirConfig(DirConfig dirConfig) {
        this.dirConfig = dirConfig;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBasePackageName() {
        return basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }
}
