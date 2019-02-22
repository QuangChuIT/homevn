package com.home.app.service.counter;

import com.home.app.repository.counter.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class CounterLocalServiceBaseImpl implements CounterLocalService{
    @Qualifier("counterRepositoryImpl")
    @Autowired
    protected CounterRepository counterRepository;
}
