package com.ytfu.yuntaifawu.ui.consult.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.consult.bean.XuanshangFaBuAliBean;
import com.ytfu.yuntaifawu.ui.consult.bean.XuanshangFaBuWatchBean;
import com.ytfu.yuntaifawu.ui.consult.presenter.XuanShangPayPresenter;
import com.ytfu.yuntaifawu.ui.consult.view.IXuanShangPayView;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityMineZiXun;
import com.ytfu.yuntaifawu.ui.mseeage.activity.ChatActivity;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/5/18
 * @Des 悬赏支付
 */
public class XuanShangPayActivity extends BaseActivity<IXuanShangPayView, XuanShangPayPresenter> implements IXuanShangPayView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_xs_price)
    TextView tvXsPrice;
    @BindView(R.id.iv_buy_weichat_select)
    ImageView ivBuyWeichatSelect;
    @BindView(R.id.ll_pay_weichat)
    LinearLayout llPayWeichat;
    @BindView(R.id.iv_buy_zhifubao_select)
    ImageView ivBuyZhifubaoSelect;
    @BindView(R.id.ll_pay_zhifubao)
    LinearLayout llPayZhifubao;
    @BindView(R.id.tv_xuangshang_fabu)
    TextView tvXuangshangFabu;
    //支付选择框
    private static final int PAY_TYPE_WECHAT = 0;  //微信支付,默认支付方式
    private static final int PAY_TYPE_ALIBABA = 1;  //支付宝支付
    private int payType = 0;
    private String edStr;
    private String tvStr;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_xuanshangpay;
    }

    @Override
    protected XuanShangPayPresenter createPresenter() {
        return new XuanShangPayPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
        hideLoading();
        EventBus.getDefault().register(this);
        tvTopTitle.setText("悬赏支付");
        uid = SpUtil.getString(AppConstant.UID, "");
        edStr = getIntent().getStringExtra("edStr");
        tvStr = getIntent().getStringExtra("tvStr");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_fanhui, R.id.tv_xuangshang_fabu, R.id.ll_pay_weichat, R.id.ll_pay_zhifubao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.ll_pay_weichat:
                if (PAY_TYPE_WECHAT != payType) {
                    ivBuyWeichatSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_xuanzhong));
                    ivBuyZhifubaoSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_weixuanzhong));
                    payType = PAY_TYPE_WECHAT;
                }
                break;
            case R.id.ll_pay_zhifubao:
                if (PAY_TYPE_ALIBABA != payType) {
                    ivBuyWeichatSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_weixuanzhong));
                    ivBuyZhifubaoSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_xuanzhong));
                    payType = PAY_TYPE_ALIBABA;
                }
                break;
            case R.id.tv_xuangshang_fabu:
                if (payType == PAY_TYPE_WECHAT) {
                    //微信支付
                    showCenterToast("微信");
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid", uid);
                    map.put("type", String.valueOf(14));
                    map.put("xitong", String.valueOf(1));
                    map.put("price", String.valueOf(0.01));
                    map.put("consult_type", tvStr);
                    map.put("consult_content", edStr);
                    mPresenter.getXuanShangWatch(map);
                } else if (payType == PAY_TYPE_ALIBABA) {
                    //支付宝支付
                    showCenterToast("支付宝");
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid", uid);
                    map.put("type", String.valueOf(14));
                    map.put("xitong", String.valueOf(1));
                    map.put("price", String.valueOf(0.01));
                    map.put("consult_type", tvStr);
                    map.put("consult_content", edStr);
                    mPresenter.getXuanShangAli(map);
                }
                //重置
                payType = PAY_TYPE_WECHAT;
                break;

        }
    }

    //悬赏微信
    @Override
    public void onXuanShangWatchPaySuccess(WxPayBean wxPayBean) {
        hideLoading();
        if (wxPayBean != null || wxPayBean.getSign() != null) {
            WxPayBean.SignBean sign = wxPayBean.getSign();
            PayHelper.getInstance().payByWechat(sign);
        }
    }

    //接受event事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getWxPayEvent(MessageEvent messageEvent) {
        switch (messageEvent.getWhat()) {
            case AppConstant.WX_PAY_SUCCESS:
                ToastUtil.showCenterToast("发布成功");
                startActivity(new Intent(XuanShangPayActivity.this, ActivityMineZiXun.class));
                break;
            default:
                break;
        }
    }

    //悬赏支付宝
    @Override
    public void onXuanShangAliPaySuccess(XuanshangFaBuAliBean faBuAliBean) {
        if (faBuAliBean != null) {
            String sign = faBuAliBean.getSign();
            getAliPay(sign);
        }
    }

    private void getAliPay(String sign) {
        PayHelper.getInstance().AliPay(XuanShangPayActivity.this, sign);
        PayHelper.getInstance().setIPayListener(new PayHelper.IPayListener() {
            @Override
            public void onSuccess() {
                ToastUtil.showCenterToast("发布成功");
                startActivity(new Intent(XuanShangPayActivity.this, ActivityMineZiXun.class));
            }

            @Override
            public void onResultConfirmation() {
                showToast("支付结果确认中");
            }

            @Override
            public void onCancel() {
                showToast("取消支付");
            }

            @Override
            public void onFail() {
                showToast("支付失败");
            }
        });
    }

    @Override
    public void onXuanShangFiled() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
