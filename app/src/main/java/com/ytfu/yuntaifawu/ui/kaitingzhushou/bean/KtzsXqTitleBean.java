package com.ytfu.yuntaifawu.ui.kaitingzhushou.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/23
*
*  @Des  开庭助手titlebean
*
*/
public class KtzsXqTitleBean {


    /**
     * status : 1
     * list : [{"id":"1","name":"一审阶段"},{"id":"32","name":"二审阶段"},{"id":"66","name":"再审阶段"}]
     * img : {"label":"交通事故","img":"https://yuntaifawu.com/themes/simplebootx_mobile/Public/ssz/images/jiaotongshigu@2x.png"}
     * type_id : 6
     * referer :
     * state : success
     */

    private int status;
    private ImgBean img;
    private String type_id;
    private String referer;
    private String state;
    private List<ListBean> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ImgBean getImg() {
        return img;
    }

    public void setImg(ImgBean img) {
        this.img = img;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
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

    public static class ImgBean {
        /**
         * label : 交通事故
         * img : https://yuntaifawu.com/themes/simplebootx_mobile/Public/ssz/images/jiaotongshigu@2x.png
         */

        private String label;
        private String img;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class ListBean {
        /**
         * id : 1
         * name : 一审阶段
         */

        private String id;
        private String name;

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
    }
}
