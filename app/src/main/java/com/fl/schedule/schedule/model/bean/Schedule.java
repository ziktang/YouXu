package com.fl.schedule.schedule.model.bean;
import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
/**
 * Created by tctctc on 2016/11/6.
 */
@Entity
public class Schedule implements Parcelable {
    public static final int UN_DONE_UN_DELETE = 0;
    public static final int DONE_UN_DELETE = 1;
    public static final int UN_DONE_DELETE = 2;
    public static final int DONE_DELETE = 3;

    //日程id
    @Id
    private long id;
    //所属用户id
    @NotNull
    @Index
    private long userId;
    //日程描述
    @NotNull
    private String description = "";
    //开始日期
    @NotNull
    @Index
    private String startDate;
    //开始时间
    @NotNull
    private String startTime;
    //结束时间
    @NotNull
    private String endTime;
    //提醒时间
    @NotNull
    private String warnTime = "不提醒";
    //状态未完成,已完成
    @NotNull
    private int status = 0;//0未完成未删除 1已完成未删除 2未完成已删除 3已完成已删除

    private String place = "";

    private String note = "";

    @Generated(hash = 1509643383)
    public Schedule(long id, long userId, @NotNull String description, @NotNull String startDate, @NotNull String startTime, @NotNull String endTime,
            @NotNull String warnTime, int status, String place, String note) {
        this.id = id;
        this.userId = userId;
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.warnTime = warnTime;
        this.status = status;
        this.place = place;
        this.note = note;
    }

    public Schedule() {
    }

    public Schedule(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(String warnTime) {
        this.warnTime = warnTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", warnTime=" + warnTime +
                ", status=" + status +
                '}';
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.userId);
        dest.writeString(this.description);
        dest.writeString(this.startDate);
        dest.writeString(this.startTime);
        dest.writeString(this.endTime);
        dest.writeString(this.warnTime);
        dest.writeInt(this.status);
        dest.writeString(this.place);
        dest.writeString(this.note);
    }

    protected Schedule(Parcel in) {
        this.id = in.readLong();
        this.userId = in.readLong();
        this.description = in.readString();
        this.startDate = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.warnTime = in.readString();
        this.status = in.readInt();
        this.place = in.readString();
        this.note = in.readString();
    }

    public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
}
