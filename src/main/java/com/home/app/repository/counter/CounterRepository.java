package com.home.app.repository.counter;

public interface CounterRepository {
    void reset(String name, int size) throws NoSuchCounterException;

    void rename(String oldName, String newName) throws NoSuchCounterException;

    long increment(String name, int size) throws NoSuchCounterException;
}
