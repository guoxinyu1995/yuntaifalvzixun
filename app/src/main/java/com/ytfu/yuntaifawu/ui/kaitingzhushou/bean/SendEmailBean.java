package com.ytfu.yuntaifawu.ui.kaitingzhushou.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/30
*
*  @Des  发送邮件到邮箱的bean
*
*/
public class SendEmailBean {


    /**
     * status : 200
     * msg : 邮件已发送,请注意查收
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
