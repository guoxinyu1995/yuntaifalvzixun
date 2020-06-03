package com.ytfu.yuntaifawu.ui.kaitingzhushou.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/27
*
*  @Des  企业另需提供bean
*
*/
public class QylxtgBean {

    /**
     * list : [{"id":"6","name":"提交伤残鉴定申请书","type":"3","url":null,"com_type":"2","neirong":"附带伤残鉴定申请说明,知道当事人使用"},{"id":"7","name":"县级医院的诊断证明","type":"1","url":null,"com_type":"2","neirong":null},{"id":"8","name":"伤情检查结果报告","type":"1","url":null,"com_type":"2","neirong":null},{"id":"9","name":"损失初期和治疗终期的CT","type":"1","url":null,"com_type":"2","neirong":null},{"id":"10","name":"X光片以及诊断报告","type":"1","url":null,"com_type":"2","neirong":null},{"id":"11","name":"医院档案室复印加盖公章的手术病例以及检查记录","type":"1","url":null,"com_type":"2","neirong":null},{"id":"12","name":"保留伤残评定发票","type":"1","url":null,"com_type":"2","neirong":null}]
     * status : 200
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private String msg;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 6
         * name : 提交伤残鉴定申请书
         * type : 3
         * url : null
         * com_type : 2
         * neirong : 附带伤残鉴定申请说明,知道当事人使用
         */

        private String id;
        private String name;
        private String type;
        private String url;
        private String com_type;
        private String neirong;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCom_type() {
            return com_type;
        }

        public void setCom_type(String com_type) {
            this.com_type = com_type;
        }

        public String getNeirong() {
            return neirong;
        }

        public void setNeirong(String neirong) {
            this.neirong = neirong;
        }
    }
}
