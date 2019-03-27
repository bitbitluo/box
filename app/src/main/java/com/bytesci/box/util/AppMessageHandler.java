package com.bytesci.box.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bytesci.box.APP;
import com.bytesci.box.R;
import com.bytesci.box.ui.MainActivity;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;
import cn.bmob.newim.notification.BmobNotificationManager;

public class AppMessageHandler extends BmobIMMessageHandler {

    @Override
    public void onMessageReceive(final MessageEvent event) {
        //在线消息
        Intent intent = new Intent(APP.APP_CONTEXT, MainActivity.class);
        Bitmap bitmap = BitmapFactory.decodeResource(APP.APP_CONTEXT.getResources(), R.drawable.test_avatar);
        BmobNotificationManager.getInstance(APP.APP_CONTEXT).showNotification(bitmap,"yrdy", "rdy", "rdy",intent);
    }

    @Override
    public void onOfflineReceive(final OfflineMessageEvent event) {
        //离线消息，每次connect的时候会查询离线消息，如果有，此方法会被调用
        Intent intent = new Intent(APP.APP_CONTEXT, MainActivity.class);
        Bitmap  bitmap = BitmapFactory.decodeResource(APP.APP_CONTEXT.getResources(), R.drawable.test_avatar);
        BmobNotificationManager.getInstance(APP.APP_CONTEXT).showNotification(bitmap,"yrdy", "rdy", "rdy",intent);
    }

}