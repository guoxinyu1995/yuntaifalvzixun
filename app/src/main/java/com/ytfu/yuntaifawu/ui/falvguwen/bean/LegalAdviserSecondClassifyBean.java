package com.ytfu.yuntaifawu.ui.falvguwen.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/22
*
*  @Des  法律顾问二级列表
*
*/
public class LegalAdviserSecondClassifyBean {

    /**
     * list : [{"id":"2520","label":"规章范本","img":"https://yuntaifawu.com/data/upload/admin/20191122/guizhangzhiduwei.png","nav_img":"https://yuntaifawu.com/data/upload/admin/20191122/guizhangzhidu.png"},{"id":"2521","label":"规章表单","img":"https://yuntaifawu.com/data/upload/admin/20191122/biaodanwei.png","nav_img":"https://yuntaifawu.com/data/upload/admin/20191122/biaodan.png"},{"id":"2522","label":"劳动报酬","img":"https://yuntaifawu.com/data/upload/admin/20191122/laodongjwei.png","nav_img":"https://yuntaifawu.com/data/upload/admin/20191122/laodongj.png"},{"id":"2523","label":"年休假","img":"https://yuntaifawu.com/data/upload/admin/20191122/nianxiujiawei.png","nav_img":"https://yuntaifawu.com/data/upload/admin/20191122/nianxiujia.png"},{"id":"2524","label":"日常管理","img":"https://yuntaifawu.com/data/upload/admin/20191122/richangguanliwei.png","nav_img":"https://yuntaifawu.com/data/upload/admin/20191122/richangguanli.png"},{"id":"2525","label":"其他协议","img":"https://yuntaifawu.com/data/upload/admin/20191122/qitawei.png","nav_img":"https://yuntaifawu.com/data/upload/admin/20191122/qita.png"}]
     * status : 200
     * msg : 成功
     * referer :
     * state : success
     */

    private int status;
    private String msg;
    private String referer;
    private String state;
    private List<ListBean> list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
         * id : 2520
         * label : 规章范本
         * img : https://yuntaifawu.com/data/upload/admin/20191122/guizhangzhiduwei.png
         * nav_img : https://yuntaifawu.com/data/upload/admin/20191122/guizhangzhidu.png
         */

        private String id;
        private String label;
        private String img;
        private String nav_img;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getNav_img() {
            return nav_img;
        }

        public void setNav_img(String nav_img) {
            this.nav_img = nav_img;
        }
    }
}
