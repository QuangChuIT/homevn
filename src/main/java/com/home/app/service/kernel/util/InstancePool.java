package com.home.app.service.kernel.util;

import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;

import java.util.HashMap;
import java.util.Map;

public class InstancePool {
	public static boolean contains(String className) {
		return _instance._contains(className);
	}

	public static Object get(String className) {
		return _instance._get(className);
	}

	public static Object get(String className, boolean logErrors) {
		return _instance._get(className, logErrors);
	}

	public static void put(String className, Object obj) {
		_instance._put(className, obj);
	}

	public static void reset() {
		_instance = new InstancePool();
	}

	private InstancePool() {
		_classPool = new HashMap<String, Object>();
	}

	private boolean _contains(String className) {
		className = className.trim();

		return _classPool.containsKey(className);
	}

	private Object _get(String className) {
		return _get(className, true);
	}

	private Object _get(String className, boolean logErrors) {
		className = className.trim();

		Object obj = _classPool.get(className);

		if (obj == null) {
			Thread currentThread = Thread.currentThread();
			
			AppClassLoaderUtil.setClassLoader(currentThread.getContextClassLoader());
			
			ClassLoader appClassLoader =
				AppClassLoaderUtil.getClassLoader();

			try {
				Class<?> classObj = appClassLoader.loadClass(className);

				obj = classObj.newInstance();

				_put(className, obj);
			}
			catch (Exception e1) {
				if (logErrors && _log.isWarnEnabled()) {
					_log.warn(
						"Unable to load " + className +
							" with the app class loader",
						e1);
				}

				ClassLoader contextClassLoader =
					currentThread.getContextClassLoader();

				try {
					Class<?> classObj = contextClassLoader.loadClass(className);

					obj = classObj.newInstance();

					_put(className, obj);
				}
				catch (Exception e2) {
					if (logErrors) {
						_log.error(
							"Unable to load " + className +
								" with the app class loader or the " +
									"current context class loader",
							e2);
					}
				}
			}
		}

		return obj;
	}

	private void _put(String className, Object obj) {
		className = className.trim();

		_classPool.put(className, obj);
	}

	private static Log _log = LogFactoryUtil.getLog(InstancePool.class);

	private static InstancePool _instance = new InstancePool();

	private Map<String, Object> _classPool;
}
