package com.linguangyu.studentinfomanagementsystem.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by 光裕 on 2018/6/6.
 */

public class SeasonTable extends BmobObject {

    private String yearSeason;
    private BmobRelation gradeSeason;

    public String getYearSeason() {
        return yearSeason;
    }

    public void setYearSeason(String yearSeason) {
        this.yearSeason = yearSeason;
    }

    public BmobRelation getGradeSeason() {
        return gradeSeason;
    }

    public void setGradeSeason(BmobRelation gradeSeason) {
        this.gradeSeason = gradeSeason;
    }

}
