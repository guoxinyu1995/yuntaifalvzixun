package com.ytfu.yuntaifawu.ui.consult.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2020/5/19
*
*  @Des  免费发布咨询bean
*
*/
public class MIanFeiFaBuBean {

    /**
     * msg : 发布成功！
     * status : 200
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
