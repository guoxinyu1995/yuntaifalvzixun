package com.ytfu.yuntaifawu.ui.lvshiwode.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2020/5/28
*
*  @Des  律师认证信息查询bean
*
*/
public class LawyerInformationBean {

    /**
     * status : 1
     * info : {"id":"24","uid":"3","name":"李四","age":"18","edu":"大专","photo":"https://yuntaifawu.com/data/upload/shenhe/2020-05-21/15900424544170.png","jigou":"好久回来呀","address":"好久不想","lingyu":"个人劳动争议,单位劳动争议,民间借贷,公司股权,","jianjie":"不行不行你","zhiyezheng":"https://yuntaifawu.com/data/upload/shenhe/2020-05-21/15900424542242.png","card1":"https://yuntaifawu.com/data/upload/shenhe/2020-05-21/15900424545807.png","card2":"https://yuntaifawu.com/data/upload/shenhe/2020-05-21/15900424549172.png","status":"3","beian":"","liyou":"不符合要求"}
     * referer :
     * state : success
     */

    private int status;
    private InfoBean info;
    private String referer;
    private String state;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
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

    public static class InfoBean {
        /**
         * id : 24
         * uid : 3
         * name : 李四
         * age : 18
         * edu : 大专
         * photo : https://yuntaifawu.com/data/upload/shenhe/2020-05-21/15900424544170.png
         * jigou : 好久回来呀
         * address : 好久不想
         * lingyu : 个人劳动争议,单位劳动争议,民间借贷,公司股权,
         * jianjie : 不行不行你
         * zhiyezheng : https://yuntaifawu.com/data/upload/shenhe/2020-05-21/15900424542242.png
         * card1 : https://yuntaifawu.com/data/upload/shenhe/2020-05-21/15900424545807.png
         * card2 : https://yuntaifawu.com/data/upload/shenhe/2020-05-21/15900424549172.png
         * status : 3
         * beian :
         * liyou : 不符合要求
         */

        private String id;
        private String uid;
        private String name;
        private String age;
        private String edu;
        private String photo;
        private String jigou;
        private String address;
        private String lingyu;
        private String jianjie;
        private String zhiyezheng;
        private String card1;
        private String card2;
        private String status;
        private String beian;
        private String liyou;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getEdu() {
            return edu;
        }

        public void setEdu(String edu) {
            this.edu = edu;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getJigou() {
            return jigou;
        }

        public void setJigou(String jigou) {
            this.jigou = jigou;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLingyu() {
            return lingyu;
        }

        public void setLingyu(String lingyu) {
            this.lingyu = lingyu;
        }

        public String getJianjie() {
            return jianjie;
        }

        public void setJianjie(String jianjie) {
            this.jianjie = jianjie;
        }

        public String getZhiyezheng() {
            return zhiyezheng;
        }

        public void setZhiyezheng(String zhiyezheng) {
            this.zhiyezheng = zhiyezheng;
        }

        public String getCard1() {
            return card1;
        }

        public void setCard1(String card1) {
            this.card1 = card1;
        }

        public String getCard2() {
            return card2;
        }

        public void setCard2(String card2) {
            this.card2 = card2;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBeian() {
            return beian;
        }

        public void setBeian(String beian) {
            this.beian = beian;
        }

        public String getLiyou() {
            return liyou;
        }

        public void setLiyou(String liyou) {
            this.liyou = liyou;
        }
    }
}
