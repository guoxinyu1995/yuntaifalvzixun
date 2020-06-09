package com.ytfu.yuntaifawu.ui.mine.activity;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.custom.XCFlowLayoutTv;
import com.ytfu.yuntaifawu.ui.mine.bean.ShangChangLingYuBean;
import com.ytfu.yuntaifawu.ui.mine.present.ShangChangLingYuPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.IShanchanglingyuView;
import com.ytfu.yuntaifawu.ui.mseeage.bean.SuccessAnLiBean;
import com.ytfu.yuntaifawu.ui.mseeage.view.ISuccessAnLiView;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/5/21
 * @Des 律师擅长领域
 */
public class ActivityShangChangLingYu extends BaseActivity<IShanchanglingyuView, ShangChangLingYuPresenter> implements IShanchanglingyuView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_qd)
    TextView tvQd;
    //    @BindView(R.id.id_flowlayout)
//    TagFlowLayout idFlowlayout;
    @BindView(R.id.flow_layout)
    XCFlowLayoutTv flow_layout;
    private LayoutInflater mInflater;
    private List<String> list;
    private String s1;
    private String tr;
    private Set<String> selectedSet = new HashSet<>();
    private ViewGroup.MarginLayoutParams lp;
    private List<String> selectedList = new ArrayList<>();

    @Override
    public void init() {
        super.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
//      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.transparent_f2));

        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_shangchaung_lingyu;
    }

    @Override
    protected ShangChangLingYuPresenter createPresenter() {
        return new ShangChangLingYuPresenter(this);
    }

    @Override
    protected void initView() {
        hideLoading();
        mInflater = LayoutInflater.from(this);
        lp = new ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 20;
        lp.rightMargin = 20;
        lp.topMargin = 15;
        lp.bottomMargin = 10;
    }

    @Override
    protected void initData() {
        getPresenter().getShangChangLingYu();
//        idFlowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                s1 = list.get(position);
//                return true;
//            }
//        });
//        idFlowlayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
//            @Override
//            public void onSelected(Set<Integer> selectPosSet) {
////                Set<Integer> selectedList = idFlowlayout.getSelectedList();
//                for (Integer str : selectPosSet) {
//                    String s = list.get(str);
//                    selectedSet.add(s);
//                }
//            }
//        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showRemind("当前内容还没有保存是否退出");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.iv_fanhui, R.id.tv_qd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                showRemind("当前内容还没有保存是否退出");
                break;
            case R.id.tv_qd:
                if (selectedList.size() != 0) {
                    StringBuffer stringBuffer = new StringBuffer();
                    for (String str : selectedList) {
                        stringBuffer.append("," + str);
                    }
                    stringBuffer.deleteCharAt(0);
                    EventBus.getDefault().post(new MessageEvent(AppConstant.LVSHI_RENZHENG_SAHNGCHANGLINGYU, stringBuffer.toString()));
                    finish();
                } else {
                    showCenterToast("请选择您擅长的领域");
                }

                break;
        }
    }

    @Override
    public void onShangChangLingYuSuccess(ShangChangLingYuBean shangChangLingYuBean) {
        if (shangChangLingYuBean != null && shangChangLingYuBean.getList() != null) {
            list = shangChangLingYuBean.getList();
//            idFlowlayout.setAdapter(new TagAdapter<String>(list) {
//                @Override
//                public View getView(FlowLayout parent, int position, String s) {
//                    TextView tv = (TextView) mInflater.inflate(R.layout.tv,
//                            idFlowlayout, false);
//                    tv.setText(s);
//                    return tv;
//                }
//            });
            for (int i = 0; i < list.size(); i++) {
                CheckBox view = new CheckBox(this);
                view.setText(list.get(i));
                view.setButtonDrawable(null);
                int finalI = i;
                view.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            if (!selectedList.contains(list.get(finalI))) {
                                selectedList.add(list.get(finalI));
                            }
                        } else {
                            if (selectedList.contains(list.get(finalI))) {
                                selectedList.remove(list.get(finalI));
                            }
                        }
                    }
                });
                ColorStateList csl = getResources().getColorStateList(R.color.text_color);
                view.setTextColor(csl);
                view.setBackgroundDrawable(getResources().getDrawable(R.drawable.tag_bg));
                flow_layout.addView(view, lp);
            }
        }
    }

    @Override
    public void onShangChangLingYuFiled() {

    }
}
