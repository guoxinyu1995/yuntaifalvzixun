package com.ytfu.yuntaifawu.ui.home.bean;

import java.util.List;

/**
 * @Auther gxy
 * @Date 2020/4/27
 * @Des 首页律师的实体类
 */
public class HomeLvShiBean {

    /**
     * status : 1
     * list : [{"id":"3","name":"马豹律师","jianjie":"           马豹律师，律师事务所创始合伙人，主任，司法部专职注册律师，全国律师协会专职会员，北京市律师协会会员。\n            自2009年从事法律工作至今。通过十年的法学理论研究和司法实践中，在房地产领域和劳动仲裁、婚姻家庭等领域取得了很高的造诣，具有高超的辩护技巧，犀利的文笔，很强的正义感和公益心，致力于基本人权的维护。从事律师行业10年，代理案件1000个，出庭800余次。业务方向是婚姻家庭、继承、房屋纠纷、民事侵权、行政诉讼和刑事辩护，因为觉得打硬仗挺刺激、过瘾。执业以来，马豹律师多次接受多家记者的采访。\n\n执业心态：干律师不要怕，怕就别干律师。\n\n最激情的时刻：在法庭上享受高手对决的感觉！","info_img":"admin/20200420/5e9d2d827b344.png","info_img_url":"http://yuntaifawu.com/data/upload/admin/20200420/5e9d2d827b344.png","title":"婚姻家事"},{"id":"6","name":"丁彩燕律师","jianjie":"              丁彩燕律师，律师事务所主任，中华全国律师协会会员，从事专职律师工作以处理合同纠纷为长，在劳动合同、交通事故、建筑施工、征地拆迁、房地产开发、公司运营等专业领域，对法律问题判断思维敏捷，见解独特，分析到位。\n              曾任职于企业管理、高等院校教学的经历，磨练出丁彩燕律师深厚、扎实的法学功底，在专职于本职工作的同时不断扩充学习，掌握了财会、机械制图、电子商务、工程造价、施工管理等多方面的知识，为承办复合型法律事务工作打下了良好的基础。\n             近年来，随着办案数量的增多，丁彩燕律师受邀参与了网络媒体的访谈栏目、发表文章；秉持正义如水的丁彩燕律师，以高度的责任感和严谨的工作作风对待每一起案件，至今已承办民商事案件千余起，涉及民事诉讼、行政诉讼、商事仲裁等程序，以专业的知识和技能切实维护当事人的正当合法权益，此外，丁彩燕律师还热心于公益事业、法援项目，参与民间机构的调解、化解社会矛盾，得到各界好评。 执业理念:真诚善待每一个人，脚踏实地做好每一件事。\n\n执业理念:真诚善待每一个人，脚踏实地做好每一件事","info_img":"admin/20200420/5e9d2e1c4fc58.png","info_img_url":"http://yuntaifawu.com/data/upload/admin/20200420/5e9d2e1c4fc58.png","title":"婚姻家事"},{"id":"9","name":"方鹏鹏律师","jianjie":"               主任律师  高级合伙人、债权债务、投资理财纠纷、婚事家庭、私募基金、金融证券、民商疑难诉讼、企业公司法律风险防控、企业公司劳动人事专家 \n              方鹏鹏，律师事务所资深主任律师，全国律师协会会员，北京市律师协会会员，毕业于河南警察政治学院，有近十年的法律工作经验，集民商、金融证券、私募基金、企业公司劳动人事、公司企业法律顾问实务于一身，具有深厚的法律理论功底和丰富的实践办案经验。\n             擅长处理民商疑难诉讼案件，企业公司法律风险防控、金融证券、私募基金、企业公司劳动人事等法律业务，秉承维护当事人最大利益的理念，得到客户的高度认可和一致好评。 \n            擅长领域：民商疑难诉讼、债权债务、投资理财纠纷、投资理财诈骗、企业公司法律风险防控、金融证券、私募基金。","info_img":"admin/20200421/5e9e623fdd287.png","info_img_url":"http://yuntaifawu.com/data/upload/admin/20200421/5e9e623fdd287.png","title":"婚姻家事"}]
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
         * id : 3
         * name : 马豹律师
         * jianjie :            马豹律师，律师事务所创始合伙人，主任，司法部专职注册律师，全国律师协会专职会员，北京市律师协会会员。
         * 自2009年从事法律工作至今。通过十年的法学理论研究和司法实践中，在房地产领域和劳动仲裁、婚姻家庭等领域取得了很高的造诣，具有高超的辩护技巧，犀利的文笔，很强的正义感和公益心，致力于基本人权的维护。从事律师行业10年，代理案件1000个，出庭800余次。业务方向是婚姻家庭、继承、房屋纠纷、民事侵权、行政诉讼和刑事辩护，因为觉得打硬仗挺刺激、过瘾。执业以来，马豹律师多次接受多家记者的采访。
         * <p>
         * 执业心态：干律师不要怕，怕就别干律师。
         * <p>
         * 最激情的时刻：在法庭上享受高手对决的感觉！
         * info_img : admin/20200420/5e9d2d827b344.png
         * info_img_url : http://yuntaifawu.com/data/upload/admin/20200420/5e9d2d827b344.png
         * title : 婚姻家事
         */

        private String id;
        private String name;
        private String jianjie;
        private String picurl;
        private String title;
        private String lid;

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getJianjie() {
            return jianjie;
        }

        public void setJianjie(String jianjie) {
            this.jianjie = jianjie;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
