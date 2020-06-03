package com.ytfu.yuntaifawu.ui.lvshiwode.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2020/5/28
*
*  @Des  律师认证修改bean
*
*/
public class LawyerRzUpdateBean {

    /**
     * status : 2
     * msg : 上传失败
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
