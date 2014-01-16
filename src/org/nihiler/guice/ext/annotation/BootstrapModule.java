package org.nihiler.guice.ext.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 声明这个注解的类,会覆盖框架默认的启动模块.
 * 
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @date 2014-1-17上午12:34:39
 * @version Revision: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
public @interface BootstrapModule {
}
