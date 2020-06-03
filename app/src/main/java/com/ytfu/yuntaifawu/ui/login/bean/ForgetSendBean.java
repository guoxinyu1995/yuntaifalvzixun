package com.ytfu.yuntaifawu.ui.login.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/15
*
*  @Des  重置密码的获取验证码bean
*
*/
public class ForgetSendBean {

    /**
     * status : 1
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;

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
