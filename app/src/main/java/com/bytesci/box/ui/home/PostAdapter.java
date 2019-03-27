package com.bytesci.box.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bytesci.box.R;
import com.bytesci.box.base.BaseActivity;
import com.bytesci.box.base.BaseBindingAdapter;
import com.bytesci.box.entity.Conversation;
import com.bytesci.box.entity.Post;
import com.bytesci.box.ui.ChatActivity;
import com.bytesci.box.util.T;

import androidx.databinding.ViewDataBinding;

public class PostAdapter extends BaseBindingAdapter<Post, ViewDataBinding> {

    public PostAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_recycler_post;
    }

    @Override
    protected void onBindItem(ViewDataBinding binding, Post item) {

    }
}
