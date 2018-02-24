package org.yannis.master4j.config;

import org.yannis.master4j.enums.CodeStyle;

/**
 * Created by yannis on 6/13/16.
 */
public class ProjectConfig {

    private CodeStyle codeStyle;

    private String outputPath;

    private DBConfig dbConfig;

    private DirConfig dirConfig;

    private String groupName;

    private String projectName;

    private String basePackageName;

    public CodeStyle getCodeStyle() {
        return codeStyle;
    }

    public ProjectConfig setCodeStyle(CodeStyle codeStyle) {
        this.codeStyle = codeStyle;
        return this;
    }

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
        return new DirConfig(outputPath, projectName, basePackageName);
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
