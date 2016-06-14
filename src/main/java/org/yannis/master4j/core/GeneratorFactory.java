package org.yannis.master4j.core;

import org.yannis.master4j.core.generator.Generator;

/**
 * Created by yannis on 6/13/16.
 */
public interface GeneratorFactory<T extends Generator> {
    T newInstance();
}
