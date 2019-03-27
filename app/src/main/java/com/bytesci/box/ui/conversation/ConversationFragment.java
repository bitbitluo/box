package com.bytesci.box.ui.conversation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bytesci.box.R;
import com.bytesci.box.entity.Conversation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.event.OfflineMessageEvent;
import com.bytesci.box.databinding.FragmentConversationBinding;


public class ConversationFragment extends Fragment {

    private FragmentConversationBinding mBinding = null;
    private ConversationAdapter mAdapter = null;
    private ConversationViewModel mViewModel = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_conversation, container, false);
        initView();
        initData();
        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void initView() {
        mAdapter = new ConversationAdapter(getContext());
        mBinding.rvConversation.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvConversation.setAdapter(mAdapter);
    }

    private void initData() {
        mViewModel = ViewModelProviders.of(this).get(ConversationViewModel.class);
        // 2019年3月22日 把这个viewModel写完
        mViewModel.init();
        mViewModel.getConversations();
        subscribe();
    }

    private void subscribe() {
        final Observer<List<Conversation>> conversationsObserver = new Observer<List<Conversation>>() {
            @Override
            public void onChanged(@Nullable List<Conversation> conversations) {
                mAdapter.getItems().clear();
                mAdapter.getItems().addAll(conversations);
            }
        };
        // 向被观察者中添加观察者
        mViewModel.getConverstionsLiveData().observe(this, conversationsObserver);
    }
}
