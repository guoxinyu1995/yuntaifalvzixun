package com.ytfu.yuntaifawu.ui.qisuzhuang.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.qisuzhuang.adaper.QszHistorytitleListAdaper;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszDeleteBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszHistoryBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.presenter.QszHistoryPresenter;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IQszHistoryView;
import com.ytfu.yuntaifawu.utils.DialorUtil;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

/**
 * @Auther gxy
 * @Date 2019/12/13
 * @Des 历史起诉张
 */
public class ActivityQszHistoryList extends BaseActivity<IQszHistoryView, QszHistoryPresenter> implements IQszHistoryView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_qsz)
    RecyclerView recycleQsz;
    @BindView(R.id.btn_add_qsz)
    Button btnAddQsz;
    @BindView(R.id.v1)
    View v1;
    private String uid;
    private QszHistorytitleListAdaper listAdaper;
    private int type;
    private LinearLayoutManager layoutManager;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_qsz_history_list;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return recycleQsz;
    }

    @Override
    protected QszHistoryPresenter createPresenter() {
        return new QszHistoryPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
//      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.transparent_half));

        }
    }

    @Override
    protected void initView() {
//        hideLoading();
        tvTopTitle.setText("起诉状");
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);
        uid = SpUtil.getString(AppConstant.UID, "");
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recycleKtzs.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        recycleKtzs.addItemDecoration(new MyItemDecoration2(0f, 0f));
        recycleQsz.setHasFixedSize(true);
        recycleQsz.setNestedScrollingEnabled(false);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        recycleQsz.addItemDecoration(divider);
        recycleQsz.setLayoutManager(layoutManager);
        //回弹效果
//        OverScrollDecoratorHelper.setUpOverScroll(recycleQsz,OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().setQszHistory(map);
    }

    @Override
    protected void initData() {
        listAdaper = new QszHistorytitleListAdaper(this);
        recycleQsz.setAdapter(listAdaper);
        recycleQsz.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if(lastVisibleItemPosition+1 == listAdaper.getItemCount()){
                    v1.setVisibility(View.VISIBLE);
                }else{
                    v1.setVisibility(View.GONE);
                }
            }
        });
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        getPresenter().setQszHistory(map);
        listAdaper.setItemClickListener(new QszHistorytitleListAdaper.IQszTitleItemClickListener() {
            @Override
            public void onQszTitleItemClickListener(int position, String id) {
                DialorUtil.getInstance().showConfirmDialog(ActivityQszHistoryList.this, "确定删除此条数据吗?", new DialorUtil.OnButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(AlertDialog dialog) {
//                        showToast("确定");
                        HashMap<String, String> map = new HashMap<>();
                        map.put("uid", uid);
                        map.put("id", id);
                        getPresenter().setQszDelete(map);
                        dialog.dismiss();
                    }

                    @Override
                    public void onNegativeButtonClick(AlertDialog dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    @OnClick({R.id.iv_fanhui, R.id.btn_add_qsz})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.btn_add_qsz:
                switch (type){
                    case 1:
                        finish();
                        break;
                    case 2:
                        startActivity(new Intent(ActivityQszHistoryList.this, ActivityQiSuZhuang.class));
                        break;
                        default:
                            break;
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onQszSuccess(QszHistoryBean historyBean) {
        hideLoading();
        if (historyBean == null || historyBean.getList() == null || historyBean.getList().isEmpty()) {
            showEmpty();
        } else {
            listAdaper.setmList(historyBean.getList());
        }
    }

    @Override
    public void onQszDelSuccess(QszDeleteBean deleteBean) {
        if (deleteBean != null) {
            int status = deleteBean.getStatus();
            if (status == 1) {
                showToast("删除成功");
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                getPresenter().setQszHistory(map);
            } else {
                showToast("删除失败");
            }
        }
    }

    @Override
    public void onQszFiled() {

    }
}
