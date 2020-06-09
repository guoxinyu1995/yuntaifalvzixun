package com.ytfu.yuntaifawu.ui.kaitingzhushou.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper.QylxtgAdaper;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.QylxtgBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter.QylxtgPresenter;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IQylxtgView;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;
import com.ytfu.yuntaifawu.utils.SnackbarUtils;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/24
 * @Des 企业另外提供
 */
public class ActivityFirmlwtg extends BaseActivity<IQylxtgView, QylxtgPresenter> implements IQylxtgView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_qylwtg)
    RecyclerView recycleQylwtg;
    @BindView(R.id.snackbar_ll)
    LinearLayout snackbarLl;
    private QylxtgAdaper qylxtgAdaper;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_qylwtg;
    }

    @Override
    protected QylxtgPresenter createPresenter() {
        return new QylxtgPresenter(this);
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
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        uid = SpUtil.getString(AppConstant.UID, "");
        tvTopTitle.setText(name);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleQylwtg.addItemDecoration(new MyItemDecoration2(0f, 0f));
        recycleQylwtg.setLayoutManager(layoutManager);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        getPresenter().setQylxtg();
    }

    @Override
    protected void initData() {
        qylxtgAdaper = new QylxtgAdaper(this);
        recycleQylwtg.setAdapter(qylxtgAdaper);
        getPresenter().setQylxtg();
        qylxtgAdaper.setQylxtgItemClickListener(new QylxtgAdaper.IQylxtgClickListener() {
            @Override
            public void onQylxtgItemClickListener(String url) {
//                showToast("企业另需提供发送邮件"+url);
                if (TextUtils.isEmpty(url)) {
                    ToastUtil.showToast("邮件路径为空");
                } else {
                    showWaitingDialog("发送中...", true);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid", uid);
                    map.put("url", url);
                    getPresenter().setSendEmail(map);
                }
            }
        });
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onQylxtgSuccess(QylxtgBean qylxtgBean) {
        hideLoading();
        if (qylxtgBean == null || qylxtgBean.getList() == null || qylxtgBean.getList().isEmpty()) {
            showEmpty();
        } else {
            qylxtgAdaper.setmList(qylxtgBean.getList());
        }
    }

    @Override
    public void onQylxtgFiled(String str) {
        ToastUtil.showToast(str);
    }

    @Override
    public void onSendEmail(SendEmailBean emailBean) {
        hideWaitingDialog();
        if (emailBean != null) {
            int status = emailBean.getStatus();
            switch (status) {
                case 200:
                case 201:
                    SnackbarUtils.showLongSnackbar(snackbarLl, emailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                            CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SnackbarUtils.dismissSnackbar();
                                }
                            });
                    break;
                case 202:
                    SnackbarUtils.showIndefiniteSnackbar(snackbarLl, emailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                            CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    showAlertDialog();
                                }
                            });
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onBdEmailSuccess(BdEmailBean bdEmailBean) {
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
                        getPresenter().setBdEmail(map);
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
}
