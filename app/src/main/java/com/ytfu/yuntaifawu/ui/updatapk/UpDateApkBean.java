package com.ytfu.yuntaifawu.ui.updatapk;
/**
*
*  @Auther  gxy
*
*  @Date    2020/1/10
*
*  @Des  apk更新提示
*
*/
public class UpDateApkBean {

    /**
     * title : 云台法律咨询
     * code : 3
     * code_str : 1.1.1
     * allow_code : 1
     * size : 11.4
     * url : http://app.yuntaifawu.com/app/yuntaifawu.apk
     * status : 1
     * referer : http://app.yuntaifawu.com/app/yuntaifawu.apk
     * state : success
     */

    private String title;
    private String code;
    private String code_str;
    private String allow_code;
    private String size;
    private String url;
    private int status;
    private String referer;
    private String state;
    private String miaoshu;

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode_str() {
        return code_str;
    }

    public void setCode_str(String code_str) {
        this.code_str = code_str;
    }

    public String getAllow_code() {
        return allow_code;
    }

    public void setAllow_code(String allow_code) {
        this.allow_code = allow_code;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
