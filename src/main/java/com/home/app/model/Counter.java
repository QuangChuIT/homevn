package com.home.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "counter")
public class Counter implements Serializable {

    @Id
    private String name;
    private long currentId;

    public Counter(String name, long currentId) {
        this.name = name;
        this.currentId = currentId;
    }

    public Counter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(long currentId) {
        this.currentId = currentId;
    }
}
