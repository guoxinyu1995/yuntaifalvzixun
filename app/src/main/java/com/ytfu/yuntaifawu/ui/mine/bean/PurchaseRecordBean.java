package com.ytfu.yuntaifawu.ui.mine.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/16
*
*  @Des  购买记录bean
*
*/
public class PurchaseRecordBean {

    /**
     * status : 1
     * list : [{"id":"43","type":"5","order_id":"1567752736","order_price":"999.0","an_id":"0","order_name":"公司版助手购买","o_date":"2019-09-06 14:53:11","user_id":"3","y_id":"0"},{"id":"47","type":"3","order_id":"1567754360","order_price":"16.0","an_id":"0","order_name":"个人版助手购买","o_date":"2019-09-06 15:19:44","user_id":"3","y_id":"0"},{"id":"56","type":"2","order_id":"1568699039","order_price":"0.0","an_id":"447","order_name":"4S店管理规章制度","o_date":"2019-09-17 13:43:59","user_id":"3","y_id":"0"},{"id":"63","type":"2","order_id":"1569736399","order_price":"0.0","an_id":"3470","order_name":"4-5个人借款管理制度 (2)","o_date":"2019-09-29 13:53:19","user_id":"3","y_id":"0"},{"id":"75","type":"1","order_id":"1570680348","order_price":"0.0","an_id":"183","order_name":"套牌车交通事故责任认定","o_date":"2019-10-10 12:05:48","user_id":"3","y_id":"0"},{"id":"80","type":"1","order_id":"2147483647","order_price":"45.0","an_id":"81","order_name":"自然人之间：进行非法活动、乘人之危、以非法手段形成的民间借贷纠纷","o_date":"2019-10-11 16:49:32","user_id":"3","y_id":"0"},{"id":"82","type":"2","order_id":"2147483647","order_price":"59.0","an_id":"5276","order_name":"变电所安装工程安全合同","o_date":"2019-10-11 16:57:24","user_id":"3","y_id":"0"},{"id":"98","type":"6","order_id":"1571370860","order_price":"990.0","an_id":"0","order_name":"购买vip","o_date":"2019-10-18 11:55:08","user_id":"3","y_id":"0"},{"id":"99","type":"6","order_id":"1571388740","order_price":"990.0","an_id":"0","order_name":"购买vip","o_date":"2019-10-18 16:52:28","user_id":"3","y_id":"0"}]
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
         * id : 43
         * type : 5
         * order_id : 1567752736
         * order_price : 999.0
         * an_id : 0
         * order_name : 公司版助手购买
         * o_date : 2019-09-06 14:53:11
         * user_id : 3
         * y_id : 0
         */

        private String id;
        private String type;
        private String order_id;
        private String order_price;
        private String an_id;
        private String order_name;
        private String o_date;
        private String user_id;
        private String y_id;
        private int type_id;

        public int getType_id() {
            return type_id;
        }

        public void setType_id(int type_id) {
            this.type_id = type_id;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_price() {
            return order_price;
        }

        public void setOrder_price(String order_price) {
            this.order_price = order_price;
        }

        public String getAn_id() {
            return an_id;
        }

        public void setAn_id(String an_id) {
            this.an_id = an_id;
        }

        public String getOrder_name() {
            return order_name;
        }

        public void setOrder_name(String order_name) {
            this.order_name = order_name;
        }

        public String getO_date() {
            return o_date;
        }

        public void setO_date(String o_date) {
            this.o_date = o_date;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getY_id() {
            return y_id;
        }

        public void setY_id(String y_id) {
            this.y_id = y_id;
        }
    }
}
