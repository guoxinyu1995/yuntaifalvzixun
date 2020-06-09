package com.ytfu.yuntaifawu.ui.lawyer.chat.act;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.MessageService;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.db.LvShiDao;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.LvShiMainActivity;
import com.ytfu.yuntaifawu.ui.MainActivity;
import com.ytfu.yuntaifawu.ui.lawyer.chat.adapter.LawyerChatRoomAdapter;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemMultiItem;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatResponse;
import com.ytfu.yuntaifawu.ui.lawyer.chat.p.LawyerChatRoomPresenter;
import com.ytfu.yuntaifawu.ui.lawyer.chat.v.LawyerChatRoomView;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityMineZiXun;
import com.ytfu.yuntaifawu.ui.mseeage.activity.LvShiDetailsActivity;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * tl_global_toolbar
 * 律师聊天界面
 */
public class LawyerChatRoomActivity extends BaseActivity<LawyerChatRoomView, LawyerChatRoomPresenter>
        implements LawyerChatRoomView {

    @BindView(R.id.tl_global_toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_global_title)
    TextView tv_global_title;


    @BindView(R.id.srl_room_refresh)
    SwipeRefreshLayout srl_room_refresh;
    @BindView(R.id.rv_room_content)
    RecyclerView rv_room_content;

    @BindView(R.id.et_room_input)
    EditText et_room_input;
    @BindView(R.id.tv_room_send)
    TextView tv_room_send;
    @BindView(R.id.tv_room_fee)
    TextView tv_room_fee;

    private LawyerChatRoomAdapter adapter;


    //===Desc:=================================================================
    private static final String KEY_TO_USER_ID = "KEY_TO_USER_ID";
    private static final String KEY_TO_USER_NAME = "KEY_TO_USER_NAME";
    private static final String KEY_TO_USER_AVATAR = "KEY_TO_USER_AVATAR";
    private static final String KEY_CONSULT_ID = "KEY_CONSULT_ID";
    private static final String KEY_IS_FROM_NOTIFICATION = "KEY_IS_FROM_NOTIFICATION";

    public static void start(Context context, boolean isFromNotification,
                             String toUserId, String toUserName, String toUserAvatar, String consultId) {
        context.startActivity(getStartIntent(context, isFromNotification, toUserId, toUserName, toUserAvatar, consultId));
    }

    public static Intent getStartIntent(Context context, boolean isFromNotification,
                                        String toUserId, String toUserName, String toUserAvatar, String consultId) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_TO_USER_ID, toUserId);
        bundle.putString(KEY_TO_USER_NAME, toUserName);
        bundle.putString(KEY_TO_USER_AVATAR, toUserAvatar);
        bundle.putString(KEY_CONSULT_ID, consultId);
        bundle.putBoolean(KEY_IS_FROM_NOTIFICATION, isFromNotification);

        Intent starter = new Intent(context, LawyerChatRoomActivity.class);
        starter.putExtras(bundle);
        return starter;
    }


    //===Desc:=================================================================


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_lawyer_chatroom;
    }

    @Override
    protected LawyerChatRoomPresenter createPresenter() {
        return new LawyerChatRoomPresenter();
    }

    @Override
    protected void initView() {
        getPresenter().registerMessageListener();

        //===Desc:设置Toolbar相关=================================================================
        toolbar.setTitle("");
        toolbar.setBackgroundColor(getResources().getColor(R.color.transparent_4c));
        toolbar.setNavigationIcon(R.drawable.fanhui_bai);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        String toUserName = getBundleString(KEY_TO_USER_NAME, getString(R.string.app_name));
        tv_global_title.setText(toUserName);
        //===Desc:顶部按钮相关=================================================================
        tv_room_fee.setOnClickListener(v -> showFwfAlertDialog());
        findViewById(R.id.tv_room_advisory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //律师端聊天页面顶部他的咨询
                String toUserId = getBundleString(KEY_TO_USER_ID, "");
                if (TextUtils.isEmpty(toUserId)) {
                    ToastUtil.showToast("应用程序出现内部错误,请稍后重试");
                    return;
                }
                Intent intent = new Intent(LawyerChatRoomActivity.this, ActivityMineZiXun.class);
                intent.putExtra("id", toUserId);
                startActivity(intent);
            }
        });

        //===Desc:设置刷新相关=================================================================
        srl_room_refresh.setColorSchemeColors(
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor()
        );
        srl_room_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        srl_room_refresh.setEnabled(false);
        //===Desc:设置聊天列表相关=================================================================
        rv_room_content.setLayoutManager(new LinearLayoutManager(this));
        String toUserAvatar = getBundleString(KEY_TO_USER_AVATAR, "");
        String uid = SpUtil.getString(AppConstant.UID, "");
        ConversationBean.MsgBean self = LvShiDao.getInstance(this).selectById(uid);
        String selfAvatar;
        if (null == self) {
            selfAvatar = "";
        } else {
            selfAvatar = self.getPicurl();
        }
        adapter = new LawyerChatRoomAdapter(toUserAvatar, selfAvatar);
//        adapter.bindToRecyclerView(rv_room_content);
        adapter.getLoadMoreModule().setEnableLoadMore(false);
        rv_room_content.setAdapter(adapter);

        rv_room_content.addOnLayoutChangeListener((v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (bottom < oldBottom) {
                rv_room_content.post(() -> rv_room_content.smoothScrollToPosition(adapter.getData().size()));
            }
        });
        adapter.addChildClickViewIds(R.id.iv_chat_item_avatar);
        //律师端头像点击事件
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                int id = view.getId();
                if (id == R.id.iv_chat_item_avatar) {
                    String shengfen = SpUtil.getString(AppConstant.SHENFEN, "1");
                    String uid = SpUtil.getString(AppConstant.UID, "");
                    if ("2".equals(shengfen)) {
                        HistoryChatItemMultiItem multiItem = LawyerChatRoomActivity.this.adapter.getData().get(position);
                        Log.e("getItemType", "onItemChildClick: " + multiItem.getItemType());
                        if (multiItem.getItemType() == HistoryChatItemMultiItem.TYPE_SEND_MSG
                                || multiItem.getItemType() == HistoryChatItemMultiItem.TYPE_SEND_FEE) {
                            String toUserid = getBundleString(KEY_TO_USER_ID, "");
                            Intent intent = new Intent(LawyerChatRoomActivity.this, LvShiDetailsActivity.class);
                            intent.putExtra("lid", uid);
                            startActivity(intent);
                        }

                    }
                }
            }
        });
        //===Desc:=================================================================
        //发送
        tv_room_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = et_room_input.getText().toString().trim();
                if (TextUtils.isEmpty(input)) {
                    ToastUtil.showToast(getString(R.string.hint_please_input));
                    return;
                }
                String selfId = SpUtil.getString(AppConstant.UID, "");
                String toUserId = getBundleString(KEY_TO_USER_ID, "");
                getPresenter().sendTextMessage(selfId, toUserId, input);

            }
        });


    }

    @Override
    protected void initData() {
        String selfId = SpUtil.getString(AppConstant.UID, "");
        String toUserId = getBundleString(KEY_TO_USER_ID, "");

        HttpUtil.getInstance().getService(MessageService.class)
                .lawyerGetHistoryRecord(toUserId, selfId, 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<HistoryChatResponse>() {
                    @Override
                    public void onNextImpl(HistoryChatResponse historyRecordResponseBean) {
                        List<HistoryChatItemMultiItem> data = new ArrayList<>();
                        List<HistoryChatItemBean> list = historyRecordResponseBean.getData();
                        for (HistoryChatItemBean item : list) {
                            HistoryChatItemMultiItem bean = new HistoryChatItemMultiItem(item);
                            data.add(bean);
                        }
                        adapter.setNewData(data);
                        rv_room_content.scrollToPosition(adapter.getData().size() - 1);

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {

                    }
                });

        hideLoading();
    }

    @Override
    protected void onDestroy() {
        getPresenter().unregisterMessageListener();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        boolean isFromNotification = getBundleBoolean(KEY_IS_FROM_NOTIFICATION, false);
        if (isFromNotification) {
            String type = SpUtil.getString(AppConstant.SHENFEN, "1");
            if ("1".equals(type)) {
                MainActivity.start(this, 1);
            } else {
                LvShiMainActivity.start(this, 1);
            }
        } else {
            super.onBackPressed();
        }
    }

    //===Desc:=================================================================

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
                if (!TextUtils.isEmpty(edPrice)) {
                    String toUserId = getBundleString(KEY_TO_USER_ID, "");
                    String selfId = SpUtil.getString(AppConstant.UID, "");
                    getPresenter().sendFeeMessage(toUserId, selfId, edPrice);
                    alertDialog.dismiss();
                } else {
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


    //===Desc:View接口相关的回调=================================================================
    @Override
    public void onSendTxtPre(HistoryChatItemBean itemBean) {
        HistoryChatItemMultiItem item = new HistoryChatItemMultiItem(itemBean);
        adapter.addData(item);
        //清空输入 并滚动
        et_room_input.setText("");
        rv_room_content.smoothScrollToPosition(adapter.getData().size() - 1);
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
                String consultId = getBundleString(KEY_CONSULT_ID, "");
                getPresenter().lawyerSyncMessageToService(consultId, toUserId, fromUserId, itemBean.getBody().getText());
            }
        });
    }

    @Override
    public void onSendTxtFail(HistoryChatItemBean itemBean) {
        //显示消息失败
    }

    @Override
    public void onSendFeePre(HistoryChatItemBean itemBean) {
        HistoryChatItemMultiItem item = new HistoryChatItemMultiItem(itemBean);
        adapter.addData(item);
        //清空输入 并滚动
        rv_room_content.smoothScrollToPosition(adapter.getData().size() - 1);
    }

    @Override
    public void onSendFeeSuccess(String toUserId, String fromUserId, HistoryChatItemBean itemBean) {
//        String content = "收取服务费" + itemBean.getExt().getPrice() + "元";
//        String consultId = getBundleString(KEY_CONSULT_ID, "");
//        getPresenter().lawyerSyncMessageToService(consultId, toUserId, fromUserId, content);
    }

    @Override
    public void onSendFeeFail(HistoryChatItemBean itemBean) {

    }
    //===Desc:=================================================================


    @Override
    public void onSyncMessageSuccess() {
        Logger.e("同步消息到服务器成功");
    }

    @Override
    public void onSyncMessageFail(String errorMsg) {
        Logger.e("同步消息到服务器失败:" + errorMsg);
    }

    //===Desc:环信消息相关=================================================================


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
//        Logger.e("接收到消息");
//        adapter.addData(list);
//        rv_room_content.scrollToPosition(adapter.getData().size() - 1);
    }

    @Override
    public void onMessageRead(List<HistoryChatItemMultiItem> list) {

    }


}
