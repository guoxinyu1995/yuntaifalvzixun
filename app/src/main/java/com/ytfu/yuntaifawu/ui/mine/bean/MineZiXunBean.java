package com.ytfu.yuntaifawu.ui.mine.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/19
*
*  @Des 我的咨询bean
*
*/
public class MineZiXunBean {

    /**
     * list : [{"id":"20","uid":"2595","consult_type":"单位劳动争议","consult_content":"在于我们是否在你","reward_price":"0.01","consult_date":"2020-05-19 11:34:35","sum":"0","user_login":"15538996516","avatar":"/data/upload/default/head_default.png"},{"id":"15","uid":"2595","consult_type":"婚姻家事","consult_content":"这些问题我没有什么地方可能发生过这种生活中最痛苦莫过于没有安全感就是幸福了\u2026\u2026不过这也算是结束以后我一定好好学习的地方吗\u2026\u2026在线的人越来越多，是否还可以通过手机客户端下载安装在网络连接过程很简单\u2026\u2026不行你去看一下房子嘛、是因为他们没有安全感是我不懂事还是我没有理由","reward_price":"0.00","consult_date":"2020-05-18 17:45:54","sum":"0","user_login":"15538996516","avatar":"/data/upload/default/head_default.png"},{"id":"21","uid":"2595","consult_type":"单位劳动争议","consult_content":"2020-05-19 11:43:28","reward_price":"0.01","consult_date":"2000-00-00 00:00:00","sum":"0","user_login":"15538996516","avatar":"/data/upload/default/head_default.png"}]
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
         * id : 20
         * uid : 2595
         * consult_type : 单位劳动争议
         * consult_content : 在于我们是否在你
         * reward_price : 0.01
         * consult_date : 2020-05-19 11:34:35
         * sum : 0
         * user_login : 15538996516
         * avatar : /data/upload/default/head_default.png
         */

        private String id;
        private String uid;
        private String consult_type;
        private String consult_content;
        private String reward_price;
        private String consult_date;
        private String sum;
        private String user_login;
        private String avatar;
        private int type;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getConsult_type() {
            return consult_type;
        }

        public void setConsult_type(String consult_type) {
            this.consult_type = consult_type;
        }

        public String getConsult_content() {
            return consult_content;
        }

        public void setConsult_content(String consult_content) {
            this.consult_content = consult_content;
        }

        public String getReward_price() {
            return reward_price;
        }

        public void setReward_price(String reward_price) {
            this.reward_price = reward_price;
        }

        public String getConsult_date() {
            return consult_date;
        }

        public void setConsult_date(String consult_date) {
            this.consult_date = consult_date;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
