package com.ytfu.yuntaifawu.ui.lvshiwenti.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/26
*
*  @Des  问题详情bean
*
*/
public class WenTiXiangQingBean {

    /**
     * xiaoxi_arr : [{"avatar":"https://yuntaifawu.com/data/upload/avatar/2019-12-19//1576722636822.jpg"}]
     * content : {"id":"34","uid":"4286","consult_type":"买房纠纷","consult_content":"小产权","reward_price":"0.00","consult_date":"2020-05-25 14:09:06","sum":"1","avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEI60tRpDHO4LTaKGdMWEbmGSgj8jJvXo92ZShpnJquRichv3VQI1PZuGWuGAGO6gOIQRD6wEddUK8g/132","user_login":"Lee","shuzu":["https://yuntaifawu.com/data/upload/avatar/2019-12-19//1576722636822.jpg"],"count":"1"}
     * msg : 成功
     * status : 200
     * referer :
     * state : success
     */

    private ContentBean content;
    private String msg;
    private int status;
    private String referer;
    private String state;
    private List<XiaoxiArrBean> xiaoxi_arr;

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
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

    public List<XiaoxiArrBean> getXiaoxi_arr() {
        return xiaoxi_arr;
    }

    public void setXiaoxi_arr(List<XiaoxiArrBean> xiaoxi_arr) {
        this.xiaoxi_arr = xiaoxi_arr;
    }

    public static class ContentBean {
        /**
         * id : 34
         * uid : 4286
         * consult_type : 买房纠纷
         * consult_content : 小产权
         * reward_price : 0.00
         * consult_date : 2020-05-25 14:09:06
         * sum : 1
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEI60tRpDHO4LTaKGdMWEbmGSgj8jJvXo92ZShpnJquRichv3VQI1PZuGWuGAGO6gOIQRD6wEddUK8g/132
         * user_login : Lee
         * shuzu : ["https://yuntaifawu.com/data/upload/avatar/2019-12-19//1576722636822.jpg"]
         * count : 1
         */

        private String id;
        private String uid;
        private String consult_type;
        private String consult_content;
        private String reward_price;
        private String consult_date;
        private String sum;
        private String avatar;
        private String user_login;
        private int count;
        private List<String> shuzu;

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

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<String> getShuzu() {
            return shuzu;
        }

        public void setShuzu(List<String> shuzu) {
            this.shuzu = shuzu;
        }
    }

    public static class XiaoxiArrBean {
        /**
         * avatar : https://yuntaifawu.com/data/upload/avatar/2019-12-19//1576722636822.jpg
         */

        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
