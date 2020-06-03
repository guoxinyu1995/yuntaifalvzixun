package com.ytfu.yuntaifawu.ui.chatroom.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.db.LvShiDao;
import com.ytfu.yuntaifawu.im.EmChatManager;
import com.ytfu.yuntaifawu.ui.MainActivity;
import com.ytfu.yuntaifawu.ui.chatroom.bean.FeeWechatOrderResponse;
import com.ytfu.yuntaifawu.ui.chatroom.bean.RoomLawyerCardInfoResponse;
import com.ytfu.yuntaifawu.ui.chatroom.header.ChatRoomCardHeaderController;
import com.ytfu.yuntaifawu.ui.chatroom.header.ChatRoomCustomerHeaderController;
import com.ytfu.yuntaifawu.ui.chatroom.p.UserChatRoomPresenter;
import com.ytfu.yuntaifawu.ui.chatroom.v.UserChatRoomView;
import com.ytfu.yuntaifawu.ui.custom.CopyButtonLibrary;
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviser;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityContractList;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityOpenHelper;
import com.ytfu.yuntaifawu.ui.lawyer.chat.adapter.LawyerChatRoomAdapter;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatExtBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemMultiItem;
import com.ytfu.yuntaifawu.ui.mseeage.activity.ComplaintActivity;
import com.ytfu.yuntaifawu.ui.mseeage.activity.EvaluateActivity;
import com.ytfu.yuntaifawu.ui.mseeage.activity.LvShiDetailsActivity;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ExChangeWeiXinBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ToCheckPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.WhetherToPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ZiXunSendMessageBean;
import com.ytfu.yuntaifawu.ui.pay.PayBottomDialog;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQiSuZhuang;
import com.ytfu.yuntaifawu.utils.DialorUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 聊天室
 */
public class UserChatRoomActivity extends BaseActivity<UserChatRoomView, UserChatRoomPresenter>
        implements UserChatRoomView {

    @BindView(R.id.tl_room_toolbar)
    Toolbar tl_room_toolbar;
    @BindView(R.id.tv_room_title)
    TextView tv_room_title;
    @BindView(R.id.ll_room_service_top)
    LinearLayout ll_room_service_top;
    @BindView(R.id.ll_room_lawyer_top)
    LinearLayout ll_room_lawyer_top;

    @BindView(R.id.rv_room_content)
    RecyclerView rv_room_content;
    @BindView(R.id.et_room_input)
    EditText et_room_input;
    @BindView(R.id.srv_room_refresh)
    SwipeRefreshLayout srv_room_refresh;

    private LawyerChatRoomAdapter adapter;
    /**
     * 显示支付的底部dialog
     */
    private PayBottomDialog dialog;

    private ChatRoomCardHeaderController cardHeaderController;
    private ChatRoomCustomerHeaderController customerHeaderController;


    /**
     * 交换微信的请求码
     */
    private final int codeExchangeWeChat = 0;
    /**
     * 评价
     */
    private final int codeAssess = 1;
    /**
     * 投诉
     */
    private final int codeComplaint = 2;


    private static final String KEY_IS_CUSTOMER_SERVICE = "KEY_IS_CUSTOMER_SERVICE";
    private static final String KEY_TO_USER_ID = "KEY_TO_USER_ID";
    private static final String KEY_TO_USER_NAME = "KEY_TO_USER_NAME";
    private static final String KEY_TO_USER_AVATAR = "KEY_TO_USER_AVATAR";
    private static final String KEY_IS_FROM_NOTIFICATION = "KEY_IS_FROM_NOTIFICATION";


    public static void start(Context context, boolean isCustomerService, boolean isFromNotification,
                             String toUserId, String toUserName, String toUserAvatar) {

        context.startActivity(getStartIntent(context, isCustomerService, isFromNotification, toUserId, toUserName, toUserAvatar));
    }


    public static Intent getStartIntent(Context context, boolean isCustomerService, boolean isFromNotification,
                                        String toUserId, String toUserName, String toUserAvatar) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_IS_CUSTOMER_SERVICE, isCustomerService);
        bundle.putString(KEY_TO_USER_ID, toUserId);
        bundle.putString(KEY_TO_USER_NAME, toUserName);
        bundle.putString(KEY_TO_USER_AVATAR, toUserAvatar);
        bundle.putBoolean(KEY_IS_FROM_NOTIFICATION, isFromNotification);
        Intent starter = new Intent(context, UserChatRoomActivity.class);
        starter.putExtras(bundle);
        return starter;
    }


    //===Desc:=================================================================


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_chat_room;
    }

    @Override
    protected UserChatRoomPresenter createPresenter() {
        return new UserChatRoomPresenter();
    }

    private HistoryChatItemMultiItem clickPayItem;

    @Override
    protected void initView() {
        //注册eventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        mPresenter.registerMessageListener();

        EMConversation conversation = EmChatManager.getInstance().getConversationById(getBundleString(KEY_TO_USER_ID, ""));
        if (null != conversation) {
            conversation.markAllMessagesAsRead();
        }
        Eyes.setStatusBarColor(this, Color.parseColor("#44A2F7"));
        //===Desc:Toolbar=================================================================
        //初始化Toolbar
        srv_room_refresh.setEnabled(false);

        String toUserName = getBundleString(KEY_TO_USER_NAME, "");
        tv_room_title.setText(toUserName);

        tl_room_toolbar.setTitle("");
        setSupportActionBar(tl_room_toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
        tl_room_toolbar.setNavigationIcon(R.drawable.fanhui_bai);
        tl_room_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //===Desc:=================================================================
        String toUserAvatar = getBundleString(KEY_TO_USER_AVATAR, "");
        ConversationBean.MsgBean msgBean = LvShiDao.getInstance(this).selectById(SpUtil.getString(AppConstant.UID, ""));
        if (null == msgBean) {
            ToastUtil.showToast("应用程序出现内部错误,请稍后重试");
            finish();
            return;
        }
        String selfAvatar = msgBean.getPicurl();
        adapter = new LawyerChatRoomAdapter(toUserAvatar, selfAvatar);
        adapter.bindToRecyclerView(rv_room_content);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int id = view.getId();
                switch (id) {
                    case R.id.rl_chat_item_pay:
                        HistoryChatItemMultiItem clickItem = UserChatRoomActivity.this.adapter.getData().get(position);
                        if (clickItem.getItemType() != HistoryChatItemMultiItem.TYPE_RECEIVE_FEE) {
                            ToastUtil.showToast("应用程序出现内部错误,请稍后重试");
                            return;
                        }
                        int type = clickItem.getChatItem().getExt().getType();
                        if (type == 2) {
                            ToastUtil.showToast("该笔订单已经支付,无需重复支付");
                            return;
                        }
                        String toUserId = clickItem.getChatItem().getExt().getLvshiid();
                        String feeId = clickItem.getChatItem().getExt().getJiluid();
                        //效验当前订单是否已经支付,请求服务器获取状态
                        String selfId = SpUtil.getString(AppConstant.UID, "");
                        mPresenter.checkMessagePayed(selfId, toUserId, feeId);
                        clickPayItem = clickItem;
                        break;
                    case R.id.iv_chat_item_avatar:
                        String shengfen = SpUtil.getString(AppConstant.SHENFEN, "1");
//                        if ("1".equals(shengfen)) {
                        HistoryChatItemMultiItem multiItem = UserChatRoomActivity.this.adapter.getData().get(position);
                        if (multiItem.getItemType() == HistoryChatItemMultiItem.TYPE_RECEIVE_MSG
                                || multiItem.getItemType() == HistoryChatItemMultiItem.TYPE_RECEIVE_FEE) {
                            String toUserid = getBundleString(KEY_TO_USER_ID, "");
                            Intent intent = new Intent(UserChatRoomActivity.this, LvShiDetailsActivity.class);
                            intent.putExtra("lid", toUserid);
                            startActivity(intent);
                        }

//                        }
                        break;
                }
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(this);
//        manager.setStackFromEnd(true);
        rv_room_content.setLayoutManager(manager);
        rv_room_content.setAdapter(adapter);

        rv_room_content.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom < oldBottom) {
                rv_room_content.post(() -> rv_room_content.smoothScrollToPosition(adapter.getData().size()));
            }
        });

        //===Desc:=================================================================
        findViewById(R.id.tv_room_send).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String input = et_room_input.getText().toString().trim();
                        if (TextUtils.isEmpty(input)) {
                            ToastUtil.showToast(getString(R.string.hint_please_input));
                            return;
                        }
                        String selfId = SpUtil.getString(AppConstant.UID, "");
                        String toUserId = getBundleString(KEY_TO_USER_ID, "");
                        mPresenter.sendTextMessage(selfId, toUserId, input);
                    }
                });

        //合同
        findViewById(R.id.tv_room_contract).

                setOnClickListener(v ->

                        startActivity(new Intent(UserChatRoomActivity.this, ActivityContractList.class)));

        //写诉状
        findViewById(R.id.tv_room_statement).

                setOnClickListener(v ->

                        startActivity(new Intent(UserChatRoomActivity.this, ActivityQiSuZhuang.class)));

        //开庭助手
        findViewById(R.id.tv_room_helper).

                setOnClickListener(v ->

                        startActivity(new Intent(UserChatRoomActivity.this, ActivityOpenHelper.class)));

        //顾问
        findViewById(R.id.tv_room_advisor).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(UserChatRoomActivity.this, ActivityLegalAdviser.class));
                    }
                });


        //微信号
        findViewById(R.id.tv_room_wechat).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selfId = SpUtil.getString(AppConstant.UID, "");
                        String toUserId = getBundleString(KEY_TO_USER_ID, "");
                        mPresenter.checkPay(codeExchangeWeChat, selfId, toUserId);
                    }
                });

        //评价ta
        findViewById(R.id.tv_room_assess).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selfId = SpUtil.getString(AppConstant.UID, "");
                        String toUserId = getBundleString(KEY_TO_USER_ID, "");
                        mPresenter.checkPay(codeAssess, selfId, toUserId);
                    }
                });

        //投诉
        findViewById(R.id.tv_room_complaint).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String selfId = SpUtil.getString(AppConstant.UID, "");
                        String toUserId = getBundleString(KEY_TO_USER_ID, "");
                        mPresenter.checkPay(codeComplaint, selfId, toUserId);
                    }
                });

        //起诉状
        findViewById(R.id.tv_room_sue).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(UserChatRoomActivity.this, ActivityQiSuZhuang.class));
                    }
                });

        findViewById(R.id.tv_room_helper1).

                setOnClickListener(v ->

                        startActivity(new Intent(UserChatRoomActivity.this, ActivityOpenHelper.class)));

    }

    @Override
    protected void initData() {
        boolean isCustomer = getBundleBoolean(KEY_IS_CUSTOMER_SERVICE, false);
        String toUserId = getBundleString(KEY_TO_USER_ID, "");
        String selfId = SpUtil.getString(AppConstant.UID, "");
        if (isCustomer) {
            ll_room_service_top.setVisibility(View.VISIBLE);
            ll_room_lawyer_top.setVisibility(View.GONE);
            customerHeaderController = new ChatRoomCustomerHeaderController(this);
            mPresenter.getCustomerServiceMessage();
        } else {
            ll_room_service_top.setVisibility(View.GONE);
            ll_room_lawyer_top.setVisibility(View.VISIBLE);
            cardHeaderController = new ChatRoomCardHeaderController(this);
            //获取律师卡片信息
            mPresenter.getLawyerCardInfo(toUserId);
        }
        //获取历史消息
        mPresenter.getHistoryRecord(toUserId, selfId);

        hideLoading();


    }

    @Override
    protected void onDestroy() {
        //反注册
        mPresenter.unregisterMessageListener();
        EventBus.getDefault().unregister(this);
        //重置未读消息数量
        EMConversation conversation = EmChatManager.getInstance().getConversationById(getBundleString(KEY_TO_USER_ID, ""));
        if (null != conversation) {
            conversation.markAllMessagesAsRead();
            EMClient.getInstance()
                    .chatManager()
                    .getConversation(getBundleString(KEY_TO_USER_ID, ""))
                    .markAllMessagesAsRead();

            int unreadMsgCount = conversation.getUnreadMsgCount();
            Logger.e("未读消息数量 -> " + unreadMsgCount);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        boolean isFromNotification = getBundleBoolean(KEY_IS_FROM_NOTIFICATION, false);
        if (isFromNotification) {
            MainActivity.start(this, 1);
        } else {
            super.onBackPressed();
        }
    }

    //===Desc:=================================================================

    @Override
    public void onGetHistoryRecordSuccess(List<HistoryChatItemMultiItem> data) {
        runOnUiThread(() -> {
            adapter.setNewData(data);
            rv_room_content.scrollToPosition(adapter.getData().size());
        });

    }

    @Override
    public void onGetHistoryRecordFail(String errorMsg) {

    }


    //===Desc:=================================================================
    @Override
    public void onSendTxtPre(HistoryChatItemBean itemBean) {
        et_room_input.setText("");
        HistoryChatItemMultiItem bean = new HistoryChatItemMultiItem(itemBean);
        adapter.addData(bean);
        rv_room_content.smoothScrollToPosition(adapter.getData().size());
    }

    @Override
    public void onSendTxtSuccess(String toUserId, String fromUserId, HistoryChatItemBean itemBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //更新adapter中ui显示
                int index = adapter.indexOfByMessageId(itemBean.getMessageId());
                if (index != -1) {
                    HistoryChatItemMultiItem item = adapter.getData().get(index);
                    item.getChatItem().setStatus(2);
                    adapter.notifyItemChanged(index);
                }

                //同步消息到服务器
//                String consultId = getBundleString(KEY_CONSULT_ID, "");
                mPresenter.userSyncMessageToService(toUserId, fromUserId, itemBean.getBody().getText());
            }
        });
    }

    @Override
    public void onSendTxtFail(HistoryChatItemBean itemBean) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWechatPaySuccess(MessageEvent event) {
        int what = event.getWhat();
        if (what == AppConstant.WX_PAY_SUCCESS) {
            //更新支付成功UI显示
            //更新UI
            if (null != clickPayItem) {
                HistoryChatExtBean ext = clickPayItem.getChatItem().getExt();
                if (null != ext) {
                    ext.setType(2);
                }
                adapter.notifyDataSetChanged();
            }
            clickPayItem = null;
        }
    }

    //     EventBus.getDefault().post(new MessageEvent(AppConstant.WX_PAY_SUCCESS));
    //===Desc:获取订单相关=================================================================
    @Override
    public void onGetFeeWechatOrderSuccess(FeeWechatOrderResponse bean) {
        if (null != dialog) {
            dialog.cancel();
        }
        if (null != bean) {
            WxPayBean.SignBean sign = bean.getSign();
            if (null != sign) {
                PayHelper.getInstance().payByWechat(sign);
            } else {
                onGetFeeWechatOrderFail("应用程序出现内部错误,请稍后重试");
            }
        } else {
            onGetFeeWechatOrderFail("应用程序出现内部错误,请稍后重试");
        }
    }

    @Override
    public void onGetFeeWechatOrderFail(String errorMsg) {
        showCenterToast(errorMsg);
    }

    @Override
    public void onGetFeeAliOrderSuccess(PayBean bean) {
        if (null == bean) {
            onGetFeeAliOrderFail("支付出现错误,请稍后重试");
            return;
        }
        String sign = bean.getSign();
        if (TextUtils.isEmpty(sign)) {
            onGetFeeAliOrderFail("支付出现错误,请稍后重试");
            return;
        }
        if (null != dialog) {
            dialog.cancel();
        }
        mPresenter.arouseAlipay(this, sign, new PayHelper.IPayListener() {
            @Override
            public void onSuccess() {
                //支付成功
                Logger.e("!!! onSuccess");
                //支付宝支付成功   更新UI显示
                if (null != clickPayItem) {
                    HistoryChatExtBean ext = clickPayItem.getChatItem().getExt();
                    if (null != ext) {
                        ext.setType(2);
                    }
                    adapter.notifyDataSetChanged();
                }
                clickPayItem = null;
            }

            @Override
            public void onResultConfirmation() {
                showToast("支付结果确认中");
            }

            @Override
            public void onCancel() {
                showToast("您取消支付");
            }

            @Override
            public void onFail() {
                showToast("支付出现错误,请联系客服");
            }
        });
    }

    @Override
    public void onGetFeeAliOrderFail(String errorMsg) {
        showCenterToast(errorMsg);
    }

    //===Desc:=================================================================
    @Override
    public void onGetLawyerCardInfoSuccess(RoomLawyerCardInfoResponse.LawyerCardInfo info) {
        adapter.addHeaderView(cardHeaderController.getHeaderView());
        cardHeaderController.render(info);
    }

    @Override
    public void onGetLawyerCardInfoFail(String errorMsg) {

    }
    //===Desc:=================================================================


    @Override
    public void onGetCustomerServiceMessageSuccess(List<ZiXunSendMessageBean.ListBean> list) {
        adapter.addHeaderView(customerHeaderController.getHeaderView());
        customerHeaderController.render(list);

    }

    @Override
    public void onGetCustomerServiceMessageFail(String errorMsg) {

    }

    //===Desc:=================================================================


    @Override
    public void onCheckPaySuccess(int requestCode, WhetherToPayBean bean) {
        if (null == bean) {
            onCheckPayFail(requestCode, "应用程序出现未知错误,请稍后充实");
            return;
        }
        String selfId = SpUtil.getString(AppConstant.UID, "");
        String toUserId = getBundleString(KEY_TO_USER_ID, "");

        int type = bean.getType();
        switch (requestCode) {
            case codeExchangeWeChat:
                if (type == 0) {
                    //未支付
                    DialorUtil.showImAlertDialog(UserChatRoomActivity.this,
                            "咨询付费后提供律师微信号", new DialorUtil.OnButtonClickListener() {
                                @Override
                                public void onPositiveButtonClick(AlertDialog dialog) {
                                    dialog.dismiss();
                                }

                                @Override
                                public void onNegativeButtonClick(AlertDialog dialog) {
                                    dialog.dismiss();
                                }
                            });
                } else if (type == 1) {
                    //已支付  交换
                    mPresenter.exchangeWeChat(selfId, toUserId);
                }

                break;
            case codeAssess:
                if (type == 0) {
                    //未支付
                    DialorUtil.showImAlertDialog(UserChatRoomActivity.this,
                            "咨询付费后才可评价", new DialorUtil.OnButtonClickListener() {
                                @Override
                                public void onPositiveButtonClick(AlertDialog dialog) {
                                    dialog.dismiss();
                                }

                                @Override
                                public void onNegativeButtonClick(AlertDialog dialog) {
                                    dialog.dismiss();
                                }
                            });
                } else if (type == 1) {
                    //已支付 评价
                    startActivity(new Intent(UserChatRoomActivity.this, EvaluateActivity.class));
                }
                break;
            case codeComplaint:
                if (type == 0) {
                    //未支付
                    DialorUtil.showImAlertDialog(UserChatRoomActivity.this,
                            "咨询付费后才可投诉", new DialorUtil.OnButtonClickListener() {
                                @Override
                                public void onPositiveButtonClick(AlertDialog dialog) {
                                    dialog.dismiss();
                                }

                                @Override
                                public void onNegativeButtonClick(AlertDialog dialog) {
                                    dialog.dismiss();
                                }
                            });
                } else if (type == 1) {
                    //已支付 投诉
                    startActivity(new Intent(UserChatRoomActivity.this, ComplaintActivity.class));
                }
                break;
        }

    }

    @Override
    public void onCheckPayFail(int requestCode, String errorMsg) {

    }
    //===Desc:=================================================================

    @Override
    public void onExchangeWeChatSuccess(ExChangeWeiXinBean data) {
        String lawyerId = data.getLvshiid();
        String userId = data.getUserid();
        String id = data.getId();
        String wxid = data.getWxid();
        String price = data.getPrice();
        if (TextUtils.isEmpty(wxid)) {
            DialorUtil.showExChangeWxDialog(UserChatRoomActivity.this,
                    "获取微信需另外支付" + price + "元",
                    new DialorUtil.OnButtonClickListener() {
                        @Override
                        public void onPositiveButtonClick(AlertDialog dialog) {
                            //支付

                        }

                        @Override
                        public void onNegativeButtonClick(AlertDialog dialog) {
                            dialog.dismiss();
                        }
                    });
        } else {
            DialorUtil.txteviewSelectDialog(UserChatRoomActivity.this, wxid, (dialog, textView) -> {
                //传入需要复制的文字的控件
                CopyButtonLibrary copyButtonLibrary = new CopyButtonLibrary(getApplicationContext(), textView);
                copyButtonLibrary.init();
                dialog.dismiss();
            });
        }
    }

    @Override
    public void onExchangeWeChatFail(String errorMsg) {
        showCenterToast(errorMsg);
    }

    //===Desc:=================================================================
    private static final int PAY_TYPE_WECHAT = 0;  //微信支付,默认支付方式
    private static final int PAY_TYPE_ALIPAY = 1;
    private int payMethod = PAY_TYPE_WECHAT;

    private void showPayDialor(String toUserId, String feeId) {
        View dialogView = getLayoutInflater().inflate(R.layout.pay_view_alert_dialor1, null);
        CheckBox cb_pay_wechat = dialogView.findViewById(R.id.cb_pay_wechat);
        CheckBox cb_pay_ali = dialogView.findViewById(R.id.cb_pay_ali);
        cb_pay_wechat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payMethod = PAY_TYPE_WECHAT;
                    cb_pay_ali.setChecked(false);
                }
            }
        });
        cb_pay_ali.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    payMethod = PAY_TYPE_ALIPAY;
                    cb_pay_wechat.setChecked(false);
                }
            }
        });
        dialog = new PayBottomDialog(this, dialogView, new int[]{R.id.ll_pay_weichat, R.id.ll_pay_zhifubao, R.id.pay, R.id.iv_guanbi});
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);

        dialog.setOnBottomItemClickListener(new PayBottomDialog.OnBottomItemClickListener() {
            @Override
            public void onBottomItemClick(PayBottomDialog payBottomDialog, View view) {
                switch (view.getId()) {
                    case R.id.iv_guanbi:
                        dialog.cancel();
                        break;
                    case R.id.ll_pay_weichat:
                        cb_pay_wechat.setChecked(true);
                        cb_pay_ali.setChecked(false);
                        break;
                    case R.id.ll_pay_zhifubao:
                        cb_pay_wechat.setChecked(false);
                        cb_pay_ali.setChecked(true);
                        break;
                    case R.id.pay:
                        switch (payMethod) {
                            case PAY_TYPE_WECHAT:
                                wechatPay(toUserId, feeId);
                                break;
                            case PAY_TYPE_ALIPAY:
                                aliPay(toUserId, feeId);
                                break;
                            default:
                                ToastUtil.showToast("应用程序出现内部错误,请稍后重试");
                                break;

                        }
                        break;
                }
            }
        });
        cb_pay_wechat.setChecked(true);
        dialog.bottmShow();
    }


    /**
     * 微信支付
     *
     * @param redPackageId 红包id
     */
    private void wechatPay(String toUserId, String redPackageId) {
        String selfId = SpUtil.getString(AppConstant.UID, "");
        mPresenter.getFeePayOrder(true, selfId, toUserId, redPackageId);
    }

    /**
     * 支付宝
     */
    private void aliPay(String toUserId, String redPackageId) {
        String selfId = SpUtil.getString(AppConstant.UID, "");
        mPresenter.getFeePayOrder(false, selfId, toUserId, redPackageId);
    }

    ///////////////////////////////////////////////////////////////////////////
    // 环信聊天接收消息的监听回调
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void onTextReceived(List<HistoryChatItemMultiItem> list) {
        Logger.e("接收到消息");
        List<HistoryChatItemMultiItem> data = new ArrayList<>();
        for (HistoryChatItemMultiItem item : list) {
            String from = item.getChatItem().getFrom();
            Logger.e("From user id : " + from + ", Current to user id = " + getBundleString(KEY_TO_USER_ID, ""));
            String toUserId = getBundleString(KEY_TO_USER_ID, "");
            if (from.equals(toUserId)) {
                data.add(item);
            }
        }
        runOnUiThread(() -> {
            adapter.addData(data);
            rv_room_content.scrollToPosition(adapter.getData().size());
        });

    }

    @Override
    public void onMessageRead(List<HistoryChatItemMultiItem> list) {

    }
    //===Desc:=================================================================


    @Override
    public void onCheckMessagePayedSuccess(ToCheckPayBean data) {
        String type = data.getType();
        //3是已经支付，4是未支付
        if ("3".equals(type)) {
            showToast("该笔订单已经支付,无需重复支付");
            return;
        }
        if (null != clickPayItem) {
            String toUserId = clickPayItem.getChatItem().getExt().getLvshiid();
            String feeId = clickPayItem.getChatItem().getExt().getJiluid();
            //效验当前订单是否已经支付,请求服务器获取状态
            showPayDialor(toUserId, feeId);
        } else {
            showToast("应用程序出现未知错误,请稍后重试");
        }
    }
}
