package com.home.app.repository.newspaper;

import com.home.app.model.Newspaper;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import com.home.app.service.newspaper.NewspaperLocalServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewspaperRepositoryImpl implements NewspaperRepository {

    @Override
    public Newspaper save(Newspaper newspaper) throws NoSuchNewspaperException {
        try {
            mongoTemplate.save(newspaper);
            return newspaper;
        } catch (NoSuchNewspaperException nsne) {
            throw new NoSuchNewspaperException();
        }
    }

    @Override
    public Newspaper find(long newspaperId) throws NoSuchNewspaperException {
        Newspaper newspaper = null;
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("id").is(newspaperId);

            query.addCriteria(criteria);

            newspaper = mongoTemplate.findOne(query, Newspaper.class);
        } catch (NoSuchNewspaperException nse) {
            _log.error(nse);
        }
        return newspaper;
    }

    @Override
    public void remove(long newspaperId) throws NoSuchNewspaperException {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("id").is(newspaperId);

            query.addCriteria(criteria);

            Newspaper newspaper = mongoTemplate.findOne(query, Newspaper.class);

            if (newspaper == null) {
                throw new NoSuchNewspaperException("Not find newspaper with newspaperId " + newspaperId);
            }
            mongoTemplate.remove(query, Newspaper.class);
        } catch (NoSuchNewspaperException nse) {
            throw new NoSuchNewspaperException();
        }
    }

    @Override
    public Newspaper findByAlias(String alias) throws NoSuchNewspaperException {
        Newspaper newspaper = null;
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("alias").is(alias);

            query.addCriteria(criteria);

            newspaper = mongoTemplate.findOne(query, Newspaper.class);
        } catch (NoSuchNewspaperException nse) {
            _log.error(nse);
        }
        return newspaper;
    }

    @Override
    public List<Newspaper> findAll() throws NoSuchNewspaperException {
        return mongoTemplate.findAll(Newspaper.class);
    }

    @Override
    public long count(Criteria criteria) throws NoSuchNewspaperException {
        try {
            Query query = new Query();
            if (criteria != null) {
                query.addCriteria(criteria);
            }
            return mongoTemplate.count(query, Newspaper.class);
        } catch (NoSuchNewspaperException nse) {
            throw new NoSuchNewspaperException();
        }
    }

    @Override
    public List<Newspaper> findAll(Sort sort, int skip, int limit) throws NoSuchNewspaperException {
        try {
            Query query = new Query();
            if (sort != null) {
                query.with(sort);
            }
            if (skip >= 0) {
                query.skip(skip);
            }
            if (limit >= 0) {
                query.skip(limit);
            }

            return mongoTemplate.find(query, Newspaper.class);
        } catch (NoSuchNewspaperException cex) {
            throw new NoSuchNewspaperException("Can't not execute query");
        }
    }

    @Override
    public List<Newspaper> findAll(Criteria criteria) throws NoSuchNewspaperException {
        try {
            Query query = new Query();
            query.addCriteria(criteria);

            return mongoTemplate.find(query, Newspaper.class);
        } catch (NoSuchNewspaperException nex) {
            throw new NoSuchNewspaperException();
        }
    }

    @Override
    public Page<Newspaper> findAll(Pageable pageable, Sort sort, Criteria criteria) throws NoSuchNewspaperException {
        try {
            Query query = new Query();
            query.with(pageable);
            if (sort != null) {
                query.with(sort);
            }
            if (criteria != null) {
                query.addCriteria(criteria);
            }
            List<Newspaper> filteredP = mongoTemplate.find(query, Newspaper.class);
            return PageableExecutionUtils.getPage(filteredP, pageable, () -> mongoTemplate.count(query, Newspaper.class));
        } catch (NoSuchNewspaperException nse) {
            throw new NoSuchNewspaperException();
        }
    }

    public NewspaperRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private MongoTemplate mongoTemplate;
    private final static Log _log = LogFactoryUtil.getLog(NewspaperLocalServiceImpl.class);
}
