package com.ytfu.yuntaifawu.ui.lvshiwenti.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/25
*
*  @Des 律师端 问题列表
*
*/
public class LvShiZiXunListBean {

    /**
     * list : [{"id":"34","uid":"4286","consult_type":"买房纠纷","consult_content":"小产权","reward_price":"0.00","consult_date":"2020-05-25 14:09:06","sum":"0","user_login":"Lee","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEI60tRpDHO4LTaKGdMWEbmGSgj8jJvXo92ZShpnJquRichv3VQI1PZuGWuGAGO6gOIQRD6wEddUK8g/132"},{"id":"33","uid":"832","consult_type":"婚姻家事","consult_content":"北京广渠律师事务所，专业代理劳动仲裁案件，不成功不收费，打赢官司再收费，地址：北京市朝阳区东四环广渠路金海商富中心A座1705、1706，电话:13520416387，马豹\n路线: 地铁7号线百子湾站A2口出，西行200米，遇到第一个红绿灯右转即到。","reward_price":"0.00","consult_date":"2020-05-25 13:06:20","sum":"0","user_login":"默认用户名","avatar":"https://yuntaifawu.com/data/upload/avatar/2020-01-11//1578710221558.png"},{"id":"32","uid":"832","consult_type":"婚姻家事","consult_content":"我们是后期收费，打赢官司才收费，让您放心打官司，您具体案情是什么？","reward_price":"0.00","consult_date":"2020-05-22 18:10:41","sum":"0","user_login":"默认用户名","avatar":"https://yuntaifawu.com/data/upload/avatar/2020-01-11//1578710221558.png"},{"id":"31","uid":"832","consult_type":"婚姻家事","consult_content":"北京广渠律师事务所，专业代理劳动仲裁案件，不成功不收费，打赢官司再收费，地址：北京市朝阳区东四环广渠路金海商富中心A座1705、1706，电话:13520416387，马豹\n路线: 地铁7号线百子湾站A2口出，西行200米，遇到第一个红绿灯右转即到。","reward_price":"0.00","consult_date":"2020-05-22 18:07:07","sum":"0","user_login":"默认用户名","avatar":"https://yuntaifawu.com/data/upload/avatar/2020-01-11//1578710221558.png"},{"id":"30","uid":"2","consult_type":"个人劳动争议","consult_content":"测试","reward_price":"0.00","consult_date":"2020-05-22 18:07:03","sum":"0","user_login":"郭先生，","avatar":"https://yuntaifawu.com/data/upload/avatar/2019-12-19//1576722636822.jpg"},{"id":"29","uid":"2","consult_type":"婚姻家事","consult_content":"好好用电脑上微信朋友圈看看自己是不是太","reward_price":"0.00","consult_date":"2020-05-22 18:02:32","sum":"0","user_login":"郭先生，","avatar":"https://yuntaifawu.com/data/upload/avatar/2019-12-19//1576722636822.jpg"},{"id":"28","uid":"1011","consult_type":"个人劳动争议","consult_content":"公司不给缴纳公积金","reward_price":"0.00","consult_date":"2020-05-20 15:46:26","sum":"0","user_login":"走走D丢L","avatar":"https://yuntaifawu.com/data/upload/avatar/2020-05-21//15900555461027.jpg"},{"id":"27","uid":"1011","consult_type":"电商纠纷","consult_content":"商品质量问题","reward_price":"0.01","consult_date":"2020-05-20 11:48:17","sum":"0","user_login":"走走D丢L","avatar":"https://yuntaifawu.com/data/upload/avatar/2020-05-21//15900555461027.jpg"},{"id":"26","uid":"1011","consult_type":"电商纠纷","consult_content":"商品质量问题","reward_price":"0.01","consult_date":"2020-05-20 11:47:55","sum":"0","user_login":"走走D丢L","avatar":"https://yuntaifawu.com/data/upload/avatar/2020-05-21//15900555461027.jpg"},{"id":"25","uid":"1011","consult_type":"刑事犯罪","consult_content":"故意杀人","reward_price":"0.01","consult_date":"2020-05-20 11:45:18","sum":"0","user_login":"走走D丢L","avatar":"https://yuntaifawu.com/data/upload/avatar/2020-05-21//15900555461027.jpg"},{"id":"24","uid":"3","consult_type":"婚姻家事","consult_content":"我","reward_price":"0.00","consult_date":"2020-05-19 17:56:00","sum":"0","user_login":"测试账号","avatar":"https://yuntaifawu.com/data/upload/avatar/2020-05-20//15899688578603.jpg"},{"id":"23","uid":"1011","consult_type":"物业纠纷","consult_content":"家里单元门坏了，物业不给修了，还打人。","reward_price":"0.00","consult_date":"2020-05-19 17:52:39","sum":"0","user_login":"走走D丢L","avatar":"https://yuntaifawu.com/data/upload/avatar/2020-05-21//15900555461027.jpg"}]
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
         * id : 34
         * uid : 4286
         * consult_type : 买房纠纷
         * consult_content : 小产权
         * reward_price : 0.00
         * consult_date : 2020-05-25 14:09:06
         * sum : 0
         * user_login : Lee
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEI60tRpDHO4LTaKGdMWEbmGSgj8jJvXo92ZShpnJquRichv3VQI1PZuGWuGAGO6gOIQRD6wEddUK8g/132
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
