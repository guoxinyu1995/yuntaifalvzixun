package com.ytfu.yuntaifawu.ui.users.act;

import android.content.Context;
import android.content.Intent;

import com.github.lee.annotation.InjectLayout;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.users.fragment.SearchListFragment;

/**
 * @Auther gxy
 * @Date 2020/6/10
 * @Des 用户端搜索列表Activity
 */
@InjectLayout(value = R.layout.activity_search_list, toolbarLayoutId = R.layout.layout_toolbar_center_title)
public class SearchListActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, SearchListActivity.class);
//        starter.putExtra();
        context.startActivity(starter);
    }

    @Override
    protected void initData() {
        super.initData();
        hideLoading();
        changeStatusBarTextColor(false);
        setToolbarLeftImage(R.drawable.fanhui_bai, v -> onBackPressed());
        setToolbarText(R.id.tv_global_title, "搜索");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_search_content, SearchListFragment.newInstance())
                .commitAllowingStateLoss();
    }
}
