package com.ytfu.yuntaifawu.ui.lvshihetong.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/18
*
*  @Des  合同列表一级
*
*/
public class ContractListFirstBean {

    /**
     * list : [{"id":"2450","label":"开庭文书"},{"id":"2449","label":"民事法律"},{"id":"2448","label":"公司法律"}]
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
         * id : 2450
         * label : 开庭文书
         */

        private String id;
        private String label;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
