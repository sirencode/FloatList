package com.diablo.floatlist;

/**
 * Created by clevo on 2015/7/27.
 */
public class InformationData {

    private int type;
    private String title;
    private long createTime;
    private String content;

    public InformationData(){

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
