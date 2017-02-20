package com.wang.interviewassistant.repository;

import android.content.Context;

import com.wang.interviewassistant.common.App;
import com.wang.interviewassistant.greendao.PeopleDao;


/**
 * Created on 2016/5/27.
 * Author: wang
 */
public abstract class BaseRepository {

    protected PeopleDao mPeopleDao;

    public BaseRepository() {
        mPeopleDao = App.getApp().getDaoSession().getPeopleDao();
    }

}
