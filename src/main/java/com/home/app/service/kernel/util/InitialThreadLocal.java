package com.home.app.service.kernel.util;

import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;

import java.lang.reflect.Method;

public class InitialThreadLocal<T> extends ThreadLocal<T> {

    public InitialThreadLocal(String name, T initialValue) {
        _name = name;
        _initialValue = initialValue;

        if (_initialValue instanceof Cloneable) {
            try {
                _cloneMethod = _initialValue.getClass()
                        .getMethod(_METHOD_CLONE);
            } catch (Exception e) {
                _log.error(e, e);
            }
        }
    }

    public String toString() {
        if (_name != null) {
            return _name;
        } else {
            return super.toString();
        }
    }

    protected T initialValue() {
        if (_cloneMethod != null) {
            try {
                return (T) _cloneMethod.invoke(_initialValue);
            } catch (Exception e) {
                _log.error(e, e);
            }
        }

        return _initialValue;
    }

    private static final String _METHOD_CLONE = "clone";

    private static Log _log = LogFactoryUtil.getLog(InitialThreadLocal.class);

    private Method _cloneMethod;
    private T _initialValue;
    private String _name;

}
