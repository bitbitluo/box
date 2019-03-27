package com.bytesci.box.viewModel;

import com.bytesci.box.entity.ChatMessage;
import com.bytesci.box.entity.User;
import com.bytesci.box.model.UserModel;
import com.bytesci.box.util.L;
import com.bytesci.box.util.T;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;
import cn.bmob.newim.bean.BmobIMMessage;
import cn.bmob.newim.bean.BmobIMTextMessage;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.BmobIMClient;
import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.listener.ConversationListener;
import cn.bmob.newim.listener.MessageSendListener;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class ChatViewModel extends ViewModel {
    public List<ChatMessage> mTest = null;
    public MutableLiveData<List<ChatMessage>> mMessages = null;
    public MutableLiveData<String> mSendContent = null;
    // 聊天对象的ID
    public String chatUserId = null;


    public void init(String chatUserId){
        mTest = new ArrayList<>();
        mMessages = new MutableLiveData<>();
        mSendContent = new MutableLiveData<>();
        this.chatUserId = chatUserId;
        T.show(chatUserId);
    }


    public void send(){
        String content = mSendContent.getValue();
        // ChatMessage message = new ChatMessage(ChatAdapter.VALUE_RIGHT_TEXT, content);
        // mTest.add(message);
        // mMessages.setValue(mTest);
        // sendTextMessage(content);
    }

    private void sendTextMessage(final String content){
        final BmobIMUserInfo info =new BmobIMUserInfo();
        L.d(chatUserId);
        info.setUserId(chatUserId);
        UserModel.getInstance().queryUser(chatUserId, new QueryListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(user.getAvatar() == null)
                    info.setAvatar("null");
                info.setAvatar(user.getAvatar());
                info.setName(user.getUsername());
                BmobIM.getInstance().startPrivateConversation(info, new ConversationListener() {
                    @Override
                    public void done(BmobIMConversation c, BmobException e) {
                        if(e == null){
                            //在此跳转到聊天页面或者直接转化
                            BmobIMConversation mBmobIMConversation=BmobIMConversation.obtain(BmobIMClient.getInstance(),c);
                            BmobIMTextMessage msg =new BmobIMTextMessage();
                            msg.setContent(content);
                            mBmobIMConversation.sendMessage(msg, new MessageSendListener() {
                                @Override
                                public void done(BmobIMMessage msg, BmobException e) {
                                    if (e == null) {
                                        T.show("发送成功");
                                    }else{
                                        T.show("发送失败" + e.getMessage());
                                    }
                                }
                            });
                        }else{
                            T.show(e.getMessage());
                        }
                    }
                });
            }
        });
    }


    public MutableLiveData<List<ChatMessage>> getMessages() {
        return mMessages;
    }

    public void setMessages(MutableLiveData<List<ChatMessage>> messages) {
        mMessages = messages;
    }

    public MutableLiveData<String> getSendContent() {
        return mSendContent;
    }

    public void setSendContent(MutableLiveData<String> sendContent) {
        mSendContent = sendContent;
    }


    public void receiveMessage(List<MessageEvent> list) {
        T.show("接收到了消息");
        for(MessageEvent i : list){
            if(i.getConversation().getConversationId().equals(chatUserId)){
                // ChatMessage c = new ChatMessage(ChatAdapter.VALUE_LEFT_TEXT, i.getMessage().getContent());
                // mTest.add(c);
                mMessages.setValue(mTest);

            }
        }
    }
}
