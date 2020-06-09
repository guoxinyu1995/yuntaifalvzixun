package com.ytfu.yuntaifawu.ui.qisuzhuang.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.adaper.ZjqdXqAdaper;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.ZjqdXqBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.ZjqdXqSendEmailBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.presenter.ZjqdXqPresenter;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IZjqdXqView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.DialorUtil;
import com.ytfu.yuntaifawu.utils.SnackbarUtils;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/18
 * @Des 证据清单详情
 */
public class ActivityZjqdXq extends BaseActivity<IZjqdXqView, ZjqdXqPresenter> implements IZjqdXqView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_zjqd_xq)
    RecyclerView recycleZjqdXq;
    @BindView(R.id.btn_send_email)
    Button btnSendEmail;
    private final int TYPE_BUY = 1;
    private final int TYPE_ITEM = 2;
    private final int TYPE_JILU = 3;
    @BindView(R.id.ll_snackbar)
    RelativeLayout llSnackbar;
    @BindView(R.id.v1)
    View v1;
    private String zhengju_weiyi;
    private String zhengjuid;
    private int types;
    private ZjqdXqAdaper zjqdXqAdaper;
    private String uid;
    private String zhengju;
    private String id;
    private LinearLayoutManager layoutManager;

    @Override

    protected int provideContentViewId() {
        return R.layout.activity_zjqd_xq;
    }

    @Override
    protected ZjqdXqPresenter createPresenter() {
        return new ZjqdXqPresenter(this);
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
        tvTopTitle.setText("证据清单");
        uid = SpUtil.getString(AppConstant.UID, "");
        Intent intent = getIntent();
        zhengju_weiyi = intent.getStringExtra("zhengju_weiyi");
        zhengjuid = intent.getStringExtra("zhengjuid");
        types = intent.getIntExtra("types", 0);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleZjqdXq.setLayoutManager(layoutManager);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        switch (types) {
            case TYPE_BUY:
//                showToast("购买");
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("zhengju_weiyi", zhengju_weiyi);
                getPresenter().setZjqdXqBuy(map);
                break;
            case TYPE_ITEM:
//                showToast("item");
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("uid", uid);
                map1.put("zhengjuid", zhengjuid);
                getPresenter().setZjqdXq(map1);
                break;
            case TYPE_JILU:
                HashMap<String, String> map2 = new HashMap<>();
                map2.put("uid", uid);
                map2.put("zhengju_weiyi", zhengjuid);
                getPresenter().setZjqdXqBuy(map2);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initData() {
        zjqdXqAdaper = new ZjqdXqAdaper(this);
        recycleZjqdXq.setAdapter(zjqdXqAdaper);
        recycleZjqdXq.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition+1 == zjqdXqAdaper.getItemCount()){
                    v1.setVisibility(View.VISIBLE);
                }else{
                    v1.setVisibility(View.GONE);
                }
            }
        });
        switch (types) {
            case TYPE_BUY:
//                showToast("购买");
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("zhengju_weiyi", zhengju_weiyi);
                getPresenter().setZjqdXqBuy(map);
                break;
            case TYPE_ITEM:
//                showToast("item");
                HashMap<String, String> map1 = new HashMap<>();
                map1.put("uid", uid);
                map1.put("zhengjuid", zhengjuid);
                getPresenter().setZjqdXq(map1);
                break;
            case TYPE_JILU:
                HashMap<String, String> map2 = new HashMap<>();
                map2.put("uid", uid);
                map2.put("zhengju_weiyi", zhengjuid);
                getPresenter().setZjqdXqBuy(map2);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.iv_fanhui, R.id.btn_send_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_send_email:
                showWaitingDialog("发送中...", true);
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("zhengjuid", id);
                getPresenter().setZjqdXqSendEmail(map);
                break;
            default:
                break;
        }
    }

    @Override
    public void onZjqdXqSuccess(ZjqdXqBean zjqdXqBean) {
        hideLoading();
        if (zjqdXqBean == null || zjqdXqBean.getFind() == null || zjqdXqBean.getFind().getZhengjulist() == null || zjqdXqBean.getFind().getZhengjulist().isEmpty()) {
            showEmpty();
        } else {
            zhengju = zjqdXqBean.getFind().getZhengju();
            id = zjqdXqBean.getFind().getId();
            zjqdXqAdaper.setmList(zjqdXqBean.getFind().getZhengjulist());
        }
    }

    @Override
    public void onZjqdXqFiled() {

    }

    @Override
    public void onZjqdXqSendSuccess(ZjqdXqSendEmailBean sendEmailBean) {
        hideWaitingDialog();
        if (sendEmailBean != null) {
            int status = sendEmailBean.getStatus();
            switch (status) {
                case 200:
                case 202:
                    SnackbarUtils.showLongSnackbar(llSnackbar, sendEmailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
                            CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SnackbarUtils.dismissSnackbar();
                                }
                            });
                    break;
                case 201:
                    SnackbarUtils.showIndefiniteSnackbar(llSnackbar, sendEmailBean.getMsg(), CommonUtil.getColor(R.color.textcolo_299),
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
