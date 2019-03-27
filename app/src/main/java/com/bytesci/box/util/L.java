package com.bytesci.box.util;


import android.util.Log;

public class L {
    private static String TAG = "BYTE_SCI: ";
    public static void d(String s){
        if(AppStatic.DEBUG == true){
            // 获取当前方法
            String method = Thread.currentThread() .getStackTrace()[1].getMethodName();
            Log.d(TAG, method + s);
        }
    }
}

