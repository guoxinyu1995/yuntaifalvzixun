package com.ytfu.yuntaifawu.ui.falvguwen.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserDetailsBean;
import com.ytfu.yuntaifawu.ui.falvguwen.presenter.LegalAdviserDetailsPresenter;
import com.ytfu.yuntaifawu.ui.falvguwen.view.ILegalAdviserDetailsView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.SnackbarUtils;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/21
 * @Des 法律顾问详情下载
 */
public class ActivityLegalAdviserDetailsDowLoad extends BaseActivity<ILegalAdviserDetailsView, LegalAdviserDetailsPresenter> implements ILegalAdviserDetailsView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
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
    //    @BindView(R.id.recycle_biaoge_icon)
//    RecyclerView recycleBiaogeIcon;
    @BindView(R.id.btn_biaoge_xiazai)
    Button btnBiaogeXiazai;
    @BindView(R.id.web_buy)
    WebView webBuy;
    @BindView(R.id.llsnack)
    LinearLayout llsnack;
    private String uid;
    private String id;
    private String doc_url;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_flgw_details_download;
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
//        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        tvBiaogeYuanjaiPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        webBuy.getSettings().setSupportZoom(true);//缩放
        webBuy.getSettings().setBuiltInZoomControls(true);
        webBuy.getSettings().setDisplayZoomControls(false);//不显示控制器
        webBuy.getSettings().setUseWideViewPort(true);
        webBuy.setHorizontalScrollBarEnabled(false);//水平不显示
        webBuy.setVerticalScrollBarEnabled(false); //垂直不显示
        webBuy.getSettings().setUseWideViewPort(true);
        webBuy.getSettings().setLoadWithOverviewMode(true);
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        mPresenter.flgwXQ(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        mPresenter.flgwXQ(map);
    }

    @OnClick({R.id.iv_fanhui, R.id.id_lin_start, R.id.btn_biaoge_xiazai})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.id_lin_start:
                break;
            case R.id.btn_biaoge_xiazai:
                showWaitingDialog("发送中...", true);
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("url", doc_url);
                mPresenter.setSendEmail(map);
                break;
            default:
                break;
        }
    }

    @Override
    public void onSuccess(LegalAdviserDetailsBean xinagQingBean) {
        hideLoading();
        if (xinagQingBean == null || xinagQingBean.getFind() == null) {
            showEmpty();
        } else {
            doc_url = xinagQingBean.getFind().getDoc_url();
            setUi(xinagQingBean);
        }
    }

    private void setUi(LegalAdviserDetailsBean xinagQingBean) {
        tvTopTitle.setText(xinagQingBean.getFind().getTitle());
        tvBiaogeTitle.setText(xinagQingBean.getFind().getTitle());
        tvBiaogeMiaoshu.setText("\u3000\u3000" + xinagQingBean.getFind().getDescript());
        tvBiaogePrice.setText("￥" + xinagQingBean.getFind().getPrice());
        tvBiaogeYuanjaiPrice.setText("原价 ￥" + xinagQingBean.getFind().getCost());
        tvBiaogeGoumaiCount.setText("已有" + xinagQingBean.getFind().getOrder_count() + "人购买");
        webBuy.loadUrl(xinagQingBean.getFind().getBuy_img());
    }

    @Override
    public void onFiled(String error) {

    }

    @Override
    public void onShouCangSuccess(AudioShouCangBean shouCangBean) {

    }

    @Override
    public void onShouCangdelSuccess(AudioShouCangBean shouCangBean) {

    }

    @Override
    public void onFlgwPaySuccess(PayBean payBean) {

    }

    @Override
    public void onFlgwPayWxSuccess(WxPayBean wxPayBean) {

    }

    @Override
    public void onSendEmailSuccess(SendEmailBean emailBean) {
        hideWaitingDialog();
        if (emailBean != null) {
            int status = emailBean.getStatus();
            switch (status) {
                case 200:
                case 201:
                    SnackbarUtils.showIndefiniteSnackbar(llsnack, emailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                            CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SnackbarUtils.dismissSnackbar();
                                }
                            });
                    break;
                case 202:
                    SnackbarUtils.showIndefiniteSnackbar(llsnack, emailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
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
                        mPresenter.setFlgwBdEmail(map);
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
        alertDialog.getWindow().setContentView(view);
    }
    @Override
    public void onFlguBdEmail(BdEmailBean bdEmailBean) {
        hideWaitingDialog();
        if (bdEmailBean != null) {
            int status = bdEmailBean.getStatus();
            switch (status){
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
}
