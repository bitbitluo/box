package com.bytesci.box.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bytesci.box.R;
import com.bytesci.box.base.BaseActivity;
import com.bytesci.box.model.UserModel;
import com.bytesci.box.ui.login.LoginActivity;
import com.bytesci.box.ui.main.MainActivity;
import com.bytesci.box.util.AppStatic;
import com.bytesci.box.util.Tools;


public class SplashActivity extends BaseActivity implements View.OnClickListener {

    private Button mBt_skip;
    private Class mTargetClass = LoginActivity.class;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppStatic.HANDLE_SPLASH:
                    startActivity(new Intent(SplashActivity.this, mTargetClass));
                    finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Tools.getTools().setFullScreen();
        if(UserModel.getInstance().isLogin())
            mTargetClass = MainActivity.class;
        handler.sendEmptyMessageDelayed(AppStatic.HANDLE_SPLASH, 3000);
        initView();
    }

    private void initView() {
        mBt_skip = findViewById(R.id.bt_skip);
        mBt_skip.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        handler.removeMessages(AppStatic.HANDLE_SPLASH);
        handler.sendEmptyMessage(AppStatic.HANDLE_SPLASH);
    }



}
