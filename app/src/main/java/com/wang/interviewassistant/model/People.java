package com.wang.interviewassistant.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by wang
 * on 2017/2/13
 */
@Entity
public class People implements Parcelable{

    @Id(autoincrement = true)
    private Long id;

    private int type;

    private String name;

    private String phone;

    private String interviewTime;

    private String callbackTime;

    private int technology;

    private int study;

    private int fit;

    private String desc;

    @Generated(hash = 1107494859)
    public People(Long id, int type, String name, String phone,
            String interviewTime, String callbackTime, int technology, int study,
            int fit, String desc) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.phone = phone;
        this.interviewTime = interviewTime;
        this.callbackTime = callbackTime;
        this.technology = technology;
        this.study = study;
        this.fit = fit;
        this.desc = desc;
    }

    @Generated(hash = 1406030881)
    public People() {
    }


    protected People(Parcel in) {
        id = in.readLong();
        type = in.readInt();
        name = in.readString();
        phone = in.readString();
        interviewTime = in.readString();
        callbackTime = in.readString();
        technology = in.readInt();
        study = in.readInt();
        fit = in.readInt();
        desc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(type);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeString(interviewTime);
        dest.writeString(callbackTime);
        dest.writeInt(technology);
        dest.writeInt(study);
        dest.writeInt(fit);
        dest.writeString(desc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInterviewTime() {
        return this.interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getCallbackTime() {
        return this.callbackTime;
    }

    public void setCallbackTime(String callbackTime) {
        this.callbackTime = callbackTime;
    }

    public int getTechnology() {
        return this.technology;
    }

    public void setTechnology(int technology) {
        this.technology = technology;
    }

    public int getStudy() {
        return this.study;
    }

    public void setStudy(int study) {
        this.study = study;
    }

    public int getFit() {
        return this.fit;
    }

    public void setFit(int fit) {
        this.fit = fit;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
