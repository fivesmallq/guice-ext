package org.nihiler.guice.ext;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.nihiler.guice.ext.annotation.AutoBind;
import org.nihiler.guice.ext.annotation.NoBind;
import org.nihiler.guice.ext.logging.Log;
import org.nihiler.guice.ext.logging.Logs;
import org.nihiler.guice.ext.scanner.AnnotationClassScanner;

import com.google.inject.AbstractModule;
import com.google.inject.name.Named;

/**
 * 自动绑定module.
 * <p>
 * 所有标注了{@link AutoBind} 注解的实现类，都会自动查找其接口进行绑定.
 * 
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @date 2014-1-16下午9:56:28
 * @version Revision: 1.0
 */
public class AutoBindingModule extends AbstractModule {
	private static final Log LOG = Logs.get();
	private static Set<Class<?>> classList;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void configure() {
		long start = System.currentTimeMillis();
		classList = AnnotationClassScanner.scan(AutoBind.class);
		for (Class<?> one : classList) {
			// 如果有这个注解，直接跳过.
			if (has(one, NoBind.class)) {
				continue;
			}
			// 不是接口的才一起玩.
			if (!one.isInterface()) {
				try {
					Class temp = one;
					if (one.getInterfaces().length > 0) {
						// 只获取第一个接口.
						Class interfaceClass = one.getInterfaces()[0];
						Annotation named = temp.getAnnotation(Named.class);
						if (named == null) {
							named = temp
									.getAnnotation(javax.inject.Named.class);
						}
						if (named != null) {
							bind(interfaceClass).annotatedWith(named).to(temp);
							LOG.debug(
									"auto bind '{}' annotated with '{}' to '{}'",
									interfaceClass.getName(), named,
									temp.getName());
						} else {
							bind(interfaceClass).to(temp);
							LOG.debug("auto bind '{}' to '{}'",
									interfaceClass.getName(), temp.getName());
						}
					}
				} catch (Exception e) {
					LOG.error("auto bind error . class:'{}'", one, e);
				}
			}
		}
		LOG.debug("init AutoBindingModule take:{}ms",
				(System.currentTimeMillis() - start));
	}

	/**
	 * 是否有指定的annotation存在.
	 * 
	 * @param classOfBean
	 * @param annotationClass
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean has(Class<?> classOfBean, Class annotationClass) {
		return classOfBean.getAnnotation(annotationClass) != null;
	}
}
