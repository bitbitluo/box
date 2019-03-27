package com.bytesci.box.ui.login;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bytesci.box.R;
import com.bytesci.box.base.BaseActivity;
import com.bytesci.box.util.Tools;


import androidx.lifecycle.ViewModelProviders;


public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        Tools.getTools().setFullScreen();

        Fragment regestFragment = RegestFragment.getInstance();
        Fragment loginFragment = LoginFragment.getInstance();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,regestFragment).hide(regestFragment)
                    .add(R.id.container,loginFragment)
                    .commitNow();
        }
    }
}
