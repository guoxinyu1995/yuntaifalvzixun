package com.ytfu.yuntaifawu.ui.users.act;

import android.content.Context;
import android.content.Intent;

import com.github.lee.annotation.InjectLayout;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.users.fragment.AnnouncementFragment;

/**
 * @Auther gxy
 * @Date 2020/6/10
 * @Des 公告页面
 */
@InjectLayout(value = R.layout.activity_announcement, toolbarLayoutId = R.layout.layout_toolbar_center_title)
public class AnnouncementActivity extends BaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, AnnouncementActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void initData() {
        super.initData();
        hideLoading();
        changeStatusBarTextColor(false);
        setToolbarLeftImage(R.drawable.fanhui_bai, v -> {
            onBackPressed();
        });
        setToolbarText(R.id.tv_global_title, "公告");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_announcenment_coun, AnnouncementFragment.newInstance())
                .commitAllowingStateLoss();
    }
}
