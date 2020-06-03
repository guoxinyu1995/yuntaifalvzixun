package com.ytfu.yuntaifawu.ui.chatroom.v;

public interface IChatMessageView {

    /**
     * 同步消息到服务器
     */
    void onSyncMessageToSuccess(String sendMessage);

    void onSyncMessageToFail(String errorMsg);

    /**
     * 获取历史消息成功
     */
    void onHistoricalMessageSuccess();

    /**
     * 获取历史消息失败的回调
     */
    void onHistoricalMessageFail();


}
