package com.bing.box.bean;

public class Menu2Bean {

    private String title;
    private String desc;
    private int imgResId;

    public Menu2Bean(String title, String desc, int imgResId) {
        this.title = title;
        this.desc = desc;
        this.imgResId = imgResId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
