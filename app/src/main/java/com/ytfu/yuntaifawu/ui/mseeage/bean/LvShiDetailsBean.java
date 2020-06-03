package com.ytfu.yuntaifawu.ui.mseeage.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2020/4/16
*
*  @Des  律师详情bean
*
*/
public class LvShiDetailsBean {

    /**
     * data : {"id":"3","name":"马律师","lingyu":"婚姻，婚姻家庭","picurl":"https://yuntaifawu.com/public/images/avatar.png","addtime":"2020-04-16 ","lid":"ls01","mobile":"13466581622","wxid":"13466581622","huashu":"您好，我是北京广渠律师事务所的马律师，有什么可以帮助您的？具体的案情您可以简要的陈述一下？","jianjie":"2004年律师执业至今，凭借着深厚的法学功底和多年的执业经验，办理了大量婚姻家庭案件。对离婚案件中各类房产纠纷、大额财产分割、公司股权处理、子女抚养权的争取、涉外离婚的办理等尤为专长。作为资深婚姻家庭律师，王红律师对公司法、合同法、证券法、房地产法律都有深入的研究，具备丰富的司法实践经验和办案技巧，成功的代理多起离婚案件，极大的维护了当事人的财产权益并在孩子抚养权争取方面赢得了巨大的优势。","cyear":"33","belong":"北京市","xueli":"研究生12","lvshitype":"资深律师12","type":null,"sex":"1","anli_content":[{"id":"2","lid":"ls01","anli_date":"2019-02-13 00:00:00","cid":"婚姻家事","content":"事件内容3事件内容3事件内容3事件内容3事件内容3事件内容3"},{"id":"4","lid":"ls01","anli_date":"2019-02-13 00:00:00","cid":"个人劳动争议","content":"事件内容2事件内容2事件内容2事件内容2事件内容2事件内容2事件内容2"}],"answernum":"3","pinglunnum":"2","pinglun":[{"cid":"1","id":"3","name":"我觉得张律师做的不错","lvshi":"ls01","talk":null,"nickname":"浪里白条","pingjia":"2","addtime":"2020-04-16","yhaddtime":"2020-04-16"},{"cid":"1","id":"5","name":"我觉得这关系打的非常好","lvshi":"ls01","talk":null,"nickname":"浪里白条","pingjia":"3","addtime":"2020-04-16","yhaddtime":"2020-04-16"}],"answser":[{"id":"2","lid":"ls01","cid":"个人劳动争议","uname":"我是一个小学生","content":"我是一个小学生","sum":"10","date":"2020-04-16 00:00:00"},{"id":"5","lid":"ls01","cid":"个人劳动争议","uname":"小学生1","content":"我是一个中学生","sum":"9","date":"2019-02-12 00:00:00"}]}
     * code : 200
     * status : success
     * referer :
     * state : success
     */

    private DataBean data;
    private int code;
    private String status;
    private String referer;
    private String state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * id : 3
         * name : 马律师
         * lingyu : 婚姻，婚姻家庭
         * picurl : https://yuntaifawu.com/public/images/avatar.png
         * addtime : 2020-04-16
         * lid : ls01
         * mobile : 13466581622
         * wxid : 13466581622
         * huashu : 您好，我是北京广渠律师事务所的马律师，有什么可以帮助您的？具体的案情您可以简要的陈述一下？
         * jianjie : 2004年律师执业至今，凭借着深厚的法学功底和多年的执业经验，办理了大量婚姻家庭案件。对离婚案件中各类房产纠纷、大额财产分割、公司股权处理、子女抚养权的争取、涉外离婚的办理等尤为专长。作为资深婚姻家庭律师，王红律师对公司法、合同法、证券法、房地产法律都有深入的研究，具备丰富的司法实践经验和办案技巧，成功的代理多起离婚案件，极大的维护了当事人的财产权益并在孩子抚养权争取方面赢得了巨大的优势。
         * cyear : 33
         * belong : 北京市
         * xueli : 研究生12
         * lvshitype : 资深律师12
         * type : null
         * sex : 1
         * anli_content : [{"id":"2","lid":"ls01","anli_date":"2019-02-13 00:00:00","cid":"婚姻家事","content":"事件内容3事件内容3事件内容3事件内容3事件内容3事件内容3"},{"id":"4","lid":"ls01","anli_date":"2019-02-13 00:00:00","cid":"个人劳动争议","content":"事件内容2事件内容2事件内容2事件内容2事件内容2事件内容2事件内容2"}]
         * answernum : 3
         * pinglunnum : 2
         * pinglun : [{"cid":"1","id":"3","name":"我觉得张律师做的不错","lvshi":"ls01","talk":null,"nickname":"浪里白条","pingjia":"2","addtime":"2020-04-16","yhaddtime":"2020-04-16"},{"cid":"1","id":"5","name":"我觉得这关系打的非常好","lvshi":"ls01","talk":null,"nickname":"浪里白条","pingjia":"3","addtime":"2020-04-16","yhaddtime":"2020-04-16"}]
         * answser : [{"id":"2","lid":"ls01","cid":"个人劳动争议","uname":"我是一个小学生","content":"我是一个小学生","sum":"10","date":"2020-04-16 00:00:00"},{"id":"5","lid":"ls01","cid":"个人劳动争议","uname":"小学生1","content":"我是一个中学生","sum":"9","date":"2019-02-12 00:00:00"}]
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
        private Object type;
        private String sex;
        private String answernum;
        private String pinglunnum;
        private String info_img;
        private List<AnliContentBean> anli_content;
        private List<PinglunBean> pinglun;
        private List<AnswserBean> answser;

        public String getInfo_img() {
            return info_img;
        }

        public void setInfo_img(String info_img) {
            this.info_img = info_img;
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

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAnswernum() {
            return answernum;
        }

        public void setAnswernum(String answernum) {
            this.answernum = answernum;
        }

        public String getPinglunnum() {
            return pinglunnum;
        }

        public void setPinglunnum(String pinglunnum) {
            this.pinglunnum = pinglunnum;
        }

        public List<AnliContentBean> getAnli_content() {
            return anli_content;
        }

        public void setAnli_content(List<AnliContentBean> anli_content) {
            this.anli_content = anli_content;
        }

        public List<PinglunBean> getPinglun() {
            return pinglun;
        }

        public void setPinglun(List<PinglunBean> pinglun) {
            this.pinglun = pinglun;
        }

        public List<AnswserBean> getAnswser() {
            return answser;
        }

        public void setAnswser(List<AnswserBean> answser) {
            this.answser = answser;
        }

        public static class AnliContentBean {
            /**
             * id : 2
             * lid : ls01
             * anli_date : 2019-02-13 00:00:00
             * cid : 婚姻家事
             * content : 事件内容3事件内容3事件内容3事件内容3事件内容3事件内容3
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

        public static class PinglunBean {
            /**
             * cid : 1
             * id : 3
             * name : 我觉得张律师做的不错
             * lvshi : ls01
             * talk : null
             * nickname : 浪里白条
             * pingjia : 2
             * addtime : 2020-04-16
             * yhaddtime : 2020-04-16
             */

            private String cid;
            private String id;
            private String name;
            private String lvshi;
            private Object talk;
            private String nickname;
            private String pingjia;
            private String addtime;
            private String yhaddtime;

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
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

            public String getLvshi() {
                return lvshi;
            }

            public void setLvshi(String lvshi) {
                this.lvshi = lvshi;
            }

            public Object getTalk() {
                return talk;
            }

            public void setTalk(Object talk) {
                this.talk = talk;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPingjia() {
                return pingjia;
            }

            public void setPingjia(String pingjia) {
                this.pingjia = pingjia;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getYhaddtime() {
                return yhaddtime;
            }

            public void setYhaddtime(String yhaddtime) {
                this.yhaddtime = yhaddtime;
            }
        }

        public static class AnswserBean {
            /**
             * id : 2
             * lid : ls01
             * cid : 个人劳动争议
             * uname : 我是一个小学生
             * content : 我是一个小学生
             * sum : 10
             * date : 2020-04-16 00:00:00
             */

            private String id;
            private String lid;
            private String cid;
            private String uname;
            private String content;
            private String sum;
            private String date;

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

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSum() {
                return sum;
            }

            public void setSum(String sum) {
                this.sum = sum;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}
