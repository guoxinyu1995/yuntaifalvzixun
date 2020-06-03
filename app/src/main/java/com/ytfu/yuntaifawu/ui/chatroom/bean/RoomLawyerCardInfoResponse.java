package com.ytfu.yuntaifawu.ui.chatroom.bean;

import java.util.List;

public class RoomLawyerCardInfoResponse {
    /**
     * data : {"id":"12","name":"耿晨旭律师","lingyu":"婚姻家庭纠纷、离婚诉讼、房地产纠纷、刑事辩护、民间借贷纠纷、股权纠纷、交通事故纠纷、财产损害赔偿纠纷、劳动纠纷","picurl":"https://yuntaifawu.com/data/upload/admin/20200421/5e9e5f90745cf.png","addtime":"2020-04-24 00:00:00","lid":"ls08","mobile":"13520416387","wxid":"ldzc12333","huashu":"          您好，我是耿律师，平台推荐资深精选律师，多年从事法律诉讼专业，现为律师事务所高级合伙人，成功处理过大量诉讼案件，法律分析全面精准，诉讼策略高效专业，我需要详细了解案件情况，然后为您提供解决方案。","jianjie":"           律师事务所主任律师，全国律师协会会员、北京市律师协会会员、律师事务所高级合伙律师、律师事务所主任律师；从事律师职业以来始终以客户利益至上为最高宗旨， 秉承\u201c受人之托，忠人之事\u201d的服务理念，践行社会赋予律师的使命，确实维护了众多当事人的合法权益，取得了一系列出色的成绩，赢得了广大当事人的充分信赖及肯定。           坚信邪不胜正，坚持有理走遍天下、无理寸步难行的真理，不畏惧各种权势！  加强律师事务所的党建工作，党建工作服务于国家发展的大局，引导律师和律师事务所的健康发展。团结和带领广大律师坚持正确的思想路线和发展道路，做中国特色社会主义法制的建设者和捍卫者。","cyear":"27","belong":"","xueli":"本科","lvshitype":"资深律师","type":"0","sex":"1","info_img":"admin/20200421/5e9e60bd9a26b.png","price":"200","orderlist":"1","status":"1","card":null,"shenhe_name":null,"shenhe_age":null,"shenhe_edu":null,"shenhe_photo":null,"shenhe_jigou":null,"shenhe_address":null,"shenhe_lingyu":null,"shenhe_jianjie":null,"shenhe_zhiyezheng":null,"shenhe_card1":null,"shenhe_card2":null,"shenhe_beian":null,"anli_content":[{"id":"42","lid":"ls08","anli_date":"2015-02-19 00:00:00","cid":"7","content":"马XX、张XX与XXX农村房屋买卖合同纠纷一审民事判决书 "},{"id":"43","lid":"ls08","anli_date":"2009-06-16 00:00:00","cid":"3","content":"安徽XX建筑劳务有限公司等建设工程分包合同纠纷二审民事判决书 "},{"id":"44","lid":"ls08","anli_date":"2014-04-15 00:00:00","cid":"3","content":"姬某与某省人民政府行政复议一审行政判决书 "},{"id":"45","lid":"ls08","anli_date":"2016-11-18 00:00:00","cid":"3","content":"某县某家庭农场与某县某镇人民政府、某县综合行政执法局一审行政判决书 "}],"picurl1":"https://yuntaifawu.com/public/images/avatar.png"}
     * code : 200
     * status : success
     * referer :
     * state : success
     */

    private LawyerCardInfo data;
    private int code;
    private String status;
    private String referer;
    private String state;

    public LawyerCardInfo getData() {
        return data;
    }

    public void setData(LawyerCardInfo data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public static class LawyerCardInfo {
        /**
         * id : 12
         * name : 耿晨旭律师
         * lingyu : 婚姻家庭纠纷、离婚诉讼、房地产纠纷、刑事辩护、民间借贷纠纷、股权纠纷、交通事故纠纷、财产损害赔偿纠纷、劳动纠纷
         * picurl : https://yuntaifawu.com/data/upload/admin/20200421/5e9e5f90745cf.png
         * addtime : 2020-04-24 00:00:00
         * lid : ls08
         * mobile : 13520416387
         * wxid : ldzc12333
         * huashu :           您好，我是耿律师，平台推荐资深精选律师，多年从事法律诉讼专业，现为律师事务所高级合伙人，成功处理过大量诉讼案件，法律分析全面精准，诉讼策略高效专业，我需要详细了解案件情况，然后为您提供解决方案。
         * jianjie :            律师事务所主任律师，全国律师协会会员、北京市律师协会会员、律师事务所高级合伙律师、律师事务所主任律师；从事律师职业以来始终以客户利益至上为最高宗旨， 秉承“受人之托，忠人之事”的服务理念，践行社会赋予律师的使命，确实维护了众多当事人的合法权益，取得了一系列出色的成绩，赢得了广大当事人的充分信赖及肯定。           坚信邪不胜正，坚持有理走遍天下、无理寸步难行的真理，不畏惧各种权势！  加强律师事务所的党建工作，党建工作服务于国家发展的大局，引导律师和律师事务所的健康发展。团结和带领广大律师坚持正确的思想路线和发展道路，做中国特色社会主义法制的建设者和捍卫者。
         * cyear : 27
         * belong :
         * xueli : 本科
         * lvshitype : 资深律师
         * type : 0
         * sex : 1
         * info_img : admin/20200421/5e9e60bd9a26b.png
         * price : 200
         * orderlist : 1
         * status : 1
         * card : null
         * shenhe_name : null
         * shenhe_age : null
         * shenhe_edu : null
         * shenhe_photo : null
         * shenhe_jigou : null
         * shenhe_address : null
         * shenhe_lingyu : null
         * shenhe_jianjie : null
         * shenhe_zhiyezheng : null
         * shenhe_card1 : null
         * shenhe_card2 : null
         * shenhe_beian : null
         * anli_content : [{"id":"42","lid":"ls08","anli_date":"2015-02-19 00:00:00","cid":"7","content":"马XX、张XX与XXX农村房屋买卖合同纠纷一审民事判决书 "},{"id":"43","lid":"ls08","anli_date":"2009-06-16 00:00:00","cid":"3","content":"安徽XX建筑劳务有限公司等建设工程分包合同纠纷二审民事判决书 "},{"id":"44","lid":"ls08","anli_date":"2014-04-15 00:00:00","cid":"3","content":"姬某与某省人民政府行政复议一审行政判决书 "},{"id":"45","lid":"ls08","anli_date":"2016-11-18 00:00:00","cid":"3","content":"某县某家庭农场与某县某镇人民政府、某县综合行政执法局一审行政判决书 "}]
         * picurl1 : https://yuntaifawu.com/public/images/avatar.png
         */

        private String id;
        private String name;
        private String lingyu;
        private String picurl;
        private String addtime;
        private String lid;
        private String mobile;
        private String wxid;
        private String huashu;
        private String jianjie;
        private String cyear;
        private String belong;
        private String xueli;
        private String lvshitype;
        private String type;
        private String sex;
        private String info_img;
        private String price;
        private String orderlist;
        private String status;
        private Object card;
        private Object shenhe_name;
        private Object shenhe_age;
        private Object shenhe_edu;
        private Object shenhe_photo;
        private Object shenhe_jigou;
        private Object shenhe_address;
        private Object shenhe_lingyu;
        private Object shenhe_jianjie;
        private Object shenhe_zhiyezheng;
        private Object shenhe_card1;
        private Object shenhe_card2;
        private Object shenhe_beian;
        private String picurl1;
        private List<AnliContentBean> anli_content;

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

        public String getLingyu() {
            return lingyu;
        }

        public void setLingyu(String lingyu) {
            this.lingyu = lingyu;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getWxid() {
            return wxid;
        }

        public void setWxid(String wxid) {
            this.wxid = wxid;
        }

        public String getHuashu() {
            return huashu;
        }

        public void setHuashu(String huashu) {
            this.huashu = huashu;
        }

        public String getJianjie() {
            return jianjie;
        }

        public void setJianjie(String jianjie) {
            this.jianjie = jianjie;
        }

        public String getCyear() {
            return cyear;
        }

        public void setCyear(String cyear) {
            this.cyear = cyear;
        }

        public String getBelong() {
            return belong;
        }

        public void setBelong(String belong) {
            this.belong = belong;
        }

        public String getXueli() {
            return xueli;
        }

        public void setXueli(String xueli) {
            this.xueli = xueli;
        }

        public String getLvshitype() {
            return lvshitype;
        }

        public void setLvshitype(String lvshitype) {
            this.lvshitype = lvshitype;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getInfo_img() {
            return info_img;
        }

        public void setInfo_img(String info_img) {
            this.info_img = info_img;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getOrderlist() {
            return orderlist;
        }

        public void setOrderlist(String orderlist) {
            this.orderlist = orderlist;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Object getCard() {
            return card;
        }

        public void setCard(Object card) {
            this.card = card;
        }

        public Object getShenhe_name() {
            return shenhe_name;
        }

        public void setShenhe_name(Object shenhe_name) {
            this.shenhe_name = shenhe_name;
        }

        public Object getShenhe_age() {
            return shenhe_age;
        }

        public void setShenhe_age(Object shenhe_age) {
            this.shenhe_age = shenhe_age;
        }

        public Object getShenhe_edu() {
            return shenhe_edu;
        }

        public void setShenhe_edu(Object shenhe_edu) {
            this.shenhe_edu = shenhe_edu;
        }

        public Object getShenhe_photo() {
            return shenhe_photo;
        }

        public void setShenhe_photo(Object shenhe_photo) {
            this.shenhe_photo = shenhe_photo;
        }

        public Object getShenhe_jigou() {
            return shenhe_jigou;
        }

        public void setShenhe_jigou(Object shenhe_jigou) {
            this.shenhe_jigou = shenhe_jigou;
        }

        public Object getShenhe_address() {
            return shenhe_address;
        }

        public void setShenhe_address(Object shenhe_address) {
            this.shenhe_address = shenhe_address;
        }

        public Object getShenhe_lingyu() {
            return shenhe_lingyu;
        }

        public void setShenhe_lingyu(Object shenhe_lingyu) {
            this.shenhe_lingyu = shenhe_lingyu;
        }

        public Object getShenhe_jianjie() {
            return shenhe_jianjie;
        }

        public void setShenhe_jianjie(Object shenhe_jianjie) {
            this.shenhe_jianjie = shenhe_jianjie;
        }

        public Object getShenhe_zhiyezheng() {
            return shenhe_zhiyezheng;
        }

        public void setShenhe_zhiyezheng(Object shenhe_zhiyezheng) {
            this.shenhe_zhiyezheng = shenhe_zhiyezheng;
        }

        public Object getShenhe_card1() {
            return shenhe_card1;
        }

        public void setShenhe_card1(Object shenhe_card1) {
            this.shenhe_card1 = shenhe_card1;
        }

        public Object getShenhe_card2() {
            return shenhe_card2;
        }

        public void setShenhe_card2(Object shenhe_card2) {
            this.shenhe_card2 = shenhe_card2;
        }

        public Object getShenhe_beian() {
            return shenhe_beian;
        }

        public void setShenhe_beian(Object shenhe_beian) {
            this.shenhe_beian = shenhe_beian;
        }

        public String getPicurl1() {
            return picurl1;
        }

        public void setPicurl1(String picurl1) {
            this.picurl1 = picurl1;
        }

        public List<AnliContentBean> getAnli_content() {
            return anli_content;
        }

        public void setAnli_content(List<AnliContentBean> anli_content) {
            this.anli_content = anli_content;
        }

        public static class AnliContentBean {
            /**
             * id : 42
             * lid : ls08
             * anli_date : 2015-02-19 00:00:00
             * cid : 7
             * content : 马XX、张XX与XXX农村房屋买卖合同纠纷一审民事判决书 
             */

            private String id;
            private String lid;
            private String anli_date;
            private String cid;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLid() {
                return lid;
            }

            public void setLid(String lid) {
                this.lid = lid;
            }

            public String getAnli_date() {
                return anli_date;
            }

            public void setAnli_date(String anli_date) {
                this.anli_date = anli_date;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }


    //    /**
//     * data : {"id":"36","name":"李五律师","lingyu":"婚姻家事, \t\t个人劳动争议, \t\t单位劳动争议","picurl":"https://yuntaifawu.com/data/upload/shenhe/2020-05-28/15906578156996.jpg","addtime":"2020-05-28 17:26:38","lid":"3","mobile":null,"wxid":null,"huashu":null,"jianjie":"个人简介","cyear":"30","belong":"百子湾","xueli":"本科","lvshitype":null,"type":null,"sex":null,"info_img":"shenhe/2020-05-28/15906578156996.jpg","price":null,"orderlist":"0","status":"0","card":null,"shenhe_name":"李五","shenhe_age":"30","shenhe_edu":"本科","shenhe_photo":"2020-05-28/15906578156996.jpg","shenhe_jigou":"广渠","shenhe_address":"百子湾","shenhe_lingyu":"婚姻家事, \t\t个人劳动争议, \t\t单位劳动争议","shenhe_jianjie":"个人简介","shenhe_zhiyezheng":"2020-05-28/15906579875815.jpg","shenhe_card1":"2020-05-28/15906559565632.jpg","shenhe_card2":"2020-05-28/15906559567077.jpg","shenhe_beian":"2020-05-28/1590655956292.jpg","picurl1":"https://yuntaifawu.com/public/images/avatar.png"}
//     * code : 200
//     * status : success
//     * referer :
//     * state : success
//     */
//
//    private LawyerCardInfo data;
//    private int code;
//    private String status;
//    private String referer;
//    private String state;
//
//    public LawyerCardInfo getData() {
//        return data;
//    }
//
//    public void setData(LawyerCardInfo data) {
//        this.data = data;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getReferer() {
//        return referer;
//    }
//
//    public void setReferer(String referer) {
//        this.referer = referer;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public static class LawyerCardInfo {
//
//     }
}
