package com.ytfu.yuntaifawu.ui.kaitingzhushou.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/23
*
*  @Des  开庭助手列表bean
*
*/
public class KtzsListBean {

    /**
     * status : 1
     * list : [{"id":"1","label":"婚姻家事"},{"id":"2","label":"劳动争议","list":[{"type":"2","beigao_name":"李四","yuangao_name":"张三","id":"2","img_type":1,"jump_type":2}]},{"id":"4","label":"民间借贷"},{"id":"5","label":"合同纠纷"},{"id":"6","label":"交通事故","list":[{"type":"6","beigao_name":"离散,账务","yuangao_name":"周说,周无","id":"1","img_type":1,"jump_type":1}]},{"id":"7","label":"建设事故"},{"id":"8","label":"医疗纠纷"},{"id":"9","label":"知识产权"},{"id":"10","label":"房地产纠纷"},{"id":"11","label":"强制执行申请书"}]
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
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

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * id : 1
         * label : 婚姻家事
         * list : [{"type":"2","beigao_name":"李四","yuangao_name":"张三","id":"2","img_type":1,"jump_type":2}]
         */

        private String id;
        private String label;
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * type : 2
             * beigao_name : 李四
             * yuangao_name : 张三
             * id : 2
             * img_type : 1
             * jump_type : 2
             */

            private String type;
            private String beigao_name;
            private String yuangao_name;
            private String id;
            private int img_type;
            private int jump_type;
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
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

            public int getImg_type() {
                return img_type;
            }

            public void setImg_type(int img_type) {
                this.img_type = img_type;
            }

            public int getJump_type() {
                return jump_type;
            }

            public void setJump_type(int jump_type) {
                this.jump_type = jump_type;
            }
        }
    }
}
