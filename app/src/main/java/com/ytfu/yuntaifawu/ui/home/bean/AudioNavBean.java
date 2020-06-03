package com.ytfu.yuntaifawu.ui.home.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  音频分类bean
*
*/
public class AudioNavBean {


    /**
     * list : [{"id":"2390","label":"婚姻家庭","nav_img":"https://yuntaifawu.com/data/upload/audio_nav/hunyinjiating.png"},{"id":"2391","label":"民间借贷","nav_img":"https://yuntaifawu.com/data/upload/audio_nav/minjianjiedai.png"},{"id":"2392","label":"劳动争议","nav_img":"https://yuntaifawu.com/data/upload/audio_nav/laodongzhengyi.png"},{"id":"2393","label":"公司股权","nav_img":"https://yuntaifawu.com/data/upload/audio_nav/gongsiguquan.png"},{"id":"2502","label":"交通事故","nav_img":"https://yuntaifawu.com/data/upload/audio_nav/jiaotongshigu.png"}]
     * status : 1
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private List<ListBean> list;
    private String zongcount;

    public String getZongcount() {
        return zongcount;
    }

    public void setZongcount(String zongcount) {
        this.zongcount = zongcount;
    }

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
         * id : 2390
         * label : 婚姻家庭
         * nav_img : https://yuntaifawu.com/data/upload/audio_nav/hunyinjiating.png
         */

        private String id;
        private String label;
        private String nav_img;
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

        public String getNav_img() {
            return nav_img;
        }

        public void setNav_img(String nav_img) {
            this.nav_img = nav_img;
        }
    }
}
