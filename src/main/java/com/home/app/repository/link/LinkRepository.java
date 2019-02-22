package com.home.app.repository.link;

import com.home.app.model.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

public interface LinkRepository {

    Link findByPrimaryKey(long linkId) throws LinkException;

    List<Link> findAll(Sort sort, int skip, int limit) throws LinkException;

    List<Link> findByCriteria(Criteria criteria, Sort sort, int skip, int limit) throws LinkException;

    <T> List<T> fetchByBasicQuery(BasicQuery basicQuery, Class<T> object) throws LinkException;

    Link findByBasicQuery(BasicQuery basicQuery) throws LinkException;

    List<Link> findByCriteria(Criteria criteria) throws LinkException;

    Page<Link> findAll(Pageable pageable, Sort sort) throws LinkException;

    long count() throws LinkException;

    Link save(Link link) throws LinkException;

    Link findByUrl(String url) throws LinkException;

    void deleteLink(long linkId) throws LinkException;
}
