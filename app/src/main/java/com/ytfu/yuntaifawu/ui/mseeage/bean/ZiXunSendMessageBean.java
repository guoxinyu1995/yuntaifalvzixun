package com.ytfu.yuntaifawu.ui.mseeage.bean;

import java.util.List;

public class ZiXunSendMessageBean {

    /**
     * list : [{"id":1,"title":"免费代写起诉状,云台法律咨询竭力为您服务","content":"凡是云台法律咨询的用户，均可使用我们的起诉状系统，我们免费为您代写一份起诉状，并为您出具开庭的代理意见，让您赢得诉讼，获得法院的支持","imgurl":"http://yuntaifawu.com/data/upload/zixun/fuwu1.png","type":1},{"id":2,"title":"云台法律咨询，给您提供最专业的的资深律师","content":"凡是云台法律咨询的用户，均可使用我们的起诉状系统，我们免费为您代写一份起诉状，并为您出具开庭的代理意见，让您赢得诉讼，获得法院的支持","imgurl":"http://yuntaifawu.com/data/upload/zixun/fuwu2.png","type":2,"url":"http://yuntaifawu.com/portal/index/xieyi"},{"id":2,"title":"开庭流程太复杂，可以使用我们的开庭助手","content":"我们平台的律师教您怎么打官司，每一步对您进行指导","imgurl":"http://yuntaifawu.com/data/upload/zixun/fuwu3.png","type":2,"url":""}]
     * status : 200
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 1
         * title : 免费代写起诉状,云台法律咨询竭力为您服务
         * content : 凡是云台法律咨询的用户，均可使用我们的起诉状系统，我们免费为您代写一份起诉状，并为您出具开庭的代理意见，让您赢得诉讼，获得法院的支持
         * imgurl : http://yuntaifawu.com/data/upload/zixun/fuwu1.png
         * type : 1
         * url : http://yuntaifawu.com/portal/index/xieyi
         */

        private int id;
        private String title;
        private String content;
        private String imgurl;
        private int type;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
