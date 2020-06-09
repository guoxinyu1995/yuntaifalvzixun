package com.ytfu.yuntaifawu.ui.lvshiwode.fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.lxj.xpopup.util.XPopupUtils;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.db.LvShiDao;
import com.ytfu.yuntaifawu.ui.LvShiMainActivity;
import com.ytfu.yuntaifawu.ui.lawyer.setting.act.LawyerSettingActivity;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.act.TransactionActivity;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.act.WalletActivity;
import com.ytfu.yuntaifawu.ui.lvshiwode.activity.LawyerNoticeActivity;
import com.ytfu.yuntaifawu.ui.lvshiwode.presenter.LawyerPersonalPresenter;
import com.ytfu.yuntaifawu.ui.lvshiwode.view.LawyerPersonalView;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityMineCollection;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivitySetting;
import com.ytfu.yuntaifawu.ui.mine.bean.MineBean;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.Random;

import butterknife.BindView;

/**
 * @Auther gxy
 * @Date 2020/5/25
 * @Des 律师端我的fragment
 */
public class LvShiWodeFragment extends BaseFragment<LawyerPersonalView, LawyerPersonalPresenter>
        implements LawyerPersonalView {
    @BindView(R.id.tl_personal_toolbar)
    Toolbar tl_personal_toolbar;
    @BindView(R.id.tv_personal_title)
    TextView tv_personal_title;

    @BindView(R.id.iv_personal_avatar)
    ImageView iv_personal_avatar;
    @BindView(R.id.tv_personal_name)
    TextView tv_personal_name;
    @BindView(R.id.srl_personal_refresh)
    SwipeRefreshLayout srl_personal_refresh;
//    private String uid;
    //===Desc:=================================================================

    public static LvShiWodeFragment newInstance() {

        return new LvShiWodeFragment();
    }

    //===Desc:=================================================================

    @Override
    protected int provideContentViewId() {
        return R.layout.lvshi_wode_fragment;
    }

    @Override
    protected LawyerPersonalPresenter createPresenter() {
        return new LawyerPersonalPresenter();
    }


    @Override
    protected void initView(View rootView) {
        //设置
        rootView.findViewById(R.id.tv_personal_setting)
                .setOnClickListener(v ->
                        LawyerSettingActivity.start(getContext())
                );
        //钱包
        rootView.findViewById(R.id.tv_personal_wallet)
                .setOnClickListener(v ->
                        WalletActivity.start(getContext())
                );
        //我的咨询
        rootView.findViewById(R.id.tv_main_zixun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LvShiMainActivity activity = (LvShiMainActivity) getActivity();
                activity.changeTagStatus(1);
            }
        });
        //我的合同
        rootView.findViewById(R.id.tv_main_ht).setOnClickListener(v ->
                ActivityMineCollection.start(getContext())
        );
        //律师须知
        rootView.findViewById(R.id.lvshi_xuzhi).setOnClickListener(v ->
                LawyerNoticeActivity.start(getContext())
        );
    }

    @Override
    protected void initData() {
        tl_personal_toolbar.setTitle("");
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tl_personal_toolbar.getLayoutParams();
        layoutParams.topMargin = getStatusHeight();
        tl_personal_toolbar.requestLayout();
        tv_personal_title.setText(getString(R.string.txt_mine));

        srl_personal_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //请求数据
                String uid = SpUtil.getString(AppConstant.UID, "");
                getPresenter().requestPersonal(uid);
            }
        });
        srl_personal_refresh.setColorSchemeColors(
                generateBeautifulColor(),
                generateBeautifulColor(),
                generateBeautifulColor(),
                generateBeautifulColor()
        );
        srl_personal_refresh.setRefreshing(true);
        //请求数据
       String uid = SpUtil.getString(AppConstant.UID, "");
        getPresenter().requestPersonal(uid);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        //请求数据
        String uid = SpUtil.getString(AppConstant.UID, "");
        getPresenter().requestPersonal(uid);
    }

    //===Desc:=================================================================
    private int getStatusHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    @Override
    public void onGetPersonalSuccess(MineBean loginBean) {
        String uid = SpUtil.getString(AppConstant.UID, "");
        MineBean.FindBean bean = loginBean.getFind();
        LvShiDao.getInstance(getContext()).lvShiAdd(uid, bean.getUser_login(), bean.getAvatar(), null, null);
        tv_personal_name.setText(bean.getUser_login());
        RoundedCorners rc = new RoundedCorners(XPopupUtils.dp2px(getContext(), 4));
        RequestOptions options = RequestOptions.bitmapTransform(rc)
                .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                .fallback(R.drawable.touxiang) //url为空的时候,显示的图片
                .error(R.drawable.touxiang);//图片加载失败后，显示的图片
        Glide.with(this).load(bean.getAvatar())
                .apply(options)
                .into(iv_personal_avatar);

        srl_personal_refresh.setRefreshing(false);
        hideLoading();
    }

    @Override
    public void onGetPersonalFail(String errorMsg) {
        srl_personal_refresh.setRefreshing(false);
        showError();
    }

    //===Desc:=================================================================
    @ColorInt
    private int generateBeautifulColor() {
        Random random = new Random();
        int r = random.nextInt(150) + 50;
        int g = random.nextInt(150) + 50;
        int b = random.nextInt(150) + 50;
        return Color.rgb(r, g, b);
    }
}
