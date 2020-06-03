package com.ytfu.yuntaifawu.ui.mseeage.bean;

public class CheckWeixinBean {

    /**
     * type : 3
     * msg : 已经购买过律师微信
     * referer :
     * state : fail
     */

    private int type;
    private String msg;
    private String referer;
    private String state;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
