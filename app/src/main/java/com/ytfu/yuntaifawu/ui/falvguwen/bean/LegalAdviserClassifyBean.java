package com.ytfu.yuntaifawu.ui.falvguwen.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/22
*
*  @Des  法律顾问分类
*
*/
public class LegalAdviserClassifyBean {

    /**
     * list : [{"id":"2519","label":"离职文件","img":"https://yuntaifawu.com/data/upload/admin/20191122/5dd747e15a9e8.png","type":"0"},{"id":"2518","label":"在职文件","img":"https://yuntaifawu.com/data/upload/admin/20191122/5dd748167828b.png","type":"1"},{"id":"2517","label":"入职文件","img":"https://yuntaifawu.com/data/upload/admin/20191122/5dd7476cee351.png","type":"0"}]
     * status : 200
     * msg : 成功
     * referer :
     * state : success
     */

    private int status;
    private String msg;
    private String referer;
    private String state;
    private String sum;
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public static class ListBean {
        /**
         * id : 2519
         * label : 离职文件
         * img : https://yuntaifawu.com/data/upload/admin/20191122/5dd747e15a9e8.png
         * type : 0
         */

        private String id;
        private String label;
        private String img;
        private String type;
        private String count;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
