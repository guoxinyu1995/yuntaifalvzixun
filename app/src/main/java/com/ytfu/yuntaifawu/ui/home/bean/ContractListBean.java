package com.ytfu.yuntaifawu.ui.home.bean;

import java.util.List;

public class ContractListBean {

    /**
     * list : [{"id":"14793","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg","title":"完成举证说明书（审理船舶碰撞案用） (2)","descript":"暂无描述","download_count":"1465","realprice":"29"},{"id":"14794","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg","title":"完成举证说明书（审理船舶碰撞案用）","descript":"暂无描述","download_count":"1893","realprice":"29"},{"id":"14829","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg","title":"委托代理_辩护协议（司法部2013版） (2)","descript":"暂无描述","download_count":"1523","realprice":"19"},{"id":"14830","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg","title":"委托代理_辩护协议（司法部2013版）","descript":"暂无描述","download_count":"1781","realprice":"19"},{"id":"14929","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg","title":"无重大诉讼事项的说明（发行企业债债券） (2)","descript":"暂无描述","download_count":"1481","realprice":"29"},{"id":"14930","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg","title":"无重大诉讼事项的说明（发行企业债债券） (3)","descript":"暂无描述","download_count":"1087","realprice":"29"}]
     * status : 1
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
         * id : 14793
         * img : https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg
         * title : 完成举证说明书（审理船舶碰撞案用） (2)
         * descript : 暂无描述
         * download_count : 1465
         * realprice : 29
         */

        private String id;
        private String img;
        private String title;
        private String descript;
        private String download_count;
        private String realprice;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public String getDownload_count() {
            return download_count;
        }

        public void setDownload_count(String download_count) {
            this.download_count = download_count;
        }

        public String getRealprice() {
            return realprice;
        }

        public void setRealprice(String realprice) {
            this.realprice = realprice;
        }
    }
}
