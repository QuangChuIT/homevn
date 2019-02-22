package com.home.app.repository.category;

import com.home.app.model.Category;
import com.home.app.service.kernel.log.Log;
import com.home.app.service.kernel.log.LogFactoryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public Category updateCategory(Category category) {
        try {
            mongoTemplate.save(category);
            return category;
        } catch (CategoryException cex) {
            throw new CategoryException();
        }
    }

    @Override
    public Category findCategory(long categoryId) throws CategoryException {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("id").is(categoryId);
            query.addCriteria(criteria);

            Category category = mongoTemplate.findOne(query, Category.class);

            if (category == null) {
                throw new CategoryException("Not found category with id: " + categoryId);
            }
            return category;
        } catch (CategoryException cex) {
            throw new CategoryException();
        }
    }

    @Override
    public void removeCategory(long categoryId) throws CategoryException {
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("id").is(categoryId);
            query.addCriteria(criteria);
            Category category = mongoTemplate.findOne(query, Category.class);

            if (category == null) {
                throw new CategoryException("Not found category with id " + categoryId);
            }

            mongoTemplate.remove(query, Category.class);

        } catch (CategoryException cex) {
            throw new CategoryException();
        }
    }

    @Override
    public Category fetchCategory(long categoryId) throws CategoryException {
        try {
            return findCategory(categoryId);
        } catch (CategoryException cex) {
            throw new CategoryException();
        }
    }

    @Override
    public List<Category> findAll() throws CategoryException {
        return mongoTemplate.findAll(Category.class);
    }

    @Override
    public List<Category> findAll(Sort sort, int skip, int limit) throws CategoryException {
        try {
            Query query = new Query();
            if (sort != null) {
                query.with(sort);
            }
            if (skip >= 0) {
                query.skip(skip);
            }
            if(limit >= 0){
                query.skip(limit);
            }

            return mongoTemplate.find(query, Category.class);
        } catch (CategoryException cex) {
            throw new CategoryException("Can't not execute query");
        }
    }

    @Override
    public Category findByAlias(String alias) throws CategoryException {
        Category category = null;
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("alias").is(alias);
            query.addCriteria(criteria);
            category = mongoTemplate.findOne(query, Category.class);
            if (category == null) {
                _log.info("Not found category with alias:" + alias);
            }
        } catch (CategoryException cex) {
            _log.error(cex);
        }
        return category;
    }

    @Override
    public boolean checkExist(String alias) throws CategoryException {
        boolean isExist = false;
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("alias").is(alias);
            query.addCriteria(criteria);
            Category category = mongoTemplate.findOne(query, Category.class);
            if (category == null) {
                isExist = true;
            }
        } catch (CategoryException cex) {
            _log.error(cex);
        }
        return isExist;
    }

    @Override
    public List<Category> findByCategoryIds(ArrayList<Long> categoryIds) {
        Query query = new Query();

        Criteria criteria = Criteria.where("id").in(categoryIds);

        query.addCriteria(criteria);

        return mongoTemplate.find(query, Category.class);

    }

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private MongoTemplate mongoTemplate;
    private final static Log _log = LogFactoryUtil.getLog(CategoryRepositoryImpl.class);
}
