package com.ytfu.yuntaifawu.ui.mine.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/20
*
*  @Des   头像上传bean
*
*/
public class PictureUploadBean {

    /**
     * src : https://yuntaifawu.com/data/upload/avatar/2019-11-20//15742359161961.jpg
     * msg : 上传成功
     * status : 1
     * referer :
     * state : success
     */

    private String src;
    private String msg;
    private int status;
    private String referer;
    private String state;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

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
