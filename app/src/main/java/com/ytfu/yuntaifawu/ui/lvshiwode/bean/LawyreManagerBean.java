package com.ytfu.yuntaifawu.ui.lvshiwode.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2020/5/28
*
*  @Des  律师认证管理bean
*
*/
public class LawyreManagerBean {

    /**
     * status : 1
     * info : {"name":"王五律师","card":"1421456******66664"}
     * referer :
     * state : success
     */

    private int status;
    private InfoBean info;
    private String referer;
    private String state;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
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

    public static class InfoBean {
        /**
         * name : 王五律师
         * card : 1421456******66664
         */

        private String name;
        private String card;
        private String picurl;

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }
    }
}
