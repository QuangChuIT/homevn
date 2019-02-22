package com.home.app.model;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class Permission {

    @Id
    private long permissionId;
    private long userId;
    private Map<String, Map<String, Long>> roles; // Map<classname, Map<role, roleId>>

    public Permission() {
    }

    public Permission(long permissionId, long userId, Map<String, Map<String, Long>> roles) {
        this.permissionId = permissionId;
        this.userId = userId;
        this.roles = roles;
    }

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Map<String, Map<String, Long>> getRoles() {
        return roles;
    }

    public void setRoles(Map<String, Map<String, Long>> roles) {
        this.roles = roles;
    }
}
