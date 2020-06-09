package com.ytfu.yuntaifawu.ui.home.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Util;
import com.google.android.material.appbar.AppBarLayout;
import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.helper.CustomPagerTitleView;
import com.ytfu.yuntaifawu.helper.ScaleTransitionPagerTitleView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioDetailsBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListerBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.home.fragment.AudioBrifeFragment;
import com.ytfu.yuntaifawu.ui.home.fragment.AudioSimilarFragment;
import com.ytfu.yuntaifawu.ui.home.fragment.RelevantContractFragment;
import com.ytfu.yuntaifawu.ui.home.presenter.AudioDetailsPresenter;
import com.ytfu.yuntaifawu.ui.home.view.IAudioDetailsView;
import com.ytfu.yuntaifawu.ui.mine.audio.AudioController;
import com.ytfu.yuntaifawu.ui.pay.PayBottomDialog;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;
import com.ytfu.yuntaifawu.ui.pay.PayResult;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.utils.AndPermissionUtil;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.DensityUtil;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.PayDialorUtil;
import com.ytfu.yuntaifawu.utils.SnackbarUtils;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @Auther gxy
 * @Date 2019/11/12
 * @Des 音频详情
 */
public class ActivityAudioDetails extends BaseActivity<IAudioDetailsView, AudioDetailsPresenter> implements IAudioDetailsView, View.OnClickListener {


    private ImageView img_back, image_png;
    private TextView text_price, text_y_price, text_audio_title, text_count, tv_exce;
    private LinearLayout ll_share, li_start;
    private String id;
    private MagicIndicator magic_indicator;
    private ViewPager view_pager;
    private List<String> indicatorList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private int shoucang;
    private String uid;
    private ImageView icon_start;
    private TextView text_start;
    private AppBarLayout appBarLayout;
    /**
     * AppBarLayout是否已折叠
     */
    private boolean folded = false;
    private ImageView iv_fanhui;
    private TextView tv_top_title;
    private TextView tv_ligm;
    private RelativeLayout rl_byu_audio_play;
    private LinearLayout lin_goumai;
    private ImageView iv_pause;
    private ImageView iv_play;
    private TextView tv_start_date;
    private TextView tv_end_date;
    private SeekBar seekbar;
    private Boolean isplaying;
    private static final int UPDATE_TIME = 2;
    private MediaPlayer mediaPlayer;

    private static final int PAY_TYPE_WECHAT = 0;  //微信支付,默认支付方式
    private static final int PAY_TYPE_ALIBABA = 1;  //支付宝支付
    private int payType = 0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    iv_play.setImageResource(R.drawable.btn_bofang_bai);
                    break;
                case UPDATE_TIME: {
                    /**
                     * 更新时间
                     */
                    try {
                        int position = mediaPlayer.getCurrentPosition();
                        int second = position / 1000;
                        int hh = second / 3600;
                        int mm = second % 3600 / 60;
                        int ss = second % 60;
                        strTime = String.valueOf(second);
                        int totalduration = mediaPlayer.getDuration();

                        updateTime(tv_start_date, position);
                        updateTime(tv_end_date, totalduration);

                        handler.sendEmptyMessageDelayed(UPDATE_TIME, 1000);
                    } catch (Exception e) {
                        e.printStackTrace();

                    }

                }
                break;
            }
        }
    };
    private String strTime;
    private ImageView iv_ls;
    private TextView tv_ls_name;
    private TextView tv_ls_jj;
    private CoordinatorLayout cdl;
    private ImageView iv_download;
    private LinearLayout ll_download;
    private String mString = "<font color='#44a2f7'>" + "本人以及云台法律咨询联合声明：在全国疫情期间您的咨询付费，我们每一笔咨询费里均会" + "</font>" + "<font color='#e13b38'>" + "提取3元定向捐赠" + "</font>" + "<font color='#44a2f7'>" + "给全国牺牲的" + "</font>" +
            "<font color='#e13b38'>" + "抗疫医生" + "</font>" + "<font color='#44a2f7'>" + "，并由第三方会计师事务所出具报告并在平台公布。（不包括美国以及其他国家）" + "</font>";
    private boolean yq_boolean;
    private ImageView mIvWeichatSelect;
    private ImageView mIvAliSelect;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_audio_details;
    }

    @Override
    protected AudioDetailsPresenter createPresenter() {
        return new AudioDetailsPresenter(this);
    }

    @Override
    protected View provideLoadServiceRootView() {
        return cdl;
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
        EventBus.getDefault().register(this);
        yq_boolean = SpUtil.getBoolean(AppConstant.YQ_SHENGMING, false);
        iv_fanhui = findViewById(R.id.iv_fanhui);
        iv_fanhui.setOnClickListener(this);
        tv_top_title = findViewById(R.id.tv_top_title);
        tv_top_title.setText("咨询律师");
        image_png = findViewById(R.id.image_png);
        text_price = findViewById(R.id.text_price);
        text_y_price = findViewById(R.id.text_y_price);
        tv_exce = findViewById(R.id.tv_exce);
        text_count = findViewById(R.id.text_count);
        appBarLayout = findViewById(R.id.appBarLayout);
        ll_share = findViewById(R.id.ll_share);
        tv_ligm = findViewById(R.id.tv_ligm);
        tv_ligm.setOnClickListener(this);
        text_audio_title = findViewById(R.id.text_audio_title);
        li_start = findViewById(R.id.li_start);
        magic_indicator = findViewById(R.id.magic_indicator);
        view_pager = findViewById(R.id.view_pager);
        icon_start = findViewById(R.id.icon_start);
        text_start = findViewById(R.id.text_start);

        rl_byu_audio_play = findViewById(R.id.rl_byu_audio_play);
        iv_pause = findViewById(R.id.iv_pause);
        iv_play = findViewById(R.id.iv_play);
        tv_start_date = findViewById(R.id.tv_start_date);
        tv_end_date = findViewById(R.id.tv_end_date);
        seekbar = findViewById(R.id.seekbar);
        iv_play.setOnClickListener(this);
        //律师简介
        iv_ls = findViewById(R.id.iv_ls);
        tv_ls_name = findViewById(R.id.tv_ls_name);
        tv_ls_jj = findViewById(R.id.tv_ls_jj);
        lin_goumai = findViewById(R.id.lin_goumai);

        //音频原文下载
        cdl = findViewById(R.id.cdl);
        iv_download = findViewById(R.id.iv_download);
        ll_download = findViewById(R.id.ll_download);
        iv_download.setOnClickListener(this);
        ll_download.setOnClickListener(this);
        text_y_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        indicatorList.add("咨询简介");
        indicatorList.add("同类咨询");
        indicatorList.add("相关合同");
        fragmentList.add(new AudioBrifeFragment());
        fragmentList.add(new AudioSimilarFragment());
        fragmentList.add(new RelevantContractFragment());
        li_start.setOnClickListener(this);
        initMagicIndicator();
        initViewPager();
        uid = SpUtil.getString(AppConstant.UID, "");
        // 滑动改变监听
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
//            Logger.i("verticalOffset = " + verticalOffset);
            if (Math.abs(verticalOffset) >= appBarLayout.getHeight()) {
                // 折叠状态
//                clHouseDetail.setBackgroundColor(CommonUtil.getColor(R.color.grey_f7));
                folded = true;
            } else {
                if (folded) {
//                    clHouseDetail.setBackgroundColor(CommonUtil.getColor(R.color.white));
                    folded = false;
                }
            }
        });
        mediaPlayer = new MediaPlayer();
    }


    private void updateTime(TextView textView, int millisecond) {
        int second = millisecond / 1000;
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;

        String str = null;
        if (hh != 0) {
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format("%02d:%02d", mm, ss);
        }

        textView.setText(str);

    }

    private void initViewPager() {
        if (getFragmentManager() != null) {
//            IndicatorVpAdapter adapter = new IndicatorVpAdapter(getFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentList, indicatorList);
            view_pager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    return fragmentList.get(position);
                }

                @Override
                public int getCount() {
                    return indicatorList.size();
                }
            });
            ViewPagerHelper.bind(magic_indicator, view_pager);
        }
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return indicatorList == null ? 0 : indicatorList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int i) {
//                CustomPagerTitleView customPagerTitleView = new CustomPagerTitleView(context, index, indicatorList.size());
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(indicatorList.get(i));
                simplePagerTitleView.setTextSize(13);
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.textColor_collect_audio_Select));
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.textColor_Details_Unselect));
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        view_pager.setCurrentItem(i);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
                indicator.setColors(getResources().getColor(R.color.textColor_collect_audio_Select));
                indicator.setLineWidth(DensityUtil.dip2px(43));
                indicator.setLineHeight(DensityUtil.dip2px(1));
                indicator.setRoundRadius(DensityUtil.dip2px(1));
                return indicator;
            }
        });
        magic_indicator.setNavigator(commonNavigator);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        getPresenter().getAudioDetails(map);
        Log.e("yin", "-----" + uid + "------" + id);
        if (yq_boolean==false) {
            showAlertDialogTiShi();
            SpUtil.putBoolean(AppConstant.YQ_SHENGMING,true);
        }

    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        getPresenter().getAudioDetails(map);
    }

    @Override
    public void onDetailsSuccess(AudioDetailsBean detailsBean) {
        hideLoading();
        if (detailsBean == null) {
            showEmpty();
        } else {
            showUi(detailsBean);
            //初始化
//            initMediaPlayer(detailsBean.getList());
//            initSeekBar();
        }

    }

    private void showUi(AudioDetailsBean list) {
        AudioDetailsBean.ListBean bean = list.getList();
        shoucang = list.getShoucang();
        GlideManager.loadImageByUrl(this, bean.getPost_img(), image_png);
//        Glide.with(this).load(bean.getPost_img()).into(image_png);
        text_price.setText(bean.getPost_price());
        tv_ligm.setText("立即连线律师" + "¥" + bean.getPost_price());
        text_y_price.setText("原价" + " " + "¥" + bean.getPost_cost());
        text_audio_title.setText(bean.getPost_title());
        text_count.setText("已有" + bean.getOrder_count() + "人咨询");
        tv_exce.setText(bean.getPost_excerpt());
        //律师简介
        GlideManager.loadImageByUrl(this, bean.getLvshi_img(), iv_ls);
        tv_ls_name.setText(bean.getLvshi_name());
        tv_ls_jj.setText(bean.getLvshi_descript());
        if (shoucang == 1) {
            icon_start.setImageResource(R.drawable.start_select_icon);
        } else if (shoucang == 0) {
            icon_start.setImageResource(R.drawable.start_unselect_icon);
        }
        //判断是否购买
        int buy = list.getBuy();
        if (buy == 1) {
            rl_byu_audio_play.setVisibility(View.VISIBLE);
            lin_goumai.setVisibility(View.GONE);
//            ll_download.setVisibility(View.VISIBLE);
//            setPlayAudioUi(bean);
            //初始化
            initMediaPlayer(bean);
            initSeekBar();
        } else if (buy == 0) {
            rl_byu_audio_play.setVisibility(View.GONE);
            lin_goumai.setVisibility(View.VISIBLE);
//            ll_download.setVisibility(View.GONE);
            //初始化
//            initMediaPlayer(bean);
//            initSeekBar();
        }

    }

    private void initMediaPlayer(AudioDetailsBean.ListBean list) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    mediaPlayer = MediaPlayer.create(ActivityAudioDetails.this, Uri.parse(list.getPost_audio()));
//                    mediaPlayer.start();
                    mediaPlayer.setDataSource(String.valueOf(Uri.parse(list.getPost_audio())));
                    Log.e("path", "---" + list.getPost_audio());
                    mediaPlayer.prepare();
                    handler.sendEmptyMessage(UPDATE_TIME);
                    int max = mediaPlayer.getDuration();
                    seekbar.setMax(max);
                    /**
                     *开启进程控制SeekBar
                     */
                    new Thread() {
                        public void run() {
                            isplaying = true;
                            while (isplaying) {
                                int position = mediaPlayer.getCurrentPosition();
                                seekbar.setProgress(position);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }

                        }
                    }.start();

                    mediaPlayer.setLooping(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initSeekBar() {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int process = seekBar.getProgress();
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(process);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                if (fromUser) {//<span style="color:#ff0000;"> 注意点</span>
                    try {
                        if (!mediaPlayer.isPlaying()) {
                            try {
                                mediaPlayer.reset();
//                                mediaPlayer.setDataSource(mediaFile.toString());
                                mediaPlayer.prepareAsync();
                                // mPlayer.prepare();
                                mediaPlayer.start();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        mediaPlayer.seekTo(seekBar.getProgress());
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onDetailFiled() {

    }

    @Override
    public void onShouCangSuccess(AudioShouCangBean shouCangBean) {
        if (shouCangBean != null) {
            int status = shouCangBean.getStatus();
            switch (status) {
                case 1:
                    if (shoucang == 1) {
                        icon_start.setImageResource(R.drawable.start_unselect_icon);
                        shoucang = 0;
                    } else if (shoucang == 0) {
                        icon_start.setImageResource(R.drawable.start_select_icon);
                        shoucang = 1;
                    }
                    ToastUtil.showToast("收藏成功");
                    break;
                case 2:
                    ToastUtil.showToast("收藏失败");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onShouCangdelSuccess(AudioShouCangBean shouCangBean) {
        if (shouCangBean != null) {
            int status = shouCangBean.getStatus();
            switch (status) {
                case 1:
                    if (shoucang == 1) {
                        icon_start.setImageResource(R.drawable.start_unselect_icon);
                        shoucang = 0;
                    } else if (shoucang == 0) {
                        icon_start.setImageResource(R.drawable.start_select_icon);
                        shoucang = 1;
                    }
                    ToastUtil.showToast("取消收藏成功");
                    break;
                case 2:
                    ToastUtil.showToast("取消收藏失败");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onAudioPaySuccess(PayBean payBean) {
        if (payBean != null) {
            String sign = payBean.getSign();
            Log.e("sign", "sign :" + sign);
            getAlipay(sign);
        }
    }

    @Override
    public void onAudioPayWxSuccess(WxPayBean wxPayBean) {
        if (wxPayBean != null || wxPayBean.getSign() != null) {
            WxPayBean.SignBean sign = wxPayBean.getSign();
//            startWxPay(sign);
            PayHelper.getInstance().payByWechat(sign);
        }
    }

    @Override
    public void onListenerSuccess(AudioListerBean listerBean) {
        if (listerBean != null) {
            int status = listerBean.getStatus();
//            if (status == 1) {
//                showToast("success");
//            } else {
//                showToast("error");
//            }
        }
    }

    //接受event事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getWxPayEvent(MessageEvent messageEvent) {
        switch (messageEvent.getWhat()) {
            case AppConstant.WX_PAY_SUCCESS:
                showToast("支付成功");
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("id", id);
                getPresenter().getAudioDetails(map);
                break;
            default:
                break;
        }
    }

    private void getAlipay(String sign) {
        PayHelper.getInstance().AliPay(ActivityAudioDetails.this, sign);
        PayHelper.getInstance().setIPayListener(new PayHelper.IPayListener() {
            @Override
            public void onSuccess() {
                showToast("支付成功");
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("id", id);
                getPresenter().getAudioDetails(map);
                rl_byu_audio_play.setVisibility(View.VISIBLE);
                lin_goumai.setVisibility(View.GONE);
            }

            @Override
            public void onResultConfirmation() {
                showToast("支付结果确认中");
            }

            @Override
            public void onCancel() {
                showToast("取消支付");
            }

            @Override
            public void onFail() {
                showToast("支付失败");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.li_start:
                if (App.getInstance().getLoginFlag()) {
                    getShouCang();
                } else {
                    toLogin();
                }
                break;
            case R.id.iv_fanhui:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }
                if (!TextUtils.isEmpty(strTime)) {
                    setTime();
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 500);
//                finish();
                break;
            case R.id.tv_ligm:
//                ToastUtil.showToast("立即购买");
                showPayDialor();
                break;
            case R.id.iv_play:
//                requestPermission(iv_play);
                AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                    @Override
                    public void onPermissionGranted() {
                        initPlay();
                    }
                }, com.yanzhenjie.permission.Permission.Group.MICROPHONE);
                break;
            case R.id.iv_pause:

                break;
            case R.id.iv_download:
            case R.id.ll_download:
                SnackbarUtils.showIndefiniteSnackbar(cdl, "添加邮箱", CommonUtil.getColor(R.color.textcolo_299),
                        CommonUtil.getColor(R.color.textcolor_26), "确定", CommonUtil.getColor(R.color.textcolo_299), new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showAlertDialog();
                            }
                        });
                break;
            default:
                break;
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View view = View.inflate(this, R.layout.view_alert_dialog_confirm_email, null);
        TextView tvMsg = view.findViewById(R.id.tv_message_dialog);
        TextView tvCancel = view.findViewById(R.id.tv_cancel_dialog);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm_dialog);
        TextView tv_tishi = view.findViewById(R.id.tv_tishi);
        EditText ed_email = view.findViewById(R.id.ed_email);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    tv_tishi.setVisibility(View.VISIBLE);
                    tv_tishi.setText("邮箱输入为空");
                } else {
                    tv_tishi.setVisibility(View.GONE);
                    boolean contains = email.contains("@");
                    if (contains == true) {
                        showWaitingDialog("请稍后...", true);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("uid", uid);
                        map.put("mail", email);
//                        getPresenter().setHtBdEmail(map);
                        alertDialog.dismiss();
                    } else {
                        tv_tishi.setVisibility(View.VISIBLE);
                        tv_tishi.setText("邮箱格式不正确");
                    }
                }
            }
        });
        //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);
    }

    //支付选择弹窗
    private void showPayDialor() {
//        PayDialorUtil.getInstance().showPayDialog(this, new PayDialorUtil.OnButtonClickListener() {
//            @Override
//            public void onPayWeiXinButtonClick(AlertDialog dialog) {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("uid", uid);
//                map.put("type", String.valueOf(1));
//                map.put("id", id);
//                map.put("xitong", String.valueOf(1));
//                getPresenter().setAudioPayWx(map);
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onPayZhiFuBaoButtonClick(AlertDialog dialog) {
//                HashMap<String, String> map = new HashMap<>();
//                map.put("uid", uid);
//                map.put("type", String.valueOf(1));
//                map.put("id", id);
//                map.put("xitong", String.valueOf(1));
//                getPresenter().setAudioPay(map);
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onGuanbiButtonClick(AlertDialog dialog) {
//                dialog.dismiss();
//            }
//        });
        View dialogView = getLayoutInflater().inflate(R.layout.pay_view_alert_dialor1, null);
        //微信支付的选择
        mIvWeichatSelect = dialogView.findViewById(R.id.iv_buy_weichat_select);
        //支付宝的选择
        mIvAliSelect = dialogView.findViewById(R.id.iv_buy_zhifubao_select);
        PayBottomDialog dialog = new PayBottomDialog(ActivityAudioDetails.this, dialogView, new int[]{R.id.ll_pay_weichat, R.id.ll_pay_zhifubao, R.id.pay, R.id.iv_guanbi});
        dialog.bottmShow();
        dialog.setOnBottomItemClickListener(new PayBottomDialog.OnBottomItemClickListener() {
            @Override
            public void onBottomItemClick(PayBottomDialog payBottomDialog, View view) {
                switch (view.getId()){
                    case R.id.ll_pay_weichat:
                        if (PAY_TYPE_WECHAT != payType) {
                            mIvWeichatSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_xuanzhong));
                            mIvAliSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_weixuanzhong));
                            payType = PAY_TYPE_WECHAT;
                        }
                        break;
                    case R.id.ll_pay_zhifubao:
                        if (PAY_TYPE_ALIBABA != payType) {
                            mIvWeichatSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_weixuanzhong));
                            mIvAliSelect.setImageDrawable(getResources().getDrawable(R.drawable.zjqd_check_xuanzhong));
                            payType = PAY_TYPE_ALIBABA;
                        }
                        break;
                    case R.id.pay:
                        if(payType == PAY_TYPE_WECHAT){
                            HashMap<String, String> map = new HashMap<>();
                            map.put("uid", uid);
                            map.put("type", String.valueOf(1));
                            map.put("id", id);
                            map.put("xitong", String.valueOf(1));
                            getPresenter().setAudioPayWx(map);
                        }else if(payType == PAY_TYPE_ALIBABA){
                            HashMap<String, String> map = new HashMap<>();
                            map.put("uid", uid);
                            map.put("type", String.valueOf(1));
                            map.put("id", id);
                            map.put("xitong", String.valueOf(1));
                            getPresenter().setAudioPay(map);
                        }
                        //重置
                        payType = PAY_TYPE_WECHAT;
                        dialog.cancel();
                        break;
                    case R.id.iv_guanbi:
                        //重置
                        payType = PAY_TYPE_WECHAT;
                        dialog.cancel();
                        break;
                }
            }
        });
    }

    private void initPlay() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            iv_play.setImageResource(R.drawable.btn_bofang_bai);
            Log.e("play", "------" + strTime);
            setTime();
        } else {
            mediaPlayer.start();
            iv_play.setImageResource(R.drawable.btn_zanting_bai);
            Log.e("stop", "------" + strTime);
        }
        //播放完监听
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                //播放位置变为0
                mediaPlayer.seekTo(0);
                iv_play.setImageResource(R.drawable.btn_bofang_bai);
                Log.e("wan", "------" + strTime);
                setTime();
            }
        });
    }

    //设置音频播放时间，传入后台。
    private void setTime() {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("yid", id);
        map.put("time", strTime);
        getPresenter().setAudioLisener(map);
    }

    //收藏
    private void getShouCang() {
        if (shoucang == 1) {
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("id", id);
            map.put("type", String.valueOf(1));
//            map.put("shoucang", String.valueOf(0));
            getPresenter().audioShouCangDel(map);
        } else if (shoucang == 0) {
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("id", id);
            map.put("type", String.valueOf(1));
//            map.put("shoucang", String.valueOf(1));
            getPresenter().audioShouCang(map);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            //按下的如果是BACK，同时没有重复
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            if (!TextUtils.isEmpty(strTime)) {
                setTime();
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 500);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        EventBus.getDefault().unregister(this);
    }

    private void showAlertDialogTiShi() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View view = View.inflate(this, R.layout.audio_contract_dialod, null);
        TextView tv_audio_tishi = view.findViewById(R.id.tv_audio_tishi);
        tv_audio_tishi.setText(Html.fromHtml(mString));
        Button btn_zhidao = view.findViewById(R.id.btn_zhidao);
        btn_zhidao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
//        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);
    }

}
