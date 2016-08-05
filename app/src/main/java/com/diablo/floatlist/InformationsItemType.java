package com.diablo.floatlist;

/**
 * Created by Diablo on 16/8/5.
 */
public enum InformationsItemType {
    Improtant(1), Normal(2), Special(3);
    private int value = 0;

    private InformationsItemType(int value) {    //    必须是private的，否则编译错误
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
