package com.ytfu.yuntaifawu.base;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/12
*
*  @Des  公共基类
*
*/
public class ResultEntity<T> {
    private int status;
    private String referer;
    private String state;
    private int buy;
    private int shoucang;
    private List<T> list;

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getShoucang() {
        return shoucang;
    }

    public void setShoucang(int shoucang) {
        this.shoucang = shoucang;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
