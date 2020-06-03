package com.ytfu.yuntaifawu.ui.pay;

import com.google.gson.annotations.SerializedName;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/30
*
*  @Des  微信支付bean
*
*/
public class WxPayBean {

    /**
     * status : 1
     * sign : {"appid":"wxfc703d618ab01bad","partnerid":"1546487171","prepayid":"wx31095851833220d2535e94ae1403070800","package":"Sign=WXPay","noncestr":"zBLBnxpA4BVOZDha","timestamp":1577757531,"sign":"341B2A19AAA684114F8DF7A4BDCFA9C2"}
     */

    private int status;
    private SignBean sign;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public SignBean getSign() {
        return sign;
    }

    public void setSign(SignBean sign) {
        this.sign = sign;
    }

    public static class SignBean {
        /**
         * appid : wxfc703d618ab01bad
         * partnerid : 1546487171
         * prepayid : wx31095851833220d2535e94ae1403070800
         * package : Sign=WXPay
         * noncestr : zBLBnxpA4BVOZDha
         * timestamp : 1577757531
         * sign : 341B2A19AAA684114F8DF7A4BDCFA9C2
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        @SerializedName("package")
        private String packageX;
        private String noncestr;
        private int timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
