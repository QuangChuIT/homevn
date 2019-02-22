package com.home.app.model;

import com.home.app.service.kernel.json.JSONArray;
import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import org.springframework.data.annotation.Id;

import java.util.List;

public class Category {
    @Id
    private long id;
    private String name;
    private String alias;
    private List<Category> categories;

    public Category() {
    }

    public Category(long id, String name, String alias, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.categories = categories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private JSONObject parseToJSON() {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("alias", alias);
        if (categories != null) {
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            for (Category cate : categories) {
                JSONObject object = cate.parseToJSON();
                jsonArray.put(object);
            }
            jsonObject.put("child", jsonArray);
        }
        return jsonObject;

    }

    @Override
    public String toString() {
        return parseToJSON().toString();
    }
}
