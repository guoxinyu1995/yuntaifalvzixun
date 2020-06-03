package com.ytfu.yuntaifawu.ui.pay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.alipay.sdk.app.PayTask;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.Map;

/**
 * @Auther gxy
 * @Date 2019/12/3
 * @Des 支付工具类
 */
public class PayHelper {
    private static PayHelper mPayHelper = null;

    public static PayHelper getInstance() {
        if (mPayHelper == null) {
            synchronized (PayHelper.class) {
                if (mPayHelper == null) {
                    mPayHelper = new PayHelper();
                }
            }
        }
        return mPayHelper;
    }

    private PayHelper() {

    }

    //支付宝
    public void AliPay(Activity activity, final String sign) {
        final PayTask alipay = new PayTask(activity);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                Map<String, String> result = alipay.payV2(sign, true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = AppConstant.SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case AppConstant.SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        if (mIPayListener != null) {
                            mIPayListener.onSuccess();
                        }
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
//                            showToast("支付结果确认中");
                            if (mIPayListener != null) {
                                mIPayListener.onResultConfirmation();
                            }
                        } else if (TextUtils.equals(resultStatus, "6001")) { //用户中途取消
//                            showToast("取消支付");
                            if (mIPayListener != null) {
                                mIPayListener.onCancel();
                            }
                        } else {
                            // 其他值就可以判断为支付失败
                            // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                            if (mIPayListener != null) {
                                mIPayListener.onFail();
                            }
                        }

                    }
                    break;
            }
        }
    };
    private IPayListener mIPayListener;

    public void setIPayListener(IPayListener mIPayListener) {
        this.mIPayListener = mIPayListener;
    }

    public interface IPayListener {
        //支付成功
        void onSuccess();

        //支付结果确认
        void onResultConfirmation();

        //取消支付
        void onCancel();

        //支付失败
        void onFail();
    }

    //微信支付
    public void payByWechat(WxPayBean.SignBean signBean) {
        if (signBean != null) {
            boolean wxAppInstalled = App.wxapi.isWXAppInstalled();
            if(!wxAppInstalled){
                ToastUtil.showCenterToast("未安装微信或微信版本过低!");
            }else{
                //调起微信支付
                PayReq payReq = new PayReq();
                //appid
                payReq.appId = signBean.getAppid();
                //商户号
                payReq.partnerId = signBean.getPartnerid();
                //预支付交易会话ID
                payReq.prepayId = signBean.getPrepayid();
                // 随机字符串
                payReq.nonceStr = signBean.getNoncestr();
                // 10位时间戳
                payReq.timeStamp = String.valueOf(signBean.getTimestamp());
                //扩展字段 暂填写固定值Sign=WXPay
                payReq.packageValue = signBean.getPackageX();
                //签名
                payReq.sign = signBean.getSign();
                //发起请求，调起微信前去支付
                boolean b = App.wxapi.sendReq(payReq);
                if (b) {
                    //如果调起微信支付页
                    Logger.i("成功");
                } else {
                    //失败
                    ToastUtil.showToast(CommonUtil.getString(R.string.onError));
//                    hideWaitingDialog();
                }
            }
        }
    }

}
