package com.home.app.service.counter;

import com.home.app.repository.counter.NoSuchCounterException;

import org.springframework.stereotype.Service;

@Service
public class CounterLocalServiceImpl extends CounterLocalServiceBaseImpl {

    @Override
    public long increment() throws NoSuchCounterException {
        return counterRepository.increment(_SYS_COUNTER_NAME, _SYS_COUNTER_INCREMENT);
    }

    @Override
    public long increment(String name) throws NoSuchCounterException {
        return counterRepository.increment(name, _SYS_COUNTER_INCREMENT);
    }

    @Override
    public long increment(String name, int size) throws NoSuchCounterException {
        return counterRepository.increment(name, size);
    }

    @Override
    public void rename(String oldName, String newName) throws NoSuchCounterException {
        counterRepository.rename(oldName, newName);
    }

    @Override
    public void reset(String name) throws NoSuchCounterException {
        counterRepository.reset(name, 0);
    }

    @Override
    public void reset(String name, int size) throws NoSuchCounterException {
        counterRepository.reset(name, size);
    }

    private static final String _SYS_COUNTER_NAME = "home.app.counter";
    private static final int _SYS_COUNTER_INCREMENT = 1;
}
