package com.ytfu.yuntaifawu.ui.consult.bean;

import com.google.gson.annotations.SerializedName;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/19
*
*  @Des  悬赏发布微信bean
*
*/
public class XuanshangFaBuWatchBean {

    /**
     * status : 1
     * sign : {"appid":"wxfc703d618ab01bad","partnerid":"1546487171","prepayid":"wx19180416963589ad5fecf4151471458400","package":"Sign=WXPay","noncestr":"46UUiSviRxvQhJM8","timestamp":1589882657,"sign":"86C412602A69EC7B7E1FB0911D5BD4FD"}
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
         * prepayid : wx19180416963589ad5fecf4151471458400
         * package : Sign=WXPay
         * noncestr : 46UUiSviRxvQhJM8
         * timestamp : 1589882657
         * sign : 86C412602A69EC7B7E1FB0911D5BD4FD
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
