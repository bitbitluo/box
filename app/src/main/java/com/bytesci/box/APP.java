package com.bytesci.box;

import android.content.Context;
import androidx.multidex.MultiDexApplication;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.bytesci.box.util.AppMessageHandler;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;

public class APP extends MultiDexApplication {

    public static Context APP_CONTEXT;


    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        ZXingLibrary.initDisplayOpinion(this);
        SDKInitializer.setCoordType(CoordType.BD09LL);
        APP_CONTEXT = getApplicationContext();
        if (getApplicationInfo().packageName.equals(getMyProcessName())){
            BmobIM.init(this);
            BmobIM.registerDefaultMessageHandler(new AppMessageHandler());
        }
    }

    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}