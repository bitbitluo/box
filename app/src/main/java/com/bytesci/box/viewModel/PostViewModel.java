package com.bytesci.box.viewModel;

import android.app.Activity;

import com.bytesci.box.databinding.ActivityEditPostBinding;
import com.bytesci.box.entity.Post;
import com.bytesci.box.entity.User;
import com.bytesci.box.model.PostModel;
import com.bytesci.box.model.UserModel;
import com.bytesci.box.ui.MainActivity;
import com.bytesci.box.util.AppManager;
import com.bytesci.box.util.T;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class PostViewModel extends ViewModel {



    private MutableLiveData<Post> post = new MutableLiveData<>();


    public void savePost(){
        String title = post.getValue().getTitle();
        String content = post.getValue().getContent();
        PostModel.getInstance().savePost(title, content, new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null)
                    AppManager.getAppManager().startActivity(MainActivity.class);
                else
                    T.show(e.getMessage());
            }
        });
    }

    public MutableLiveData<Post> getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post.setValue(post);
    }
}
