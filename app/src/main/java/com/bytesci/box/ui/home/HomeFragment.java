package com.bytesci.box.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytesci.box.R;
import com.bytesci.box.entity.Post;
import com.bytesci.box.ui.car.CarControllActivity;
import com.bytesci.box.util.AppManager;
import com.bytesci.box.viewModel.PostViewModel;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bytesci.box.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment implements View.OnClickListener{
    private FragmentHomeBinding mBinding;
    private PostViewModel mViewModel;
    private PostAdapter mPostAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 绑定布局
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        mBinding.addBlog.setOnClickListener(this);
        mBinding.carControll.setOnClickListener(this);
        mViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        mBinding.setViewModel(mViewModel);
        mBinding.rvPostContainer.setLayoutManager(new LinearLayoutManager(getContext()));
        mPostAdapter = new PostAdapter(getContext());
        mBinding.rvPostContainer.setAdapter(mPostAdapter);
        for (int i = 0; i < 99; i++) {
            mPostAdapter.getItems().add(new Post());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_blog:
                AppManager.getAppManager().startActivity(EditPostActivity.class);
                break;
            case R.id.car_controll:
                Intent intent2 = new Intent(getActivity(), CarControllActivity.class);
                startActivity(intent2);
                break;
        }
    }


}
