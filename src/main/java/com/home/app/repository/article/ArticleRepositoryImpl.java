package com.home.app.repository.article;

import com.home.app.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.management.Query;
import java.util.List;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ArticleRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<Article> findAll() {
        try {
            return mongoTemplate.findAll(Article.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;

        }
    }
}
