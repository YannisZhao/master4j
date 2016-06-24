package org.yannis.master4j.core.engine;

import org.yannis.master4j.config.ProjectConfig;
import org.yannis.master4j.meta.TableMeta;

/**
 * Created by dell on 2016/6/24.
 */
public interface Constructor {
    void construct(final String targetPath, final ProjectConfig projectConfig, final TableMeta meta);
}
