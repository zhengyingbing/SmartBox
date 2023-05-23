package com.bing.box.bean;

/**
 * @author: bing
 * @desc: 自定义view的实体类
 * @date: 2023/5/22 17:07
 */
public class CustomViewBean {
    private int id;
    private String title;
    private String desc;

    public CustomViewBean(int id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
