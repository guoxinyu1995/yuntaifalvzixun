package com.ytfu.yuntaifawu.ui.pay.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/12/2
*
*  @Des  支付bean
*
*/
public class PayBean {

    /**
     * status : 1
     * sign : alipay_sdk=alipay-sdk-php-easyalipay-20190926&amp;app_id=2019101068237834&amp;biz_content=%7B%22body%22%3A%22%5Cu79bb%5Cu5a5a%5Cu65f6%5Cu623f%5Cu5c4b%5Cu7684%5Cu5904%5Cu7406%5Cu95ee%5Cu9898%5Cuff08%5Cu4e00%5Cuff09%5Cu5a5a%5Cu540e%5Cu7236%5Cu6bcd%5Cu51fa%5Cu8d44%5Cu4ea7%5Cu6743%5Cu5f52%5Cu8c01%22%2C%22subject%22%3A%22%5Cu79bb%5Cu5a5a%5Cu65f6%5Cu623f%5Cu5c4b%5Cu7684%5Cu5904%5Cu7406%5Cu95ee%5Cu9898%5Cuff08%5Cu4e00%5Cuff09%5Cu5a5a%5Cu540e%5Cu7236%5Cu6bcd%5Cu51fa%5Cu8d44%5Cu4ea7%5Cu6743%5Cu5f52%5Cu8c01%22%2C%22out_trade_no%22%3A%2221575273252%22%2C%22timeout_express%22%3A%221m%22%2C%22total_amount%22%3A%2239%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&amp;charset=UTF-8&amp;format=json&amp;method=alipay.trade.app.pay&amp;notify_url=http%3A%2F%2Fyuntaifawu.com%2Fapi%2Fpay%2FaliPayBack%2Fshop_id%2F114%2Forder_id%2F21575273252%2Ftype%2F1%2Fprice%2F%2Fuid%2F3&amp;sign_type=RSA2&amp;timestamp=2019-12-02+15%3A54%3A12&amp;version=1.0&amp;sign=MxzkWkAI9vNxi5sPByyhAda7oyHS8xKqKgwbeNab6F39cpIX4Cch0z%2B%2BJw5tS%2BFKoil3bfaL8Vx%2Ba2YR7APJF5O5CnTMKUqEv%2FFxUCfbKMyJITWcUOHlYvu6xUq3aGWbAYzEjwZoCdBMi4NjOoieWD9CuhbrJiXae52w%2B9Mta9y3%2BlWtyAwTFBXvIqBf51MHx5tQLO5Pgf6NQ2AY912BjJEvExZ4WYmePoJi843k6APA%2FW5vbqRafVJI5Llqf7ZJdA0fSeIIeGw3atsyqgkKhcxrgYkkOwRrD4yTLmh1x2MnrMT3i8LXY4N5W9GvYmfMvg6TMnT%2FsRHQjXNoBcDUsw%3D%3D
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
