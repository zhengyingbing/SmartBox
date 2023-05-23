package com.bing.box.utils;

import android.util.Log;

public class Logger{

    private static final String TAG = "app";
    private static boolean isShowLog = true;
    private static boolean isShowStackTrace = true;

    public static void setShowLog(boolean isShowLog) {
        Logger.isShowLog = isShowLog;
    }

    public static void setShowStackTrace(boolean isShowStackTrace) {
        Logger.isShowStackTrace = isShowStackTrace;
    }

    public static void d(String content) {
        if (isShowLog){
            Log.d(TAG, content + getExtInfo());
        }
    }

    public static void i(String content) {
        if (isShowLog){
            Log.i(TAG, content + getExtInfo());
        }
    }

    public static void w(String content) {
        if (isShowLog){
            Log.w(TAG, content + getExtInfo());
        }
    }

    public static void e(String content) {
        if (isShowLog){
            Log.e(TAG, content + getExtInfo());
        }
    }

    private static String getExtInfo(){
        if (isShowStackTrace){
            StackTraceElement element = Thread.currentThread().getStackTrace()[3];
            String separator = " & ";
            StringBuilder sb = new StringBuilder(" [ ");
            sb.append("ThreadName=").append(Thread.currentThread().getName()).append(separator);
            sb.append("LineNumber=").append(element.getLineNumber());
            sb.append(" ] ");
            return sb.toString();
        }else{
            return "";
        }
    }
}
