package org.nihiler.guice.ext.scanner;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.nihiler.guice.ext.logging.Log;
import org.nihiler.guice.ext.logging.Logs;
import org.nihiler.guice.ext.util.ClassPath;
import org.nihiler.guice.ext.util.ClassPath.ClassInfo;

/**
 * 扫描所有的指定包下面的class.
 * 
 * @author <a href="mailto:fivesmallq@gmail.com">fivesmallq</a>
 * @date 2014-1-17上午12:35:29
 * @version Revision: 1.0
 */
public class ClassScanner {
	private static final Log LOG = Logs.get();

	public static Set<Class<?>> scan(String... packageNames) {
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		if (packageNames != null && packageNames.length > 0) {
			try {
				for (String one : packageNames) {
					Set<ClassInfo> classInfos = null;

					classInfos = ClassPath.from(
							Thread.currentThread().getContextClassLoader())
							.getTopLevelClassesRecursive(one);

					for (ClassInfo classInfo : classInfos) {
						classes.add(classInfo.load());
					}
				}
			} catch (IOException e) {
				LOG.error("scan class error . package:{}",
						Arrays.toString(packageNames));
			}
		}
		return classes;
	}
}