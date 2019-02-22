package com.home.app.service.kernel.util;

public class AutoResetThreadLocal<T> extends InitialThreadLocal<T> {

	public AutoResetThreadLocal(String name) {
		super(name, null);
	}

	public AutoResetThreadLocal(String name, T initialValue) {
		super(name, initialValue);
	}

	public void set(T value) {
		ThreadLocalRegistry.registerThreadLocal(this);

		super.set(value);
	}

	protected T initialValue() {
		ThreadLocalRegistry.registerThreadLocal(this);

		return super.initialValue();
	}

}