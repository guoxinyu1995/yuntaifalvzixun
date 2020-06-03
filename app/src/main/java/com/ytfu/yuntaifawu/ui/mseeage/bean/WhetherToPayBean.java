package com.ytfu.yuntaifawu.ui.mseeage.bean;

public class WhetherToPayBean {

    /**
     * user_id : 1011
     * lv_id : ls01
     * type : 0
     * referer :
     * state : fail
     */

    private String user_id;
    private String lv_id;
    private int type;
    private String referer;
    private String state;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLv_id() {
        return lv_id;
    }

    public void setLv_id(String lv_id) {
        this.lv_id = lv_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
