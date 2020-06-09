package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.custom.CopyButtonLibrary;
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviser;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityContractList;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityOpenHelper;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityMineZiXun;
import com.ytfu.yuntaifawu.ui.mseeage.bean.CheckWeixinBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ExChangeWeiXinBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.FirstMessageBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiCardBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ToCheckPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.WhetherToPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.fragment.ChatFragment;
import com.ytfu.yuntaifawu.ui.mseeage.presenter.ChatPresenter;
import com.ytfu.yuntaifawu.ui.mseeage.view.IChatView;
import com.ytfu.yuntaifawu.ui.pay.PayBottomDialog;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQiSuZhuang;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.DialorUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends BaseActivity<IChatView, ChatPresenter> implements IChatView {
    @BindView(R.id.fl_chat)
    FrameLayout flChat;
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_weixin)
    TextView tvWeixin;
    @BindView(R.id.tv_pingjia)
    TextView tvPingjia;
    @BindView(R.id.tv_tousu)
    TextView tvTousu;
    @BindView(R.id.ll_lvshi)
    LinearLayout llLvshi;
    @BindView(R.id.tv_hetong)
    TextView tvHetong;
    @BindView(R.id.tv_qsz)
    TextView tvQsz;
    @BindView(R.id.tv_kaiting)
    TextView tvKaiting;
    @BindView(R.id.tv_guwen)
    TextView tvGuwen;
    @BindView(R.id.ll_fwh)
    LinearLayout llFwh;
    @BindView(R.id.tv_zixun)
    TextView tvZixun;
    @BindView(R.id.tv_hongbao)
    TextView tvHongbao;
    @BindView(R.id.ll_lvshi_duan)
    LinearLayout llLvshiDuan;
    private ChatFragment chatFragment;
    private String id;
    private String userName;
    private EMMessageListener msgListener;
    private String picurl;
    private ImageView mIvWeichatSelect;
    private ImageView mIvAliSelect;
    private static final int PAY_TYPE_WECHAT = 0;  //微信支付,默认支付方式
    private static final int PAY_TYPE_ALIBABA = 1;  //支付宝支付
    private int payType = 0;
    private String userid;
    private String huashuid;
    private String lvshiid;
    private String jiluid;
    private String type;
    //律师卡片
    private String name;
    private String lingyu;
    private String picurlc;
    private String lvshitype;
    private String xueli;
    private String cyear;
    private String jianjie;
    private String sex;
    //    private List<LvShiCardBean.DataBean.AnliContentBean> anli_content;
    private String anli_one;
    private String anli_two;
    private String uid;
    private int type_pay;
    private String status;
    private int pay_type;
    private String ex_lvshiid;
    private String ex_userid;
    private String ex_jilu_id;
    private String ex_id;
    private String ex_wxid;

    @Override
    protected int provideContentViewId() {
        return R.layout.chat_activity;
    }

    @Override
    protected ChatPresenter createPresenter() {
        return new ChatPresenter(this);
    }

    @Override
    protected void onResume() {
        //        EaseUI.getInstance().getNotifier().reset();
        id = getIntent().getStringExtra(AppConstant.EXTRA_USER_ID);
        uid = SpUtil.getString(AppConstant.UID, "");
        Log.e("llid","-------"+id+"-----"+uid);
        //点击微信是否购买
        HashMap<String, String> mapPay = new HashMap<>();
        mapPay.put("user_id", uid);
        mapPay.put("lv_id", id);
        getPresenter().getWhetherToPay(mapPay);
        super.onResume();
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        Intent intent = getIntent();
        id = intent.getStringExtra(AppConstant.EXTRA_USER_ID);

        userName = intent.getStringExtra("name");
        picurl = intent.getStringExtra("picurl");
        Logger.e("pic url = " + picurl);
        type = intent.getStringExtra("type");
        uid = SpUtil.getString(AppConstant.UID, "");
        String shengfen = SpUtil.getString(AppConstant.SHENFEN, "");
        tvTopTitle.setText(userName);
        chatFragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(EaseConstant.EXTRA_USER_ID, id);
        args.putString("picurl", this.picurl);
        args.putString("type", type);
        args.putString("userName", userName);
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.fl_chat, chatFragment).commit();
        if(shengfen.equals("1")){
            llLvshiDuan.setVisibility(View.GONE);
            if (!TextUtils.isEmpty(type) && type.equals("1")) {
                llFwh.setVisibility(View.VISIBLE);
                llLvshi.setVisibility(View.GONE);
            } else {
                llFwh.setVisibility(View.GONE);
                llLvshi.setVisibility(View.VISIBLE);
            }
        }else if(shengfen.equals("2")){
            llLvshiDuan.setVisibility(View.VISIBLE);
            llFwh.setVisibility(View.GONE);
            llLvshi.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initData() {
        hideLoading();
        //通知
        HashMap<String, String> mapNotice = new HashMap<>();
        mapNotice.put("userid", uid);
        mapNotice.put("lsid", id);
        //        getPresenter().getNotice(mapNotice);
        //点击微信是否购买
        HashMap<String, String> mapPay = new HashMap<>();
        mapPay.put("user_id", uid);
        mapPay.put("lv_id", id);
        getPresenter().getWhetherToPay(mapPay);
        //律师卡片
        HashMap<String, String> map = new HashMap<>();
        map.put("lid", id);
        //        getPresenter().getLvShiCard(map);
        chatFragment.setIKuozhanPayClickListener(new ChatFragment.IKuozhanPayClickListener() {
            @Override
            public void kuoZhanPayClickListener(EMMessage message) {
                //红包支付点击
                pay_type = 1;
                //用户id
                userid = message.getStringAttribute("userid", "");
                //话术id
                huashuid = message.getStringAttribute("huashuid", "");
                //律师id
                lvshiid = message.getStringAttribute("lvshiid", "");
                //记录id
                jiluid = message.getStringAttribute("jiluid", "");
                status = message.getStringAttribute("status", "");
                //                if (status.equals("1")) {
                //                    HashMap<String, String> mapCheckWeixin = new HashMap<>();
                //                    mapCheckWeixin.put("user_id", userid);
                //                    mapCheckWeixin.put("lv_id", lvshiid);
                //                    mapCheckWeixin.put("h_id", huashuid);
                //                    getPresenter().getCheckWeiXin(mapCheckWeixin);
                //                } else {
                getPresenter().getToCheckPay(userid, lvshiid, huashuid);
                //                }
            }

            @Override
            public void iconClickListener() {
                //聊天页面头像点击
                if (TextUtils.isEmpty(type) || !type.equals("1")) {
                    Intent intent = new Intent(ChatActivity.this, LvShiDetailsActivity.class);
                    intent.putExtra("lid", id);
                    intent.putExtra("userName", userName);
                    intent.putExtra("types", 1);
                    startActivity(intent);
                }
            }

            @Override
            public void lvShiDetails() {
                Intent intent = new Intent(ChatActivity.this, LvShiDetailsActivity.class);
                intent.putExtra("lid", id);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });

    }


    @OnClick({R.id.tv_sue, R.id.iv_fanhui, R.id.tv_weixin, R.id.tv_pingjia, R.id.tv_tousu, R.id.tv_hetong, R.id.tv_qsz, R.id.tv_kaiting, R.id.tv_guwen,R.id.tv_zixun, R.id.tv_hongbao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sue:
                Logger.e("起诉状");
                startActivity(new Intent(this, ActivityQiSuZhuang.class));
                break;
            case R.id.iv_fanhui:
                finish();
                if (chatFragment.inputMenu.onBackPressed()) {
                    finish();
                    if (chatFragment.chatType == EaseConstant.CHATTYPE_GROUP) {
                        EaseAtMessageHelper.get().removeAtMeGroup(id);
                        EaseAtMessageHelper.get().cleanToAtUserList();
                    }
                    if (chatFragment.chatType == EaseConstant.CHATTYPE_CHATROOM) {
                        EMClient.getInstance().chatroomManager().leaveChatRoom(id);
                    }
                }
                break;
            case R.id.tv_weixin:
                pay_type = 2;
                if (type_pay == 0) {
                    DialorUtil.getInstance().showImAlertDialog(ChatActivity.this, "咨询付费后提供律师微信号", new DialorUtil.OnButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(AlertDialog dialog) {

                        }

                        @Override
                        public void onNegativeButtonClick(AlertDialog dialog) {
                            dialog.dismiss();
                        }
                    });
                } else if (type_pay == 1) {
                    //TODO 点击微信支付过
                    //                    ToastUtil.showCenterToast(userid);
                    //                    ToastUtil.showCenterToast("暂未开通此功能");
                    HashMap<String, String> map = new HashMap<>();
                    map.put("userid", uid);
                    map.put("lvshiid", id);
                    getPresenter().getExChangeWx(map);
                }
                break;
            case R.id.tv_pingjia:
                if (type_pay == 0) {
                    DialorUtil.getInstance().showImAlertDialog(ChatActivity.this, "咨询付费后才可评价", new DialorUtil.OnButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(AlertDialog dialog) {

                        }

                        @Override
                        public void onNegativeButtonClick(AlertDialog dialog) {
                            dialog.dismiss();
                        }
                    });
                } else if (type_pay == 1) {
                    startActivity(new Intent(ChatActivity.this, EvaluateActivity.class));
                }
                break;
            case R.id.tv_tousu:
                if (type_pay == 0) {
                    DialorUtil.getInstance().showImAlertDialog(ChatActivity.this, "咨询付费后才可投诉", new DialorUtil.OnButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(AlertDialog dialog) {

                        }

                        @Override
                        public void onNegativeButtonClick(AlertDialog dialog) {
                            dialog.dismiss();
                        }
                    });
                } else if (type_pay == 1) {
                    startActivity(new Intent(ChatActivity.this, ComplaintActivity.class));
                }
                break;
            case R.id.tv_hetong:
                startActivity(new Intent(ChatActivity.this, ActivityContractList.class));
                break;
            case R.id.tv_qsz:
                startActivity(new Intent(ChatActivity.this, ActivityQiSuZhuang.class));
                break;
            case R.id.tv_kaiting:
                startActivity(new Intent(ChatActivity.this, ActivityOpenHelper.class));
                break;
            case R.id.tv_guwen:
                startActivity(new Intent(ChatActivity.this, ActivityLegalAdviser.class));
                break;
            case R.id.tv_zixun:
                //律师端聊天页面顶部他的咨询
                Intent intent = new Intent(ChatActivity.this, ActivityMineZiXun.class);
                intent.putExtra("id",id);
                startActivity(intent);
                break;
            case R.id.tv_hongbao:
                //律师端聊天页面顶部收服务费
                showFwfAlertDialog();
                break;
        }

    }
    private void showFwfAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View view = View.inflate(this, R.layout.view_alert_dialog_fwf, null);
        TextView tv_qrsq = view.findViewById(R.id.tv_qrsq);
        EditText ed_email = view.findViewById(R.id.ed_email);
       tv_qrsq.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String edPrice = ed_email.getText().toString().trim();
               if(!TextUtils.isEmpty(edPrice)){
                   showCenterToast(edPrice);
               }else{
                   showCenterToast("输入金额不能为空");
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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Todo 因为环信创建会话的原因   目前解决方案是突出之后删除本地会话,之后考虑重构
        EMClient.getInstance().chatManager().deleteConversation(id, false);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onToCheckPaySucccess(ToCheckPayBean checkPayBean) {
        hideLoading();
        if (checkPayBean == null) {
            ToastUtil.showCenterToast(checkPayBean.getMsg());
        } else {
            String type = checkPayBean.getType();
            switch (type) {
                case "3":
                    ToastUtil.showCenterToast(checkPayBean.getMsg());
                    break;
                case "4":
                    //                    ToastUtil.showCenterToast("未支付");
                    //支付选择弹框
                    showPayDialor();
                    break;
            }
        }
    }

    private void showPayDialor() {
        View dialogView = getLayoutInflater().inflate(R.layout.pay_view_alert_dialor1, null);
        //微信支付的选择
        mIvWeichatSelect = dialogView.findViewById(R.id.iv_buy_weichat_select);
        //支付宝的选择
        mIvAliSelect = dialogView.findViewById(R.id.iv_buy_zhifubao_select);
        PayBottomDialog dialog = new PayBottomDialog(ChatActivity.this, dialogView, new int[]{R.id.ll_pay_weichat, R.id.ll_pay_zhifubao, R.id.pay, R.id.iv_guanbi});
        dialog.bottmShow();
        dialog.setOnBottomItemClickListener(new PayBottomDialog.OnBottomItemClickListener() {
            @Override
            public void onBottomItemClick(PayBottomDialog payBottomDialog, View view) {
                switch (view.getId()) {
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
                        if (payType == PAY_TYPE_WECHAT) {
                            //                           ToastUtil.showCenterToast("微信");
                            if (pay_type == 2) {
                                //                                ToastUtil.showCenterToast("13");
                                HashMap<String, String> map = new HashMap<>();
                                map.put("type", String.valueOf(13));
                                map.put("xitong", String.valueOf(1));
                                map.put("id", ex_id);
                                map.put("uid", ex_userid);
                                map.put("lv_id", ex_lvshiid);
                                map.put("jilu_id", ex_jilu_id);
                                getPresenter().setMessageWatchPay(map);
                            } else if (pay_type == 1) {
                                HashMap<String, String> map = new HashMap<>();
                                map.put("type", String.valueOf(12));
                                map.put("xitong", String.valueOf(1));
                                map.put("id", huashuid);
                                map.put("uid", userid);
                                map.put("lv_id", lvshiid);
                                map.put("jilu_id", jiluid);
                                getPresenter().setMessageWatchPay(map);
                            }
                        } else if (payType == PAY_TYPE_ALIBABA) {
                            // ToastUtil.showCenterToast("支付宝");
                            if (pay_type == 2) {
                                HashMap<String, String> map = new HashMap<>();
                                map.put("type", String.valueOf(13));
                                map.put("xitong", String.valueOf(1));
                                map.put("id", ex_id);
                                map.put("uid", ex_userid);
                                map.put("lv_id", ex_lvshiid);
                                map.put("jilu_id", ex_jilu_id);
                                getPresenter().setMessageAliPay(map);
                            } else if (pay_type == 1) {
                                HashMap<String, String> map = new HashMap<>();
                                map.put("type", String.valueOf(12));
                                map.put("xitong", String.valueOf(1));
                                map.put("id", String.valueOf(3));
                                map.put("uid", userid);
                                map.put("lv_id", lvshiid);
                                map.put("jilu_id", jiluid);
                                getPresenter().setMessageAliPay(map);
                            }

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

    //微信支付成功回调
    @Override
    public void onMessageWatchPaySucccess(WxPayBean wxPayBean) {
        hideLoading();
        if (wxPayBean != null || wxPayBean.getSign() != null) {
            WxPayBean.SignBean sign = wxPayBean.getSign();
            PayHelper.getInstance().payByWechat(sign);
        }
    }

    //接受event事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getWxPayEvent(MessageEvent messageEvent) {
        switch (messageEvent.getWhat()) {
            case AppConstant.WX_PAY_SUCCESS:
                if (pay_type == 2) {
                    //交换微信
                    HashMap<String, String> map = new HashMap<>();
                    map.put("userid", uid);
                    map.put("lvshiid", id);
                    getPresenter().getExChangeWx(map);
                } else {
                    showToast("支付成功");
                    HashMap<String, String> mapPay = new HashMap<>();
                    mapPay.put("user_id", uid);
                    mapPay.put("lv_id", id);
                    getPresenter().getWhetherToPay(mapPay);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onMessageAliPaySuccess(PayBean payBean) {
        hideLoading();
        if (payBean != null) {
            String sign = payBean.getSign();
            getAliPay(sign);
        }
    }

    @Override
    public void onLvShiCardSuccess(LvShiCardBean lvShiCardBean) {
        hideLoading();
        if (lvShiCardBean != null || lvShiCardBean.getData() != null) {
            name = lvShiCardBean.getData().getName();
            lingyu = lvShiCardBean.getData().getLingyu();
            picurlc = lvShiCardBean.getData().getPicurl();
            lvshitype = lvShiCardBean.getData().getLvshitype();
            xueli = lvShiCardBean.getData().getXueli();
            cyear = lvShiCardBean.getData().getCyear();
            jianjie = lvShiCardBean.getData().getJianjie();
            sex = lvShiCardBean.getData().getSex();
            List<LvShiCardBean.DataBean.AnliContentBean> anli_content = lvShiCardBean.getData().getAnli_content();
            if (anli_content != null) {
                anli_one = anli_content.get(0).getContent();
                anli_two = anli_content.get(1).getContent();
            }
            /*if (TextUtils.isEmpty(type) || !type.equals("1")) {
                EMConversation conversation2 = EMClient.getInstance().chatManager().getConversation(id, EaseCommonUtils.getConversationType(AppConstant.CHATTYPE_SINGLE), true);
                if (null != conversation2 && null != conversation2.getAllMessages() && !conversation2.getAllMessages().isEmpty()) {
                    //因为进入会话页面 会给律师发一条消息  第一次进入聊天页面 即当此会话只有一条消息记录时
                    if (conversation2.getAllMessages().size() == 1) {
                        EMMessage message = EMMessage.createTxtSendMessage("[个人信息]", id);
//                        message.setAttribute("ctype", true);
                        message.setAttribute("ctype", "4");
                        message.setAttribute("name", name);
                        message.setAttribute("picurlc", picurlc);
                        message.setAttribute("lingyu", lingyu);
                        message.setAttribute("lvshitype", lvshitype);
                        message.setAttribute("xueli", xueli);
                        message.setAttribute("cyear", cyear);
                        message.setAttribute("jianjie", jianjie);
                        message.setAttribute("sex", sex);
                        message.setAttribute("anli_one", anli_one);
                        message.setAttribute("anli_two", anli_two);
                        // save accept message
                        //发送扩展消息
                        EMClient.getInstance().chatManager().sendMessage(message);
                        conversation2.insertMessage(message);
                    }

                }
            }*/
        }
    }

    @Override
    public void onFirstMessageSuccess(FirstMessageBean messageBean) {

    }

    @Override
    public void onWhetherToPay(WhetherToPayBean whetherToPayBean) {
        if (whetherToPayBean != null) {
            type_pay = whetherToPayBean.getType();
        }
    }

    @Override
    public void onCheckWeiXinSuccess(CheckWeixinBean checkWeixinBean) {
        if (checkWeixinBean != null) {
            int type = checkWeixinBean.getType();
            switch (type) {
                case 3:
                    ToastUtil.showCenterToast(checkWeixinBean.getMsg());
                    break;
                case 4:
                    //                    ToastUtil.showCenterToast("未支付");
                    //支付选择弹框
                    showPayDialor();
                    break;
            }
        }
    }

    //交换微信成功回调
    @Override
    public void onExChangeWx(ExChangeWeiXinBean exChangeWeiXinBean) {
        if (exChangeWeiXinBean != null) {
            ex_lvshiid = exChangeWeiXinBean.getLvshiid();
            ex_userid = exChangeWeiXinBean.getUserid();
            ex_jilu_id = exChangeWeiXinBean.getJilu_id();
            ex_id = exChangeWeiXinBean.getId();
            ex_wxid = exChangeWeiXinBean.getWxid();
            String price = exChangeWeiXinBean.getPrice();
            if (TextUtils.isEmpty(exChangeWeiXinBean.getWxid())) {
                DialorUtil.showExChangeWxDialog(ChatActivity.this, "获取微信需另外支付" + price + "元", new DialorUtil.OnButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(AlertDialog dialog) {
                        dialog.dismiss();
                        showPayDialor();
                    }

                    @Override
                    public void onNegativeButtonClick(AlertDialog dialog) {
                        dialog.dismiss();
                    }
                });
            } else {
                DialorUtil.txteviewSelectDialog(ChatActivity.this, exChangeWeiXinBean.getWxid(), new DialorUtil.OnCopyClickListener() {
                    @Override
                    public void onCopyClick(AlertDialog dialog, TextView textView) {
                        //传入需要复制的文字的控件
                        CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), textView);
                        copyButtonLibrary.init();
                        dialog.dismiss();
                    }
                });
            }
        }
    }

    private void getAliPay(String sign) {
        PayHelper.getInstance().AliPay(ChatActivity.this, sign);
        PayHelper.getInstance().setIPayListener(new PayHelper.IPayListener() {
            @Override
            public void onSuccess() {
                if (pay_type == 2) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("userid", uid);
                    map.put("lvshiid", id);
                    getPresenter().getExChangeWx(map);
                } else {
                    showToast("支付成功");
                    HashMap<String, String> mapPay = new HashMap<>();
                    mapPay.put("user_id", uid);
                    mapPay.put("lv_id", id);
                    getPresenter().getWhetherToPay(mapPay);
                }
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
    public void onChatFiled() {

    }
}
