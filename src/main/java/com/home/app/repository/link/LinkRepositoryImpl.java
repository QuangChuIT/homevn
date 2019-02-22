package com.home.app.repository.link;

import com.home.app.model.Link;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LinkRepositoryImpl implements LinkRepository {

    @Override
    public Link findByPrimaryKey(long linkId) throws LinkException {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("linkId").is(linkId);

            query.addCriteria(criteria);

            return mongoTemplate.findOne(query, Link.class);
        } catch (LinkException le) {
            _log.error(le);
            return null;
        }
    }

    @Override
    public List<Link> findAll(Sort sort, int skip, int limit) throws LinkException {
        try {
            Query query = new Query();

            if (sort != null) {
                query.with(sort);
            }

            query.skip(skip).limit(limit);
            return mongoTemplate.find(query, Link.class);
        } catch (LinkException le) {
            throw new LinkException();
        }
    }

    @Override
    public List<Link> findByCriteria(Criteria criteria, Sort sort, int skip, int limit) throws LinkException {
        try {
            Query query = new Query();
            query.addCriteria(criteria);

            if (sort != null) {
                query.with(sort);
            }

            query.skip(skip).limit(limit);

            return mongoTemplate.find(query, Link.class);
        } catch (LinkException le) {
            throw new LinkException();
        }
    }

    @Override
    public <T> List<T> fetchByBasicQuery(BasicQuery basicQuery, Class<T> object) throws LinkException {
        try {
            return mongoTemplate.find(basicQuery, object);
        } catch (LinkException le) {
            throw new LinkException();
        }
    }

    @Override
    public Link findByBasicQuery(BasicQuery basicQuery) throws LinkException {
        try {
            return mongoTemplate.findOne(basicQuery, Link.class);
        } catch (LinkException lne) {
            throw new LinkException("Can't not execute query");
        }
    }

    @Override
    public List<Link> findByCriteria(Criteria criteria) throws LinkException {
        try {
            Query query = new Query();
            query.addCriteria(criteria);
            return mongoTemplate.find(query, Link.class);
        } catch (LinkException le) {
            throw new LinkException();
        }
    }

    @Override
    public Page<Link> findAll(Pageable pageable, Sort sort) throws LinkException {
        try {
            Query query = new Query().with(pageable);
            if (sort != null) {
                query.with(sort);
            }
            List<Link> filteredP =
                    mongoTemplate.find(query, Link.class);
            return PageableExecutionUtils.getPage(
                    filteredP,
                    pageable,
                    () -> mongoTemplate.count(query, Link.class));
        } catch (LinkException lex) {
            throw new LinkException();
        }
    }

    @Override
    public long count() throws LinkException {
        try {
            Query query = new Query();
            return mongoTemplate.count(query, Link.class);
        } catch (LinkException ex) {
            throw new LinkException();
        }
    }

    @Override
    public Link save(Link link) throws LinkException {
        try {
            mongoTemplate.save(link);
            return link;
        } catch (LinkException le) {
            _log.error(le);
            return null;
        }
    }

    @Override
    public Link findByUrl(String url) throws LinkException {
        Link link = null;
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("url").is(url);
            query.addCriteria(criteria);
            link = mongoTemplate.findOne(query, Link.class);
        } catch (LinkException lne) {
            _log.error(lne);
        }
        return link;
    }

    @Override
    public void deleteLink(long linkId) throws LinkException {
        try {
            Query query = new Query();

            Criteria criteria = Criteria.where("linkId").is(linkId);
            query.addCriteria(criteria);
            Link link = mongoTemplate.findOne(query, Link.class);
            if (link == null) {
                throw new LinkException();
            }

            mongoTemplate.remove(query, Link.class);
        } catch (LinkException le) {
            throw new LinkException();
        }
    }

    @Autowired
    public LinkRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private MongoTemplate mongoTemplate;
    private final static Log _log = LogFactoryUtil.getLog(LinkRepositoryImpl.class);
}
