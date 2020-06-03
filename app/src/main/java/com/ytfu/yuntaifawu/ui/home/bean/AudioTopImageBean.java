package com.ytfu.yuntaifawu.ui.home.bean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  音频列表顶部图片bean
*
*/
public class AudioTopImageBean {


    /**
     * img : https://yuntaifawu.com/public/images/gongsiguquan.png
     * status : 1
     * referer :
     * state : success
     */

    private String img;
    private int status;
    private String referer;
    private String state;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
