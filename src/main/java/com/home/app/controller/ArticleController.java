package com.home.app.controller;

import com.home.app.model.Article;
import com.home.app.model.Category;
import com.home.app.repository.article.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/quan-tri-bai-viet")
public class ArticleController {

    private ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public String articeIndex() {
        List<Article> articles = articleRepository.findAll();
        for (Article item : articles) {
            System.out.println(item.getArticleId());
            Category category = item.getCategory();
            List<String> tags = item.getTags();
            for (String tag : tags) {
                System.out.println(tag);
            }
        }
        return "";
    }
}
