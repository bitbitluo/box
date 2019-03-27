package com.bytesci.box.ui.conversation;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.bytesci.box.R;
import com.bytesci.box.base.BaseBindingAdapter;
import com.bytesci.box.entity.Conversation;
import com.bytesci.box.ui.ChatActivity;
import com.bytesci.box.util.T;
import com.bytesci.box.databinding.ItemRecyclerConversationBinding;

public class ConversationAdapter extends BaseBindingAdapter<Conversation, ItemRecyclerConversationBinding> {
    public ConversationAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutResId(int viewType) {
        return R.layout.item_recycler_conversation;
    }

    @Override
    protected void onBindItem(ItemRecyclerConversationBinding binding, final Conversation item) {
        binding.setConversation(item);
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("chatUserId", item.getChatUserId());
                T.show(item.getChatUserId());
                context.startActivity(intent);
            }
        });
    }
}
