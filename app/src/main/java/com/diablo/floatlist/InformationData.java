package com.diablo.floatlist;

/**
 * Created by clevo on 2015/7/27.
 */
public class InformationData {
    private int img;
    private String title;

    public InformationData(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
