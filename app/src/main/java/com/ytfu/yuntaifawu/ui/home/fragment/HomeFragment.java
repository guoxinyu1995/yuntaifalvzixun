package com.ytfu.yuntaifawu.ui.home.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviser;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityClassification;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityContractList;
import com.ytfu.yuntaifawu.ui.home.adaper.RecommendTitleAdaper;
import com.ytfu.yuntaifawu.ui.home.bean.HomeBannerBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomeLvShiBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomePingJIaBean;
import com.ytfu.yuntaifawu.ui.home.bean.RecommendListBean;
import com.ytfu.yuntaifawu.ui.home.presenter.RecommendPresenter;
import com.ytfu.yuntaifawu.ui.home.view.IRecommendView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityOpenHelper;
import com.ytfu.yuntaifawu.ui.mseeage.activity.EaseConversationListActivity;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQiSuZhuang;
import com.ytfu.yuntaifawu.utils.DemoHelper;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  首页
*
*/
public class HomeFragment extends BaseFragment<IRecommendView, RecommendPresenter> implements IRecommendView,View.OnClickListener {

    private LinearLayout lin_audio, lin_contract, lin_flgw,lin_qsz,lin_ktzs;
    private Banner banner;
    private RecyclerView recycle_title;
    private List<String> bannerList = new ArrayList<>();
    private RecommendTitleAdaper titleAdaper;
    private ImageView iv_contract;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected RecommendPresenter createPresenter() {
        return new RecommendPresenter(getContext());
    }

    @Override
    protected void initView(View rootView) {
        banner = rootView.findViewById(R.id.banner);
        lin_qsz = rootView.findViewById(R.id.lin_qsz);
        lin_audio = rootView.findViewById(R.id.lin_audio);
        lin_ktzs = rootView.findViewById(R.id.lin_ktzs);
//        lin_contract = rootView.findViewById(R.id.lin_contract);
        lin_flgw = rootView.findViewById(R.id.lin_flgw);
        recycle_title = rootView.findViewById(R.id.recycle_title);
        iv_contract = rootView.findViewById(R.id.iv_contract);
        lin_audio.setOnClickListener(this);
        lin_ktzs.setOnClickListener(this);
        lin_flgw.setOnClickListener(this);
        lin_qsz.setOnClickListener(this);
        iv_contract.setOnClickListener(this);
//        lin_contract.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), ActivityContractList.class);
//                getActivity().startActivity(intent);
//            }
//        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_title.setLayoutManager(linearLayoutManager);
        //设置样式，里面有很多种样式可以自己都看看效果
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        banner.setBannerAnimation(Transformer.ZoomOutSlide);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        banner.setIndicatorGravity(BannerConfig.CENTER);
    }

    @Override
    protected void initData() {
        titleAdaper = new RecommendTitleAdaper(getContext());
        recycle_title.setAdapter(titleAdaper);
        getPresenter().getRecommendList();
        getPresenter().getBannerList();
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        getPresenter().getRecommendList();
        getPresenter().getBannerList();
    }

    @Override
    public void onRecommendSuccess(RecommendListBean listBeans) {
        hideLoading();
        if (listBeans != null || listBeans.getList() != null || !listBeans.getList().isEmpty()) {
//            List<RecommendListBean.HengfuBean> hengfu = listBeans.get(0).getHengfu();
//            List<RecommendListBean.ShufuBean> shufu = listBeans.get(0).getShufu();
            titleAdaper.setmList(listBeans.getList());
        }
    }

    @Override
    public void onRecommendFiled() {
        showToast("网络开小差");
    }

    @Override
    public void onBannerSuccess(HomeBannerBean bannerBeans) {
        if (bannerBeans == null || bannerBeans.getList()==null || bannerBeans.getList().isEmpty()) {
            Logger.e("bannerBeans is empty");
        } else {
            GlideManager.loadImageByUrl(getContext(),bannerBeans.getUrl(),iv_contract);
            bannerList.clear();
            List<HomeBannerBean.ListBean> list = bannerBeans.getList();
            for (int i = 0; i < list.size(); i++) {
                bannerList.add(list.get(i).getPic());
            }
            //设置图片加载器
            banner.setImageLoader(new MyImageLoader());
            //设置图片集合
            banner.setImages(bannerList);
            banner.start();
        }
    }

    @Override
    public void onHomeLvShiSuccess(HomeLvShiBean homeLvShiBean) {

    }

    @Override
    public void onHomePingJiaSuccess(HomePingJIaBean homePingJIaBean) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_audio:
                //&& DemoHelper.getInstance().isLoggedIn()
                if(App.getInstance().getLoginFlag() ){
                    Intent intent = new Intent(getActivity(), ActivityContractList.class);
                    getActivity().startActivity(intent);
                }else{
                    toLogin();
                }
                break;
            case R.id.lin_qsz:
                if(App.getInstance().getLoginFlag()){
                    getActivity().startActivity(new Intent(getActivity(), ActivityQiSuZhuang.class));
                }else{
                    toLogin();
                }

                break;
            case R.id.lin_ktzs:
                if(App.getInstance().getLoginFlag()){
                    getActivity().startActivity(new Intent(getActivity(), ActivityOpenHelper.class));
                }else{
                    toLogin();
                }
                break;
            case R.id.lin_flgw:
                if(App.getInstance().getLoginFlag()){
                    getActivity().startActivity(new Intent(getActivity(), ActivityLegalAdviser.class));
                }else{
                    toLogin();
                }
                break;
            case R.id.iv_contract:
                if(App.getInstance().getLoginFlag()){
                    getActivity().startActivity(new Intent(getActivity(), ActivityContractList.class));
                }else{
                    toLogin();
                }
                break;
                default:
                    break;
        }
    }

    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);

        }
    }

}
