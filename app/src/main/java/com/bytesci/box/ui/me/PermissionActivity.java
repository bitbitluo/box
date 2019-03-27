package com.bytesci.box.ui.me;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bytesci.box.R;
import com.bytesci.box.base.BaseActivity;
import com.bytesci.box.util.T;


import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.BaseObservable;

public class PermissionActivity extends BaseActivity implements View.OnClickListener {

    private static final int ACCESS__LOCATION__REQUEST_CODE = 0;
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        location = findViewById(R.id.location);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, location);
        } else {
            T.show("您的系统小于安卓6.0，不需要权限授权");
        }
    }

    @SuppressLint("ResourceAsColor")
    public void checkPermission(String s, TextView tv){
        // 如果没有授权则可以点击授权
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            tv.setOnClickListener(this);
        } else {
            tv.setTextColor(R.color.good);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.location:
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS__LOCATION__REQUEST_CODE);
                break;
        }
    }
}
