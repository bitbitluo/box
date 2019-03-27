package com.bytesci.box.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.bytesci.box.APP;
import com.bytesci.box.entity.User;
import com.bytesci.box.model.UserModel;
import com.bytesci.box.ui.MainActivity;
import com.bytesci.box.util.T;


import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> passwd1 = new MutableLiveData<>();
    public MutableLiveData<String> passwd2 = new MutableLiveData<>();

    public void login(final Context context, String username, String password){
        UserModel.getInstance().login(username, password, new ConnectListener() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                } else {
                    T.show(e.getMessage());
                }
            }
        });
    }

    public void regest(final Context context, final String usename, final String password1, String password2){
        final User user = new User();
        if(!password1.equals(password2)){
            Toast.makeText(APP.APP_CONTEXT, "两次密码不一致", Toast.LENGTH_SHORT).show();
            T.show("" + password1 + password2);
            return ;
        }
        if(password1.length() <= 6){
            Toast.makeText(APP.APP_CONTEXT, "请至少输入六位数的密码", Toast.LENGTH_SHORT).show();
            return ;
        }
        user.setUsername(usename);
        user.setPassword(password1);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    Toast.makeText(APP.APP_CONTEXT, "账号注册成功", Toast.LENGTH_SHORT).show();
                    login(context, usename, password1);
                } else {
                    Toast.makeText(APP.APP_CONTEXT, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
