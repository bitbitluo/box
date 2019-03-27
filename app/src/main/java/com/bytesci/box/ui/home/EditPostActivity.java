package com.bytesci.box.ui.home;


import android.os.Bundle;

import com.bytesci.box.R;
import com.bytesci.box.base.BaseActivity;
import com.bytesci.box.entity.Post;
import com.bytesci.box.viewModel.PostViewModel;
import com.bytesci.box.databinding.ActivityEditPostBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;


public class EditPostActivity extends BaseActivity {

    private PostViewModel mBlogViewModel;
    private ActivityEditPostBinding mBinding;
    private Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置布局和得到binding
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit_post);
        mBlogViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        mPost = new Post("", "");
        mBlogViewModel.setPost(mPost);
        mBinding.setViewModel(mBlogViewModel);
        mBinding.setPost(mBlogViewModel.getPost().getValue());
    }
}
