package com.ytfu.yuntaifawu.apis;

import com.ytfu.yuntaifawu.ui.chatroom.bean.AddMessageResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.FeeResponse;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.bean.TransactionResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.bean.UnbindAliResponse;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.bean.WalletResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.withdraw.bean.WithdrawResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 交易相关
 */
public interface TransactionService {

    @POST("surplus/TransAction")
    @FormUrlEncoded
    Observable<TransactionResponseBean> getTransaction(@Field("UserID") String uid,
                                                       @Field("Page") int page,
                                                       @Field("Size") int size);

    /**
     * 获取个人钱包信息
     */
    @POST("Surplus/getWalletInfo")
    @FormUrlEncoded
    Observable<WalletResponseBean> getWalletInfo(@Field("UserID") String uid);

    /**
     * 解绑支付宝
     *
     * @param type 解绑支付宝  出入固定参数2
     */
    @POST("surplus/bindingZFBaccount")
    @FormUrlEncoded
    Observable<UnbindAliResponse> unbindAliPay(@Field("UserID") String uid,
                                               @Field("Type") int type);

    /**
     * 绑定支付宝
     *
     * @param type 解绑支付宝  出入固定参数1
     */
    @POST("surplus/bindingZFBaccount")
    @FormUrlEncoded
    Observable<UnbindAliResponse> bindAliPay(@Field("UserID") String uid,
                                             @Field("Account") String account,
                                             @Field("TrueName") String realName,
                                             @Field("Type") int type);


    @POST("surplus/SubmitWithdraw")
    @FormUrlEncoded
    Observable<WithdrawResponseBean> withdraw(@Field("UserID") String uid,
                                              @Field("Money") String money);


    /**
     * @param toUserId 用户的id对方的id
     * @param selfId   自己的id律师id
     * @param price    价钱
     */
    @POST("zixun/get_send_pay")
    @FormUrlEncoded
    Observable<FeeResponse> chargeFee(@Field("yonghu") String toUserId,
                                      @Field("lvshi") String selfId,
                                      @Field("price") String price
    );


}
