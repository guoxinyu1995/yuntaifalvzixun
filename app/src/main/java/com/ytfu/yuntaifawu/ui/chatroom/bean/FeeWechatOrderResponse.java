package com.ytfu.yuntaifawu.ui.chatroom.bean;

import com.google.gson.annotations.SerializedName;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;

/**
 * 咨询服务费获取微信订单响应
 */
public class FeeWechatOrderResponse {

    /**
     * orderid : 15906610106
     * status : 1
     * sign : {"appid":"wxfc703d618ab01bad","partnerid":"1546487171","prepayid":"wx2911054173158665e45d92291157807400","package":"Sign=WXPay","noncestr":"boMi23D6pdB4s5AC","timestamp":1590721541,"sign":"20C1AA27F94E608C4AF638D23532A38C"}
     */

    private String orderid;
    private int status;
    private WxPayBean.SignBean sign;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public WxPayBean.SignBean getSign() {
        return sign;
    }

    public void setSign(WxPayBean.SignBean sign) {
        this.sign = sign;
    }
}
