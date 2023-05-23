package com.bing.box;

import android.app.Application;
import android.content.Context;

import com.bing.box.utils.AppUtils;
import com.bing.box.utils.Logger;

public class BoxApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Logger.i("BoxApplication.attachBaseContext");
        AppUtils.init(this);
    }
}
