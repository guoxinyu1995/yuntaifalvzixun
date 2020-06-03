package com.ytfu.yuntaifawu.ui.mine.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2020/1/6
*
*  @Des  个性签名
*
*/
public class GeQianBean {

    /**
     * status : 1
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private String msg;

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
