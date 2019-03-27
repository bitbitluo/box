package com.bytesci.box.viewModel;

import com.bytesci.box.entity.User;
import com.bytesci.box.model.UserModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SearchUserViewModel extends ViewModel {

    private MutableLiveData<List<User>> mFoundUsers = new MutableLiveData<>();
    private MutableLiveData<String> mSearchName = new MutableLiveData<>();

    public MutableLiveData<String> getSearchName() {
        if (mSearchName == null)
            mSearchName = new MutableLiveData<>();
        return mSearchName;
    }

    public void setSearchName(MutableLiveData<String> searchName) {
        mSearchName = searchName;
    }

    public MutableLiveData<List<User>> getFoundUsers() {
        if (mFoundUsers == null)
            mFoundUsers = new MutableLiveData<List<User>>();
        return mFoundUsers;
    }

    public void setFoundUsers(MutableLiveData<List<User>> foundUsers) {
        mFoundUsers = foundUsers;
    }

    public void searchUser(){
        if(mSearchName.getValue().equals(""))
            return;
        UserModel.getInstance().queryUsers(mSearchName.getValue(), new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e == null)
                    mFoundUsers.setValue(list);
            }
        });
    }
}
