package com.ytfu.yuntaifawu.ui.consult.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2020/5/19
*
*  @Des  悬赏发布支付宝bean
*
*/
public class XuanshangFaBuAliBean {

    /**
     * status : 1
     * sign : alipay_sdk=alipay-sdk-php-easyalipay-20190926&app_id=2019101068237834&biz_content=%7B%22body%22%3A%22%5Cu54a8%5Cu8be2%5Cu60ac%5Cu8d4f%22%2C%22subject%22%3A%22%5Cu54a8%5Cu8be2%5Cu60ac%5Cu8d4f%22%2C%22out_trade_no%22%3A%22P14P81589882785P0.01P1011P1PPP33%22%2C%22timeout_express%22%3A%221m%22%2C%22total_amount%22%3A%220.01%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22shop_id%22%3A%22%22%2C%22type%22%3A%2214%22%2C%22uid%22%3A%221011%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Fyuntaifawu.com%2Fapi%2Fpay%2FaliPayBack&sign_type=RSA2&timestamp=2020-05-19+18%3A06%3A25&version=1.0&sign=Guvssgmu3xVrR%2BCQonPgt%2FAoNr5ud0u5EFCGDwHlTBZSYUjGKNJtAK3f80RM82IowvQVo722Q1gj5DIRKKopvfxw9hi%2B7lK3IoOz5TJiExhBJFfxHFGnDLrdpteVaE90Xru3n0KgtJU%2FcDizj8uofw3rGsfaMgnbyZnuGvmxupOyMC5KRR4wjwNdl%2BHY6%2FeFqd9LQqH3eiUk49MicWOySBtnZHgrrFK%2BhJaJr6VSui7tAkLOK6xIZkfcGe0VPHSAzcJOT6jsVfmu14XoDWizyLVEwumyM76WuVZUka46woqH%2F8hfW1fw4O2y2TeZXKb4fxbqK1Q1dQJL%2BQni0T1oXw%3D%3D
     * referer :
     * state : success
     */

    private int status;
    private String sign;
    private String referer;
    private String state;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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
}
