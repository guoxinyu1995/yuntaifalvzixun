package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/4/20
 * @Des 我要投诉
 */
public class ComplaintActivity extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_aorao)
    TextView tvAorao;
    @BindView(R.id.iv_saorao)
    ImageView ivSaorao;
    @BindView(R.id.rl_saorao)
    RelativeLayout rlSaorao;
    @BindView(R.id.tv_xujia)
    TextView tvXujia;
    @BindView(R.id.iv_xujia)
    ImageView ivXujia;
    @BindView(R.id.rl_xujia)
    RelativeLayout rlXujia;
    @BindView(R.id.tv_qita)
    TextView tvQita;
    @BindView(R.id.iv_qita)
    ImageView ivQita;
    @BindView(R.id.rl_qita)
    RelativeLayout rlQita;
    @BindView(R.id.et_shuru)
    EditText etShuru;
    @BindView(R.id.tv_geshu)
    TextView tvGeshu;
    @BindView(R.id.tv_tijiao)
    TextView tvTijiao;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_complaint;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }
    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("我要投诉");
    }

    @Override
    protected void initData() {

        etShuru.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Editable text = etShuru.getText();
                int length = text.length();
                tvGeshu.setText(length+""+"/200字");
                if (length > 200) {
                    ToastUtil.showCenterToast( "超出字数限制");
                    int selEndIndex = Selection.getSelectionEnd(text);
                    String str = text.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 200);
                    etShuru.setText(newStr);
                    text = etShuru.getText();

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

    @OnClick({R.id.rl_saorao, R.id.rl_xujia, R.id.rl_qita, R.id.tv_tijiao,R.id.iv_fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_saorao:
                tvAorao.setTextColor(Color.parseColor("#44A2F7"));
                tvXujia.setTextColor(Color.parseColor("#666666"));
                tvQita.setTextColor(Color.parseColor("#666666"));
                ivSaorao.setVisibility(View.VISIBLE);
                ivXujia.setVisibility(View.GONE);
                ivQita.setVisibility(View.GONE);
                break;
            case R.id.rl_xujia:
                tvAorao.setTextColor(Color.parseColor("#666666"));
                tvXujia.setTextColor(Color.parseColor("#44A2F7"));
                tvQita.setTextColor(Color.parseColor("#666666"));
                ivSaorao.setVisibility(View.GONE);
                ivXujia.setVisibility(View.VISIBLE);
                ivQita.setVisibility(View.GONE);
                break;
            case R.id.rl_qita:
                tvAorao.setTextColor(Color.parseColor("#666666"));
                tvXujia.setTextColor(Color.parseColor("#666666"));
                tvQita.setTextColor(Color.parseColor("#44A2F7"));
                ivSaorao.setVisibility(View.GONE);
                ivXujia.setVisibility(View.GONE);
                ivQita.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_tijiao:
                if(TextUtils.isEmpty(etShuru.getText())){
                    ToastUtil.showCenterToast("问题描述为空");
                }else{
                    ToastUtil.showCenterToast("提交成功");
                }
                break;
            case R.id.iv_fanhui:
                finish();
                break;
        }
    }
}
