package com.linguangyu.studentinfomanagementsystem.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by 光裕 on 2018/6/5.
 */

public class GradeTable extends BmobObject {

    private String courseName;
    private String isBiXiu;
    private String courseClass;
    private String testClass;
    private Float xuefen;
    private Integer fenshu;

    public Integer getFenshu() {
        return fenshu;
    }

    public void setFenshu(Integer fenshu) {
        this.fenshu = fenshu;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIsBiXiu() {
        return isBiXiu;
    }

    public void setIsBiXiu(String isBiXiu) {
        this.isBiXiu = isBiXiu;
    }

    public String getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(String courseClass) {
        this.courseClass = courseClass;
    }

    public String getTestClass() {
        return testClass;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

    public Float getXuefen() {
        return xuefen;
    }

    public void setXuefen(Float xuefen) {
        this.xuefen = xuefen;
    }

}
