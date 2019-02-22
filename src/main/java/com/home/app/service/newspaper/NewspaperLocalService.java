package com.home.app.service.newspaper;

import com.home.app.model.Newspaper;
import com.home.app.repository.newspaper.NoSuchNewspaperException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public interface NewspaperLocalService {
    Newspaper insert(long id, String name, String alias) throws NoSuchNewspaperException;

    Newspaper find(long newspaperId) throws NoSuchNewspaperException;

    List<Newspaper> findAll() throws NoSuchNewspaperException;

    List<Newspaper> findAll(Sort sort, int skip, int limit) throws NoSuchNewspaperException;

    void remove(long newspaperId) throws NoSuchNewspaperException;

    long count(Criteria criteria) throws NoSuchNewspaperException;

    Newspaper findByAlias(String alias) throws NoSuchNewspaperException;

    List<Newspaper> findByName(String name) throws NoSuchNewspaperException;

    Page<Newspaper> findAll(Criteria criteria, Pageable pageable, Sort sort) throws NoSuchNewspaperException;
}
