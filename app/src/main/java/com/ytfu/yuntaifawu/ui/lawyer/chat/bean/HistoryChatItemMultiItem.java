package com.ytfu.yuntaifawu.ui.lawyer.chat.bean;

import android.text.TextUtils;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class HistoryChatItemMultiItem implements MultiItemEntity {
    public static final int TYPE_SEND_MSG = 0;
    public static final int TYPE_RECEIVE_MSG = 1;
    public static final int TYPE_SEND_FEE = 2;
    public static final int TYPE_RECEIVE_FEE = 3;

    private HistoryChatItemBean chatItem;

    public HistoryChatItemMultiItem(HistoryChatItemBean chatItem) {
        this.chatItem = chatItem;
    }

    @Override
    public int getItemType() {
        // 0 自己发送的 1 是接收的消息
        if (chatItem.getDirection() == 0) {
            if (chatItem.getExt() == null || TextUtils.isEmpty(chatItem.getExt().getJiluid())) {
                return TYPE_SEND_MSG;
            } else {
                return TYPE_SEND_FEE;
            }
        } else {
            if (chatItem.getExt() == null || TextUtils.isEmpty(chatItem.getExt().getJiluid())) {
                return TYPE_RECEIVE_MSG;
            } else {
                return TYPE_RECEIVE_FEE;
            }
        }
    }

    public HistoryChatItemBean getChatItem() {
        return chatItem;
    }

    public void setChatItem(HistoryChatItemBean chatItem) {
        this.chatItem = chatItem;
    }

}
