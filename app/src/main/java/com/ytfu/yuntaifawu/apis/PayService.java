package com.ytfu.yuntaifawu.apis;

import com.ytfu.yuntaifawu.ui.chatroom.bean.FeeWechatOrderResponse;
import com.ytfu.yuntaifawu.ui.mseeage.bean.WhetherToPayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 支付相关网络请求
 */
public interface PayService {


    /**
     * 咨询付费,获取微信订单
     *
     * @param selfId       自己的id
     * @param receiverId   接收者的id
     * @param redPackageId 对应的咨询服务费的id
     * @param type         固定为12
     * @param xitong       固定为1
     */
    @POST("wxapppay/buy")
    @FormUrlEncoded
    Observable<FeeWechatOrderResponse> getFeeWechatPayOrder(@Field("uid") String selfId,
                                                            @Field("lv_id") String receiverId,
                                                            @Field("jilu_id") String redPackageId,
                                                            @Field("type") int type,
                                                            @Field("xitong") int xitong
    );

    /**
     * 咨询付费,获取支付宝订单
     *
     * @param selfId       自己的id
     * @param receiverId   接收者的id
     * @param redPackageId 对应的咨询服务费的id
     * @param type         固定为12
     * @param xitong       固定为1
     */
    @POST("pay/buy")
    @FormUrlEncoded
    Observable<PayBean> getFeeAliPayOrder(@Field("uid") String selfId,
                                          @Field("lv_id") String receiverId,
                                          @Field("jilu_id") String redPackageId,
                                          @Field("type") int type,
                                          @Field("xitong") int xitong
    );


    /**检测用户时候已经支付,已经支付了才能进行下一步操作*/
    @POST("huanxin/checkpay2")
    @FormUrlEncoded
    Observable<WhetherToPayBean> checkPay(@Field("user_id") String selfId,
                                          @Field("lv_id") String toUserId
    );


}
