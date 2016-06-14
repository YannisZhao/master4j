package org.yannis.master4j.core.engine;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.DatabaseMeta;

/**
 * Created by yannis on 6/13/16.
 */
public abstract class AbstractBuilder implements Builder{

    protected DatabaseMeta dbMeta;

    protected ProjectConfig projectConfig;

    public DatabaseMeta getDbMeta() {
        return dbMeta;
    }

    public void setDbMeta(DatabaseMeta dbMeta) {
        this.dbMeta = dbMeta;
    }

    public ProjectConfig getProjectConfig() {
        return projectConfig;
    }

    @Override
    public void setProjectConfig(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }
}
