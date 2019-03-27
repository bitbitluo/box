package com.bytesci.box.model;

import android.text.TextUtils;
import android.widget.Toast;

import com.bytesci.box.APP;
import com.bytesci.box.base.BaseModel;
import com.bytesci.box.entity.Friend;
import com.bytesci.box.entity.User;
import com.bytesci.box.util.T;

import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class UserModel extends BaseModel {

    public static UserModel sUserModel = null;

    // 单例
    public static UserModel getInstance(){
        if(UserModel.sUserModel == null){
            sUserModel = new UserModel();
            return sUserModel;
        } else {
            return sUserModel;
        }
    }

    public boolean isLogin(){
        return BmobUser.isLogin();
    }

    // 注册
    public void register(String username,String password, String pwdagain, final SaveListener<User> listener) {
        final User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.signUp(new SaveListener<User>() {

            @Override
            public void done(User user, BmobException e) {
                listener.done(user, e);
            }
        });
    }

    // 登录
    public void login(String username, String password, final ConnectListener c){
        final User user = new User();
        //此处替换为你的用户名
        user.setUsername(username);
        //此处替换为你的密码
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User bmobUser, BmobException e) {
                if (e == null) {
                    // 连接IM服务器
                    if (!TextUtils.isEmpty(user.getObjectId())) {
                        BmobIM.connect(user.getObjectId(), new ConnectListener() {
                            @Override
                            public void done(String uid, BmobException e) {
                                c.done(uid, e);
                            }
                        });
                    }
                } else {
                    Toast.makeText(APP.APP_CONTEXT, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // 登出
    public void logout(){
        BmobUser.logOut();
    }

    // 获得当前用户
    public User getCurrentUser(){
        return BmobUser.getCurrentUser(User.class);
    }

    // 添加好友
    public void agreeAddFriend(User friend,SaveListener<String> listener){
        Friend f = new Friend();
        User user =BmobUser.getCurrentUser(User.class);
        f.setUser(user);
        f.setFriendUser(friend);
        f.save(listener);
    }

    /**
     * 删除好友
     */
    public void deleteFriend(Friend f,UpdateListener listener){
        Friend friend =new Friend();
        friend.delete(f.getObjectId(),listener);
    }

    public void queryFriends(final FindListener<Friend> listener){
        BmobQuery<Friend> query = new BmobQuery<>();
        User user =BmobUser.getCurrentUser(User.class);
        query.addWhereEqualTo("user", user);
        query.include("friendUser");
        query.order("-updatedAt");
        query.findObjects(new FindListener<Friend>() {
            @Override
            public void done(List<Friend> list, BmobException e) {
                if(e == null) {
                    if (list != null && list.size() > 0) {
                        listener.done(list, e);
                        T.show("查询成功");
                    } else {
                        T.show("暂无好友");
                    }
                } else {
                    T.show("查询失败" + e.getMessage());
                }
            }
        });
    }

    public void queryUser(String uid, final QueryListener<User> listener){
        BmobQuery<User> bmobQuery = new BmobQuery<User>();
        bmobQuery.getObject(uid, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                listener.done(user, e);
            }
        });
    }

    public void queryUsers(String username, final FindListener<User> listener) {
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("username", username);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if(e == null) {
                    if (list != null && list.size() > 0) {
                        listener.done(list, e);
                        T.show("查询成功");
                    } else {
                        T.show("暂无好友");
                    }
                } else {
                    T.show("查询失败" + e.getMessage());
                }
            }
        });
    }

    public void updateUserInfo(BmobIMUserInfo info){
        BmobIM.getInstance().updateUserInfo(info);
    }

}
