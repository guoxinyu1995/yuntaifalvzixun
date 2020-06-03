package com.ytfu.yuntaifawu.ui.home.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des 音频列表bean
*
*/
public class AudioListBean {

    /**
     * list : [{"id":"73","order_count":"3965","post_excerpt":"在婚姻中，其中一方确实有过错，那另一方若有证据，是可以诉请离婚的。","post_title":"案例：丈夫出轨  女方诉请离婚获百万赔偿","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191204/5de755256828a.jpg","post_price":"39","post_cost":"99"},{"id":"157","order_count":"3895","post_excerpt":"合法解除试用期员工只有8个正当理由，除此之外都是违法解除，单位要承担风险","post_title":"试用期解雇员工正当理由","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191204/5de756ae133d4.jpg","post_price":"29","post_cost":"99"},{"id":"112","order_count":"3754","post_excerpt":"这是一个关于民间借贷适用法律的音频","post_title":"规范民间借贷纠纷的法律渊源条目索引","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191204/5de75a78b4c1b.jpg","post_price":"59","post_cost":"99"},{"id":"245","order_count":"3698","post_excerpt":"网络贷款平台被人们视作金融信息服务机构，主要服务于民间借贷，是民间借贷的网络版，借贷过程中，资料与资金、合同、手续等全部通过网络实现，它是随着互联网的发展和民间借贷的兴起而发展起来的一种新的金融服务模式。","post_title":"陷入网贷如何上岸？给你一招教你如何自救！","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191209/5dedea099f4fb.jpg","post_price":"19","post_cost":"99"},{"id":"44","order_count":"3659","post_excerpt":"对于员工不能胜任工作，单位可合法解除劳动合同，但对于调岗需特别注意。","post_title":"怎么合法辞退绩效考核不合格的员工？","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191206/5de9ec7ec5387.jpg","post_price":"39","post_cost":"99"},{"id":"201","order_count":"3659","post_excerpt":"  创业公司的股东如果直接由他的爱人取得股权，通常也会给团队带来麻烦，进而导致项目发展融资都可能出现严重困难，这样的问题应该如何处理呢？","post_title":"合伙人离婚股权怎么办","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191204/5de7597093c11.jpg","post_price":"29","post_cost":"99"}]
     * status : 1
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
         * id : 73
         * order_count : 3965
         * post_excerpt : 在婚姻中，其中一方确实有过错，那另一方若有证据，是可以诉请离婚的。
         * post_title : 案例：丈夫出轨  女方诉请离婚获百万赔偿
         * post_img : https://www.yuntaifawu.com/data/upload/admin/20191204/5de755256828a.jpg
         * post_price : 39
         * post_cost : 99
         */

        private String id;
        private String order_count;
        private String post_excerpt;
        private String post_title;
        private String post_img;
        private String post_price;
        private String post_cost;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }

        public String getPost_excerpt() {
            return post_excerpt;
        }

        public void setPost_excerpt(String post_excerpt) {
            this.post_excerpt = post_excerpt;
        }

        public String getPost_title() {
            return post_title;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public String getPost_img() {
            return post_img;
        }

        public void setPost_img(String post_img) {
            this.post_img = post_img;
        }

        public String getPost_price() {
            return post_price;
        }

        public void setPost_price(String post_price) {
            this.post_price = post_price;
        }

        public String getPost_cost() {
            return post_cost;
        }

        public void setPost_cost(String post_cost) {
            this.post_cost = post_cost;
        }
    }
}
