package com.bing.box.utils;

import android.os.Build;
import android.view.View;

import java.util.HashMap;

public class ViewUtils {

    private static HashMap<String, Long> map = new HashMap();

    public ViewUtils() {
    }

    public static synchronized boolean isFastDoubleClick(String key) {
        return isFastDoubleClick(key, 1000L);
    }

    public static synchronized boolean isFastDoubleClick(String key, long time) {
        long lastClickTime = 0L;
        if (map.containsKey(key)) {
            lastClickTime = (Long)map.get(key);
        } else {
            lastClickTime = 0L;
        }

        long currentTime = System.currentTimeMillis();
        long timeInterval = currentTime - lastClickTime;
        if (0L < timeInterval && timeInterval < time) {
            return true;
        } else {
            lastClickTime = currentTime;
            map.put(key, lastClickTime);
            return false;
        }
    }

    public static void fullScreenImmersive(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            int uiOptions = 5894;
            view.setSystemUiVisibility(uiOptions);
        }

    }
}