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
    private String weather;
    private String place;

    private String mood;
    @NotNull
    private String description;
    private String photosPath;
    private String voicePath;
    private int color;
    private String tags;
    @Generated(hash = 2047602879)
    public Daily(long id, long userId, @NotNull String date, @NotNull String time,
            String weather, String place, String mood, @NotNull String description,
            String photosPath, String voicePath, int color, String tags) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.time = time;
        this.weather = weather;
        this.place = place;
        this.mood = mood;
        this.description = description;
        this.photosPath = photosPath;
        this.voicePath = voicePath;
        this.color = color;
        this.tags = tags;
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
    public String getWeather() {
        return this.weather;
    }
    public void setWeather(String weather) {
        this.weather = weather;
    }
    public String getPlace() {
        return this.place;
    }
    public void setPlace(String place) {
        this.place = place;
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
    public String getPhotosPath() {
        return this.photosPath;
    }
    public void setPhotosPath(String photosPath) {
        this.photosPath = photosPath;
    }
    public String getVoicePath() {
        return this.voicePath;
    }
    public void setVoicePath(String voicePath) {
        this.voicePath = voicePath;
    }
    public int getColor() {
        return this.color;
    }
    public void setColor(int color) {
        this.color = color;
    }
    public String getTags() {
        return this.tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
}
