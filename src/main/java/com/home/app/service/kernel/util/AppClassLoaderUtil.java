package com.home.app.service.kernel.util;


public class AppClassLoaderUtil {
	public static ClassLoader getClassLoader() {
		return _classLoader;
	}

	public static void setClassLoader(ClassLoader contextClassLoader) {
		_classLoader = contextClassLoader;
	}

	private static ClassLoader _classLoader;
}
