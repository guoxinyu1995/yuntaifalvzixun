package com.ytfu.yuntaifawu.ui.lawyer.chat.bean;


import androidx.annotation.Keep;

import java.util.List;
@Keep
public class HistoryChatResponse {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"messageId":"10536","direction":0,"from":"3","to":"4286","timestamp":1590632197,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"123"},"ext":{}}]
     */

    private String code;
    private String msg;
    private List<HistoryChatItemBean> data;

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

    public List<HistoryChatItemBean> getData() {
        return data;
    }

    public void setData(List<HistoryChatItemBean> data) {
        this.data = data;
    }


}
