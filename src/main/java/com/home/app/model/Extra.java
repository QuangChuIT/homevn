package com.home.app.model;

import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Extra {
    private long extraId;
    private String tag;
    private String clazz;

    public Extra(long extraId, String tag, String clazz) {
        this.extraId = extraId;
        this.tag = tag;
        this.clazz = clazz;
    }

    public Extra() {
    }

    public long getExtraId() {
        return extraId;
    }

    public void setExtraId(long extraId) {
        this.extraId = extraId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    private JSONObject parseToJSON() {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put("extraId", extraId);
        jsonObject.put("tag", tag);
        jsonObject.put("clazz", clazz);
        return jsonObject;
    }

    @Override
    public String toString() {
        return parseToJSON().toString();
    }
}
