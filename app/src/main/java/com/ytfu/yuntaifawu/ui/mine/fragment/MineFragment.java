package com.ytfu.yuntaifawu.ui.mine.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.lee.annotation.InjectPresenter;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.db.LvShiDao;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityGrzx;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityLvShiRenZheng;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityMineZiXun;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityMyAudioLibrary;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityMyContractLibrary;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivitySetting;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityInvitationRecord;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityMineCollection;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityPurchaseRecord;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivitySheHeJinDu;
import com.ytfu.yuntaifawu.ui.mine.bean.MineBean;
import com.ytfu.yuntaifawu.ui.mine.bean.ShenHeJInduBean;
import com.ytfu.yuntaifawu.ui.mine.present.MinePresenter;
import com.ytfu.yuntaifawu.ui.mine.view.IMineView;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQszHistoryList;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/27
 * @Des 我的
 */
@InjectPresenter(MinePresenter.class)
public class MineFragment extends BaseFragment<IMineView, MinePresenter> implements IMineView {
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.cl_grzx)
    ConstraintLayout clGrzx;
    @BindView(R.id.tv_qsz_num)
    TextView tvQszNum;
    @BindView(R.id.ll_qsz)
    LinearLayout llQsz;
    @BindView(R.id.tv_audio_num)
    TextView tvAudioNum;
    @BindView(R.id.ll_audio)
    LinearLayout llAudio;
    @BindView(R.id.tv_zixun_num)
    TextView tvZiXunNum;
    @BindView(R.id.ll_zixun)
    LinearLayout llZixun;
    @BindView(R.id.iv_lvshirenzheng)
    ImageView ivLvshirenzheng;
    @BindView(R.id.cl_lvshirenzheng)
    ConstraintLayout clLvshirenzheng;
    @BindView(R.id.iv_gmjl)
    ImageView ivGmjl;
    @BindView(R.id.cl_gmjl)
    ConstraintLayout clGmjl;
    @BindView(R.id.iv_yqjl)
    ImageView ivYqjl;
    @BindView(R.id.cl_yqjl)
    ConstraintLayout clYqjl;
    @BindView(R.id.iv_sz)
    ImageView ivSz;
    @BindView(R.id.cl_sz)
    ConstraintLayout clSz;
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.v_1)
    View v1;
    @BindView(R.id.v_2)
    View v2;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_mine;
    }

//    @Override
//    protected MinePresenter createPresenter() {
//        return new MinePresenter(getContext());
//    }

    @Override
    public void onResume() {
        super.onResume();
        uid = SpUtil.getString(AppConstant.UID, "");
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().setMine(map);
    }

    @Override
    protected void initView(View rootView) {
        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        ivFanhui.setVisibility(View.GONE);
        tvTopTitle.setText("我的");
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().setMine(map);
    }

    @OnClick({R.id.cl_grzx, R.id.ll_qsz, R.id.ll_audio, R.id.ll_zixun, R.id.cl_lvshirenzheng, R.id.cl_gmjl, R.id.cl_yqjl, R.id.cl_sz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_grzx:
                //个人中心
                startActivity(new Intent(getActivity(), ActivityGrzx.class));
                break;
            case R.id.ll_qsz:
                //我的起诉状
                Intent intent = new Intent(getActivity(), ActivityQszHistoryList.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            case R.id.ll_audio:
                //合同
                startActivity(new Intent(getActivity(), ActivityMyAudioLibrary.class));
                break;
            case R.id.ll_zixun:
                //我的咨询
                startActivity(new Intent(getActivity(), ActivityMineZiXun.class));
                break;
            case R.id.cl_lvshirenzheng:
                //律师认证
                showWaitingDialog("加载中。。。", true);
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                getPresenter().getShengHeJIndu(map);
                break;
            case R.id.cl_gmjl:
                //购买记录
                startActivity(new Intent(getActivity(), ActivityPurchaseRecord.class));
                break;
            case R.id.cl_yqjl:
                startActivity(new Intent(getActivity(), ActivityInvitationRecord.class));
                break;
            case R.id.cl_sz:
                startActivity(new Intent(getActivity(), ActivitySetting.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onMineSuccess(MineBean mineBean) {
        hideLoading();
        if (mineBean != null) {
            MineBean.FindBean find = mineBean.getFind();
            LvShiDao.getInstance(getContext()).lvShiAdd(uid, find.getUser_login(), find.getAvatar(), null, null);
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                    .fallback(R.drawable.touxiang) //url为空的时候,显示的图片
                    .error(R.drawable.touxiang);//图片加载失败后，显示的图片
            Glide.with(getContext()).load(find.getAvatar())
                    .apply(options)
                    .into(ivHeader);
            tvName.setText(find.getUser_login());
            tvQszNum.setText(find.getQsz_count());
//            tvAudioNum.setText(find.getAudio_count());
            tvZiXunNum.setText(find.getZixun());
        }
    }

    //判断律师认证是否在审核
    @Override
    public void onShenHeJinSuccess(ShenHeJInduBean shenHeJInduBean) {
        hideWaitingDialog();
        if (shenHeJInduBean != null) {
            int status = shenHeJInduBean.getStatus();
            if (status == 0) {
                startActivity(new Intent(getActivity(), ActivityLvShiRenZheng.class));
            } else {
                Intent intent = new Intent(getActivity(), ActivitySheHeJinDu.class);
                intent.putExtra("status", status);
                intent.putExtra("name", shenHeJInduBean.getName());
                intent.putExtra("photo", shenHeJInduBean.getPhoto());
                intent.putExtra("liyou", shenHeJInduBean.getLiyou());
                intent.putExtra("yuanyin", shenHeJInduBean.getYuanyin());
                startActivity(intent);
            }
        }
    }

    @Override
    public void onMineFiled() {

    }
}
