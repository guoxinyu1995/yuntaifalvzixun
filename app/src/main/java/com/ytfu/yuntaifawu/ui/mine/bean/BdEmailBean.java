package com.ytfu.yuntaifawu.ui.mine.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/12/21
*
*  @Des  绑定邮箱bean
*
*/
public class BdEmailBean {

    /**
     * status : 200
     * referer :
     * state : success
     * msg :
     */

    private int status;
    private String referer;
    private String state;
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
