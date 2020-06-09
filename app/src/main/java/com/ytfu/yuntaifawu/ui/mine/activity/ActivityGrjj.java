package com.ytfu.yuntaifawu.ui.mine.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mine.bean.GeQianBean;
import com.ytfu.yuntaifawu.ui.mine.present.GeQianPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.IGeQianView;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/30
 * @Des 个人简介
 */
public class ActivityGrjj extends BaseActivity<IGeQianView, GeQianPresenter> implements IGeQianView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_qd)
    TextView tvQd;
    @BindView(R.id.et_grjj)
    EditText etGrjj;
    @BindView(R.id.tv_sr_num)
    TextView tvSrNum;
    private int num = 50;//限制的最大字数
    private String uid;
    private String et_str;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_grjj;
    }

    @Override
    protected GeQianPresenter createPresenter() {
        return new GeQianPresenter(this);
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
        tvTopTitle.setText("个性签名");
        String signature = getIntent().getStringExtra("signature");
        etGrjj.setText(signature);
        et_str = etGrjj.getText().toString();
        int length = et_str.length();
        tvSrNum.setText(length+""+"/50字");
    }

    @Override
    protected void initData() {
        etGrjj.addTextChangedListener(new TextWatcher() {
//            private CharSequence temp;
//            private int selectionStart;
//            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                int number = num - s.length();
//                tvSrNum.setText(number + "字");
//                selectionStart = etGrjj.getSelectionStart();
//                selectionEnd = etGrjj.getSelectionEnd();
//                //System.out.println("start="+selectionStart+",end="+selectionEnd);
//                if (temp.length() > num) {
//                    s.delete(selectionStart - 1, selectionEnd);
//                    int tempSelection = selectionStart;
//                    etGrjj.setText(s);
//                    etGrjj.setSelection(tempSelection);//设置光标在最后
//                }
                Editable text = etGrjj.getText();
                int length = text.length();
                tvSrNum.setText(length+""+"/50字");
                if (length > 50) {
                    ToastUtil.showCenterToast( "超出字数限制");
                    int selEndIndex = Selection.getSelectionEnd(text);
                    String str = text.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 50);
                    etGrjj.setText(newStr);
                    text = etGrjj.getText();

                    //新字符串的长度
                    int newLen = text.length();
                    //旧光标位置超过字符串长度
                    if (selEndIndex > newLen) {
                        selEndIndex = text.length();
                    }
                    //设置新光标所在的位置
                    Selection.setSelection(text, selEndIndex);
                }

            }
        });
    }


    @OnClick({R.id.iv_fanhui, R.id.tv_qd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_qd:
                setData();
                break;
        }
    }

    private void setData() {
        String grjj = etGrjj.getText().toString();
        if (TextUtils.isEmpty(grjj)) {
            ToastUtil.showCenterToast("输入为空");
        } else {
            showWaitingDialog("修改中...");
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("geqian", grjj);
            getPresenter().setGeQian(map);
        }
    }

    @Override
    public void onGqSuccess(GeQianBean geQianBean) {
        hideWaitingDialog();
        if (geQianBean != null) {
            int status = geQianBean.getStatus();
            if(status == 1){
                ToastUtil.showCenterToast("修改成功");
                finish();
            }else if(status == 0){
                ToastUtil.showCenterToast("修改失败");
            }else if(status == 2){
                ToastUtil.showCenterToast(geQianBean.getMsg());
            }
        }
    }

    @Override
    public void onGqFiled() {

    }
}
