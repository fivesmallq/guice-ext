package org.nihiler.guice.ext.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明这个注解的类,会在自动扫描的时候被忽略绑定.
 * 
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @date 2014-1-16下午9:53:22
 * @version Revision: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
public @interface NoBind {
	// XXX 先暂时提供一个注解来确保范型实现类和接口不被绑定.
}
