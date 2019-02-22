package com.home.app.service.link;

import com.home.app.model.Category;
import com.home.app.model.Link;
import com.home.app.model.Newspaper;
import com.home.app.model.Property;
import com.home.app.repository.link.LinkException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.Date;
import java.util.List;

public interface LinkService {

    Link updateLink(long linkId, String url, List<String> cateCrawls, Category category, Property property,
                    int timeCrawl,
                    String domain, long channelId, int active) throws LinkException;

    void deleteLink(long lindId) throws LinkException;

    List<Link> findAll(Sort sort, int skip, int limit) throws LinkException;

    Page<Link> findAll(Pageable pageable, Sort sort) throws LinkException;

    List<Link> findAll(Criteria criteria) throws LinkException;

    List<Link> search(String keyword) throws LinkException;

    long count() throws LinkException;

    Link findOne(long linkId) throws LinkException;

    Link findBy_C_L(String cateAlias, long linkId) throws LinkException;

    List<Link> findByCategory(long categoryId) throws LinkException;

    boolean checkExistUrl(String url) throws LinkException;
}
