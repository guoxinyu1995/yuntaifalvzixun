package com.ytfu.yuntaifawu.ui.qisuzhuang.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/17
*
*  @Des  起诉状历史bean
*
*/
public class QszHistoryBean {

    /**
     * status : 1
     * list : [{"id":"1","label":"婚姻家事"},{"id":"2","label":"劳动争议","list":[{"type":"2","beigao_name":"滴定","yuangao_name":"爱计算机三级","id":"77","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191204/1575458264.docx"},{"type":"2","beigao_name":"额呵呵","yuangao_name":"李四","id":"78","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191204/1575459137.docx"},{"type":"2","beigao_name":"阿德","yuangao_name":"啊啊啊","id":"145","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575880927.docx"},{"type":"2","beigao_name":"吧","yuangao_name":"back","id":"146","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575881226.docx"},{"type":"2","beigao_name":"得","yuangao_name":"吧","id":"148","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575882845.docx"},{"type":"2","beigao_name":"哦婆婆好","yuangao_name":"哦婆婆哦","id":"149","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575883024.docx"},{"type":"2","beigao_name":"八点","yuangao_name":"了","id":"150","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575883300.docx"},{"type":"2","beigao_name":"Rrrrr ","yuangao_name":"Ww ","id":"152","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575884653.docx"},{"type":"2","beigao_name":"鱼死网破后悔","yuangao_name":"艺欣","id":"153","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943384.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"154","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943796.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"155","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943808.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"156","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943810.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"157","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943813.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"158","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943814.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"159","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943824.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"160","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943831.docx"},{"type":"2","beigao_name":"Sd","yuangao_name":"Gq","id":"161","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575944001.docx"}]},{"id":"4","label":"民间借贷","list":[{"type":"4","beigao_name":"啊啊啊","yuangao_name":"啊啊啊","id":"147","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575881430.docx"},{"type":"4","beigao_name":"吧","yuangao_name":"洗洗","id":"151","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575883474.docx"}]},{"id":"6","label":"交通事故"},{"id":"7","label":"建设工程"},{"id":"8","label":"医疗纠纷"},{"id":"9","label":"知识产权"},{"id":"10","label":"房地产纠纷"}]
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
         * list : [{"type":"2","beigao_name":"滴定","yuangao_name":"爱计算机三级","id":"77","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191204/1575458264.docx"},{"type":"2","beigao_name":"额呵呵","yuangao_name":"李四","id":"78","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191204/1575459137.docx"},{"type":"2","beigao_name":"阿德","yuangao_name":"啊啊啊","id":"145","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575880927.docx"},{"type":"2","beigao_name":"吧","yuangao_name":"back","id":"146","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575881226.docx"},{"type":"2","beigao_name":"得","yuangao_name":"吧","id":"148","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575882845.docx"},{"type":"2","beigao_name":"哦婆婆好","yuangao_name":"哦婆婆哦","id":"149","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575883024.docx"},{"type":"2","beigao_name":"八点","yuangao_name":"了","id":"150","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575883300.docx"},{"type":"2","beigao_name":"Rrrrr ","yuangao_name":"Ww ","id":"152","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191209/1575884653.docx"},{"type":"2","beigao_name":"鱼死网破后悔","yuangao_name":"艺欣","id":"153","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943384.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"154","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943796.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"155","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943808.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"156","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943810.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"157","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943813.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"158","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943814.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"159","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943824.docx"},{"type":"2","beigao_name":"Gq ","yuangao_name":"Sd ","id":"160","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575943831.docx"},{"type":"2","beigao_name":"Sd","yuangao_name":"Gq","id":"161","url":"https://yuntaifawu.com/data/upload/qisuzhuang_word/20191210/1575944001.docx"}]
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
             * beigao_name : 滴定
             * yuangao_name : 爱计算机三级
             * id : 77
             * url : https://yuntaifawu.com/data/upload/qisuzhuang_word/20191204/1575458264.docx
             */

            private String type;
            private String beigao_name;
            private String yuangao_name;
            private String id;
            private String url;

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
