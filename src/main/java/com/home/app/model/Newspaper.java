package com.home.app.model;

import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "newspaper")
public class Newspaper {
    @Id
    private long id;
    private String name;
    private String alias;

    public Newspaper(long id, String name, String alias) {
        this.id = id;
        this.name = name;
        this.alias = alias;
    }

    public Newspaper() {
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

    public JSONObject parseToJSON() {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("alias", alias);
        return jsonObject;
    }

    @Override
    public String toString() {
        return parseToJSON().toString();
    }
}
