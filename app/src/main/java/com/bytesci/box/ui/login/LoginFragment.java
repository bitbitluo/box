package com.bytesci.box.ui.login;


import android.annotation.SuppressLint;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bytesci.box.R;
import com.bytesci.box.databinding.FragmentLoginBinding;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding mBinding;

   LoginViewModel mViewModel;

    // 单例
    private static LoginFragment sLoginFragment;
    @SuppressLint("ValidFragment")
    private LoginFragment(){
        mBinding = null;
    }
    public static LoginFragment getInstance() {
        if(sLoginFragment == null)
            sLoginFragment = new LoginFragment();
        return sLoginFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        setClickListen(mBinding);

        return mBinding.getRoot();
    }

    private void setClickListen(FragmentLoginBinding binding) {
        binding.txvRegest.setOnClickListener(this);
        binding.btLogin.setOnClickListener(this);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txv_regest:
                changeToAnotherFragment();
                break;
            case R.id.bt_login:
                String username = mBinding.username.getText().toString();
                String password = mBinding.passwd.getText().toString();
                mViewModel.login(getActivity(), username, password);
                break;
        }
    }


    private void changeToAnotherFragment(){
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container , RegestFragment.getInstance()).commit();
    }

}
