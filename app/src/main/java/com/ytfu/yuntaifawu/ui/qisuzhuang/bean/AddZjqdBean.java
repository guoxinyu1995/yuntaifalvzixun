package com.ytfu.yuntaifawu.ui.qisuzhuang.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/18
*
*  @Des  添加证据清单
*
*/
public class AddZjqdBean {

    /**
     * list : [{"id":"1","zhengju":"43,44,45,","order_date":"2019-12-18 14:11:08","qsz_id":"202","uid":"2"},{"id":"2","zhengju":"43,44,45,","order_date":"2019-12-18 14:11:02","qsz_id":"202","uid":"2"}]
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
         * zhengju : 43,44,45,
         * order_date : 2019-12-18 14:11:08
         * qsz_id : 202
         * uid : 2
         */

        private String id;
        private String zhengju;
        private String order_date;
        private String qsz_id;
        private String uid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getZhengju() {
            return zhengju;
        }

        public void setZhengju(String zhengju) {
            this.zhengju = zhengju;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getQsz_id() {
            return qsz_id;
        }

        public void setQsz_id(String qsz_id) {
            this.qsz_id = qsz_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
