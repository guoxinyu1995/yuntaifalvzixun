package com.ytfu.yuntaifawu.ui.mine.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/21
*
*  @Des  擅长领域bean
*
*/
public class ShangChangLingYuBean {

    /**
     * status : 1
     * list : ["婚姻家事","个人劳动争议","单位劳动争议","交通事故","民间借贷","公司股权","买房纠纷","物业纠纷","产品质量","电商纠纷","刑事犯罪"]
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private List<String> list;

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

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
