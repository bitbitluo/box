package com.bytesci.box.util;

import android.util.Log;
import android.widget.Toast;

import com.bytesci.box.APP;


public class T {
    public static void show(String s){
        if(AppStatic.DEBUG == true){
            Toast.makeText(APP.APP_CONTEXT, s, Toast.LENGTH_SHORT).show();
        }
    }
}

