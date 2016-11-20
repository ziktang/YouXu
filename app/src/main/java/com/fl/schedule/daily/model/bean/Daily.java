package com.fl.schedule.daily.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by tctctc on 2016/11/8.
 */

@Entity
public class Daily {
    @Id
    private long id;
    @NotNull
    private long userId;
    @NotNull
    private String date;
    @NotNull
    private String time;
    private String mood;
    @NotNull
    private String description;
    private String photos;
    @Generated(hash = 2111600196)
    public Daily(long id, long userId, @NotNull String date, @NotNull String time,
            String mood, @NotNull String description, String photos) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.mood = mood;
        this.description = description;
        this.photos = photos;
    }
    @Generated(hash = 2135515054)
    public Daily() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getMood() {
        return this.mood;
    }
    public void setMood(String mood) {
        this.mood = mood;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPhotos() {
        return this.photos;
    }
    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
