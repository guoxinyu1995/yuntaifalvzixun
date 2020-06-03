package com.ytfu.yuntaifawu.ui.falvguwen.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/23
*
*  @Des  法律顾问详情
*
*/
public class LegalAdviserDetailsBean {

    /**
     * bay_type : 1
     * find : {"title":"2020工 作 分 析 调 查 表","descript":"这份问卷是为了提供你目前岗位的有关信息而设计的，其目的不是衡量你的业绩和工作效率，它只是分析和描述岗位工作的一个工具。这份问卷主要涉及构成你工作的任务和活动以及其它的一些信息，问卷内容将不会个别发表，有关资料将由工作分析小组作汇总分析，并编制成《岗位说明书》","price":"9.9","cost":"19.0","order_count":"4188","img":"https://yuntaifawu.com/data/upload/admin/20191122/5dd74d60209fa.jpg","date":"2019-11-22 10:54:14"}
     * status : 200
     * msg : 成功
     * referer :
     * state : success
     */

    private int bay_type;
    private FindBean find;
    private int status;
    private String msg;
    private String referer;
    private String state;
    private int shoucang;

    public int getShoucang() {
        return shoucang;
    }

    public void setShoucang(int shoucang) {
        this.shoucang = shoucang;
    }

    public int getBay_type() {
        return bay_type;
    }

    public void setBay_type(int bay_type) {
        this.bay_type = bay_type;
    }

    public FindBean getFind() {
        return find;
    }

    public void setFind(FindBean find) {
        this.find = find;
    }

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

    public static class FindBean {
        /**
         * title : 2020工 作 分 析 调 查 表
         * descript : 这份问卷是为了提供你目前岗位的有关信息而设计的，其目的不是衡量你的业绩和工作效率，它只是分析和描述岗位工作的一个工具。这份问卷主要涉及构成你工作的任务和活动以及其它的一些信息，问卷内容将不会个别发表，有关资料将由工作分析小组作汇总分析，并编制成《岗位说明书》
         * price : 9.9
         * cost : 19.0
         * order_count : 4188
         * img : https://yuntaifawu.com/data/upload/admin/20191122/5dd74d60209fa.jpg
         * date : 2019-11-22 10:54:14
         */

        private String title;
        private String descript;
        private String price;
        private String cost;
        private String order_count;
        private String img;
        private String date;
        private String buy_img;
        private String doc_url;

        public String getBuy_img() {
            return buy_img;
        }

        public void setBuy_img(String buy_img) {
            this.buy_img = buy_img;
        }

        public String getDoc_url() {
            return doc_url;
        }

        public void setDoc_url(String doc_url) {
            this.doc_url = doc_url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getCost() {
            return cost;
        }

        public void setCost(String cost) {
            this.cost = cost;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
