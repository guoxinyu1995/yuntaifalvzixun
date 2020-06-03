package com.ytfu.yuntaifawu.ui.login.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/14
*
*  @Des   验证码登录bean
*
*/
public class CodeLoginBean {

    /**
     * uid : 3
     * status : 3
     * referer :
     * state : success
     */

    private String uid;
    private int status;
    private String referer;
    private String state;
    private String msg;
    private String shenfen; //1是用户 2是律师

    public String getShenfen() {
        return shenfen;
    }

    public void setShenfen(String shenfen) {
        this.shenfen = shenfen;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
