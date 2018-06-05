package com.linguangyu.studentinfomanagementsystem.activity;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by 光裕 on 2018/6/3.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(getApplicationContext(),"841bd7888ab964137195382ee2420a5f");
    }
}
