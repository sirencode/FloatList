package com.diablo.floatlist;

/**
 * Created by Diablo on 16/8/5.
 */
public enum InformationsItemType {
    TodayFirstAttention(1), //今日重点关注
    RollReport(2),//滚动播报
    NightGrail(3), //鏖战夜盘
    InformationAction(4), //资讯活动
    YTXDayNews(5),//银天下日报
    TeacherChannel(6),//老师专栏
    KeyAttention(7),//重点关注
    EuropeanSketch(8),//欧盘综述
    MorningGrailSketch(9);//早盘市场综述
    private int value = 0;

    private InformationsItemType(int value) {    //    必须是private的，否则编译错误
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
