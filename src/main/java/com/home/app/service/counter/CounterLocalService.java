package com.home.app.service.counter;

import com.home.app.repository.counter.NoSuchCounterException;

public interface CounterLocalService {
    long increment() throws NoSuchCounterException;

    long increment(String name) throws NoSuchCounterException;

    long increment(String name, int size) throws NoSuchCounterException;

    void rename(String oldName, String newName) throws NoSuchCounterException;

    void reset(String name) throws NoSuchCounterException;

    void reset(String name, int size) throws NoSuchCounterException;
}
