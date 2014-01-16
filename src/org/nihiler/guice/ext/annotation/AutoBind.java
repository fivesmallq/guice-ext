package org.nihiler.guice.ext.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动绑定注解.
 * <p>
 * 声明这个注解的类会自动绑定到接口上,多个实现可以使用guice自带的Named注解标识
 * 
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @date 2014-1-16下午9:35:51
 * @version Revision: 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
public @interface AutoBind {
}
