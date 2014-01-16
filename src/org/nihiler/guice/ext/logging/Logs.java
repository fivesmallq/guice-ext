/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.nihiler.guice.ext.logging;

/**
 * Creates an {@link Log} or changes the default factory implementation. This
 * factory allows you to choose what logging framework Netty should use. The
 * default factory is {@link JdkLoggerFactory}. You can change it to your
 * preferred logging framework before other Netty classes are loaded:
 * 
 * <pre>
 * {@link Logs}.setDefaultFactory(new {@link Log4JLoggerFactory}());
 * </pre>
 * 
 * Please note that the new default factory is effective only for the classes
 * which were loaded after the default factory is changed. Therefore,
 * {@link #setDefaultFactory(Logs)} should be called as early as possible and
 * shouldn't be called more than once.
 */
public abstract class Logs {
	private static volatile Logs defaultFactory;

	static {
		final String name = Logs.class.getName();
		Logs f;
		try {
			f = new Slf4JLoggerFactory(true);
			f.newInstance(name).debug(
					"Using SLF4J as the default logging framework");
			defaultFactory = f;
		} catch (Throwable t1) {
			try {
				f = new Log4JLoggerFactory();
				f.newInstance(name).debug(
						"Using Log4J as the default logging framework");
			} catch (Throwable t2) {
				f = new JdkLoggerFactory();
				f.newInstance(name)
						.debug("Using java.util.logging as the default logging framework");
			}
		}

		defaultFactory = f;
	}

	/**
	 * Returns the default factory. The initial default factory is
	 * {@link JdkLoggerFactory}.
	 */
	public static Logs getDefaultFactory() {
		return defaultFactory;
	}

	/**
	 * Changes the default factory.
	 */
	public static void setDefaultFactory(Logs defaultFactory) {
		if (defaultFactory == null) {
			throw new NullPointerException("defaultFactory");
		}
		Logs.defaultFactory = defaultFactory;
	}

	/**
	 * Creates a new logger instance with the name of the specified class.
	 */
	public static Log getLog(Class<?> clazz) {
		return getLog(clazz.getName());
	}

	/**
	 * Creates a new logger instance with the specified name.
	 */
	public static Log getLog(String name) {
		return getDefaultFactory().newInstance(name);
	}

	/**
	 * 返回以调用者的类命名的Log,是获取Log对象最简单的方法!
	 * 
	 * @return
	 */
	public static Log get() {
		return getDefaultFactory().newInstance(
				Thread.currentThread().getStackTrace()[2].getClassName());
	}

	/**
	 * Creates a new logger instance with the specified name.
	 */
	protected abstract Log newInstance(String name);
}
