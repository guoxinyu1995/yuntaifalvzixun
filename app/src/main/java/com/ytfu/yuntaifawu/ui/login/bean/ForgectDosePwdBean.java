package com.ytfu.yuntaifawu.ui.login.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/15
*
*  @Des  重置密码bean
*
*/
public class ForgectDosePwdBean {

    /**
     * msg :
     * status : 1
     * referer :
     * state : success
     */

    private String msg;
    private int status;
    private String referer;
    private String state;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
