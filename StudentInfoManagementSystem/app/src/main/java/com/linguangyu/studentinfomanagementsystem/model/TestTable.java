package com.linguangyu.studentinfomanagementsystem.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

/**
 * Created by 光裕 on 2018/6/7.
 */

public class TestTable extends BmobObject {

    private String courseName;
    private String testTime;
    private String testTeacher;
    private String testPlase;
    private String testCompus;
    private String testClass;
    private String testLength;

    public String getTestLength() {
        return testLength;
    }

    public void setTestLength(String testLength) {
        this.testLength = testLength;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTestTime() {
        return testTime;
    }

    public void setTestTime(String testTime) {
        this.testTime = testTime;
    }

    public String getTestTeacher() {
        return testTeacher;
    }

    public void setTestTeacher(String testTeacher) {
        this.testTeacher = testTeacher;
    }

    public String getTestPlase() {
        return testPlase;
    }

    public void setTestPlase(String testPlase) {
        this.testPlase = testPlase;
    }

    public String getTestCompus() {
        return testCompus;
    }

    public void setTestCompus(String testCompus) {
        this.testCompus = testCompus;
    }

    public String getTestClass() {
        return testClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

}
