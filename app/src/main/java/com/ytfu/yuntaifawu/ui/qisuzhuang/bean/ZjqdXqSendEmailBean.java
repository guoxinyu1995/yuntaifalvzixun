package com.ytfu.yuntaifawu.ui.qisuzhuang.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/12/18
*
*  @Des 证据清单发送邮箱
*
*/
public class ZjqdXqSendEmailBean {

    /**
     * status : 200
     * msg : 发送成功
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
