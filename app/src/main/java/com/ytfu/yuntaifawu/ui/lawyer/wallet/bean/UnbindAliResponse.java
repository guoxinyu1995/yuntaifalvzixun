package com.ytfu.yuntaifawu.ui.lawyer.wallet.bean;

import java.util.List;

public class UnbindAliResponse {

    /**
     * code : 200
     * msg : 解绑成功
     * data : []
     */

    private int code;
    private String msg;
    private List<?> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
