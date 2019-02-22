package com.home.app.service.counter;


import com.home.app.repository.counter.NoSuchCounterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CounterLocalServiceUtil {

    public static long increment() throws NoSuchCounterException {
        return getService().increment();
    }

    public static long increment(String name) throws NoSuchCounterException {
        return getService().increment(name);
    }

    public static long increment(String name, int size) throws NoSuchCounterException {
        return getService().increment(name, size);
    }

    public static void rename(String oldName, String newName) throws NoSuchCounterException {
        getService().rename(oldName, newName);
    }

    public static void reset(String name) throws NoSuchCounterException {
        getService().reset(name);
    }

    public static void reset(String name, int size) throws NoSuchCounterException {
        getService().reset(name, size);
    }

    @Autowired
    public void setService(CounterLocalService service) {
        _service = service;
    }

    private static CounterLocalService getService() {
        return _service;
    }

    private static CounterLocalService _service;
}
