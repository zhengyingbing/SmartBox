package com.bing.box.base;

public interface IBaseActivity {

    int getLayoutId();

    void initView();

    void initData();

    default void back(){};

    default void more(){};

    default int getViewId(){
        return -1;
    }
}
