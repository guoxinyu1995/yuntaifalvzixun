package com.ytfu.yuntaifawu.ui.kaitingzhushou.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityFirmlwtg;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper.KtzsXqTitleListAdaper;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsXqListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter.KtzsXqListPresenter;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IKtzsXqListView;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityZjqdAddList;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;
import com.ytfu.yuntaifawu.utils.SnackbarUtils;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;

/**
 * @Auther gxy
 * @Date 2019/11/22
 * @Des 开庭助手详情fragment
 */
public class FragmentKTZSXQList extends BaseFragment<IKtzsXqListView, KtzsXqListPresenter> implements IKtzsXqListView {
    @BindView(R.id.recycle_ktzs_xq_list)
    RecyclerView recycleKtzsXqList;
    @BindView(R.id.snackbar_ll)
    LinearLayout snackbarLl;
    private String id;
    private KtzsXqTitleListAdaper titleListAdaper;
    private String sid;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_ktzs_xq_list;
    }

    @Override
    protected KtzsXqListPresenter createPresenter() {
        return new KtzsXqListPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != getArguments()) {
//            indicator_tag = getArguments().getInt("indicator_tag");
            id = getArguments().getString("id");
            sid = getArguments().getString("sid");
            Log.e("sid", "=+++++++++" + sid);
        }
        if (!TextUtils.isEmpty(id) || !TextUtils.isEmpty(sid)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("sid", sid);
            getPresenter().setKtzsZqList(map);
        }
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("sid", sid);
        getPresenter().setKtzsZqList(map);
    }

    @Override
    protected void initView(View rootView) {
//        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleKtzsXqList.addItemDecoration(new MyItemDecoration2(0f, 0f));
        recycleKtzsXqList.setLayoutManager(layoutManager);

    }

    @Override
    protected void initData() {
        titleListAdaper = new KtzsXqTitleListAdaper(getContext());
        recycleKtzsXqList.setAdapter(titleListAdaper);
        titleListAdaper.setXqItemClickListener(new KtzsXqTitleListAdaper.IXqTitleClickListener() {
            @Override
            public void onXqTitleItemClickListenet(String url) {
                Log.e("zurl","------"+url);
//                showToast("开庭助手详情发送邮箱"+url);
                if (TextUtils.isEmpty(url)) {
                    showToast("文件地址为空");
                } else {
                    showWaitingDialog("发送中...", true);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("url", url);
                    map.put("uid", uid);
                    getPresenter().setSendEmail(map);
                }
            }
        });

    titleListAdaper.setXqJmItemClickListener(new KtzsXqTitleListAdaper.IXqJmTitleClickListener() {
        @Override
        public void onXqTitleItemClickListenet(String name, String s1id) {
            if(name.equals("企业需另外提供")){
                            Intent intent = new Intent(getContext(), ActivityFirmlwtg.class);
                            intent.putExtra("id",s1id);
                            intent.putExtra("name",name);
                            startActivity(intent);
                        }else{
                            Intent intent = new Intent(getContext(), ActivityZjqdAddList.class);
                            intent.putExtra("id",sid);
                            intent.putExtra("name",name);
                           startActivity(intent);
                        }
        }
    });
    }

    @Override
    public void onSuccess(KtzsXqListBean xqListBean) {
        hideLoading();
        if (xqListBean == null || xqListBean.getList() == null || xqListBean.getList().isEmpty()) {
            showEmpty();
        } else {
            titleListAdaper.setList(xqListBean.getList());
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
    public void onKtzsBdEmail(BdEmailBean bdEmailBean) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View view = View.inflate(getContext(), R.layout.view_alert_dialog_confirm_email, null);
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
                        getPresenter().setKtzsBdEmail(map);
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
        WindowManager m = getActivity().getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);
    }
}
