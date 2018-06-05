package com.linguangyu.studentinfomanagementsystem.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by 光裕 on 2018/6/3.
 */

public class Weeks extends BmobObject {
    private Integer week;
    private BmobRelation timetableWeeks;

    public BmobRelation getTimetableWeeks() {
        return timetableWeeks;
    }

    public void setTimetableWeeks(BmobRelation timetableWeeks) {
        this.timetableWeeks = timetableWeeks;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
