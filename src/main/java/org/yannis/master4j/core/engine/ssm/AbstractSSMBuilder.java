package org.yannis.master4j.core.engine.ssm;

import org.yannis.master4j.core.engine.AbstractBuilder;
import org.yannis.master4j.model.Context;

public abstract class AbstractSSMBuilder extends AbstractBuilder {

    public AbstractSSMBuilder(Context context) {
        super(context);
    }

    public abstract void buildMeta();

    public abstract void buildController();

    public abstract void buildService();

    public abstract void buildServiceImpl();

    public abstract void buildBizService();

    public abstract void buildCacheService();

    public abstract void buildMapper();

    public abstract void buildMapperImpl();

    public abstract void buildTest();

}