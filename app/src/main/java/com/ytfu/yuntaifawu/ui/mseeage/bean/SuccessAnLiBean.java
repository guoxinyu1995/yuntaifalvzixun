package com.ytfu.yuntaifawu.ui.mseeage.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2020/4/20
*
*  @Des  成功案例bean
*
*/
public class SuccessAnLiBean {

    /**
     * code : 200
     * shuju : [{"id":"2","lid":"ls01","anli_date":"2019-02-13 00:00:00","cid":"婚姻家事","content":"事件内容3事件内容3事件内容3事件内容3事件内容3事件内容3"},{"id":"4","lid":"ls01","anli_date":"2019-02-13 00:00:00","cid":"个人劳动争议","content":"事件内容2事件内容2事件内容2事件内容2事件内容2事件内容2事件内容2"}]
     * category : [{"id":1,"name":"婚姻家事"},{"id":2,"name":"个人劳动争议"},{"id":3,"name":"单位劳动争议"},{"id":4,"name":"交通事故"},{"id":5,"name":"民间借贷"},{"id":6,"name":"公司股权"}]
     * status : success
     * referer :
     * state : success
     */

    private int code;
    private String status;
    private String referer;
    private String state;
    private List<ShujuBean> shuju;
    private List<CategoryBean> category;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public List<ShujuBean> getShuju() {
        return shuju;
    }

    public void setShuju(List<ShujuBean> shuju) {
        this.shuju = shuju;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public static class ShujuBean {
        /**
         * id : 2
         * lid : ls01
         * anli_date : 2019-02-13 00:00:00
         * cid : 婚姻家事
         * content : 事件内容3事件内容3事件内容3事件内容3事件内容3事件内容3
         */

        private String id;
        private String lid;
        private String anli_date;
        private String cid;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getAnli_date() {
            return anli_date;
        }

        public void setAnli_date(String anli_date) {
            this.anli_date = anli_date;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class CategoryBean {
        /**
         * id : 1
         * name : 婚姻家事
         */

        private int id;
        private String name;

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
    }
}
