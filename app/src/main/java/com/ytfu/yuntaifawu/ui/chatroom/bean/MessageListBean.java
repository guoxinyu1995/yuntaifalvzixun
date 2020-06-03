package com.ytfu.yuntaifawu.ui.chatroom.bean;

import java.util.List;

public class MessageListBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"messageId":"10095","direction":1,"from":"2","to":"3","timestamp":1589880327,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"你在干嘛"},"ext":{}}]
     */

    private String code;
    private String msg;
    private List<ItemMessage> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ItemMessage> getData() {
        return data;
    }

    public void setData(List<ItemMessage> data) {
        this.data = data;
    }

    public static class ItemMessage {
        /**
         * messageId : 10095
         * direction : 1
         * from : 2
         * to : 3
         * timestamp : 1589880327
         * chatType : 0
         * status : 2
         * isRead : 1
         * body : {"type":1,"text":"你在干嘛"}
         * ext : {}
         */

        private String messageId;
        private int direction;
        private String from;
        private String to;
        private int timestamp;
        private int chatType;
        private int status;
        private int isRead;
        private BodyBean body;
        private ExtBean ext;

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

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
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

        public BodyBean getBody() {
            return body;
        }

        public void setBody(BodyBean body) {
            this.body = body;
        }

        public ExtBean getExt() {
            return ext;
        }

        public void setExt(ExtBean ext) {
            this.ext = ext;
        }

        public static class BodyBean {
        }

        public static class ExtBean {
        }
    }
}
