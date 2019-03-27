package com.bytesci.box.ui.me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bytesci.box.R;
import com.bytesci.box.entity.User;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import cn.bmob.v3.BmobUser;

public class MeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        TextView username = view.findViewById(R.id.tv_username);
        User user = BmobUser.getCurrentUser(User.class);
        username.setText(user.getUsername());
        LinearLayout friendList = view.findViewById(R.id.friend_list);
        friendList.setOnClickListener((v) ->{

        });
        view.findViewById(R.id.item_permission).setOnClickListener((v)->{
            Intent intent = new Intent(getActivity(), PermissionActivity.class);
            startActivity(intent);
        });
        return view;
    }
}
