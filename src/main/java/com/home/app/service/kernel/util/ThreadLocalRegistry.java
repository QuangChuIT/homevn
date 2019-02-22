package com.home.app.service.kernel.util;

import java.util.HashSet;
import java.util.Set;

public class ThreadLocalRegistry {
	public static ThreadLocal<?>[] captureSnapshot() {
		Set<ThreadLocal<?>> threadLocalSet = _threadLocalSet.get();

		return threadLocalSet
				.toArray(new ThreadLocal<?>[threadLocalSet.size()]);
	}

	public static void registerThreadLocal(ThreadLocal<?> threadLocal) {
		Set<ThreadLocal<?>> threadLocalSet = _threadLocalSet.get();

		threadLocalSet.add(threadLocal);
	}

	public static void resetThreadLocals() {
		Set<ThreadLocal<?>> threadLocalSet = _threadLocalSet.get();

		if (threadLocalSet == null) {
			return;
		}

		for (ThreadLocal<?> threadLocal : threadLocalSet) {
			threadLocal.remove();
		}
	}

	private static ThreadLocal<Set<ThreadLocal<?>>> _threadLocalSet = new InitialThreadLocal<Set<ThreadLocal<?>>>(
			ThreadLocalRegistry.class + "._threadLocalSet",
			new HashSet<ThreadLocal<?>>());
}
