package com.bytesci.box.entity;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    private String avatar;
    private String signature;
    private boolean showPost;
    private boolean isMan;
    private String number;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public boolean isShowPost() {
        return showPost;
    }

    public void setShowPost(boolean showPost) {
        this.showPost = showPost;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}