package com.bytesci.box.ui.login;

import android.annotation.SuppressLint;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bytesci.box.R;
import com.bytesci.box.databinding.FragmentRegestBinding;


public class RegestFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "sdfsdf";
    private static LoginViewModel mViewModel;
    private static RegestFragment sRegestFragment;
    private FragmentRegestBinding mBinding;


    public static RegestFragment getInstance() {
        if(sRegestFragment == null){
            new RegestFragment();
        }


        return new RegestFragment();
    }



    @SuppressLint("ValidFragment")
    private RegestFragment(){

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = FragmentRegestBinding.inflate(inflater);
        mViewModel = new LoginViewModel();
        mBinding.setViewHolder(mViewModel);
        setClickListen(mBinding);
        return mBinding.getRoot();
    }

    private void setClickListen(FragmentRegestBinding binding) {
        binding.txvLogin.setOnClickListener(this);
        binding.btSubmit.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txv_login:
                changeToAnotherFragment();
                break;
            case R.id.bt_submit:
                mViewModel.regest(getActivity(), mBinding.username.getText().toString(), mBinding.passwd.getText().toString(), mBinding.passwd2.getText().toString());
                break;
        }
    }

    private void changeToAnotherFragment(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, LoginFragment.getInstance()).commit();
    }

}
