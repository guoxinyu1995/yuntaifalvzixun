package com.ytfu.yuntaifawu.ui.mine.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/17
*
*  @Des  我的音频合同bean
*
*/
public class MyLibraryBean {

    /**
     * status : 1
     * list : [{"url":"default/20190909/5d75b3d310976.mp3","date":"2019-10-11 16:49:32","name":"自然人之间：进行非法活动、乘人之危、以非法手段形成的民间借贷纠纷","descript":"以非法手段形成的民间借贷纠纷，法院一般会视情况来定，这个协议有无效力。","yid":"81"},{"url":"default/20190929/5d905eb79d69e.mp3","date":"2019-10-10 12:05:48","name":"套牌车交通事故责任认定","descript":"自己的车被套牌，出现事故，什么情况下需要承担赔偿责任？","yid":"183"}]
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
         * url : default/20190909/5d75b3d310976.mp3
         * date : 2019-10-11 16:49:32
         * name : 自然人之间：进行非法活动、乘人之危、以非法手段形成的民间借贷纠纷
         * descript : 以非法手段形成的民间借贷纠纷，法院一般会视情况来定，这个协议有无效力。
         * yid : 81
         */

        private String url;
        private String date;
        private String name;
        private String descript;
        private String yid;

        String startTime;
        String endTime;
        boolean playStatus;
        long duration;

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public boolean isPlayStatus() {
            return playStatus;
        }

        public void setPlayStatus(boolean playStatus) {
            this.playStatus = playStatus;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public String getYid() {
            return yid;
        }

        public void setYid(String yid) {
            this.yid = yid;
        }
    }
}
