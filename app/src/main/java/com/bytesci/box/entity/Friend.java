package com.bytesci.box.entity;

import cn.bmob.v3.BmobObject;

public class Friend extends BmobObject {
    // 一个是用户，一个是用户的朋友
    private User user;
    private User friendUser;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(User friendUser) {
        this.friendUser = friendUser;
    }
}
