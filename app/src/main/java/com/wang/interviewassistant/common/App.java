package com.wang.interviewassistant.common;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.wang.interviewassistant.greendao.DaoMaster;
import com.wang.interviewassistant.greendao.DaoSession;
import com.wang.interviewassistant.greendao.MySQLiteOpenHelper;

import org.greenrobot.greendao.database.Database;


/**
 * Created by wang
 * on 2017/2/8
 */

public class App extends Application {

    private DaoSession daoSession;

    private static App sApp;

    public static App getApp(){
        return sApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this, "people-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
