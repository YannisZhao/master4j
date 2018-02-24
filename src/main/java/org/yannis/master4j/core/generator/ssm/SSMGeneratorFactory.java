package org.yannis.master4j.core.generator.ssm;

import org.yannis.master4j.core.generator.AbstractGeneratorFactory;
import org.yannis.master4j.core.generator.Generator;

/**
 * @author Yannis Zhao
 * @date 2018/1/21
 * @since 1.0
 */
public class SSMGeneratorFactory extends
    AbstractGeneratorFactory<Generator> {

    @Override
    public Generator newInstance() {
        return new DefaultSSMGenerator();
    }
}
