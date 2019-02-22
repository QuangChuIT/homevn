package com.home.app.model;

import com.home.app.service.kernel.json.JSONArray;
import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "link")
public class Link {

    @Id
    private long linkId;
    private String url;
    private Category category;
    private Property property;
    private List<String> categoriesCrawl;
    private Date lastCrawl;
    private long channelId;
    private String domain;
    private List<Category> categories;
    private int timeCrawl;
    private long lastArticleCrawl;
    private int active;

    public Link(long linkId, String url, Category category, Property property,
                List<String> categoriesCrawl, Date lastCrawl, int timeCrawl, long lastArticleCrawl, List<Category> categories, long channelId, String domain, int active) {
        this.linkId = linkId;
        this.url = url;
        this.category = category;
        this.property = property;
        this.categories = categories;
        this.domain = domain;
        this.categoriesCrawl = categoriesCrawl;
        this.lastCrawl = lastCrawl;
        this.channelId = channelId;
        this.timeCrawl = timeCrawl;
        this.active = active;
        this.lastArticleCrawl = lastArticleCrawl;
    }

    public Link() {
    }

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<String> getCategoriesCrawl() {
        return categoriesCrawl;
    }

    public void setCategoriesCrawl(List<String> categoriesCrawl) {
        this.categoriesCrawl = categoriesCrawl;
    }

    public Date getLastCrawl() {
        return lastCrawl;
    }

    public void setLastCrawl(Date lastCrawl) {
        this.lastCrawl = lastCrawl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getTimeCrawl() {
        return timeCrawl;
    }

    public void setTimeCrawl(int timeCrawl) {
        this.timeCrawl = timeCrawl;
    }

    public long getLastArticleCrawl() {
        return lastArticleCrawl;
    }

    public void setLastArticleCrawl(long lastArticleCrawl) {
        this.lastArticleCrawl = lastArticleCrawl;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private JSONObject parseToJSon() {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put("linkId", linkId);
        jsonObject.put("url", url);
        jsonObject.put("category", category.toString());
        jsonObject.put("property", property.toString());
        jsonObject.put("channelId", channelId);
        JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
        if (categoriesCrawl != null && categoriesCrawl.size() > 0) {
            for (String item : categoriesCrawl) {
                jsonArray.put(item);
            }
        }
        jsonObject.put("categoriesCrawl", jsonArray);
        jsonObject.put("lastCrawl", lastCrawl);
        jsonObject.put("domain", domain);
        jsonObject.put("lastArticleCrawl", lastArticleCrawl);
        jsonObject.put("timeCrawl", timeCrawl);
        jsonObject.put("active", active);
        return jsonObject;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return parseToJSon().toString();
    }
}
