package com.ytfu.yuntaifawu.ui.kaitingzhushou.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/26
*
*  @Des  证据清单bean
*
*/
public class ZjqdBean {

    /**
     * list : [{"id":"4","evidence_name":"结婚证或婚姻登记机关的证明"},{"id":"5","evidence_name":"无登记结婚的，提供同居时间及举行婚礼时间的证明"},{"id":"6","evidence_name":"居委会或村委会出具的证明"},{"id":"7","evidence_name":"户口簿及身份证"},{"id":"8","evidence_name":"婚后感情证明"},{"id":"9","evidence_name":"曾提起过离婚诉讼的证据"},{"id":"10","evidence_name":"一方有过错证明"},{"id":"11","evidence_name":"法医鉴定"},{"id":"12","evidence_name":"证人"},{"id":"13","evidence_name":"提交居委会或村委会或公安机关出具的证明"},{"id":"14","evidence_name":"提交有关处罚决定或判决书"},{"id":"15","evidence_name":"子女姓名年龄，生活状况证明"},{"id":"16","evidence_name":"家庭财产清单及债务证明"},{"id":"18","evidence_name":"银行存单或或银行出具的借款合同及其他有价证券等"},{"id":"19","evidence_name":"提供出资证明、股权证明等"},{"id":"20","evidence_name":"住房情况证明"},{"id":"21","evidence_name":"双方身份证明"},{"id":"22","evidence_name":"离婚调解书、判决书或婚姻登记机关的证明"},{"id":"23","evidence_name":"工资收入状况和经济负担的证明"},{"id":"24","evidence_name":"有关居住证明"},{"id":"25","evidence_name":"提交子女本人愿跟随父或跟随母生活的相关证据"},{"id":"26","evidence_name":"无生育能力证据"},{"id":"27","evidence_name":"自己的受教育程度的证书"},{"id":"28","evidence_name":"子女医疗费用票据证明"},{"id":"29","evidence_name":"子女学习费用票据证明"},{"id":"30","evidence_name":"被继承人死亡证明或宣告死亡判决书"},{"id":"31","evidence_name":"被继承人婚姻、生育和抚养子女状况的证据"},{"id":"32","evidence_name":"继承人以外、依靠被继承人抚养的缺乏劳动能力又没有生活来源的提交居委会、村委会或者被继承人单位出具的证"},{"id":"33","evidence_name":"个人身份证明"},{"id":"34","evidence_name":"被继承人遗嘱、录音、代书"},{"id":"35","evidence_name":"户口簿、身份证、居委会和村委会的证明"},{"id":"36","evidence_name":"被赡养人的身体、经济、居住等情况的证明"},{"id":"37","evidence_name":"子女对被赡养人的赡养情况的证明"},{"id":"38","evidence_name":"子女各自的收入、居住等情况的证明"}]
     * status : 200
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
         * id : 4
         * evidence_name : 结婚证或婚姻登记机关的证明
         */

        private String id;
        private String evidence_name;
        private boolean is_check = false;

        public String getId() {
            return id;
        }

        public boolean isIs_check() {
            return is_check;
        }

        public void setIs_check(boolean is_check) {
            this.is_check = is_check;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEvidence_name() {
            return evidence_name;
        }

        public void setEvidence_name(String evidence_name) {
            this.evidence_name = evidence_name;
        }
    }
}
