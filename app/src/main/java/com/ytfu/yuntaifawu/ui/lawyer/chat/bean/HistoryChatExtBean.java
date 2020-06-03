package com.ytfu.yuntaifawu.ui.lawyer.chat.bean;

import androidx.annotation.Keep;

@Keep
public class HistoryChatExtBean {


    /**
     *  "huashuid":"15910655635",    }
     *
     *
     * jiluid : 15906521994
     * lvshiid : 3
     * userid : 4286
     * price : 0.0001
     * type : 1
     */

    private String jiluid;
    private String lvshiid;
    private String userid;
    private String price;
    private int type;

    public String getJiluid() {
        return jiluid;
    }

    public void setJiluid(String jiluid) {
        this.jiluid = jiluid;
    }

    public String getLvshiid() {
        return lvshiid;
    }

    public void setLvshiid(String lvshiid) {
        this.lvshiid = lvshiid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
