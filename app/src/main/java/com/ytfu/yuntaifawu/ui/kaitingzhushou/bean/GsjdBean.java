package com.ytfu.yuntaifawu.ui.kaitingzhushou.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/27
*
*  @Des  工伤鉴定bean
*
*/
public class GsjdBean {

    /**
     * list : [{"id":"1","name":"工伤认定","list":[{"id":"3","name":"工商认定申请表","neirong":null,"type":"2","url":null,"parent":"1"},{"id":"4","name":"与用人单位存在劳动关系的证据","neirong":"劳动合同,仲裁院或者法院的裁决书,判决书等证明","type":"3","url":null,"parent":"1"},{"id":"5","name":"身份证复印件","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"6","name":"医院诊断证明","neirong":"有的医院只给开一次","type":"4","url":null,"parent":"1"},{"id":"7","name":"病例","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"8","name":"出院证明","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"9","name":"CT","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"10","name":"职业病诊断证明书","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"11","name":"就诊病例","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"12","name":"两个以上的证人证言","neirong":null,"type":"2","url":null,"parent":"1"}]},{"id":"2","name":"工伤级别鉴定","list":[{"id":"13","name":"工伤证","neirong":null,"type":"1","url":null,"parent":"2"},{"id":"14","name":"工伤认定决定书","neirong":null,"type":"2","url":null,"parent":"2"},{"id":"15","name":"身份证复印件","neirong":null,"type":"1","url":null,"parent":"2"},{"id":"16","name":"医院诊断证明","neirong":"有的医院只给开一次","type":"4","url":null,"parent":"2"},{"id":"17","name":"病例","neirong":null,"type":"1","url":null,"parent":"2"},{"id":"18","name":"出院证明","neirong":null,"type":"1","url":null,"parent":"2"},{"id":"19","name":"CT","neirong":null,"type":"1","url":null,"parent":"2"},{"id":"20","name":"职工伤残劳动鉴定审批表","neirong":"到当地工伤科领取","type":"3","url":null,"parent":"2"}]}]
     * status : 200
     * referer :
     * state : success
     * msg :
     */

    private int status;
    private String referer;
    private String state;
    private String msg;
    private List<ListBeanX> list;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * id : 1
         * name : 工伤认定
         * list : [{"id":"3","name":"工商认定申请表","neirong":null,"type":"2","url":null,"parent":"1"},{"id":"4","name":"与用人单位存在劳动关系的证据","neirong":"劳动合同,仲裁院或者法院的裁决书,判决书等证明","type":"3","url":null,"parent":"1"},{"id":"5","name":"身份证复印件","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"6","name":"医院诊断证明","neirong":"有的医院只给开一次","type":"4","url":null,"parent":"1"},{"id":"7","name":"病例","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"8","name":"出院证明","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"9","name":"CT","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"10","name":"职业病诊断证明书","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"11","name":"就诊病例","neirong":null,"type":"1","url":null,"parent":"1"},{"id":"12","name":"两个以上的证人证言","neirong":null,"type":"2","url":null,"parent":"1"}]
         */

        private String id;
        private String name;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 3
             * name : 工商认定申请表
             * neirong : null
             * type : 2
             * url : null
             * parent : 1
             */

            private String id;
            private String name;
            private String neirong;
            private String type;
            private String url;
            private String parent;

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

            public String getNeirong() {
                return neirong;
            }

            public void setNeirong(String neirong) {
                this.neirong = neirong;
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

            public String getParent() {
                return parent;
            }

            public void setParent(String parent) {
                this.parent = parent;
            }
        }
    }
}
