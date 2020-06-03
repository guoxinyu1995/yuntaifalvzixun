package com.ytfu.yuntaifawu.ui.home.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  首页轮播图
*
*/
public class HomeBannerBean {

    /**
     * status : 1
     * list : [{"name":"免费代写起诉状","pic":"https://www.yuntaifawu.com/data/upload/admin/20191206/5de9ae46b4762.jpg","url":"###"},{"name":"开庭全挺指导","pic":"https://www.yuntaifawu.com/data/upload/admin/20191206/5de9ae623da73.jpg","url":"###"},{"name":"律师教你打官司","pic":"https://www.yuntaifawu.com/data/upload/admin/20191206/5de9ae75b670d.jpg","url":"###"},{"name":"全套咨询方案","pic":"https://www.yuntaifawu.com/data/upload/admin/20191206/5de9ae8d2e93f.jpg","url":"###"}]
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private String url;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class ListBean {
        /**
         * name : 免费代写起诉状
         * pic : https://www.yuntaifawu.com/data/upload/admin/20191206/5de9ae46b4762.jpg
         * url : ###
         */

        private String name;
        private String pic;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
