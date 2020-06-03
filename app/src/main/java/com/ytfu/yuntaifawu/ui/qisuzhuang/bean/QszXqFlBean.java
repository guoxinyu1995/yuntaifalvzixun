package com.ytfu.yuntaifawu.ui.qisuzhuang.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/17
*
*  @Des  起诉状详情分类bean
*
*/
public class QszXqFlBean {

    /**
     * list : [{"name":"起诉状","img":"https://yuntaifawu.com/data/upload/audio_nav/qisuzhuang.png","type":1,"id":"77"},{"name":"代理词","img":"https://yuntaifawu.com/data/upload/audio_nav/dailici.png","buy":1,"type":2,"id":"77"},{"name":"证据清单","img":"https://yuntaifawu.com/data/upload/audio_nav/zhengjuqingdan.png","buy":0,"type":3,"id":"77"}]
     * status : 200
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private String zong_count;
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

    public String getZong_count() {
        return zong_count;
    }

    public void setZong_count(String zong_count) {
        this.zong_count = zong_count;
    }

    public static class ListBean {
        /**
         * name : 起诉状
         * img : https://yuntaifawu.com/data/upload/audio_nav/qisuzhuang.png
         * type : 1
         * id : 77
         * buy : 1
         */

        private String name;
        private String img;
        private int type;
        private String id;
        private int buy;
        private String count;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
