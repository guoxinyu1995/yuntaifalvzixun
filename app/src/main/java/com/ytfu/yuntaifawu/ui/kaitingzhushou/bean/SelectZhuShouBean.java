package com.ytfu.yuntaifawu.ui.kaitingzhushou.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/12/17
*
*  @Des  选择开庭助手
*
*/
public class SelectZhuShouBean {

    /**
     * status : 200
     * list : [{"type":"2","beigao_name":"Sd","yuangao_name":"Gq","id":"161","label":"劳动争议"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"160","label":"劳动争议"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"159","label":"劳动争议"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"158","label":"劳动争议"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"157","label":"劳动争议"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"156","label":"劳动争议"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"155","label":"劳动争议"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"154","label":"劳动争议"},{"type":"2","beigao_name":"鱼死网破后悔","yuangao_name":"艺欣","id":"153","label":"劳动争议"},{"type":"2","beigao_name":"Rrrrr ","yuangao_name":"Ww ","id":"152","label":"劳动争议"},{"type":"4","beigao_name":"吧","yuangao_name":"洗洗","id":"151","label":"民间借贷"},{"type":"2","beigao_name":"八点","yuangao_name":"了","id":"150","label":"劳动争议"},{"type":"2","beigao_name":"哦婆婆好","yuangao_name":"哦婆婆哦","id":"149","label":"劳动争议"},{"type":"2","beigao_name":"得","yuangao_name":"吧","id":"148","label":"劳动争议"},{"type":"4","beigao_name":"啊啊啊","yuangao_name":"啊啊啊","id":"147","label":"民间借贷"},{"type":"2","beigao_name":"吧","yuangao_name":"back","id":"146","label":"劳动争议"},{"type":"2","beigao_name":"阿德","yuangao_name":"啊啊啊","id":"145","label":"劳动争议"},{"type":"2","beigao_name":"额呵呵","yuangao_name":"李四","id":"78","label":"劳动争议"},{"type":"2","beigao_name":"滴定","yuangao_name":"爱计算机三级","id":"77","label":"劳动争议"}]
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
         * type : 2
         * beigao_name : Sd
         * yuangao_name : Gq
         * id : 161
         * label : 劳动争议
         */

        private String type;
        private String beigao_name;
        private String yuangao_name;
        private String id;
        private String label;
        private boolean is_check = false;

        public boolean isIs_check() {
            return is_check;
        }

        public void setIs_check(boolean is_check) {
            this.is_check = is_check;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getBeigao_name() {
            return beigao_name;
        }

        public void setBeigao_name(String beigao_name) {
            this.beigao_name = beigao_name;
        }

        public String getYuangao_name() {
            return yuangao_name;
        }

        public void setYuangao_name(String yuangao_name) {
            this.yuangao_name = yuangao_name;
        }

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
