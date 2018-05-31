package com.linguangyu.studentinfomanagementsystem.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by 光裕 on 2018/5/30.
 * 用户表
 */

public class UserTable extends BmobObject {

    private String studentId;
    private String password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
