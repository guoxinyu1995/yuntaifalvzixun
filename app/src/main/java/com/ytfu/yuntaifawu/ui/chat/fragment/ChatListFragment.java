package com.ytfu.yuntaifawu.ui.chat.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.MessageService;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.db.LvShiDao;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.im.EmChatManager;
import com.ytfu.yuntaifawu.ui.chat.adapter.LawyerListAdapter;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.chat.p.ChatListPresenter;
import com.ytfu.yuntaifawu.ui.chat.v.IChatListView;
import com.ytfu.yuntaifawu.ui.chatroom.activity.UserChatRoomActivity;
import com.ytfu.yuntaifawu.ui.lawyer.chat.act.LawyerChatRoomActivity;
import com.ytfu.yuntaifawu.ui.mseeage.activity.ChatActivity;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HistoryRecordResponseBean;
import com.ytfu.yuntaifawu.utils.RecyclerViewItemDecoration;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * 聊天列表
 */
public class ChatListFragment extends BaseFragment<IChatListView, ChatListPresenter> implements IChatListView {


    private RecyclerView recyele_view;
    private SwipeRefreshLayout srl_message_refresh;
    private TextView tvTitle;
    private LawyerListAdapter adapter;
    private Handler mHandler = new Handler();

    public static ChatListFragment newInstance() {
        return new ChatListFragment();
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_message;
    }

    @Override
    protected ChatListPresenter createPresenter() {
        return new ChatListPresenter();
    }

    @Override
    protected void initView(View rootView) {
        mPresenter.registerMessageListener();
        recyele_view = rootView.findViewById(R.id.rv_message_content);
        tvTitle = rootView.findViewById(R.id.tv_toolbar_title);
        srl_message_refresh = rootView.findViewById(R.id.srl_message_refresh);

        String type = SpUtil.getString(AppConstant.SHENFEN, "1");
        if (type.equals("2")) {
            rootView.findViewById(R.id.tl_title_toolbar).setBackgroundColor(getResources().getColor(R.color.transparent_4c));
            tvTitle.setTextColor(Color.WHITE);
        } else {
            rootView.findViewById(R.id.tl_title_toolbar).setBackgroundColor(Color.WHITE);
            tvTitle.setTextColor(Color.parseColor("#434343"));
        }
    }

    @Override
    public void onResume() {
        reLoadList();
        super.onResume();
    }

    @Override
    protected void initData() {
        tvTitle.setText("消息");

        //设置用户信息名字，头像等
        EaseUI.getInstance().setUserProfileProvider(username -> {
            EaseUser easeUser = new EaseUser(username);
            if (!TextUtils.isEmpty(username)) {
                List<ConversationBean.MsgBean> beanList = LvShiDao.getInstance(getContext()).lvshiSelect();
                String s = username.toLowerCase();
                for (int i = 0; i < beanList.size(); i++) {
                    ConversationBean.MsgBean msgBean = beanList.get(i);
                    Logger.e("ss = " + s + ", pic = " + msgBean.getPicurl());

                    if (!TextUtils.isEmpty(msgBean.getLid())) {
                        String s1 = msgBean.getLid().toLowerCase();
                        if (s.equals(s1)) {
                            if (!TextUtils.isEmpty(msgBean.getPicurl())) {
                                easeUser.setAvatar(msgBean.getPicurl());
                                Logger.e("s = " + s + ", pic = " + msgBean.getPicurl());
                            }
                            easeUser.setNickname(msgBean.getName());
                            return easeUser;
                        }
                    }
                    EMConversation conversation2 = EMClient.getInstance().chatManager().getConversation(username, EMConversation.EMConversationType.Chat, true);
                    if (conversation2 != null) {
                        EMMessage lastMessage = conversation2.getLastMessage();
                        if (lastMessage != null) {
                            conversation2.appendMessage(lastMessage);
                        }
                    }
                }

            }

            //            EaseUser user = new EaseUser(lawyerItemBean.getConversationId().toLowerCase());
            //            user.setNickname(lawyerItemBean.getNickName());
            //            user.setAvatar(lawyerItemBean.getHeaderImage());
            //            DemoHelper.getInstance().getModel().saveContact(user);

            return null;
        });

        srl_message_refresh.setColorSchemeColors(
                generateBeautifulColor(),
                generateBeautifulColor(),
                generateBeautifulColor(),
                generateBeautifulColor()
        );
        srl_message_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (App.getInstance().getLoginFlag()) {
                    reLoadList();
                }
            }
        });
        int lineColor = Color.parseColor("#F5F5F5");
        recyele_view.addItemDecoration(RecyclerViewItemDecoration.createVertical(lineColor, 1, 0));

        recyele_view.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LawyerListAdapter();
        adapter.bindToRecyclerView(recyele_view);
        recyele_view.setAdapter(adapter);
        adapter.setEnableLoadMore(true);

        if (AppConstant.DEBUG) {
            //测试使用 长安删除本地回话
            adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                    LawyerListBean.LawyerItemBean lawyerItemBean = ChatListFragment.this.adapter.getData().get(position);
                    boolean isDel = EmChatManager.getInstance().deleteConversation(lawyerItemBean.getConversationId(), false);
                    if (isDel) {
                        adapter.remove(position);
                    }
                    return true;
                }
            });
        }

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LawyerListBean.LawyerItemBean lawyerItemBean = ChatListFragment.this.adapter.getData().get(position);
                boolean isCustomerService = false;
                if (lawyerItemBean.getType() == 1) {
                    isCustomerService = true;
                }
                String toUserId = lawyerItemBean.getConversationId();
                String toUserName = lawyerItemBean.getNickName();
                String toUserAvatar = lawyerItemBean.getHeaderImage();

                String type = SpUtil.getString(AppConstant.SHENFEN, "1");
                if ("1".equals(type)) {
                    UserChatRoomActivity.start(getContext(), isCustomerService,false, toUserId, toUserName, toUserAvatar);
                } else {
                    LawyerChatRoomActivity.start(getContext(),false, toUserId, toUserName, toUserAvatar, "");
                }
                //                getHistoryRecord(lawyerItemBean);
                //                EMConversation conversation =
                //                        EmChatManager.getInstance().getConversationById(lawyerItemBean.getConversationId());
                //                if (null == conversation || conversation.getAllMsgCount() == 0) {
                //                    getHistoryRecord(lawyerItemBean);
                //                } else {
                //                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                //                    intent.putExtra("name", lawyerItemBean.getNickName());
                //                    intent.putExtra(AppConstant.EXTRA_USER_ID, lawyerItemBean.getConversationId());
                //                    Logger.e("$$$$$$$$$$$$$$$$$$$$$$$$$$" + lawyerItemBean.getHeaderImage());
                //                    intent.putExtra("picurl", lawyerItemBean.getHeaderImage());
                //                    intent.putExtra("type", String.valueOf(lawyerItemBean.getType()));
                //                    startActivity(intent);
                //                }
            }
        });
        if (App.getInstance().getLoginFlag()) {
            reLoadList();
        }
    }

    @Override
    public void onDestroy() {
        mPresenter.unRegisterMessageListener();
        super.onDestroy();
    }

    //===Desc:=================================================================

    @Override
    public void onLawyerListSuccess(List<LawyerListBean.LawyerItemBean> list) {
        List<LawyerListBean.LawyerItemBean> data = new ArrayList<>();

        for (LawyerListBean.LawyerItemBean item : list) {
            if (item.getType() == 1) {
                data.add(0, item);
            } else {
                data.add(item);
            }
        }
        if (data.isEmpty()) {
            adapter.setEmptyView(R.layout.layout_empty);
        } else {
            adapter.setNewData(data);
        }
        hideLoading();
        srl_message_refresh.setRefreshing(false);

    }

    @Override
    public void onLawyerListFail(String msg) {
        mHandler.post(() -> {
            adapter.setEmptyView(R.layout.layout_error);
            ToastUtil.showToast(msg);
            srl_message_refresh.setRefreshing(false);
            hideLoading();
        });
    }


    /**
     * 从服务器获取你是聊天数据显示
     */
    private void getHistoryRecord(LawyerListBean.LawyerItemBean lawyerItemBean) {
        String uid = SpUtil.getString(AppConstant.UID, "");
        String sourceStr = SpUtil.getString(AppConstant.SHENFEN, "1");
        int source;
        try {
            source = Integer.parseInt(sourceStr);
        } catch (NumberFormatException e) {
            source = 1;
        }
        String type = SpUtil.getString(AppConstant.SHENFEN, "1");
        String to;
        String from;
        if ("1".equals(type)) {
            //User
            to = lawyerItemBean.getConversationId();
            from = uid;
        } else {
            to = uid;
            from = lawyerItemBean.getConversationId();
        }
        HttpUtil.getInstance().getService(MessageService.class)
                .getHistoryRecord(from, to, source)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<HistoryRecordResponseBean>() {
                    @Override
                    public void onSubscribeImpl(Disposable d) {
                        super.onSubscribeImpl(d);
                        showWaitingDialog("", true);
                    }

                    @Override
                    public void onNextImpl(HistoryRecordResponseBean bean) {
                        if ("200".equals(bean.getCode())) {
                            List<EMMessage> msgs = new ArrayList<>();
                            for (HistoryRecordResponseBean.DataBean item : bean.getData()) {
                                EMMessage message = null;
                                // 自己发送的
                                if (item.getDirection() == 0) {
                                    message = EMMessage.createTxtSendMessage(item.getBody().getText(), item.getTo());
                                    message.setDirection(EMMessage.Direct.SEND);
                                    message.setFrom(item.getFrom());
                                    message.setTo(item.getTo());
                                } else {//律师发送的
                                    message = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
                                    EMTextMessageBody body = new EMTextMessageBody(item.getBody().getText());
                                    message.addBody(body);
                                    message.setDirection(EMMessage.Direct.RECEIVE);
                                    message.setChatType(EMMessage.ChatType.GroupChat);
                                    message.setFrom(item.getFrom());
                                    Logger.e("form == " + item.getFrom());
                                    message.setTo(item.getTo());
                                }
                                //                                message.setFrom(item.getFrom());
                                //                                message.setTo(item.getTo());

                                Logger.e("c id = " + message.conversationId() + ", from : " + item.getFrom() + ",, to : " + item.getTo());
                                //   EMMessage message = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
                                message.setChatType(EMMessage.ChatType.Chat);
                                message.setStatus(EMMessage.Status.SUCCESS);
                                message.setUnread(false);
                                message.setDelivered(true);
                                message.setMsgId(item.getMessageId());
                                message.setMsgTime(item.getTimestamp() * 1000);
                                message.setLocalTime(item.getTimestamp() * 1000);
                                message.setChatType(EMMessage.ChatType.Chat);
                                message.setStatus(EMMessage.Status.SUCCESS);
                                message.setAcked(item.getIsRead() == 1);
                                message.setProgress(100);

                                String conversationId = "";
                                if (message.direct() == EMMessage.Direct.RECEIVE) {
                                    conversationId = item.getFrom();
                                } else {
                                    conversationId = item.getTo();
                                }
                                EMClient.getInstance()
                                        .chatManager()
                                        .getConversation(conversationId, EMConversation.EMConversationType.GroupChat, true)
                                        .insertMessage(message);
                                //                                EMClient.getInstance()
                                //                                        .chatManager().saveMessage(message);
                            }

                        }

                        // ChatRoomActivity.start(getContext(), lawyerItemBean,bean.getData());
                        Intent intent = new Intent(getActivity(), ChatActivity.class);
                        intent.putExtra("name", lawyerItemBean.getNickName());
                        intent.putExtra(AppConstant.EXTRA_USER_ID, lawyerItemBean.getConversationId());
                        intent.putExtra("picurl", lawyerItemBean.getHeaderImage());
                        intent.putExtra("type", String.valueOf(lawyerItemBean.getType()));
                        startActivity(intent);
                        hideWaitingDialog();
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        hideWaitingDialog();
                    }
                });
    }

    private @ColorInt
    int generateBeautifulColor() {
        Random random = new Random();
        int r = random.nextInt(150) + 50;
        int g = random.nextInt(150) + 50;
        int b = random.nextInt(150) + 50;
        return Color.rgb(r, g, b);
    }


    @Override
    public void reLoadList() {
        mHandler.post(() -> {
            String uid = SpUtil.getString(AppConstant.UID, "");
            mPresenter.loadLawyerList(uid);
        });
    }
}
