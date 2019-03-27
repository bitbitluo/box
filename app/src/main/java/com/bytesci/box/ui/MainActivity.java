package com.bytesci.box.ui;

import androidx.databinding.BaseObservable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.StrictMode;

import com.bytesci.box.R;
import com.bytesci.box.base.BaseActivity;
import com.bytesci.box.entity.TabItem;
import com.bytesci.box.ui.home.HomeFragment;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    // 导航
    private String[] mTitles = {"首页", "寄送", "消息", "自己"};
    private int[] mIconUnselectedIds = {
            R.drawable.navbar_house, R.drawable.navbar_send,
            R.drawable.navbar_message, R.drawable.navbar_account
    };
    private int[] mIconSelectedIds = {
            R.drawable.navbar_house_s, R.drawable.navbar_send_s,
            R.drawable.navbar_message_s, R.drawable.navbar_account_s
    };
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private CommonTabLayout mCT_navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());
        mFragments.add(new HomeFragment());

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabItem(mTitles[i], mIconSelectedIds[i], mIconUnselectedIds[i]));
        }

        mCT_navbar = (CommonTabLayout) findViewById(R.id.ct_navbar);
        mCT_navbar.setTabData(mTabEntities, this, R.id.fl_container, mFragments);
        mCT_navbar.setIconWidth(24);
        mCT_navbar.setIconHeight(24);
        mCT_navbar.setTextSelectColor(R.color.textColor);
        mCT_navbar.setTextUnselectColor(R.color.textColor);
        mCT_navbar.showMsg(2, 99);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }
}
