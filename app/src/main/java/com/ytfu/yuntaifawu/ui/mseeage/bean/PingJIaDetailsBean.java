package com.ytfu.yuntaifawu.ui.mseeage.bean;

import java.util.List;

public class PingJIaDetailsBean {

    /**
     * cid : 3
     * id : 1
     * name : 问题回答的挺好，可惜还是解决不了我的困扰。哎问题回答的挺好，可惜还是解决不了我的困扰。哎。
     * lvshi : ls01
     * talk : null
     * nickname : yt9678
     * pingjia : 2
     * addtime : null
     * yhaddtime : 2019-10-03
     * talkdetail : [{"id":"8","yhname":"请问我这个案子打的咋样？？","tid":"1","yhaddtime":"2020-02-02","addtime":"2020-04-21","lvshitime":"2020-04-21","type":"1","picurl":"https://yuntaifawu.com/data/upload/admin/20200421/5e9e5fa17cf68.png","nickname":"马律师"},{"id":"9","yhname":"非常好 律师完美的解决我的问题","tid":"1","yhaddtime":"2020-02-03","addtime":"2020-04-21","lvshitime":"2020-04-21","type":"2","picurl":"https://yuntaifawu.com/public/images/avatar.png","nickname":"yt9678"},{"id":"11","yhname":"感谢您以后有案子可以来找我","tid":"1","yhaddtime":"2020-02-04","addtime":"2020-04-21","lvshitime":"2020-04-21","type":"1","picurl":"https://yuntaifawu.com/data/upload/admin/20200421/5e9e5fa17cf68.png","nickname":"马律师"},{"id":"12","yhname":"感谢您张律师","tid":"1","yhaddtime":"2020-02-07","addtime":"2020-04-21","lvshitime":"2020-04-21","type":"2","picurl":"https://yuntaifawu.com/public/images/avatar.png","nickname":"yt9678"}]
     * code : 200
     * count : 4
     * status : success
     * referer :
     * state : success
     */

    private String cid;
    private String id;
    private String name;
    private String lvshi;
    private String talk;
    private String nickname;
    private String pingjia;
    private String addtime;
    private String yhaddtime;
    private int code;
    private String count;
    private String status;
    private String referer;
    private String state;
    private List<TalkdetailBean> talkdetail;

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

    public void setTalk(String talk) {
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

    public Object getAddtime() {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
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

    public List<TalkdetailBean> getTalkdetail() {
        return talkdetail;
    }

    public void setTalkdetail(List<TalkdetailBean> talkdetail) {
        this.talkdetail = talkdetail;
    }

    public static class TalkdetailBean {
        /**
         * id : 8
         * yhname : 请问我这个案子打的咋样？？
         * tid : 1
         * yhaddtime : 2020-02-02
         * addtime : 2020-04-21
         * lvshitime : 2020-04-21
         * type : 1
         * picurl : https://yuntaifawu.com/data/upload/admin/20200421/5e9e5fa17cf68.png
         * nickname : 马律师
         */

        private String id;
        private String yhname;
        private String tid;
        private String yhaddtime;
        private String addtime;
        private String lvshitime;
        private String type;
        private String picurl;
        private String nickname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getYhname() {
            return yhname;
        }

        public void setYhname(String yhname) {
            this.yhname = yhname;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getYhaddtime() {
            return yhaddtime;
        }

        public void setYhaddtime(String yhaddtime) {
            this.yhaddtime = yhaddtime;
        }

        public String getAddtime() {
            return addtime;
        }

        public void setAddtime(String addtime) {
            this.addtime = addtime;
        }

        public String getLvshitime() {
            return lvshitime;
        }

        public void setLvshitime(String lvshitime) {
            this.lvshitime = lvshitime;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
