package com.linguangyu.studentinfomanagementsystem.model;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by 光裕 on 2018/6/3.
 */

public class StudentUser extends BmobUser {

    private String name;
    private String college;
    private String nianji;
    private BmobRelation timetable;
    private BmobRelation grade;

    public BmobRelation getGrade() {
        return grade;
    }

    public void setGrade(BmobRelation grade) {
        this.grade = grade;
    }

    public BmobRelation getTimetable() {
        return timetable;
    }

    public void setTimetable(BmobRelation timetable) {
        this.timetable = timetable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getNianji() {
        return nianji;
    }

    public void setNianji(String nianji) {
        this.nianji = nianji;
    }

}
