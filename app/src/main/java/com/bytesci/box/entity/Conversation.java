package com.bytesci.box.entity;

public class Conversation {
    private String chatUserId;
    private String username;
    private String avatar;
    private String sub_content;
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChatUserId() {
        return chatUserId;
    }

    public void setChatUserId(String chatUserId) {
        this.chatUserId = chatUserId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSub_content() {
        return sub_content;
    }

    public void setSub_content(String sub_content) {
        this.sub_content = sub_content;
    }
}

