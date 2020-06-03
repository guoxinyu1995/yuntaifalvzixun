package com.ytfu.yuntaifawu.ui.mseeage.bean;

import java.util.List;

public class UserEvaluateClassBean {

    /**
     * status : 1
     * type : [{"id":4,"name":"最新","num":"492"},{"id":1,"name":"好评","num":"465"},{"id":2,"name":"中评","num":"9"},{"id":3,"name":"差评","num":"18"}]
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private List<TypeBean> type;

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

    public List<TypeBean> getType() {
        return type;
    }

    public void setType(List<TypeBean> type) {
        this.type = type;
    }

    public static class TypeBean {
        /**
         * id : 4
         * name : 最新
         * num : 492
         */

        private int id;
        private String name;
        private String num;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
