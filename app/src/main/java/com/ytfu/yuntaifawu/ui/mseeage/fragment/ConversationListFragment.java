package com.ytfu.yuntaifawu.ui.mseeage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.hyphenate.chat.EMChatManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.model.EaseAtMessageHelper;
import com.hyphenate.easeui.model.EaseDingMessageHelper;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.util.NetUtils;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.ChatService;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.db.InviteMessgeDao;
import com.ytfu.yuntaifawu.db.LvShiDao;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.im.EmChatManager;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.mseeage.activity.ChatActivity;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ConversationListFragment extends EaseConversationListFragment {

    private String type;
    private List<ConversationBean.MsgBean> beanList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //    @Override
    //    public void onActivityCreated(Bundle savedInstanceState) {
    //        Bundle bundle = getArguments();
    //        type = bundle.getString("type");
    //        Log.e("tyoe","-------"+ type);
    //        super.onActivityCreated(savedInstanceState);
    //    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String uid = SpUtil.getString(AppConstant.UID, "");
        HttpUtil.getInstance().getService(ChatService.class)
                .getLawyerChatList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<LawyerListBean>() {

                    @Override
                    public void onNextImpl(LawyerListBean lawyerListBean) {
                        if (String.valueOf(AppConstant.STATUS_SUCCESS).equals(lawyerListBean.getCode())) {
                            List<LawyerListBean.LawyerItemBean> data = lawyerListBean.getData();
                            List<EMConversation> list = new ArrayList<>();
                            EMChatManager chatManager = EMClient.getInstance().chatManager();
                            for (LawyerListBean.LawyerItemBean item : data) {
                                EMConversation conversation = chatManager.getConversation(item.getConversationId());
                                if (null != conversation)
                                list.add(conversation);
                            }
                            addConversations(list);
                            //                            getView().onGetLawyerChatListSuccess(lawyerListBean.getData());
                        } else {
                            //                            getView().onGetLawyerChatListFail("获取数据失败,请稍后重试");
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        //  ToastUtil.showToast("网络开小差了");
                        //                        getView().onConversationFiled();
                    }
                });
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        registerForContextMenu(conversationListView);
        conversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EMConversation conversation = conversationListView.getItem(position);
                String username = conversation.conversationId().toLowerCase();
                if (username.equals(EMClient.getInstance().getCurrentUser()))
                    Toast.makeText(getActivity(), R.string.Cant_chat_with_yourself, Toast.LENGTH_SHORT).show();
                else {
                    // start chat acitivity
                    Intent intent = new Intent(getActivity(), ChatActivity.class);
                    if (conversation.isGroup()) {
                        if (conversation.getType() == EMConversation.EMConversationType.ChatRoom) {
                            // it's group chat
                            intent.putExtra(AppConstant.EXTRA_CHAT_TYPE, AppConstant.CHATTYPE_CHATROOM);
                        } else {
                            intent.putExtra(AppConstant.EXTRA_CHAT_TYPE, AppConstant.CHATTYPE_GROUP);
                        }

                    }
                    // it's single chat
                    intent.putExtra(AppConstant.EXTRA_USER_ID, username);
                    startActivity(intent);
                }
            }
        });
        super.setUpView();
    }

    @Override
    protected void onConnectionDisconnected() {
        super.onConnectionDisconnected();
        if (NetUtils.hasNetwork(getActivity())) {
            //            errorText.setText(R.string.can_not_connect_chat_server_connection);
        } else {
            //            errorText.setText(R.string.the_current_network);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //        getActivity().getMenuInflater().inflate(R.menu.em_delete_message, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean deleteMessage = false;
        //        if (item.getItemId() == R.id.delete_message) {
        //            deleteMessage = true;
        //        } else if (item.getItemId() == R.id.delete_conversation) {
        //            deleteMessage = false;
        //        }
        EMConversation tobeDeleteCons = conversationListView.getItem(((AdapterView.AdapterContextMenuInfo) item.getMenuInfo()).position);
        if (tobeDeleteCons == null) {
            return true;
        }
        if (tobeDeleteCons.getType() == EMConversation.EMConversationType.GroupChat) {
            EaseAtMessageHelper.get().removeAtMeGroup(tobeDeleteCons.conversationId());
        }
        try {
            // delete conversation
            EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.conversationId(), deleteMessage);
            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
            inviteMessgeDao.deleteMessage(tobeDeleteCons.conversationId());
            // To delete the native stored adked users in this conversation.
            if (deleteMessage) {
                EaseDingMessageHelper.get().delete(tobeDeleteCons);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        refresh();

        // update unread count
        //        ((MainActivity) getActivity()).updateUnreadLabel();
        return true;
    }
}
