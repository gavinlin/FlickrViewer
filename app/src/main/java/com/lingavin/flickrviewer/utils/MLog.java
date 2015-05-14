package com.lingavin.flickrviewer.utils;

import android.util.Log;

/**
 * Created by gavin on 15/5/14.
 * print logs if Constants.DEBUG is true
 */
public class MLog {

    public static void v(String tag, String msg) {
        if (Constants.DEBUG) {
            Log.v(tag, msg);
        }
    }
    public static void i(String tag, String msg) {
        if (Constants.DEBUG) {
            Log.i(tag, msg);
        }
    }
    public static void w(String tag, String msg) {
        if (Constants.DEBUG) {
            Log.w(tag, msg);
        }
    }
    public static void e(String tag, String msg) {
        if (Constants.DEBUG) {
            Log.e(tag, msg);
        }
    }
}
