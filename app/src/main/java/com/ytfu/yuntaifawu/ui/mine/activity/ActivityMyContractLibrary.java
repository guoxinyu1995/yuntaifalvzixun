package com.ytfu.yuntaifawu.ui.mine.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.adaper.MyCollectionLibraryAdaper;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.MyLibraryBean;
import com.ytfu.yuntaifawu.ui.mine.present.MyLibraryPresent;
import com.ytfu.yuntaifawu.ui.mine.view.IMyLibraryView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.SnackbarUtils;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/15
 * @Des 我的合同库
 */
public class ActivityMyContractLibrary extends BaseActivity<IMyLibraryView, MyLibraryPresent> implements IMyLibraryView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.et_collect_seatch)
    EditText etCollectSeatch;
    @BindView(R.id.recycle_collect_library)
    RecyclerView recycleCollectLibrary;
    @BindView(R.id.snackbar_ll)
    LinearLayout snackbarLl;
    @BindView(R.id.ll_null)
    LinearLayout llNull;
    private MyCollectionLibraryAdaper libraryAdaper;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_contract_library;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return llNull;
    }

    @Override
    protected MyLibraryPresent createPresenter() {
        return new MyLibraryPresent(this);
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
        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        tvTopTitle.setText("合同库");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleCollectLibrary.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recycleCollectLibrary.setLayoutManager(layoutManager);
        etCollectSeatch.addTextChangedListener(new TextWatcher() {

            private String keyword;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyword = etCollectSeatch.getText().toString().trim();
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("f_type", String.valueOf(2));
                map.put("type", String.valueOf(1));
                map.put("keyword", keyword);
                getPresenter().myLibrary(map);
            }
        });
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("f_type", String.valueOf(2));
        map.put("type", String.valueOf(1));
        getPresenter().myLibrary(map);
    }

    @Override
    protected void initData() {
        libraryAdaper = new MyCollectionLibraryAdaper(this);
        recycleCollectLibrary.setAdapter(libraryAdaper);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("f_type", String.valueOf(2));
        map.put("type", String.valueOf(1));
        getPresenter().myLibrary(map);
        libraryAdaper.setLibraryClickListener(new MyCollectionLibraryAdaper.CollectionLibraryClickListener() {
            @Override
            public void libraryItemClickListener(String url) {
                if (TextUtils.isEmpty(url)) {
                    showToast("文件地址为空");
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
    public void onMyLibrarySuccess(MyLibraryBean libraryBean) {
        hideLoading();
        if (libraryBean == null || libraryBean.getList() == null || libraryBean.getList().isEmpty()) {
            showEmpty();
        } else {
            libraryAdaper.setmList(libraryBean.getList());
        }
    }

    @Override
    public void onFiled() {

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
//                    showToast(emailBean.getMsg());
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
                        getPresenter().setLibraryBdEmail(map);
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
    public void onLibraryBdEmailSuccess(BdEmailBean bdEmailBean) {
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
