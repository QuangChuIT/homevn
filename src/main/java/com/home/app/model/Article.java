package com.home.app.model;


import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Test")
public class Article {
    @Id
    private Long articleId;
    private String title;
    private String url;
    private String urlTitle;
    private String description;
    private List<String> tags;
    private String keyword;
    private String imageUrl;
    private String text;
    private Date pubDate;
    private Category category;
    private Newspaper newspaper;
    private String html;


    public Article(Long articleId, String title, String urlTitle, String description, List<String> tags, String keyword, String imageUrl, String text, Date pubDate, Category category, Newspaper newspaper, String html, String url) {
        this.articleId = articleId;
        this.title = title;
        this.urlTitle = urlTitle;
        this.description = description;
        this.tags = tags;
        this.keyword = keyword;
        this.imageUrl = imageUrl;
        this.text = text;
        this.pubDate = pubDate;
        this.category = category;
        this.newspaper = newspaper;
        this.html = html;
        this.url = url;
    }

    public Article() {
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Newspaper getNewspaper() {
        return newspaper;
    }

    public void setNewspaper(Newspaper newspaper) {
        this.newspaper = newspaper;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
