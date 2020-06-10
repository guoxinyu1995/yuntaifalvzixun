package com.ytfu.yuntaifawu.ui.users.header;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.ui.MainActivity;
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviser;
import com.ytfu.yuntaifawu.ui.home.bean.HomeBannerBean;
import com.ytfu.yuntaifawu.ui.home.fragment.HomeFragment1;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityOpenHelper;
import com.ytfu.yuntaifawu.ui.login.activity.LoginCodeActivity;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQiSuZhuang;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.ToastUtil;
import com.ytfu.yuntaifawu.utils.header.base.BaseHeaderController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2020/6/9
 * @Des 用户首页header
 */
public class UserHomeHeader extends BaseHeaderController<HomeBannerBean> {
    private List<String> bannerList = new ArrayList<>();

    public UserHomeHeader(Context mContext) {
        super(mContext);
    }

    @Override
    protected View onCreateHeaderView() {
        return inflateView(R.layout.user_home_header_view);
    }

    @Override
    public void render(HomeBannerBean data) {
        super.render(data);
        Banner banner = findHeaderViewById(R.id.banner);
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
        bannerList.clear();
        List<HomeBannerBean.ListBean> list = data.getList();
        for (int i = 0; i < list.size(); i++) {
            bannerList.add(list.get(i).getPic());
        }
        //设置图片加载器
        banner.setImageLoader(new MyImageLoader());
        //设置图片集合
        banner.setImages(bannerList);
        banner.start();
        //咨询
        findHeaderViewById(R.id.tv_home_consultation).setOnClickListener(v -> {
            if (App.getInstance().getLoginFlag()) {
                MainActivity activity = (MainActivity)mContext;
                Eyes.setStatusBarColor(activity, CommonUtil.getColor(R.color.transparent_4c));
                activity.changeTagStatus(2);
            } else {
                toLogin();
            }
        });
        //起诉状
        findHeaderViewById(R.id.tv_home_complaint).setOnClickListener(v -> {
            if (App.getInstance().getLoginFlag()) {
                MainActivity activity = (MainActivity)mContext;
                activity.changeTagStatus(3);
            } else {
                toLogin();
            }
        });
        //开庭助手
        findHeaderViewById(R.id.tv_home_open_court_assistant).setOnClickListener(v -> {
            if (App.getInstance().getLoginFlag()) {
               ActivityOpenHelper.start(mContext);
            } else {
                toLogin();
            }
        });
        //法律顾问
        findHeaderViewById(R.id.id_tv_home_legal_counsel).setOnClickListener(v -> {
            if (App.getInstance().getLoginFlag()) {
                ActivityLegalAdviser.start(mContext);
            } else {
                toLogin();
            }
        });
    }

    private void toLogin() {
        Intent intent = new Intent(mContext, LoginCodeActivity.class);
        intent.putExtra(AppConstant.NOTLOGIN, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
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
