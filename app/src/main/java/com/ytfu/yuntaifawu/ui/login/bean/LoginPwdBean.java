package com.ytfu.yuntaifawu.ui.login.bean;

/**
 * @Auther gxy
 * @Date 2019/11/15
 * @Des 账号密码登录
 */
public class LoginPwdBean {


    /**
     * status : 1
     * uid : 1041
     * referer :
     * state : success
     */

    private int status;
    private String uid;
    private String referer;
    private String state;
    private String shenfen;//1是用户 2是律师

    public String getShenfen() {
        return shenfen;
    }

    public void setShenfen(String shenfen) {
        this.shenfen = shenfen;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
