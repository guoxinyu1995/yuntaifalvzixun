package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.db.LvShiDao;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.FirstMessageBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiCardBean;
import com.ytfu.yuntaifawu.ui.mseeage.fragment.ConversationListFragment;
import com.ytfu.yuntaifawu.ui.mseeage.presenter.ConversationPresenter;
import com.ytfu.yuntaifawu.ui.mseeage.view.IConversationView;
import com.ytfu.yuntaifawu.utils.DemoHelper;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/4/23
 * @Des 消息列表
 */
public class EaseConversationListActivity extends BaseActivity<IConversationView, ConversationPresenter> implements IConversationView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.fl_conversation)
    FrameLayout flConversation;
    private ConversationListFragment conversationListFragment;

    private String uid;
    private Bundle args;
    private String type;
    private String lid;
    private EMConversation conversation2;

    @Override
    protected int provideContentViewId() {
        return R.layout.ease_conversation_list_activity;
    }

    @Override
    protected ConversationPresenter createPresenter() {
        return new ConversationPresenter(this);
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

    //    @Override
    //    protected void onResume() {
    //        super.onResume();
    //        if (null != conversationListFragment && null != conversationListFragment.conversationListView)
    //            conversationListFragment.conversationListView.refresh();
    //    }

    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("消息");
        conversationListFragment = new ConversationListFragment();
        //        ConversationListFragment listFragment = new ConversationListFragment();
        args = new Bundle();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_conversation, conversationListFragment).commit();
        uid = SpUtil.getString(AppConstant.UID, "");
        //        registerForContextMenu(conversationListFragment.conversationListView);
        //        conversationListFragment.refresh();
        //        EventBus.getDefault().register(this);
    }

    //    @Subscribe(threadMode = ThreadMode.MAIN)
    //    public void onMessageEvent(MessageEvent messageEvent) {
    //        if (messageEvent.getKey().equals("newMessage")) {
    //            conversationListFragment.refresh();
    //        }
    //
    //    }

    @Override
    protected void initData() {
        //        mPresenter.getConversationList();

//        String uid = SpUtil.getString(AppConstant.UID, "");
//        mPresenter.getLawyerChatList(uid);

        //        HashMap<String, String> map = new HashMap<>();
        //        map.put("from", uid);
        //        mPresenter.getFirstMessage(map);
        EMClient.getInstance().chatManager().loadAllConversations();
        //设置用户信息名字，头像等
        EaseUI.getInstance().setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {
            @Override
            public EaseUser getUser(String username) {
                EaseUser easeUser = new EaseUser(username);
                if (!TextUtils.isEmpty(username)) {
                    List<ConversationBean.MsgBean> beanList = LvShiDao.getInstance(EaseConversationListActivity.this).lvshiSelect();
                    Log.e("beanlist", "-----" + beanList.size());
                    String s = username.toLowerCase();
                    for (int i = 0; i < beanList.size(); i++) {
                        ConversationBean.MsgBean msgBean = beanList.get(i);
                        Log.e("beanname", "-----" + msgBean.getName());
                        if (!TextUtils.isEmpty(msgBean.getLid())) {
                            String s1 = msgBean.getLid().toLowerCase();
                            if (s.equals(s1)) {
                                if (!TextUtils.isEmpty(msgBean.getPicurl())) {
                                    easeUser.setAvatar(msgBean.getPicurl());
                                }
                                easeUser.setNickname(msgBean.getName());
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
                return easeUser;
            }
        });
        conversationListFragment.setConversationListItemClickListener(new EaseConversationListFragment.EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                String conversationId = conversation.conversationId().toLowerCase();
                Intent intent = new Intent(EaseConversationListActivity.this, ChatActivity.class);
                List<ConversationBean.MsgBean> beanList = LvShiDao.getInstance(EaseConversationListActivity.this).lvshiSelect();
                for (int i = 0; i < beanList.size(); i++) {
                    ConversationBean.MsgBean msgBean = beanList.get(i);
                    if (!TextUtils.isEmpty(msgBean.getLid())) {
                        String s = msgBean.getLid().toLowerCase();
                        if (conversationId.toLowerCase().equals(s)) {
                            intent.putExtra("name", msgBean.getName());
                            intent.putExtra(AppConstant.EXTRA_USER_ID, msgBean.getLid());
                            intent.putExtra("picurl", msgBean.getPicurl());
                            intent.putExtra("type", msgBean.getType());
                            startActivity(intent);
                        }
                    }
                }
                //                intent.putExtra(AppConstant.EXTRA_USER_ID, conversationId);
            }
        });
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onCoversationSuccess(ConversationBean conversationBean) {
        if (conversationBean == null || conversationBean.getMsg() == null || conversationBean.getMsg().isEmpty()) {
            showEmpty();
        } else {
            Map<String, EMConversation> allConversations = EMClient.getInstance().chatManager().getAllConversations();
            List<ConversationBean.MsgBean> conversationBeanMsg = conversationBean.getMsg();
            for (int i = 0; i < conversationBeanMsg.size(); i++) {
                if (!allConversations.containsKey(conversationBeanMsg.get(i).getLid().toLowerCase())) {
                    //本地会话列表没有和此律师的聊天会话
                    //创建消息 创建会话 一般创建完消息 就会有会话
                    //消息 是文本类型的问候语
                    EMConversation conversation2 = EMClient.getInstance().chatManager().getConversation(conversationBeanMsg.get(i).getLid().toLowerCase(), EaseCommonUtils.getConversationType(AppConstant.CHATTYPE_SINGLE), true);
                    List<EMMessage> list = new ArrayList<>();

                    EMMessage message = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
                    message.setChatType(EMMessage.ChatType.Chat);
                    message.setFrom(conversationBeanMsg.get(i).getLid().toLowerCase());
                    if (TextUtils.isEmpty(conversationBeanMsg.get(i).getType()) || !conversationBeanMsg.get(i).getType().equals("1")) {
                        EMMessage message2 = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
                        message2.setChatType(EMMessage.ChatType.Chat);
                        message2.setFrom(conversationBeanMsg.get(i).getLid().toLowerCase());
                        message2.setAttribute("ctype", "4");
                        message2.setAttribute("name", conversationBeanMsg.get(i).getName());
                        message2.setAttribute("picurlc", conversationBeanMsg.get(i).getPicurl());
                        message2.setAttribute("lingyu", conversationBeanMsg.get(i).getLingyu());
                        message2.setAttribute("lvshitype", conversationBeanMsg.get(i).getLvshitype());
                        message2.setAttribute("xueli", conversationBeanMsg.get(i).getXueli());
                        message2.setAttribute("cyear", conversationBeanMsg.get(i).getCyear());
                        message2.setAttribute("jianjie", conversationBeanMsg.get(i).getJianjie());
                        message2.setAttribute("sex", conversationBeanMsg.get(i).getSex());
                        List<ConversationBean.MsgBean.AnLiBean> an_li = conversationBeanMsg.get(i).getAn_li();
                        if (an_li != null) {
                            String anli_one = an_li.get(0).getContent();
                            String anli_two = an_li.get(1).getContent();
                            message2.setAttribute("anli_one", anli_one);
                            message2.setAttribute("anli_two", anli_two);
                        }
                        message2.addBody(new EMTextMessageBody("[个人消息]"));
                        message2.setStatus(EMMessage.Status.SUCCESS);
                        message2.setUnread(true);
                        EMClient.getInstance().chatManager().sendMessage(message2);
                        conversation2.insertMessage(message2);
                    }
                    message.addBody(new EMTextMessageBody(conversationBeanMsg.get(i).getHuashu()));
                    message.setStatus(EMMessage.Status.SUCCESS);
                    message.setUnread(true);

                    // save accept message
                    EMClient.getInstance().chatManager().sendMessage(message);
                    conversation2.insertMessage(message);
                    // 创建当前用户
                    EaseUser user = new EaseUser(conversationBeanMsg.get(i).getLid().toLowerCase());
                    user.setNickname(conversationBeanMsg.get(i).getName());
                    user.setAvatar(conversationBeanMsg.get(i).getPicurl());
                    DemoHelper.getInstance().getModel().saveContact(user);
                    list.add(message);
                    EMClient.getInstance().chatManager().importMessages(list);
                }
                LvShiDao.getInstance(this).lvShiAdd(conversationBeanMsg.get(i).getLid().toLowerCase(), conversationBeanMsg.get(i).getName(), conversationBeanMsg.get(i).getPicurl(), conversationBeanMsg.get(i).getHuashu(), conversationBeanMsg.get(i).getType());
            }
            conversationListFragment.loadConversationList();
            conversationListFragment.refresh();

        }
    }

    @Override
    public void onLvShiCardSuccess(LvShiCardBean lvShiCardBean) {
        //        if (lvShiCardBean != null && lvShiCardBean.getData() != null) {
        ////                                if (TextUtils.isEmpty(conversationBeanMsg.get(i).getType()) || !conversationBeanMsg.get(i).getType().equals("1")) {
        //            EMMessage message2 = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
        //            message2.setChatType(EMMessage.ChatType.Chat);
        //            message2.setFrom(lvShiCardBean.getData().getLid().toLowerCase());
        //            message2.setAttribute("ctype", "4");
        //            message2.setAttribute("name", lvShiCardBean.getData().getName());
        //            message2.setAttribute("picurlc", lvShiCardBean.getData().getPicurl());
        //            message2.setAttribute("lingyu", lvShiCardBean.getData().getLingyu());
        //            message2.setAttribute("lvshitype", lvShiCardBean.getData().getLvshitype());
        //            message2.setAttribute("xueli", lvShiCardBean.getData().getXueli());
        //            message2.setAttribute("cyear", lvShiCardBean.getData().getCyear());
        //            message2.setAttribute("jianjie", lvShiCardBean.getData().getJianjie());
        //            message2.setAttribute("sex", lvShiCardBean.getData().getSex());
        //            message2.addBody(new EMTextMessageBody("[个人消息]"));
        //            message2.setStatus(EMMessage.Status.SUCCESS);
        //            message2.setUnread(true);
        //            EMClient.getInstance().chatManager().sendMessage(message2);
        //            conversation2.insertMessage(message2);
        ////                    }
        //        }
    }

    @Override
    public void onConversationFiled() {

    }

    @Override
    public void onGetLawyerChatListSuccess(List<LawyerListBean.LawyerItemBean> data) {

    }

    @Override
    public void onGetLawyerChatListFail(String msg) {

    }
}
