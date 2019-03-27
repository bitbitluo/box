package com.bytesci.box.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bytesci.box.R;
import com.bytesci.box.base.BaseActivity;

public class ChatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }
}
