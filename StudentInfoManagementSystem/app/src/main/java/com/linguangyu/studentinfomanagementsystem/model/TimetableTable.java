package com.linguangyu.studentinfomanagementsystem.model;

import android.content.Intent;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by 光裕 on 2018/6/3.
 */

public class TimetableTable extends BmobObject{

    private BmobRelation weeks;
    private String pitchNumber;
    private String CourseName;
    private String teacher;
    private String venue;
    private BmobRelation student;

    public String getWeekend() {
        return weekend;
    }

    public void setWeekend(String weekend) {
        this.weekend = weekend;
    }

    private String weekend;

    public BmobRelation getWeeks() {
        return weeks;
    }

    public void setWeeks(BmobRelation weeks) {
        this.weeks = weeks;
    }

    public String getPitchNumber() {
        return pitchNumber;
    }

    public void setPitchNumber(String pitchNumber) {
        this.pitchNumber = pitchNumber;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public BmobRelation getStudent() {
        return student;
    }

    public void setStudent(BmobRelation student) {
        this.student = student;
    }
}
