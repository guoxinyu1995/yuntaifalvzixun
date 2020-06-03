package com.ytfu.yuntaifawu.ui.lvshihetong.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/18
*
*  @Des  合同列表二级
*
*/
public class ContractListSecondBean {

    /**
     * list : [{"id":"2480","label":"金融保险证券期货合同"},{"id":"2478","label":"公用事业合同"},{"id":"2477","label":"矿产资源合同"},{"id":"2475","label":"体育赛事合同"},{"id":"2476","label":"农业、林业承包经营合同"},{"id":"2474","label":"旅游合同"},{"id":"2473","label":"软件、it、游戏开发运营合同"},{"id":"2472","label":"物流运输合同"},{"id":"2471","label":"演艺合同"},{"id":"2470","label":"房地产开发运营合同"},{"id":"2469","label":"影视剧融资、制作与发行合同"},{"id":"2468","label":"资本合作合同"},{"id":"2467","label":"广告营销、展览赞助合同"},{"id":"2466","label":"建设工程合同"},{"id":"2465","label":"不良资产处置合同"},{"id":"2464","label":"私募基金合同"},{"id":"2463","label":"特许经营（许可加盟）合同"},{"id":"2462","label":"合作合同"},{"id":"2461","label":"担保、抵押合同"},{"id":"2460","label":"知识产权合同"},{"id":"2459","label":"中介、居间推广、行纪合同"},{"id":"2458","label":"承包经营合同"},{"id":"2457","label":"租赁合同"},{"id":"2456","label":"赠与合同"},{"id":"2455","label":"承揽/劳务/服务合同"},{"id":"2454","label":"婚姻家庭/继承合同"},{"id":"2453","label":"民间借贷/借款合同"},{"id":"2452","label":"劳动用工合同"},{"id":"2451","label":"买卖转让合同"},{"id":"2501","label":"债权转让、转出合同"}]
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
         * id : 2480
         * label : 金融保险证券期货合同
         */

        private String id;
        private String label;

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
    }
}
