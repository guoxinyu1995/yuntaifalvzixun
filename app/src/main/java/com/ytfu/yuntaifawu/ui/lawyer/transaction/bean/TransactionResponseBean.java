package com.ytfu.yuntaifawu.ui.lawyer.transaction.bean;

import androidx.annotation.Keep;

import java.util.List;

@Keep
public class TransactionResponseBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : [{"id":"6","userid":"2","ordernumber":"TX_1590044228_2","type":"1","addtime":"2020-05-21 14:58:38","money":"104"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        /**
         * id : 13
         * userid : 3214
         * ordernumber : TX_1590566199_3214
         * type : 1
         * addtime : 2020-05-27 15:57:11
         * money : +0.1
         * status : 3
         * title : 提现 -失败
         */

        private String id;
        private String userid;
        private String ordernumber;
        private String type;
        private String addtime;
        private String money;
        private String status;
        private String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getOrdernumber() {
            return ordernumber;
        }

        public void setOrdernumber(String ordernumber) {
            this.ordernumber = ordernumber;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
