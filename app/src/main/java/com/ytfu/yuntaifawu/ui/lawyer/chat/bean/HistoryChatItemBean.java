package com.ytfu.yuntaifawu.ui.lawyer.chat.bean;


import androidx.annotation.Keep;

@Keep
public class HistoryChatItemBean {
    /**
     * messageId : 10536
     * direction : 0
     * from : 3
     * to : 4286
     * timestamp : 1590632197
     * chatType : 0
     * status : 2
     * isRead : 1
     * body : {"type":1,"text":"123"}
     * ext : {}
     */
    private String messageId;
    private int direction;
    private String from;
    private String to;
    private long timestamp;
    private int chatType;
    private int status;
    private int isRead;


    private HistoryChatBodyBean body;
    private HistoryChatExtBean ext;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getChatType() {
        return chatType;
    }

    public void setChatType(int chatType) {
        this.chatType = chatType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public HistoryChatBodyBean getBody() {
        return body;
    }

    public void setBody(HistoryChatBodyBean body) {
        this.body = body;
    }

    public HistoryChatExtBean getExt() {
        return ext;
    }

    public void setExt(HistoryChatExtBean ext) {
        this.ext = ext;
    }
}
