package com.ytfu.yuntaifawu.ui.home.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.consult.activity.XuanShangPayActivity;
import com.ytfu.yuntaifawu.ui.consult.bean.MIanFeiFaBuBean;
import com.ytfu.yuntaifawu.ui.consult.bean.ZiXunNavBean;
import com.ytfu.yuntaifawu.ui.consult.presenter.ZiXunNavPresenter;
import com.ytfu.yuntaifawu.ui.consult.view.IZiXunNavView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioNavBean;
import com.ytfu.yuntaifawu.ui.home.presenter.NavPresenter;
import com.ytfu.yuntaifawu.ui.home.view.INavView;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityMineZiXun;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/3/20
 * @Des 咨询fragment（咨询）
 */
public class ConsultFragment extends BaseFragment<IZiXunNavView, ZiXunNavPresenter> implements IZiXunNavView {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_wenti_type)
    TextView tvWentiType;
    @BindView(R.id.iv_type)
    ImageView ivType;
    @BindView(R.id.ed_wenti)
    EditText edWenti;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.iv_xuanshang)
    ImageView ivXuanshang;
    @BindView(R.id.tv_fabu)
    TextView tvFabu;
    @BindView(R.id.linear_layout)
    LinearLayout linearLayout;
    private PopupWindow popupWindow;
    private View popupView;
    // 声明平移动画
    private TranslateAnimation animation;
    private List<String> mOptionsItems = new ArrayList<>();

    private int lvshiNum;
    private int sum;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_consulp;
    }

    @Override
    protected ZiXunNavPresenter createPresenter() {
        return new ZiXunNavPresenter(getActivity());
    }

    public static ConsultFragment newInstance() {
        return new ConsultFragment();
    }

    @Override
    protected void initView(View rootView) {
        hideLoading();
        ivFanhui.setVisibility(View.GONE);
        tvTopTitle.setText("我要咨询");
        edWenti.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Editable text = edWenti.getText();
                int length = text.length();
                tvNumber.setText(length + "" + "/200");
                if (length > 200) {
                    ToastUtil.showCenterToast("超出字数限制");
                    int selEndIndex = Selection.getSelectionEnd(text);
                    String str = text.toString();
                    //截取新字符串
                    String newStr = str.substring(0, 200);
                    edWenti.setText(newStr);
                    text = edWenti.getText();

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

    @Override
    protected void initData() {
        mPresenter.setZiXunNav();
    }
    //初始化点击选择问题类型的变量
    private int t = 0;
    @OnClick({R.id.tv_wenti_type, R.id.iv_xuanshang, R.id.tv_fabu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wenti_type:
                t = 1;
                showDialog();
                lightoff();
                break;
            case R.id.iv_xuanshang:
                if (t != 0) {
                    if(i==-1){
                        showToast("请选择问题类型");
                        return;
                    }
                    if (!TextUtils.isEmpty(edWenti.getText().toString())) {
                        String edStr = edWenti.getText().toString();
//                        String tvStr = tvWentiType.getText().toString();
                        String tvStr = mOptionsItems.get(i);
                        Intent intent = new Intent(getActivity(), XuanShangPayActivity.class);
                        intent.putExtra("edStr", edStr);
                        intent.putExtra("tvStr", tvStr);
                        startActivity(intent);
                    } else {
                        showToast("输入不能为空");
                    }
                } else {
                    showToast("请选择问题类型");
                }
                break;
            case R.id.tv_fabu:
                if (t != 0) {
                    if (!TextUtils.isEmpty(edWenti.getText().toString())) {
                        if (i != -1) {
                            showFaBuDialog();
                        } else {
                            showToast("请选择问题类型");
                        }
                    } else {
                        showToast("输入不能为空");
                    }
                } else {
                    showToast("请选择问题类型");
                }
                break;
        }
    }

    //点击发布弹框
    private void showFaBuDialog() {
//1、使用Dialog、设置style
        Dialog dialog = new Dialog(getContext(), R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(getContext(), R.layout.dialog_fabu, null);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager m = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);
        dialog.show();
        TextView tv_lvshi_num = dialog.findViewById(R.id.tv_lvshi_num);
        TextView tv_jieda_num = dialog.findViewById(R.id.tv_jieda_num);
        TextView tv_mianfei = dialog.findViewById(R.id.tv_mianfei);
        TextView tv_xuanshang = dialog.findViewById(R.id.tv_xuanshang);
        tv_lvshi_num.setText("当前在线" + lvshiNum + "名律师");
        tv_jieda_num.setText(sum + "");
        tv_mianfei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWaitingDialog("发布中。。。", true);
                String uid = SpUtil.getString(AppConstant.UID, "");
                String edStr = edWenti.getText().toString();
//                String tvStr = tvWentiType.getText().toString();
                String tvStr = mOptionsItems.get(i);
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("consult_type", tvStr);
                map.put("consult_content", edStr);
                mPresenter.getMianFeiFabu(map);
                dialog.dismiss();
            }
        });
        tv_xuanshang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edStr = edWenti.getText().toString();
//                String tvStr = tvWentiType.getText().toString();
                String tvStr = mOptionsItems.get(i);
                Intent intent = new Intent(getActivity(), XuanShangPayActivity.class);
                intent.putExtra("edStr", edStr);
                intent.putExtra("tvStr", tvStr);
                startActivity(intent);
                dialog.dismiss();
            }
        });
    }

    //展示底部选择器
    private int i;

    private void showDialog() {
        //1、使用Dialog、设置style
        Dialog dialog = new Dialog(getContext(), R.style.DialogTheme);
        //2、设置布局
        View view = View.inflate(getContext(), R.layout.dialog_type_item, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        //设置弹出位置
        window.setGravity(Gravity.BOTTOM);
        //设置弹出动画
        window.setWindowAnimations(R.style.main_menu_animStyle);
        //设置对话框大小
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                lighton();
            }
        });
        WheelView wheelView = dialog.findViewById(R.id.wheel_view);
        wheelView.setTextSize(20);
        wheelView.setLineSpacingMultiplier(2f);
        // wheelView.setDividerWidth(6);
        wheelView.setDividerType(WheelView.DividerType.FILL);
        wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(int index) {
                i = index;
            }
        });
        dialog.findViewById(R.id.tv_quxiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    tvWentiType.setText("请选择问题类型");
                    i = -1;
                    lighton();
                }
            }
        });
        dialog.findViewById(R.id.tv_queding).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvWentiType.setText(mOptionsItems.get(i));
                dialog.dismiss();
                lighton();
            }
        });
    }

//    private void showPopup() {
//        popupView = View.inflate(getContext(), R.layout.dialog_type_item, null);
//        popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT,
//                WindowManager.LayoutParams.WRAP_CONTENT);
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                lighton();
//            }
//        });
//        // 设置背景图片， 必须设置，不然动画没作用
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.setFocusable(true);
//        // 设置点击popupwindow外屏幕其它地方消失
//        popupWindow.setOutsideTouchable(false);
//        popupWindow.setTouchable(true);
//        // 平移动画相对于手机屏幕的底部开始，X轴不变，Y轴从1变0
//        animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0,
//                Animation.RELATIVE_TO_PARENT, 1, Animation.RELATIVE_TO_PARENT, 0);
//        animation.setInterpolator(new AccelerateInterpolator());
//        animation.setDuration(200);
//        WheelView wheelView = popupView.findViewById(R.id.wheel_view);
//        wheelView.setTextSize(20);
//        wheelView.setLineSpacingMultiplier(2f);
//        // wheelView.setDividerWidth(6);
//        wheelView.setDividerType(WheelView.DividerType.FILL);
//        wheelView.setAdapter(new ArrayWheelAdapter(mOptionsItems));
//        wheelView.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(int index) {
//                i = index;
//            }
//        });
//        popupView.findViewById(R.id.tv_quxiao).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (popupWindow.isShowing()) {
//                    popupWindow.dismiss();
//                    lighton();
//                }
//            }
//        });
//        popupView.findViewById(R.id.tv_queding).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvWentiType.setText(mOptionsItems.get(i));
//                popupWindow.dismiss();
//                lighton();
//            }
//        });
//        // 设置popupWindow的显示位置，此处是在手机屏幕底部且水平居中的位置
//        popupWindow.showAtLocation(linearLayout, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//        popupView.startAnimation(animation);
//    }

    /**
     * 设置手机屏幕亮度变暗
     */
    private void lightoff() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.3f;
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * 设置手机屏幕亮度显示正常
     */
    private void lighton() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 1f;
        getActivity().getWindow().setAttributes(lp);
    }

    //咨询分类成功回调
    @Override
    public void onZiXunNavSuccess(ZiXunNavBean ziXunNavBean) {
        if (ziXunNavBean != null && ziXunNavBean.getList() != null) {
            List<ZiXunNavBean.ListBean> list = ziXunNavBean.getList();
            for (int j = 0; j < list.size(); j++) {
                mOptionsItems.add(list.get(j).getName());
            }
            //当前在线律师
            lvshiNum = ziXunNavBean.getLvshi();
            //当前咨询问题
            sum = ziXunNavBean.getSum();
        }
    }

    //发布成功回调
    @Override
    public void onMianFeiFaBuSuccess(MIanFeiFaBuBean mIanFeiFaBuBean) {
        hideWaitingDialog();
        if (mIanFeiFaBuBean != null) {
            if (mIanFeiFaBuBean.getStatus() == 200) {
                ToastUtil.showCenterToast(mIanFeiFaBuBean.getMsg());
                //跳转到我的咨询
                startActivity(new Intent(getActivity(), ActivityMineZiXun.class));
                i = -1;
            } else {
                ToastUtil.showCenterToast(mIanFeiFaBuBean.getMsg());
            }
        }
    }

    @Override
    public void onZiXunNavFiled() {

    }
}
