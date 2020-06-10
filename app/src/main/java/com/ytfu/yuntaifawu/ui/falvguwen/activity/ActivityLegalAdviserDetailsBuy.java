package com.ytfu.yuntaifawu.ui.falvguwen.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserDetailsBean;
import com.ytfu.yuntaifawu.ui.falvguwen.presenter.LegalAdviserDetailsPresenter;
import com.ytfu.yuntaifawu.ui.falvguwen.view.ILegalAdviserDetailsView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.pay.PayBottomDialog;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;

import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/21
 * @Des 法律顾问详情购买Activity
 */
public class ActivityLegalAdviserDetailsBuy extends BaseActivity<ILegalAdviserDetailsView, LegalAdviserDetailsPresenter> implements ILegalAdviserDetailsView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.iv_flgw)
    ImageView ivFlgw;
    @BindView(R.id.iv_biaoge)
    ImageView ivBiaoge;
    @BindView(R.id.tv_biaoge_title)
    TextView tvBiaogeTitle;
    @BindView(R.id.tv_biaoge_miaoshu)
    TextView tvBiaogeMiaoshu;
    @BindView(R.id.tv_biaoge_price)
    TextView tvBiaogePrice;
    @BindView(R.id.tv_biaoge_yuanjai_price)
    TextView tvBiaogeYuanjaiPrice;
    @BindView(R.id.tv_biaoge_goumai_count)
    TextView tvBiaogeGoumaiCount;
    @BindView(R.id.icon_biaoge_start)
    ImageView iconBiaogeStart;
    @BindView(R.id.text_biaoge_start)
    TextView textBiaogeStart;
    @BindView(R.id.id_lin_start)
    LinearLayout idLinStart;
    @BindView(R.id.btn_biaoge_goumai)
    Button btnBiaogeGoumai;
    private String uid;
    private String id;
    private int shoucang;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_flgw_details_buy;
    }

    @Override
    protected LegalAdviserDetailsPresenter createPresenter() {
        return new LegalAdviserDetailsPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        uid = SpUtil.getString(AppConstant.UID, "");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        tvBiaogeYuanjaiPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        getPresenter().flgwXQ(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        getPresenter().flgwXQ(map);
    }

    @OnClick({R.id.iv_fanhui, R.id.id_lin_start, R.id.btn_biaoge_goumai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.id_lin_start:
                if (App.getInstance().getLoginFlag()) {
                    getShouCang();
                } else {
                    toLogin();
                }
                break;
            case R.id.btn_biaoge_goumai:
                showPayDialor();
                break;
            default:
                break;
        }
    }

    //支付选择框
    private static final int PAY_TYPE_WECHAT = 0;  //微信支付,默认支付方式
    private static final int PAY_TYPE_ALIBABA = 1;  //支付宝支付
    private int payType = 0;
    private ImageView mIvWeichatSelect;
    private ImageView mIvAliSelect;
    private void showPayDialor() {
        View dialogView = getLayoutInflater().inflate(R.layout.pay_view_alert_dialor1, null);
        //微信支付的选择
        mIvWeichatSelect = dialogView.findViewById(R.id.iv_buy_weichat_select);
        //支付宝的选择
        mIvAliSelect = dialogView.findViewById(R.id.iv_buy_zhifubao_select);
        PayBottomDialog dialog = new PayBottomDialog(ActivityLegalAdviserDetailsBuy.this, dialogView, new int[]{R.id.ll_pay_weichat, R.id.ll_pay_zhifubao, R.id.pay, R.id.iv_guanbi});
        dialog.bottmShow();
        dialog.setOnBottomItemClickListener(new PayBottomDialog.OnBottomItemClickListener() {
            @Override
            public void onBottomItemClick(PayBottomDialog payBottomDialog, View view) {
                switch (view.getId()){
                    case R.id.ll_pay_weichat:
                        if (PAY_TYPE_WECHAT != payType) {
                            mIvWeichatSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_xuanzhong));
                            mIvAliSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_weixuanzhong));
                            payType = PAY_TYPE_WECHAT;
                        }
                        break;
                    case R.id.ll_pay_zhifubao:
                        if (PAY_TYPE_ALIBABA != payType) {
                            mIvWeichatSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_weixuanzhong));
                            mIvAliSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_xuanzhong));
                            payType = PAY_TYPE_ALIBABA;
                        }
                        break;
                    case R.id.pay:
                        if(payType == PAY_TYPE_WECHAT){
                            HashMap<String, String> map = new HashMap<>();
                            map.put("uid", uid);
                            map.put("type", String.valueOf(7));
                            map.put("id", id);
                            map.put("xitong", String.valueOf(1));
                            getPresenter().setFlgwPayWx(map);
                        }else if(payType == PAY_TYPE_ALIBABA){
                            HashMap<String, String> map = new HashMap<>();
                            map.put("uid", uid);
                            map.put("type", String.valueOf(7));
                            map.put("id", id);
                            map.put("xitong", String.valueOf(1));
                            getPresenter().setFlgwPay(map);
                        }
                        //重置
                        payType = PAY_TYPE_WECHAT;
                        dialog.cancel();
                        break;
                    case R.id.iv_guanbi:
                        //重置
                        payType = PAY_TYPE_WECHAT;
                        dialog.cancel();
                        break;
                }
            }
        });
    }
    private void getShouCang() {
//        if (shoucang == 1) {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("uid", uid);
//            map.put("id", id);
//            map.put("type", String.valueOf(1));
//            map.put("shoucang", String.valueOf(0));
//            getPresenter().flgwShouCangDel(map);
//        } else if (shoucang == 0) {
//            HashMap<String, String> map = new HashMap<>();
//            map.put("uid", uid);
//            map.put("id", id);
//            map.put("type", String.valueOf(1));
//            map.put("shoucang", String.valueOf(1));
//            getPresenter().flgwShouCang(map);
//        }
    }

    @Override
    public void onSuccess(LegalAdviserDetailsBean xinagQingBean) {
        hideLoading();
        if (xinagQingBean == null || xinagQingBean.getFind() == null) {
            showEmpty();
        } else {
            setUi(xinagQingBean);
        }
    }

    private void setUi(LegalAdviserDetailsBean xinagQingBean) {
        tvTopTitle.setText(xinagQingBean.getFind().getTitle());
        shoucang = xinagQingBean.getShoucang();
        GlideManager.loadImageByUrl(this, xinagQingBean.getFind().getImg(), ivFlgw);
        tvBiaogeTitle.setText(xinagQingBean.getFind().getTitle());
        tvBiaogeMiaoshu.setText("\u3000\u3000" + xinagQingBean.getFind().getDescript());
        tvBiaogePrice.setText("￥" + xinagQingBean.getFind().getPrice());
        tvBiaogeYuanjaiPrice.setText("原价 ￥" + xinagQingBean.getFind().getCost());
        tvBiaogeGoumaiCount.setText("已有" + xinagQingBean.getFind().getOrder_count() + "人购买");
//        if (shoucang == 1) {
//            iconBiaogeStart.setImageResource(R.drawable.start_select_icon);
//        } else if (shoucang == 0) {
//            iconBiaogeStart.setImageResource(R.drawable.start_unselect_icon);
//        }
    }

    @Override
    public void onFiled(String error) {

    }

    @Override
    public void onShouCangSuccess(AudioShouCangBean shouCangBean) {
//        if (shouCangBean != null) {
//            int status = shouCangBean.getStatus();
//            switch (status) {
//                case 1:
//                    if (shoucang == 1) {
//                        iconBiaogeStart.setImageResource(R.drawable.start_unselect_icon);
//                        shoucang = 0;
//                    } else if (shoucang == 0) {
//                        iconBiaogeStart.setImageResource(R.drawable.start_select_icon);
//                        shoucang = 1;
//                    }
//                    ToastUtil.showToast("收藏成功");
//                    break;
//                case 2:
//                    ToastUtil.showToast("收藏失败");
//                    break;
//                default:
//                    break;
//            }
//        }
    }

    @Override
    public void onShouCangdelSuccess(AudioShouCangBean shouCangBean) {
//        if (shouCangBean != null) {
//            int status = shouCangBean.getStatus();
//            switch (status) {
//                case 1:
//                    if (shoucang == 1) {
//                        iconBiaogeStart.setImageResource(R.drawable.start_unselect_icon);
//                        shoucang = 0;
//                    } else if (shoucang == 0) {
//                        iconBiaogeStart.setImageResource(R.drawable.start_select_icon);
//                        shoucang = 1;
//                    }
//                    ToastUtil.showToast("取消收藏成功");
//                    break;
//                case 2:
//                    ToastUtil.showToast("取消收藏失败");
//                    break;
//                default:
//                    break;
//            }
//        }
    }

    @Override
    public void onFlgwPaySuccess(PayBean payBean) {
        if (payBean != null) {
            String sign = payBean.getSign();
            getAlipay(sign);
        }
    }

    @Override
    public void onFlgwPayWxSuccess(WxPayBean wxPayBean) {
        if (wxPayBean != null || wxPayBean.getSign() != null) {
            WxPayBean.SignBean sign = wxPayBean.getSign();
//            startWxPay(sign);
            PayHelper.getInstance().payByWechat(sign);
        }
    }
    //接受event事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getWxPayEvent(MessageEvent messageEvent) {
        switch (messageEvent.getWhat()) {
            case AppConstant.WX_PAY_SUCCESS:
                showToast("支付成功");
                Intent intent = new Intent(ActivityLegalAdviserDetailsBuy.this, ActivityLegalAdviserDetailsDowLoad.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
    @Override
    public void onSendEmailSuccess(SendEmailBean emailBean) {

    }

    @Override
    public void onFlguBdEmail(BdEmailBean bdEmailBean) {

    }

    private void getAlipay(String sign) {
        PayHelper.getInstance().AliPay(ActivityLegalAdviserDetailsBuy.this, sign);
        PayHelper.getInstance().setIPayListener(new PayHelper.IPayListener() {
            @Override
            public void onSuccess() {
                showToast("支付成功");
                Intent intent = new Intent(ActivityLegalAdviserDetailsBuy.this, ActivityLegalAdviserDetailsDowLoad.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }

            @Override
            public void onResultConfirmation() {
                showToast("支付结果处理中");
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
