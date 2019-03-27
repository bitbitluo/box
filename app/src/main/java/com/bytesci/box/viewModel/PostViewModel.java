package com.bytesci.box.viewModel;

import android.app.Activity;
import android.content.Intent;

import com.bytesci.box.R;
import com.bytesci.box.databinding.ActivityEditPostBinding;
import com.bytesci.box.entity.User;
import com.bytesci.box.model.PostModel;
import com.bytesci.box.ui.main.MainActivity;

import java.util.List;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.ViewModel;

public class PostViewModel extends ViewModel {
    private Activity mActivity;
    private ActivityEditPostBinding mBinding;
    private PostModel mBlogModel;
    List<User> dbBlogs;




    public ObservableField<List<User>> blogs = new ObservableField<>();
    // 构造函数
    public PostViewModel(Activity activity, ActivityEditPostBinding binding) {
        mActivity = activity;
        mBinding = binding;
        mBlogModel = new PostModel();
    }

    public PostViewModel(Activity activity) {
        mActivity = activity;
        mBlogModel = new PostModel();

    }

    public void savePost(){

    }

    public void getPostFromDB(){

    }

    public void updatePost(){

    }
}
