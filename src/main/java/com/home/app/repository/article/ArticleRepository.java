package com.home.app.repository.article;

import com.home.app.model.Article;

import java.util.List;

public interface ArticleRepository {
    List<Article> findAll();
}
