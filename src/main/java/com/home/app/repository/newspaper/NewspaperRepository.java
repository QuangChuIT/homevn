package com.home.app.repository.newspaper;

import com.home.app.model.Newspaper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public interface NewspaperRepository {
    Newspaper save(Newspaper newspaper) throws NoSuchNewspaperException;

    Newspaper find(long newspaperId) throws NoSuchNewspaperException;

    void remove(long newspaperId) throws NoSuchNewspaperException;

    Newspaper findByAlias(String alias) throws NoSuchNewspaperException;

    List<Newspaper> findAll() throws NoSuchNewspaperException;

    long count(Criteria criteria) throws NoSuchNewspaperException;

    List<Newspaper> findAll(Sort sort, int skip, int limit) throws NoSuchNewspaperException;

    List<Newspaper> findAll(Criteria criteria) throws NoSuchNewspaperException;

    Page<Newspaper> findAll(Pageable pageable, Sort sort, Criteria criteria) throws NoSuchNewspaperException;
}
