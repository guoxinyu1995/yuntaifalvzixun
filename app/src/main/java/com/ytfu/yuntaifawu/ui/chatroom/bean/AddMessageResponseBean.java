package com.ytfu.yuntaifawu.ui.chatroom.bean;

import java.util.List;

public class AddMessageResponseBean {

    /**
     * code : 200
     * msg : 添加成功
     * data : []
     */

    private String code;
    private String msg;
    private List<?> data;

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

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
