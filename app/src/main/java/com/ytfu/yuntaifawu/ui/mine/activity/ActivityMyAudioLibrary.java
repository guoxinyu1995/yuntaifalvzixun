package com.ytfu.yuntaifawu.ui.mine.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.adaper.MyAudioLibraryAdaper;
import com.ytfu.yuntaifawu.ui.mine.adaper.MyAudioLibraryAdaper1;
import com.ytfu.yuntaifawu.ui.mine.audio.AudioController;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.MyLibraryBean;
import com.ytfu.yuntaifawu.ui.mine.present.MyLibraryPresent;
import com.ytfu.yuntaifawu.ui.mine.view.IMyLibraryView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.MyItemDecoration;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/15
 * @Des 我的音频库
 */
public class ActivityMyAudioLibrary extends BaseActivity<IMyLibraryView, MyLibraryPresent> implements IMyLibraryView {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.et_collect_seatch)
    EditText etCollectSeatch;
    @BindView(R.id.recycle_audio_library)
    RecyclerView recycleAudioLibrary;
    @BindView(R.id.ll_null)
    LinearLayout llNull;
    private AudioController audioControl;
    private MyAudioLibraryAdaper1 myAudioLibraryAdaper1;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_my_audio_library;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return llNull;
    }

    @Override
    protected MyLibraryPresent createPresenter() {
        return new MyLibraryPresent(this);
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
        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
//        iv_fanhui = findViewById(R.id.iv_fanhui);
//        tv_top_title = findViewById(R.id.tv_top_title);
//        et_collect_seatch = findViewById(R.id.et_collect_seatch);
//        recycle_audio_library = findViewById(R.id.recycle_audio_library);
//        iv_fanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
        tvTopTitle.setText("音频库");
        audioControl = new AudioController(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleAudioLibrary.addItemDecoration(new MyItemDecoration(4f));
        recycleAudioLibrary.setLayoutManager(layoutManager);
    }

    @Override
    protected void initData() {
        etCollectSeatch.addTextChangedListener(new TextWatcher() {

            private String keyword;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyword = etCollectSeatch.getText().toString().trim();
                HashMap<String, String> map = new HashMap<>();
                String uid = SpUtil.getString(AppConstant.UID, "");
                map.put("uid", uid);
                map.put("f_type", String.valueOf(1));
                map.put("type", String.valueOf(1));
                map.put("keyword", keyword);
                mPresenter.myLibrary(map);
            }
        });
//        libraryAdaper = new MyAudioLibraryAdaper(this);
//        recyclerView.setAdapter(libraryAdaper);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("f_type", String.valueOf(1));
        map.put("type", String.valueOf(1));
        mPresenter.myLibrary(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("f_type", String.valueOf(1));
        map.put("type", String.valueOf(1));
        mPresenter.myLibrary(map);

    }

    @Override
    public void onMyLibrarySuccess(MyLibraryBean libraryBean) {
        hideLoading();
        if (libraryBean == null || libraryBean.getList() == null || libraryBean.getList().isEmpty()) {
            showEmpty();
        } else {
            myAudioLibraryAdaper1 = new MyAudioLibraryAdaper1(this,audioControl, libraryBean.getList());
            myAudioLibraryAdaper1.bindToRecyclerView(recycleAudioLibrary);
        }
    }

    @Override
    public void onFiled() {

    }

    @Override
    public void onSendEmail(SendEmailBean emailBean) {

    }

    @Override
    public void onLibraryBdEmailSuccess(BdEmailBean bdEmailBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        audioControl.release();
    }


    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }
}
