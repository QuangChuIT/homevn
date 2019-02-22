package com.home.app.model;

import com.home.app.service.kernel.json.JSONArray;
import com.home.app.service.kernel.json.JSONFactoryUtil;
import com.home.app.service.kernel.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

public class Property {
    private String type;
    private List<Extra> css;
    private String xPath;
    private String tag;

    public Property(String type, List<Extra> css, String tag, String xPath) {
        this.type = type;
        this.tag = tag;
        this.xPath = xPath;
        this.css = css;
    }

    public Property() {
    }

    public String getxPath() {
        return xPath;
    }

    public void setxPath(String xPath) {
        this.xPath = xPath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Extra> getCss() {
        return css;
    }

    public void setCss(List<Extra> css) {
        this.css = css;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private JSONObject parsToJSON() {
        JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
        jsonObject.put("type", type);
        jsonObject.put("xPath", xPath);
        jsonObject.put("tag", tag);
        if (css != null) {
            JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
            for (Extra extra : css) {
                jsonArray.put(extra.toString());
            }
            jsonObject.put("css", jsonArray);
        } else {
            jsonObject.put("css", JSONFactoryUtil.createJSONArray());
        }
        return jsonObject;
    }

    @Override
    public String toString() {
        return parsToJSON().toString();
    }
}
