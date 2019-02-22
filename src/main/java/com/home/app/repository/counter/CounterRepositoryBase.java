package com.home.app.repository.counter;

import com.home.app.model.Counter;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CounterRepositoryBase extends PagingAndSortingRepository<Counter, String>, CounterRepository {
}
