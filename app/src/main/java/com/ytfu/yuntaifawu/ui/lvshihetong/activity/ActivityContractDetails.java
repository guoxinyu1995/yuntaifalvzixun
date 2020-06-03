package com.ytfu.yuntaifawu.ui.lvshihetong.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.helper.ScaleTransitionPagerTitleView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractDetailsBean;
import com.ytfu.yuntaifawu.ui.home.fragment.ContractBrifeFragment;
import com.ytfu.yuntaifawu.ui.home.fragment.ContractSimilarFragment;
import com.ytfu.yuntaifawu.ui.home.fragment.RelevantAudioFragment;
import com.ytfu.yuntaifawu.ui.home.presenter.ContractDetailsPresenter;
import com.ytfu.yuntaifawu.ui.home.view.IContractDetailsView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityPreview;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.pay.PayBottomDialog;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.DensityUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SnackbarUtils;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2019/11/13
 * @Des 合同详情activity
 */
public class ActivityContractDetails extends BaseActivity<IContractDetailsView, ContractDetailsPresenter> implements IContractDetailsView, View.OnClickListener {

    private MagicIndicator magic_indicator;
    private ViewPager view_pager;
    private List<String> indicatorList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private TextView text_con_title, text_price, text_yuan_price, text_count, text_start, tv_hygm, tv_ligm;
    private LinearLayout ll_share, li_start;
    private ImageView icon_start;
    private View popView;
    private PopupWindow popupWindow;
    private String id;
    private String uid;
    private int shoucang;
    private ImageView iv_fanhui;
    private TextView tv_top_title;
    private Button btn_yulan;
    private String contract_url;
    private ImageView iv_download;
    private RelativeLayout rl_null;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_contract_details;
    }

    @Override
    protected ContractDetailsPresenter createPresenter() {
        return new ContractDetailsPresenter(this);
    }

    @Override
    public void init() {
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
//        hideLoading();
        EventBus.getDefault().register(this);
        iv_fanhui = findViewById(R.id.iv_fanhui);
        iv_fanhui.setOnClickListener(this);
        tv_top_title = findViewById(R.id.tv_top_title);
        tv_top_title.setText("合同详情");
        text_con_title = findViewById(R.id.text_con_title);
        ll_share = findViewById(R.id.ll_share);
        li_start = findViewById(R.id.li_start);
        li_start.setOnClickListener(this);
        text_price = findViewById(R.id.text_price);
        text_yuan_price = findViewById(R.id.text_yuan_price);
        text_count = findViewById(R.id.text_count);
        icon_start = findViewById(R.id.icon_start);
        text_start = findViewById(R.id.text_start);
        tv_hygm = findViewById(R.id.tv_hygm);
        tv_ligm = findViewById(R.id.tv_ligm);
        btn_yulan = findViewById(R.id.btn_yulan);
        magic_indicator = findViewById(R.id.magic_indicator);
        view_pager = findViewById(R.id.view_pager);
        iv_download = findViewById(R.id.iv_download);
        rl_null = findViewById(R.id.rl_null);
        text_yuan_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        iv_download.setOnClickListener(this);
        tv_ligm.setOnClickListener(this);
        tv_hygm.setOnClickListener(this);
        btn_yulan.setOnClickListener(this);
        indicatorList.add("合同简介");
        indicatorList.add("同类合同");
        fragmentList.add(new ContractBrifeFragment());
        fragmentList.add(new ContractSimilarFragment());
        fragmentList.add(new RelevantAudioFragment());
        uid = SpUtil.getString(AppConstant.UID, "");
        initMagicIndicator();
        initViewPager();
    }

    private void initViewPager() {
        if (getFragmentManager() != null) {
//            IndicatorVpAdapter adapter = new IndicatorVpAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList, indicatorList);
            view_pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return indicatorList.size();
                }
            });
            ViewPagerHelper.bind(magic_indicator, view_pager);
        }
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return indicatorList == null ? 0 : indicatorList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
//                CustomPagerTitleView customPagerTitleView = new CustomPagerTitleView(context, index, indicatorList.size());
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(indicatorList.get(i));
                simplePagerTitleView.setTextSize(13);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.textColor_collect_audio_Select));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.textColor_Details_Unselect));
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view_pager.setCurrentItem(i);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setColors(getResources().getColor(R.color.textColor_collect_audio_Select));
                indicator.setLineWidth(DensityUtil.dip2px(43));
                indicator.setLineHeight(DensityUtil.dip2px(1));
                indicator.setRoundRadius(DensityUtil.dip2px(1));
                return indicator;
            }
        });
        magic_indicator.setNavigator(commonNavigator);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        mPresenter.getContractDetails(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        mPresenter.getContractDetails(map);
    }

    @Override
    public void onContractDetailsSuccess(ContractDetailsBean detailsBean) {
        hideLoading();
        if (detailsBean == null) {
            showEmpty();
        } else {
            showUi(detailsBean);
        }
    }

    private void showUi(ContractDetailsBean detailsBean) {
        shoucang = detailsBean.getShoucang();
        text_con_title.setText(detailsBean.getList().getTitle());
        text_price.setText(detailsBean.getList().getRealprice());
        tv_ligm.setText("立即购买 " + "¥" + detailsBean.getList().getRealprice());
        text_yuan_price.setText("原价" + " " + "¥" + detailsBean.getList().getPrice());
        text_count.setText("已有" + detailsBean.getList().getDownload_count() + "人浏览");
        contract_url = detailsBean.getList().getContract_url();
        if (shoucang == 1) {
            icon_start.setImageResource(R.drawable.start_select_icon);
        } else if (shoucang == 0) {
            icon_start.setImageResource(R.drawable.start_unselect_icon);
        }
        int buy = detailsBean.getBuy();
        if (buy == 1) {
            btn_yulan.setVisibility(View.VISIBLE);
            tv_ligm.setVisibility(View.GONE);
            iv_download.setVisibility(View.VISIBLE);
        } else if (buy == 0) {
            btn_yulan.setVisibility(View.GONE);
            tv_ligm.setVisibility(View.VISIBLE);
            iv_download.setVisibility(View.GONE);
        }
    }

    @Override
    public void onContractDetailFiled() {

    }

    @Override
    public void onShouCangSuccess(AudioShouCangBean shouCangBean) {
        if (shouCangBean == null) {
//            showEmpty();
        } else {
            int status = shouCangBean.getStatus();
            switch (status) {
                case 1:
                    if (shoucang == 1) {
                        icon_start.setImageResource(R.drawable.start_unselect_icon);
                        shoucang = 0;
                    } else if (shoucang == 0) {
                        icon_start.setImageResource(R.drawable.start_select_icon);
                        shoucang = 1;
                    }
                    ToastUtil.showToast("收藏成功");
                    break;
                case 2:
                    ToastUtil.showToast("收藏失败");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onShouCangdelSuccess(AudioShouCangBean shouCangBean) {
        if (shouCangBean == null) {
//            showEmpty();
        } else {
            int status = shouCangBean.getStatus();
            switch (status) {
                case 1:
                    if (shoucang == 1) {
                        icon_start.setImageResource(R.drawable.start_unselect_icon);
                        shoucang = 0;
                    } else if (shoucang == 0) {
                        icon_start.setImageResource(R.drawable.start_select_icon);
                        shoucang = 1;
                    }
                    ToastUtil.showToast("取消收藏成功");
                    break;
                case 2:
                    ToastUtil.showToast("取消收藏失败");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onContractPaySuccess(PayBean payBean) {
        if (payBean != null) {
            String sign = payBean.getSign();
            getAlipay(sign);
        }
    }

    @Override
    public void onContractPayWxSuccess(WxPayBean wxPayBean) {
        if (wxPayBean != null || wxPayBean.getSign() != null) {
            WxPayBean.SignBean sign = wxPayBean.getSign();
//            startWxPay(sign);
            PayHelper.getInstance().payByWechat(sign);
        }
    }

    @Override
    public void onSendEmailSuccess(SendEmailBean emailBean) {
        hideWaitingDialog();
        if (emailBean != null) {
            int status = emailBean.getStatus();
            switch (status) {
                case 200:
                case 201:
                    SnackbarUtils.showLongSnackbar(rl_null, emailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                            CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SnackbarUtils.dismissSnackbar();
                                }
                            });
                    break;
                case 202:
                    SnackbarUtils.showIndefiniteSnackbar(rl_null, emailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                            CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showAlertDialog();
                                }
                            });
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
                        mPresenter.setHtBdEmail(map);
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
    public void onContractBdEmailSuccess(BdEmailBean bdEmailBean) {
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
        PayHelper.getInstance().AliPay(ActivityContractDetails.this, sign);
        PayHelper.getInstance().setIPayListener(new PayHelper.IPayListener() {
            @Override
            public void onSuccess() {
                showToast("支付成功");
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("id", id);
                mPresenter.getContractDetails(map);
                btn_yulan.setVisibility(View.VISIBLE);
                tv_ligm.setVisibility(View.GONE);
                iv_download.setVisibility(View.VISIBLE);
            }

            @Override
            public void onResultConfirmation() {
                showToast("支付结果确认");
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_hygm:

                break;
            case R.id.tv_ligm:
                showPayDialor();
                break;
            case R.id.li_start:
                if (App.getInstance().getLoginFlag()) {
                    getShouCang();
                } else {
                    toLogin();
                }
                break;
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_yulan:
                Intent intent = new Intent(ActivityContractDetails.this, ActivityPreview.class);
                intent.putExtra("url", contract_url);
                startActivity(intent);
                break;
            case R.id.iv_download:
                showWaitingDialog("发送中...", true);
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("uid", uid);
                map1.put("url", contract_url);
                mPresenter.setSendEmail(map1);
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
    //支付选择弹框
    private void showPayDialor() {
//        PayDialorUtil.getInstance().showPayDialog(this, new PayDialorUtil.OnButtonClickListener() {
//            @Override
//            public void onPayWeiXinButtonClick(AlertDialog dialog) {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("uid", uid);
//                map.put("type", String.valueOf(2));
//                map.put("id", id);
//                map.put("xitong", String.valueOf(1));
//                mPresenter.setContractPayWx(map);
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onPayZhiFuBaoButtonClick(AlertDialog dialog) {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("uid", uid);
//                map.put("type", String.valueOf(2));
//                map.put("id", id);
//                map.put("xitong", String.valueOf(1));
//                mPresenter.setContractPay(map);
//                dialog.dismiss();
//            }
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
        PayBottomDialog dialog = new PayBottomDialog(ActivityContractDetails.this, dialogView, new int[]{R.id.ll_pay_weichat, R.id.ll_pay_zhifubao, R.id.pay, R.id.iv_guanbi});
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
                            map.put("type", String.valueOf(2));
                            map.put("id", id);
                            map.put("xitong", String.valueOf(1));
                            mPresenter.setContractPayWx(map);
                        }else if(payType == PAY_TYPE_ALIBABA){
                            HashMap<String, String> map = new HashMap<>();
                            map.put("uid", uid);
                            map.put("type", String.valueOf(2));
                            map.put("id", id);
                            map.put("xitong", String.valueOf(1));
                            mPresenter.setContractPay(map);
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
    //接受event事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getWxPayEvent(MessageEvent messageEvent) {
        switch (messageEvent.getWhat()) {
            case AppConstant.WX_PAY_SUCCESS:
                showToast("支付成功");
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("id", id);
                mPresenter.getContractDetails(map);
                break;
            default:
                break;
        }
    }
    private void getShouCang() {
        if (shoucang == 1) {
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("id", id);
            map.put("type", String.valueOf(2));
            map.put("shoucang", String.valueOf(0));
            mPresenter.contractShouCangDel(map);
        } else if (shoucang == 0) {
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("id", id);
            map.put("type", String.valueOf(2));
            map.put("shoucang", String.valueOf(1));
            mPresenter.contractShouCang(map);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
