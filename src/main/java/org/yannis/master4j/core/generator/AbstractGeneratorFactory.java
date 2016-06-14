/**
 * 
 */
package org.yannis.master4j.core.generator;

import org.yannis.master4j.core.GeneratorFactory;

/**
 * @author Yanjun Zhao
 *
 */
public abstract class AbstractGeneratorFactory<T extends Generator> implements GeneratorFactory<Generator> {
	public abstract T newInstance();
}
