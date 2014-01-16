package org.nihiler.guice.ext;

import java.util.Set;

import org.nihiler.guice.ext.annotation.GuiceModule;
import org.nihiler.guice.ext.logging.Log;
import org.nihiler.guice.ext.logging.Logs;
import org.nihiler.guice.ext.scanner.AnnotationClassScanner;
import org.nihiler.guice.ext.util.Reflect;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

/**
 * 自动把标记了{@link GuiceModule} 注解的module装载.
 * 
 * @author <a href="mailto:wuzhiqiang@novacloud.com">wuzq</a>
 * @date 2013-9-8下午7:52:24
 * @version Revision: 1.3
 */
public class AutoInstallModule extends AbstractModule {
	private static final Log LOG = Logs.get();
	private static Set<Class<?>> classList;

	@Override
	protected void configure() {
		long start = System.currentTimeMillis();
		classList = AnnotationClassScanner.scan(GuiceModule.class);
		for (Class<?> one : classList) {
			try {
				Class<?> temp = one;
				Module module = Reflect.on(temp).create().get();
				install(module);
				LOG.debug("install module '{}'", module);
			} catch (Exception e) {
				LOG.error("auto install module error . class:'{}'", one, e);
			}
		}
		LOG.debug("init AutoInstallModule take:{}ms",
				(System.currentTimeMillis() - start));
	}
}
