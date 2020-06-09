package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.chatroom.activity.UserChatRoomActivity;
import com.ytfu.yuntaifawu.ui.custom.MyRadioGroup;
import com.ytfu.yuntaifawu.ui.lvshiwode.activity.LawyerRenZhengInforActivity;
import com.ytfu.yuntaifawu.ui.mseeage.adaper.LvShiDetailsPingJiaAdaper;
import com.ytfu.yuntaifawu.ui.mseeage.adaper.LvShiDetailsSuccessAnliAdaper;
import com.ytfu.yuntaifawu.ui.mseeage.adaper.LvShiDetailsTaHuiDaAdaper;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiDetailsBean1;
import com.ytfu.yuntaifawu.ui.mseeage.presenter.LvShiDetailsPresent;
import com.ytfu.yuntaifawu.ui.mseeage.view.ILvShiDetailsView;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQiSuZhuang;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/4/15
 * @Des 律师详情
 */
public class LvShiDetailsActivity extends BaseActivity<ILvShiDetailsView, LvShiDetailsPresent> implements ILvShiDetailsView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.iv_detail_head)
    ImageView ivDetailHead;
    @BindView(R.id.iv_detail_xb)
    ImageView ivDetailXb;
    @BindView(R.id.tv_detail_name)
    TextView tvDetailName;
    //    @BindView(R.id.tv_detail_lingyu)
//    TextView tvDetailLingyu;
    @BindView(R.id.tv_detail_dengji)
    TextView tvDetailDengji;
    @BindView(R.id.tv_detail_xueli)
    TextView tvDetailXueli;
    @BindView(R.id.tv_detail_nianling)
    TextView tvDetailNianling;
    @BindView(R.id.tv_detail_jianjie)
    TextView tvDetailJianjie;
    @BindView(R.id.rl_success_anli)
    RecyclerView rlSuccessAnli;
    @BindView(R.id.tv_success_quanbu)
    TextView tvSuccessQuanbu;
    @BindView(R.id.tv_user_pingjia)
    TextView tvUserPingjia;
    @BindView(R.id.rl_pingjia)
    RecyclerView rlPingjia;
    @BindView(R.id.tv_pingjia_quanbu)
    TextView tvPingjiaQuanbu;
    @BindView(R.id.tv_ta_huida)
    TextView tvTaHuida;
    @BindView(R.id.rl_huida)
    RecyclerView rlHuida;
    @BindView(R.id.tv_huida_quanbu)
    TextView tvHuidaQuanbu;
    @BindView(R.id.tv_da_guansi)
    TextView tvDaGuansi;
    @BindView(R.id.tv_liji_zixun)
    TextView tvLijiZixun;
    @BindView(R.id.ll_liji_zixun)
    LinearLayout llLijiZixun;
    @BindView(R.id.myradiogroup)
    MyRadioGroup myradiogroup;
    @BindView(R.id.ll_user)
    LinearLayout llUser;
    @BindView(R.id.tv_lawyer_infor)
    TextView tvLawyerInfor;
    //    @BindView(R.id.ll_list)
//    LinearLayout llList;
    private String lid;
    private LvShiDetailsSuccessAnliAdaper lvShiDetailsSuccessAnliAdaper;
    private LvShiDetailsPingJiaAdaper lvShiDetailsPingJiaAdaper;
    private LvShiDetailsTaHuiDaAdaper lvShiDetailsTaHuiDaAdaper;
    private String name;
    private String lingyu;
    private String lvshitype;
    private String xueli;
    private String cyear;
    private String picurl;
    private String userName;
    private String info_img;
    private ViewGroup.MarginLayoutParams layoutParams;
    private int types;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_lvshidetails;
    }

//    @Override
//    protected View provideLoadServiceRootView() {
//        return llList;
//    }

    @Override
    protected LvShiDetailsPresent createPresenter() {
        return new LvShiDetailsPresent(this);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
//        hideLoading();
        userName = getIntent().getStringExtra("userName");
        //types   1 点击首页item进入律师详情  2 从聊天页面点击头像和顶部卡片进入详情
        types = getIntent().getIntExtra("types", 0);
//        showToast(lid);
        tvTopTitle.setText("律师详情");
        layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = 20;
        layoutParams.rightMargin = 20;
        layoutParams.topMargin = 15;
        layoutParams.bottomMargin = 10;
        String identity = SpUtil.getString(AppConstant.SHENFEN, "1");
        if ("1".equals(identity)) {
            llUser.setVisibility(View.VISIBLE);
            tvLawyerInfor.setVisibility(View.GONE);
        } else {
            llUser.setVisibility(View.GONE);
            tvLawyerInfor.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initData() {
        String lid = getIntent().getStringExtra("lid");
        HashMap<String, String> map = new HashMap<>();
        map.put("lid", lid);
        getPresenter().getLvShiDetails(map);
        lvShiDetailsSuccessAnliAdaper = new LvShiDetailsSuccessAnliAdaper(this);
        lvShiDetailsPingJiaAdaper = new LvShiDetailsPingJiaAdaper(this);
        lvShiDetailsTaHuiDaAdaper = new LvShiDetailsTaHuiDaAdaper(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        rlSuccessAnli.setLayoutManager(linearLayoutManager);
        rlPingjia.setLayoutManager(linearLayoutManager1);
        rlHuida.setLayoutManager(linearLayoutManager2);
        rlSuccessAnli.setAdapter(lvShiDetailsSuccessAnliAdaper);
        rlPingjia.setAdapter(lvShiDetailsPingJiaAdaper);
        rlHuida.setAdapter(lvShiDetailsTaHuiDaAdaper);
    }

    @OnClick({R.id.iv_detail_head, R.id.iv_fanhui, R.id.tv_success_quanbu, R.id.tv_pingjia_quanbu, R.id.tv_huida_quanbu, R.id.tv_da_guansi, R.id.ll_liji_zixun, R.id.tv_lawyer_infor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_detail_head:
                Intent intent = new Intent(LvShiDetailsActivity.this, LvShiDaTuActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("lingyu", lingyu);
                intent.putExtra("lvshitype", lvshitype);
                intent.putExtra("xueli", xueli);
                intent.putExtra("cyear", cyear);
                intent.putExtra("info_img", info_img);
                startActivity(intent);
                break;
            case R.id.tv_success_quanbu:
                Intent intentSuccess = new Intent(LvShiDetailsActivity.this, SuccessAnLiActivity.class);
                intentSuccess.putExtra("lid", lid);
                startActivity(intentSuccess);
                break;
            case R.id.tv_pingjia_quanbu:
                Intent intentPingJia = new Intent(LvShiDetailsActivity.this, UserEvaluateActivity.class);
                intentPingJia.putExtra("lid", lid);
                startActivity(intentPingJia);
                break;
            case R.id.tv_huida_quanbu:
                Intent intentTa = new Intent(LvShiDetailsActivity.this, TaHuiDaActivity.class);
                intentTa.putExtra("lid", lid);
                intentTa.putExtra("userName", userName);
                startActivity(intentTa);
                break;
            case R.id.tv_da_guansi:
                Intent intentGuanSi = new Intent(LvShiDetailsActivity.this, ActivityQiSuZhuang.class);
                startActivity(intentGuanSi);
                break;
            case R.id.ll_liji_zixun:
                if (types == 1) {
                    String toUserId = lid;
                    String toUserName = userName;
                    String toUserAvatar = picurl;
                    UserChatRoomActivity.start(LvShiDetailsActivity.this,
                            false, false,
                            toUserId, toUserName, toUserAvatar);
                } else if (types == 2) {
                    finish();
                }
                break;
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_lawyer_infor:
                LawyerRenZhengInforActivity.start(this);
                break;
        }
    }

    Bitmap a = null;

    @Override
    public void onLvShiDetailsSuccess(LvShiDetailsBean1 lvShiDetailsBean) {
        hideLoading();
        //  || lvShiDetailsBean.getData().getAnli_content().isEmpty() || lvShiDetailsBean.getData().getPinglun().isEmpty() || lvShiDetailsBean.getData().getAnswser().isEmpty()
        if (lvShiDetailsBean == null || lvShiDetailsBean.getData() == null) {
            showEmpty();
        } else {
            LvShiDetailsBean1.DataBean data = lvShiDetailsBean.getData();
            name = data.getName();
            lingyu = data.getLingyu();
            lvshitype = data.getLvshitype();
            xueli = data.getXueli();
            cyear = data.getCyear();
            picurl = data.getPicurl();
            info_img = data.getInfo_img();
            tvDetailName.setText(data.getName());
//            tvDetailLingyu.setText(data.getLingyu());
            for (int i = 0; i < data.getXunhuan().size(); i++) {
                RadioButton button = new RadioButton(this);
                button.setButtonDrawable(new BitmapDrawable(a));
                button.setText(data.getXunhuan().get(i));
                button.setBackgroundResource(R.drawable.lvshi_details_pubu_shape);
                button.setTextColor(Color.parseColor("#67C6A3"));
                button.setEnabled(false);
                myradiogroup.addView(button, layoutParams);
            }
            tvDetailDengji.setText(data.getLvshitype());
            tvDetailXueli.setText(data.getXueli());
            tvDetailNianling.setText(data.getCyear());
            tvDetailJianjie.setText(data.getJianjie());
            GlideManager.loadImageByUrl(this, data.getPicurl(), ivDetailHead);
            tvUserPingjia.setText("用户评价" + "(" + data.getPinglunnum() + ")");
            tvTaHuida.setText("Ta的回答" + "(" + data.getAnswernum() + ")");
            String sex = data.getSex();
            if (!TextUtils.isEmpty(sex)) {
                switch (sex) {
                    case "1":
                        ivDetailXb.setBackgroundResource(R.drawable.xingbie_boy);
                        break;
                    case "2":
                        ivDetailXb.setBackgroundResource(R.drawable.xingbie_gril);
                        break;
                }
            }
            if (data.getAnli_content() != null) {
                lvShiDetailsSuccessAnliAdaper.setAnliContentBeans(data.getAnli_content());

            }
            if (data.getPinglun() != null) {
                lvShiDetailsPingJiaAdaper.setPinglunBeanList(data.getPinglun());

            }
            if (data.getAnswser() != null) {
                lvShiDetailsTaHuiDaAdaper.setAnswserBeanList(data.getAnswser());

            }


        }
    }

    @Override
    public void onLvShiDetailsFiled() {

    }

}
