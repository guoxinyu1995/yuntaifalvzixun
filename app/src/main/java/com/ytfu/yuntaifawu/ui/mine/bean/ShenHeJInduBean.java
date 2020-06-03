package com.ytfu.yuntaifawu.ui.mine.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2020/5/21
*
*  @Des  审核进度bean
*
*/
public class ShenHeJInduBean {

    /**
     * status : 3
     * name : 好久不想
     * photo : https://yuntaifawu.com/data/upload/shenhe/2020-05-21/15900325191991.png
     * liyou : 不是本人
     * referer :
     * state : success
     */

    private int status;
    private String name;
    private String photo;
    private String liyou;
    private String referer;
    private String state;
    private String yuanyin;

    public String getYuanyin() {
        return yuanyin;
    }

    public void setYuanyin(String yuanyin) {
        this.yuanyin = yuanyin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLiyou() {
        return liyou;
    }

    public void setLiyou(String liyou) {
        this.liyou = liyou;
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
