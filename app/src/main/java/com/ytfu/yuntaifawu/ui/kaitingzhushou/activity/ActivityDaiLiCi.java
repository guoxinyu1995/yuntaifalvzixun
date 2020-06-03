package com.ytfu.yuntaifawu.ui.kaitingzhushou.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper.DlcTileAdaper;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.DlcBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.DlcSendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter.DlcPresenter;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IDlCiView;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.pay.PayBottomDialog;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SnackbarUtils;
import com.ytfu.yuntaifawu.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/21
 * @Des 代理词Activity
 */
public class ActivityDaiLiCi extends BaseActivity<IDlCiView, DlcPresenter> implements IDlCiView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_dlc_wenhou)
    TextView tvDlcWenhou;
    @BindView(R.id.btn_dailici_buy)
    Button btnDailiciBuy;
    @BindView(R.id.btn_dailici_fasong)
    Button btnDailiciFasong;
//    @BindView(R.id.tv_dlc_title)
//    TextView tvDlcTitle;
    //    @BindView(R.id.tv_dlc_nierong)
//    TextView tvDlcNierong;
//    @BindView(R.id.ll_bufen)
//    LinearLayout llBufen;
    @BindView(R.id.recycle_dlc)
    RecyclerView recycleDlc;
    @BindView(R.id.snackbar_ll)
    LinearLayout snackbarLl;
    @BindView(R.id.scroll_view)
    NestedScrollView scrollView;
    @BindView(R.id.tv_dlc_qy)
    TextView tvDlcQy;
    @BindView(R.id.iv_weifufei)
    ImageView ivWeifufei;
    private String uid;
    private String id;
    private DlcTileAdaper tileAdaper;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_dai_li_ci;
    }

    @Override
    protected DlcPresenter createPresenter() {
        return new DlcPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
//      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.transparent_half));

        }
    }

    @Override
    protected void initView() {
//        hideLoading();
        EventBus.getDefault().register(this);
        uid = SpUtil.getString(AppConstant.UID, "");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        tvTopTitle.setText("代理词");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleDlc.setLayoutManager(layoutManager);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("zhushouid", id);
        mPresenter.setDlc(map);
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("zhushouid", id);
        mPresenter.setDlc(map);
    }

    @OnClick({R.id.iv_fanhui, R.id.btn_dailici_buy, R.id.btn_dailici_fasong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_dailici_buy:
                showPayDialor();
                break;
            case R.id.btn_dailici_fasong:
                showWaitingDialog("发送中...", true);
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("zhushouid", id);
                mPresenter.setDlcSendEmail(map);
                break;
            default:
                break;
        }

    }

    private static final int PAY_TYPE_WECHAT = 0;  //微信支付,默认支付方式
    private static final int PAY_TYPE_ALIBABA = 1;  //支付宝支付
    private int payType = 0;
    private ImageView mIvWeichatSelect;
    private ImageView mIvAliSelect;

    //支付选择框
    private void showPayDialor() {
//        PayDialorUtil.getInstance().showPayDialog(this, new PayDialorUtil.OnButtonClickListener() {
//            @Override
//            public void onPayWeiXinButtonClick(AlertDialog dialog) {
//                HashMap<String, String> buyMap = new HashMap<>();
//                buyMap.put("uid", uid);
//                buyMap.put("type", String.valueOf(8));
//                buyMap.put("id", id);
//                buyMap.put("xitong", String.valueOf(1));
//                mPresenter.setDlcPayWx(buyMap);
//                dialog.dismiss();
//            }
//            @Override
//            public void onPayZhiFuBaoButtonClick(AlertDialog dialog) {
//                HashMap<String, String> buyMap = new HashMap<>();
//                buyMap.put("uid", uid);
//                buyMap.put("type", String.valueOf(8));
//                buyMap.put("id", id);
//                buyMap.put("xitong", String.valueOf(1));
//                mPresenter.setDlcPay(buyMap);
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onGuanbiButtonClick(AlertDialog dialog) {
//                dialog.dismiss();
//            }
//        });
        View dialogView = getLayoutInflater().inflate(R.layout.pay_view_alert_dialor1, null);
        //微信支付的选择
        mIvWeichatSelect = dialogView.findViewById(R.id.iv_buy_weichat_select);
        //支付宝的选择
        mIvAliSelect = dialogView.findViewById(R.id.iv_buy_zhifubao_select);
        PayBottomDialog dialog = new PayBottomDialog(ActivityDaiLiCi.this, dialogView, new int[]{R.id.ll_pay_weichat, R.id.ll_pay_zhifubao, R.id.pay, R.id.iv_guanbi});
        dialog.bottmShow();
        dialog.setOnBottomItemClickListener(new PayBottomDialog.OnBottomItemClickListener() {
            @Override
            public void onBottomItemClick(PayBottomDialog payBottomDialog, View view) {
                switch (view.getId()) {
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
                        if (payType == PAY_TYPE_WECHAT) {
                            HashMap<String, String> buyMap = new HashMap<>();
                            buyMap.put("uid", uid);
                            buyMap.put("type", String.valueOf(8));
                            buyMap.put("id", id);
                            buyMap.put("xitong", String.valueOf(1));
                            mPresenter.setDlcPayWx(buyMap);
                        } else if (payType == PAY_TYPE_ALIBABA) {
                            HashMap<String, String> buyMap = new HashMap<>();
                            buyMap.put("uid", uid);
                            buyMap.put("type", String.valueOf(8));
                            buyMap.put("id", id);
                            buyMap.put("xitong", String.valueOf(1));
                            mPresenter.setDlcPay(buyMap);
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

    @Override
    public void onDlcSuccess(DlcBean dlcBean) {
        hideLoading();
        if (dlcBean == null || dlcBean.getArr() == null) {
            showEmpty();
        } else {
            setUi(dlcBean);
        }
    }

    private void setUi(DlcBean dlcBean) {
        tvDlcQy.setText(dlcBean.getQianyan());
        tvDlcWenhou.setText(dlcBean.getMoban());
        btnDailiciBuy.setText("立即购买 " + "¥" + dlcBean.getPrice());
        int buy = dlcBean.getBuy();
        switch (buy) {
            case 1:
                tileAdaper = new DlcTileAdaper(this);
                recycleDlc.setAdapter(tileAdaper);
                btnDailiciBuy.setVisibility(View.GONE);
//                llBufen.setVisibility(View.GONE);
                ivWeifufei.setVisibility(View.GONE);
                tileAdaper.setmList(dlcBean.getArr());
                btnDailiciFasong.setVisibility(View.VISIBLE);
                recycleDlc.setVisibility(View.VISIBLE);
                scrollView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                break;
            case 2:
                btnDailiciBuy.setVisibility(View.VISIBLE);
//                llBufen.setVisibility(View.VISIBLE);
                ivWeifufei.setVisibility(View.VISIBLE);
//                tvDlcTitle.setText(dlcBean.getArr().get(0).getAgent_name());
//                tvDlcNierong.setText(Html.fromHtml(dlcBean.getArr().get(0).getAgent_content()));
                btnDailiciFasong.setVisibility(View.GONE);
                recycleDlc.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDlicFiled() {

    }

    @Override
    public void onDlcSendEmailSuccess(DlcSendEmailBean sendEmailBean) {
        hideWaitingDialog();
        if (sendEmailBean != null) {
            int status = sendEmailBean.getStatus();
            switch (status) {
                case 200:
                    SnackbarUtils.showLongSnackbar(snackbarLl, sendEmailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                            CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SnackbarUtils.dismissSnackbar();
                                }
                            });
                    break;
                case 201:
                    SnackbarUtils.showIndefiniteSnackbar(snackbarLl, sendEmailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                            CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showAlertDialog();
                                }
                            });
                    break;
                case 202:
                    SnackbarUtils.showLongSnackbar(snackbarLl, "邮箱发送失败", CommonUtil.getColor(R.color.textcolo_299),
                            CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SnackbarUtils.dismissSnackbar();
                                }
                            });
                    break;
                default:
                    break;
            }
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View view = View.inflate(this, R.layout.view_alert_dialog_confirm_email, null);
        TextView tvMsg = view.findViewById(R.id.tv_message_dialog);
        TextView tvCancel = view.findViewById(R.id.tv_cancel_dialog);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm_dialog);
        TextView tv_tishi = view.findViewById(R.id.tv_tishi);
        EditText ed_email = view.findViewById(R.id.ed_email);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    tv_tishi.setVisibility(View.VISIBLE);
                    tv_tishi.setText("邮箱输入为空");
                } else {
                    tv_tishi.setVisibility(View.GONE);
                    boolean contains = email.contains("@");
                    if (contains == true) {
                        showWaitingDialog("请稍后...", true);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("uid", uid);
                        map.put("mail", email);
                        mPresenter.setDlcBdEmail(map);
                        alertDialog.dismiss();
                    } else {
                        tv_tishi.setVisibility(View.VISIBLE);
                        tv_tishi.setText("邮箱格式不正确");
                    }
                }
            }
        });
        //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);
    }

    @Override
    public void onDlcPaySuccess(PayBean payBean) {
        if (payBean != null) {
            String sign = payBean.getSign();
            getAlipay(sign);
        }
    }

    @Override
    public void onDlcPayWxSuccess(WxPayBean wxPayBean) {
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
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("zhushouid", id);
                mPresenter.setDlc(map);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDlcBdEmail(BdEmailBean bdEmailBean) {
        hideWaitingDialog();
        if (bdEmailBean != null) {
            int status = bdEmailBean.getStatus();
            switch (status) {
                case 200:
                case 201:
                case 202:
                    showToast(bdEmailBean.getMsg());
                    break;
                default:
                    break;
            }
        }
    }

    private void getAlipay(String sign) {
        PayHelper.getInstance().AliPay(ActivityDaiLiCi.this, sign);
        PayHelper.getInstance().setIPayListener(new PayHelper.IPayListener() {
            @Override
            public void onSuccess() {
                showToast("支付成功");
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("zhushouid", id);
                mPresenter.setDlc(map);
                btnDailiciBuy.setVisibility(View.GONE);
//                llBufen.setVisibility(View.GONE);
                ivWeifufei.setVisibility(View.GONE);
                btnDailiciFasong.setVisibility(View.VISIBLE);
                recycleDlc.setVisibility(View.VISIBLE);
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
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
