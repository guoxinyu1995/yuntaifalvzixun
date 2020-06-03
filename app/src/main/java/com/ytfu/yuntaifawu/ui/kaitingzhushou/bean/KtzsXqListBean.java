package com.ytfu.yuntaifawu.ui.kaitingzhushou.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/23
*
*  @Des  开庭助手详情列表bean
*
*/
public class KtzsXqListBean {

    /**
     * status : 1
     * list : [{"id":"2","name":"立案阶段","type":"1","msg":"","son":[{"id":"3","name":"起诉状","type":"3","msg":""},{"id":"4","name":"身份证复印件","type":"1","msg":""},{"id":"5","name":"证据清单","type":"2","msg":""},{"id":"6","name":"被告身份证信息","type":"1","msg":""},{"id":"7","name":"个人直系近亲属立案的委托授权书","type":"4","msg":"当事人本人立案无需提供"},{"id":"8","name":"企业需另外提供","type":"2","msg":""}]},{"id":"9","name":"调查取证阶段","type":"1","msg":"","son":[{"id":"10","name":"调查取证申请书","type":"3","msg":""},{"id":"11","name":"梳理证据并制作证据清单","type":"2","msg":""}]},{"id":"12","name":"财产保全阶段包含诉前保全","type":"1","msg":"","son":[{"id":"13","name":"准备保全申请书","type":"3","msg":""},{"id":"14","name":"向法院提交保全申请书","type":"1","msg":""}]},{"id":"15","name":"开庭阶段","type":"1","msg":"","son":[{"id":"16","name":"宣布法官到庭","type":"1","msg":""},{"id":"17","name":"法官来核对当事人的身份","type":"1","msg":""},{"id":"18","name":"宣布开庭","type":"1","msg":""},{"id":"19","name":"告知当事人权利和义务","type":"1","msg":""},{"id":"20","name":"被告答辩，（答辩状）","type":"3","msg":""},{"id":"21","name":"举证质证","type":"1","msg":""},{"id":"22","name":"陈述辩论意见","type":"1","msg":""},{"id":"23","name":"陈述意见以及补充","type":"1","msg":""}]},{"id":"24","name":"庭后阶段","type":"1","msg":"","son":[{"id":"25","name":"领取裁判文书","type":"1","msg":""},{"id":"26","name":"开具生效","type":"5","msg":"后期申请强制执行时使用"}]},{"id":"27","name":"强制执行阶段","type":"1","msg":"","son":[{"id":"28","name":"书写强制执行申请书","type":"3","msg":""},{"id":"29","name":"提交限制高消费申请书","type":"3","msg":""},{"id":"30","name":"失信申请书","type":"3","msg":""},{"id":"31","name":"领取执行通知书","type":"1","msg":""}]}]
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
         * id : 2
         * name : 立案阶段
         * type : 1
         * msg :
         * son : [{"id":"3","name":"起诉状","type":"3","msg":""},{"id":"4","name":"身份证复印件","type":"1","msg":""},{"id":"5","name":"证据清单","type":"2","msg":""},{"id":"6","name":"被告身份证信息","type":"1","msg":""},{"id":"7","name":"个人直系近亲属立案的委托授权书","type":"4","msg":"当事人本人立案无需提供"},{"id":"8","name":"企业需另外提供","type":"2","msg":""}]
         */

        private String id;
        private String name;
        private String type;
        private String msg;
        private List<SonBean> son;

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

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<SonBean> getSon() {
            return son;
        }

        public void setSon(List<SonBean> son) {
            this.son = son;
        }

        public static class SonBean {
            /**
             * id : 3
             * name : 起诉状
             * type : 3
             * msg :
             */

            private String id;
            private String name;
            private String type;
            private String msg;
            private String type_id;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getType_id() {
                return type_id;
            }

            public void setType_id(String type_id) {
                this.type_id = type_id;
            }

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

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }
        }
    }
}
