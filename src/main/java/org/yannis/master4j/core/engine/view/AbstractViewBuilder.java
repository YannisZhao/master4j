package org.yannis.master4j.core.engine.view;

import org.yannis.master4j.core.engine.AbstractBuilder;

public abstract class AbstractViewBuilder extends AbstractBuilder {

    public abstract boolean buildScripts();

    public abstract boolean buildStyles();

    public abstract boolean buildPlugins();

    public abstract boolean buildImages();

}
