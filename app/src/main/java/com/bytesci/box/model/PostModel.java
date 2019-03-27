package com.bytesci.box.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bytesci.box.APP;
import com.bytesci.box.entity.Post;
import com.bytesci.box.entity.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class PostModel {


    private static PostModel instance;
    private PostModel(){}
    public static PostModel getInstance(){
        if(instance == null)
            instance = new PostModel();
        return instance;
    }


    public void getPosts(int amount, int skip, FindListener<Post> listener) {
        BmobQuery<Post> query = new BmobQuery<>();
        query.setLimit(amount).setSkip(skip).order("-createdAt")
                .findObjects(listener);
    }

    public void savePost(String title, String content, SaveListener<String> listener){
        Post p = new Post(title, content);
        p.save(listener);
    }
}
