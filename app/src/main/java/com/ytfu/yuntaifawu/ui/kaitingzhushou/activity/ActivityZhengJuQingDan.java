package com.ytfu.yuntaifawu.ui.kaitingzhushou.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper.ZjqdAdaper;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ZjqdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ZjqdSendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter.ZjqdPresenter;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IZjqdView;
import com.ytfu.yuntaifawu.ui.pay.PayBottomDialog;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityZjqdXq;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.DateUtil;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;
import com.ytfu.yuntaifawu.utils.SnackbarUtils;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * @Auther gxy
 * @Date 2019/11/24
 * @Des 证据清单
 */
public class ActivityZhengJuQingDan extends BaseActivity<IZjqdView, ZjqdPresenter> implements IZjqdView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recyclezjqd)
    RecyclerView recycleZjqd;
    @BindView(R.id.btn_buy_zjqd)
    Button btnBuyZjqd;
    @BindView(R.id.snackbar_ll)
    RelativeLayout snackbarLl;
    @BindView(R.id.v1)
    View v1;
    private ZjqdAdaper zjqdAdaper;
    private String id;
    private String name;
    private List<ZjqdBean.ListBean> list;
    private List<String> zjqdList = new ArrayList<>();
    private String uid;
    private String xqStr;
    private LinearLayoutManager layoutManager;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_zhengjuqingdan;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return recycleZjqd;
    }

    @Override
    protected ZjqdPresenter createPresenter() {
        return new ZjqdPresenter(this);
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
        long curTimeLong = DateUtil.getCurTimeLong();
        String str_date = String.valueOf(curTimeLong);
        xqStr = uid + str_date;
        Log.e("curTimeLong", "---------" + str_date);
        Log.e("xqStr", "------" + xqStr);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        tvTopTitle.setText(name);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleZjqd.addItemDecoration(new MyItemDecoration2(0f, 0f));
        recycleZjqd.setLayoutManager(layoutManager);
        recycleZjqd.setHasFixedSize(true);
        recycleZjqd.setNestedScrollingEnabled(false);
        // Vertical
//        OverScrollDecoratorHelper.setUpOverScroll(recycleZjqd, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        getPresenter().setZjqd(map);
    }

    @Override
    protected void initData() {
        zjqdAdaper = new ZjqdAdaper(this);
        recycleZjqd.setAdapter(zjqdAdaper);
        recycleZjqd.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition+1 == zjqdAdaper.getItemCount()){
                    v1.setVisibility(View.VISIBLE);
                }else{
                    v1.setVisibility(View.GONE);
                }
            }
        });
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        getPresenter().setZjqd(map);
        zjqdAdaper.setItemClickListener(new ZjqdAdaper.RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClickListener(int position, boolean check) {
                if (check) {
                    if (zjqdList.size() <= 50) {
                        zjqdList.add(list.get(position).getId());
                    } else {
                        zjqdAdaper.setItemCheck(position, false);
                    }
                } else {
                    for (int i = 0; i < zjqdList.size(); i++) {
                        if (TextUtils.equals(list.get(position).getId(), zjqdList.get(i))) {
                            zjqdList.remove(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    /**
     * list 转为 String 加空格
     *
     * @param list
     * @return
     */
    private String list2String(List<String> list) {
        StringBuilder codeString = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                codeString.append(list.get(i)).append(",");
            } else {
                codeString.append(list.get(i));
            }
        }
        return codeString.toString();
    }

    @Override
    public void onZjqdSuccess(ZjqdBean zjqdBean) {
        hideLoading();
        if (zjqdBean == null || zjqdBean.getList() == null || zjqdBean.getList().isEmpty()) {
            showEmpty();
        } else {
            list = zjqdBean.getList();
            zjqdAdaper.setmList(zjqdBean.getList());
        }
    }

    @Override
    public void onZjqdBuySuccess(PayBean payBean) {
        if (payBean != null) {
            String sign = payBean.getSign();
            getAlipay(sign);
        }
    }

    @Override
    public void onZjqdBuyWxSuccess(WxPayBean wxPayBean) {
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
                Intent intent = new Intent(ActivityZhengJuQingDan.this, ActivityZjqdXq.class);
                intent.putExtra("zhengju_weiyi", xqStr);
                intent.putExtra("types", 1);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void getAlipay(String sign) {
        PayHelper.getInstance().AliPay(ActivityZhengJuQingDan.this, sign);
        PayHelper.getInstance().setIPayListener(new PayHelper.IPayListener() {
            @Override
            public void onSuccess() {
                showToast("支付成功");
                Intent intent = new Intent(ActivityZhengJuQingDan.this, ActivityZjqdXq.class);
                intent.putExtra("zhengju_weiyi", xqStr);
                intent.putExtra("types", 1);
                startActivity(intent);
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
    public void onZjqdFiled() {

    }


    @OnClick({R.id.iv_fanhui, R.id.btn_buy_zjqd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_buy_zjqd:
                String zhengju_str = list2String(zjqdList);
                if (TextUtils.isEmpty(zhengju_str)) {
                    showToast("请先选择要购买的证据");
                } else {
                    showPayDialor();
                }
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
//        PayDialorUtil.getInstance().showPayDialog(this, new PayDialorUtil.OnButtonClickListener() {
//            @Override
//            public void onPayWeiXinButtonClick(AlertDialog dialog) {
//                String zhengju_str = list2String(zjqdList);
//                if (TextUtils.isEmpty(zhengju_str)) {
//                    showToast("请先选择要购买的证据");
//                } else {
//                    HashMap<String, String> map = new HashMap<>();
//                    map.put("uid", uid);
//                    map.put("type", String.valueOf(10));
//                    map.put("zhengju_str", zhengju_str);
//                    map.put("sid", id);
//                    map.put("zhengju_weiyi", xqStr);
//                    map.put("xitong", String.valueOf(1));
//                    getPresenter().setZjqdPayWx(map);
//                }
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onPayZhiFuBaoButtonClick(AlertDialog dialog) {
//                String zhengju_str = list2String(zjqdList);
//                if (TextUtils.isEmpty(zhengju_str)) {
//                    showToast("请先选择要购买的证据");
//                } else {
//                    HashMap<String, String> map = new HashMap<>();
//                    map.put("uid", uid);
//                    map.put("type", String.valueOf(10));
//                    map.put("zhengju_str", zhengju_str);
//                    map.put("sid", id);
//                    map.put("zhengju_weiyi", xqStr);
//                    map.put("xitong", String.valueOf(1));
//                    getPresenter().setZjqdPay(map);
//                }
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
        PayBottomDialog dialog = new PayBottomDialog(ActivityZhengJuQingDan.this, dialogView, new int[]{R.id.ll_pay_weichat, R.id.ll_pay_zhifubao, R.id.pay, R.id.iv_guanbi});
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
                            String zhengju_str = list2String(zjqdList);
//                            if (TextUtils.isEmpty(zhengju_str)) {
//                                showToast("请先选择要购买的证据");
//                            } else {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("uid", uid);
                            map.put("type", String.valueOf(10));
                            map.put("zhengju_str", zhengju_str);
                            map.put("sid", id);
                            map.put("zhengju_weiyi", xqStr);
                            map.put("xitong", String.valueOf(1));
                            getPresenter().setZjqdPayWx(map);
//                            }
                        } else if (payType == PAY_TYPE_ALIBABA) {
                            String zhengju_str = list2String(zjqdList);
//                            if (TextUtils.isEmpty(zhengju_str)) {
//                                showToast("请先选择要购买的证据");
//                            } else {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("uid", uid);
                            map.put("type", String.valueOf(10));
                            map.put("zhengju_str", zhengju_str);
                            map.put("sid", id);
                            map.put("zhengju_weiyi", xqStr);
                            map.put("xitong", String.valueOf(1));
                            getPresenter().setZjqdPay(map);
//                            }
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

    private void showChecked() {
        showWaitingDialog("发送中...", true);
        String arr = list2String(zjqdList);
        Log.e("arr", "-------" + arr);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("arr", arr);
        sendEmail(map);
    }

    //生成发送邮箱
    private void sendEmail(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().getSendEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<ZjqdSendEmailBean>() {
                    @Override
                    public void onNextImpl(ZjqdSendEmailBean emailBean) {
//                        if (AppConstant.STATUS_SUCCESS == emailBean.getStatus()) {
                        onSendSuccess(emailBean);
//                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setZjqd" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                    }
                });
    }

    private void onSendSuccess(ZjqdSendEmailBean emailBean) {
        hideWaitingDialog();
        if (emailBean != null) {
            int status = emailBean.getStatus();
            if (status == 200) {
                SnackbarUtils.showIndefiniteSnackbar(snackbarLl, emailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                        CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SnackbarUtils.dismissSnackbar();
                            }
                        });
            } else if (status == 201) {
                SnackbarUtils.showIndefiniteSnackbar(snackbarLl, emailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                        CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SnackbarUtils.dismissSnackbar();
                            }
                        });
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
