package com.ytfu.yuntaifawu.ui.qisuzhuang.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/18
*
*  @Des  证据清单详情bean
*
*/
public class ZjqdXqBean {

    /**
     * find : {"id":"2","zhengju":"43,44,45,","order_date":"2019-12-18 14:11:02","qsz_id":"202","uid":"2","zhengju_weiyi":"","zhengjulist":[{"evidence_name":"结婚证、户口薄、村乡或街道社居委证明及单位证明","prove_fact":"证明原告与死者或伤者之间的亲属关系及是否为实际被抚养、赡养和抚养关系"},{"evidence_name":"劳动合同","prove_fact":"证明原告从事的职业和收入情况"},{"evidence_name":"工资流水","prove_fact":"证明原告进城务工的事实，应按城镇户口计算伤残赔偿金"}]}
     * status : 200
     * referer :
     * state : success
     */

    private FindBean find;
    private int status;
    private String referer;
    private String state;

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
         * id : 2
         * zhengju : 43,44,45,
         * order_date : 2019-12-18 14:11:02
         * qsz_id : 202
         * uid : 2
         * zhengju_weiyi :
         * zhengjulist : [{"evidence_name":"结婚证、户口薄、村乡或街道社居委证明及单位证明","prove_fact":"证明原告与死者或伤者之间的亲属关系及是否为实际被抚养、赡养和抚养关系"},{"evidence_name":"劳动合同","prove_fact":"证明原告从事的职业和收入情况"},{"evidence_name":"工资流水","prove_fact":"证明原告进城务工的事实，应按城镇户口计算伤残赔偿金"}]
         */

        private String id;
        private String zhengju;
        private String order_date;
        private String qsz_id;
        private String uid;
        private String zhengju_weiyi;
        private List<ZhengjulistBean> zhengjulist;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getZhengju() {
            return zhengju;
        }

        public void setZhengju(String zhengju) {
            this.zhengju = zhengju;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getQsz_id() {
            return qsz_id;
        }

        public void setQsz_id(String qsz_id) {
            this.qsz_id = qsz_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getZhengju_weiyi() {
            return zhengju_weiyi;
        }

        public void setZhengju_weiyi(String zhengju_weiyi) {
            this.zhengju_weiyi = zhengju_weiyi;
        }

        public List<ZhengjulistBean> getZhengjulist() {
            return zhengjulist;
        }

        public void setZhengjulist(List<ZhengjulistBean> zhengjulist) {
            this.zhengjulist = zhengjulist;
        }

        public static class ZhengjulistBean {
            /**
             * evidence_name : 结婚证、户口薄、村乡或街道社居委证明及单位证明
             * prove_fact : 证明原告与死者或伤者之间的亲属关系及是否为实际被抚养、赡养和抚养关系
             */

            private String evidence_name;
            private String prove_fact;

            public String getEvidence_name() {
                return evidence_name;
            }

            public void setEvidence_name(String evidence_name) {
                this.evidence_name = evidence_name;
            }

            public String getProve_fact() {
                return prove_fact;
            }

            public void setProve_fact(String prove_fact) {
                this.prove_fact = prove_fact;
            }
        }
    }
}
