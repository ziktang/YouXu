package com.fl.schedule.home.model.bean;


/**
 * Created by tctctc on 2016/11/6.
 */

public class UserInfo {
    private long id;
    private String name;
    private String phoneNumber;
    private String token;

    public UserInfo(long id, String name, String phoneNumber, String token) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.token = token;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
