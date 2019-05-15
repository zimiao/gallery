package com.example.myapplication;

import android.util.Log;

public class LogUtils {
    public static void log(Class className,String s) {
        if (className == null) {
            Log.e("anonymous class", s);
        } else {
            Log.e(className.getSimpleName(), s);
        }
    }
}
