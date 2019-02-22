package com.home.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Set;

public class Account {
    @Id
    private long accountId;
    private String email;
    private String fullName;
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String userName;
    private String passWord;
    private boolean active;
    private Set<Role> roles;

    public Account() {
    }

    public Account(long accountId, String email, String fullName, String userName, String passWord, boolean active, Set<Role> roles) {
        this.accountId = accountId;
        this.email = email;
        this.fullName = fullName;
        this.userName = userName;
        this.passWord = passWord;
        this.roles = roles;
        this.active = active;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
