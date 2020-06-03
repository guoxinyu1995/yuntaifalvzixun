package com.ytfu.yuntaifawu.ui.kaitingzhushou.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/12/2
*
*  @Des  代理词发送邮箱
*
*/
public class DlcSendEmailBean {

    /**
     * status : 202
     * msg : 没有购买代理词
     * referer :
     * state : success
     */

    private int status;
    private String msg;
    private String referer;
    private String state;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
