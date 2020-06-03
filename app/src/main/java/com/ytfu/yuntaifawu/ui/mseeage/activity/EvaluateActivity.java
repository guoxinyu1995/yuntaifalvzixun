package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.custom.RatingBar;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import per.wsj.library.AndRatingBar;

/**
 * @Auther gxy
 * @Date 2020/4/20
 * @Des 发表评价
 */
public class EvaluateActivity extends BaseActivity {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_pj)
    TextView tvPj;
    @BindView(R.id.rb)
    RatingBar rb;
    @BindView(R.id.ll_star)
    LinearLayout llStar;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.lly)
    LinearLayout lly;
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.tv_tj)
    TextView tvTj;
    @BindView(R.id.tv_tijiao)
    TextView tvTijiao;
    @BindView(R.id.iv_niming)
    ImageView ivNiming;
    boolean isChanged = false;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_evaluate;
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
        tvTopTitle.setText("发表评价");
    }

    @Override
    protected void initData() {
        rb.setClickable(true);//设置可否点击
        rb.setStar(5f);//设置显示的星星个数
        rb.setStepSize(RatingBar.StepSize.Full);//设置每次点击增加一颗星还是半颗星
        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Editable text = etText.getText();
                int length = text.length();
                tvTj.setText(length + "" + "/200字");
                if (length > 200) {
                    ToastUtil.showCenterToast("超出字数限制");
                    int selEndIndex = Selection.getSelectionEnd(text);
                    String str = text.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 200);
                    etText.setText(newStr);
                    text = etText.getText();

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

    @OnClick({R.id.iv_fanhui, R.id.tv_tijiao,R.id.iv_niming})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_tijiao:
                if (TextUtils.isEmpty(etText.getText())) {
                    ToastUtil.showCenterToast("输入为空");
                } else {
                    ToastUtil.showCenterToast("提交评论成功");
                }
                break;
            case R.id.iv_niming:
                if(isChanged){
                    ivNiming.setImageDrawable(getResources().getDrawable(R.drawable.weixuanzhong));
                }else
                {
                    ivNiming.setImageDrawable(getResources().getDrawable(R.drawable.xuanzhong));
                }
                isChanged = !isChanged;
                break;
        }
    }

}
