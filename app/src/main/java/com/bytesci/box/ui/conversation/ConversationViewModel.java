package com.bytesci.box.ui.conversation;



import com.bytesci.box.entity.Conversation;
import com.bytesci.box.util.L;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMConversation;

public class ConversationViewModel extends ViewModel {
    // 当前所有的会话
    public List<Conversation> conversations;
    public MutableLiveData<List<Conversation>> converstionsLiveData ;

    // 初始化
    public void init(){
        conversations = new ArrayList<>();
        converstionsLiveData = new MutableLiveData<>();
        update();
    }

    public void getConversations(){
        //添加会话
        conversations.clear();
        List<BmobIMConversation> list =BmobIM.getInstance().loadAllConversation();
        if(list != null && list.size() > 0){
            for (BmobIMConversation item : list){
                Conversation c = new Conversation();
                c.setSub_content(item.getConversationTitle());
                conversations.add(c);
            }
        }
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public MutableLiveData<List<Conversation>> getConverstionsLiveData() {
        return converstionsLiveData;
    }

    public void setConverstionsLiveData(MutableLiveData<List<Conversation>> converstionsLiveData) {
        this.converstionsLiveData = converstionsLiveData;
    }

    // 更新数据
    public void update(){
        conversations.clear();
        List<BmobIMConversation> list = BmobIM.getInstance().loadAllConversation();
        for (BmobIMConversation i : list){
            Conversation conversation = new Conversation();
            conversation.setUsername(i.getConversationTitle());
            conversation.setChatUserId(i.getConversationId());
            L.d("update(): " + i.getConversationTitle());
            conversations.add(conversation);
            converstionsLiveData.setValue(conversations);
        }
    }
}
