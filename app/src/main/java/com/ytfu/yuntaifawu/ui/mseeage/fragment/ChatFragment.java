package com.ytfu.yuntaifawu.ui.mseeage.fragment;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.model.EaseDingMessageHelper;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.hyphenate.easeui.widget.presenter.EaseChatRowCardPresenter;
import com.hyphenate.easeui.widget.presenter.EaseChatRowPresenter;
import com.hyphenate.exceptions.HyphenateException;
import com.hyphenate.util.EasyUtils;
import com.hyphenate.util.PathUtil;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.MessageService;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.domain.RobotUser;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.CustomMessageHelper;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.MainActivity;
import com.ytfu.yuntaifawu.ui.chatroom.bean.AddMessageResponseBean;
import com.ytfu.yuntaifawu.ui.mseeage.EaseChatRecallPresenter;
import com.ytfu.yuntaifawu.ui.mseeage.EaseChatVoiceCallPresenter;
import com.ytfu.yuntaifawu.ui.mseeage.activity.ChatActivity;
import com.ytfu.yuntaifawu.ui.mseeage.activity.ContextMenuActivity;
import com.ytfu.yuntaifawu.ui.mseeage.activity.FwhMessageDetailsActivity;
import com.ytfu.yuntaifawu.ui.mseeage.activity.LvShiDetailsActivity;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HistoryRecordResponseBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HuanXinLoginBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiCardBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.SendMessageBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ZiXunSendMessageBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQiSuZhuang;
import com.ytfu.yuntaifawu.utils.DemoHelper;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

public class ChatFragment extends EaseChatFragment implements EaseChatFragment.EaseChatFragmentHelper {
    // constant start from 11 to avoid conflict with constant in base class
    private static final int ITEM_VIDEO = 11;
    private static final int ITEM_FILE = 12;
    private static final int ITEM_VOICE_CALL = 13;
    private static final int ITEM_VIDEO_CALL = 14;
    private static final int ITEM_CONFERENCE_CALL = 15;
    private static final int ITEM_LIVE = 16;

    private static final int REQUEST_CODE_SELECT_VIDEO = 11;
    private static final int REQUEST_CODE_SELECT_FILE = 12;
    private static final int REQUEST_CODE_GROUP_DETAIL = 13;
    private static final int REQUEST_CODE_CONTEXT_MENU = 14;
    private static final int REQUEST_CODE_SELECT_AT_USER = 15;

    private static final int MESSAGE_TYPE_SENT_RICH_CARD = 16;
    private static final int MESSAGE_TYPE_RECV_RICH_CARD = 17;


    private static final int MESSAGE_TYPE_SENT_VOICE_CALL = 1;
    private static final int MESSAGE_TYPE_RECV_VOICE_CALL = 2;
    private static final int MESSAGE_TYPE_SENT_VIDEO_CALL = 3;
    private static final int MESSAGE_TYPE_RECV_VIDEO_CALL = 4;
    private static final int MESSAGE_TYPE_CONFERENCE_INVITE = 5;
    private static final int MESSAGE_TYPE_LIVE_INVITE = 6;
    private static final int MESSAGE_TYPE_RECALL = 9;
    private String nikeName;
    private String avatar;
    private long lastClickTime = 0L;

    /**
     * if it is chatBot
     */
    private boolean isRobot;
    private String uid;
    private ImageView iv_head;
    private ImageView iv_xing;
    private TextView tv_name;
    private TextView tv_lingyu;
    private TextView tv_dengji;
    private TextView tv_xueli;
    private TextView tv_nianling;
    private TextView tv_jianjie;
    private TextView tv_anli_one;
    private TextView tv_anli_two;
    private String type;
    private String lid;
    private String userName;
    private static final String TAG = "ChatFragment";
    private String shenfen;


    /**
     * 同步消息到服务器
     */
    private void syncToService(String content) {
        String type = SpUtil.getString(AppConstant.SHENFEN, "1");
        String to = "";
        String from = "";
        if ("1".equals(type)) {
            //User
            to = uid;
            from = lid;
        } else {
            to = lid;
            from = uid;
        }
        final String toFinal = to;
        final String fromFinal = from;

        handler.post(() -> HttpUtil.getInstance().getService2(MessageService.class)
                .syncMessageToService(toFinal, fromFinal, content, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(ChatFragment.this))
                .subscribe(new BaseRxObserver<AddMessageResponseBean>() {
                    @Override
                    public void onNextImpl(AddMessageResponseBean response) {
                        Logger.e("同步成功");
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("同步失败 --> " + errorMessage);
                        //  getView().onSyncMessageToFail(errorMessage);
                    }
                }));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Logger.e("Chat Fragment onCreateView");
        setOnMessageCallback(new OnMessageCallback() {
            @Override
            public void onSuccess(EMMessage message, String content) {
                String s = message.conversationId();
                Logger.e("发送成功之后的 conversation Id : " + s);
                syncToService(content);
            }

            @Override
            public void onError(int code, String msg) {

            }
        });

        return super.onCreateView(inflater, container, savedInstanceState, DemoHelper.getInstance().getModel().isMsgRoaming() && (chatType != EaseConstant.CHATTYPE_CHATROOM));
    }

    @Override
    protected boolean turnOnTyping() {
        return DemoHelper.getInstance().getModel().isShowMsgTyping();
    }

    @Override
    protected void initView() {
        uid = SpUtil.getString(AppConstant.UID, "");
        shenfen = SpUtil.getString(AppConstant.SHENFEN, "");
        Bundle arguments = getArguments();
        lid = arguments.getString(EaseConstant.EXTRA_USER_ID);
        type = arguments.getString("type");
        userName = arguments.getString("userName");
        HashMap<String, String> map = new HashMap<>();
        map.put("lid", lid);
        getLvShiCard(map);
        super.initView();

    }

    @Override
    protected void setUpView() {
        Bundle bundle = getArguments();
        if (null == bundle) {
            return;
        }

        lid = bundle.getString(EaseConstant.EXTRA_USER_ID);


        setChatFragmentHelper(this);
        //        setCheckVoice(this);
        if (chatType == EaseConstant.CHATTYPE_SINGLE) {
            Map<String, RobotUser> robotMap = DemoHelper.getInstance().getRobotList();
            if (robotMap != null && robotMap.containsKey(toChatUsername)) {
                isRobot = true;
            }
        }
        super.setUpView();
        titleBar.setLeftLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (EasyUtils.isSingleActivity(getActivity())) {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                onBackPressed();
            }
        });
        if (chatType == EaseConstant.CHATTYPE_GROUP) {
            inputMenu.getPrimaryMenu().getEditText().addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    /*if(count == 1 && "@".equals(String.valueOf(s.charAt(start)))){
                        startActivityForResult(new Intent(getActivity(), PickAtUserActivity.class).
                                putExtra("groupId", toChatUsername), REQUEST_CODE_SELECT_AT_USER);
                    }*/
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
        View view = LayoutInflater.from(getContext()).inflate(R.layout.ease_row_received_header_card, null);
        //        View headerView = View.inflate(getContext(), R.layout.ease_row_received_header_card, null);
        iv_head = view.findViewById(R.id.iv_head);
        iv_xing = view.findViewById(R.id.iv_xing);
        tv_name = view.findViewById(R.id.tv_name);
        tv_lingyu = view.findViewById(R.id.tv_lingyu);
        tv_dengji = view.findViewById(R.id.tv_dengji);
        tv_xueli = view.findViewById(R.id.tv_xueli);
        tv_nianling = view.findViewById(R.id.tv_nianling);
        tv_jianjie = view.findViewById(R.id.tv_jianjie);
        tv_anli_one = view.findViewById(R.id.tv_anli_one);
        tv_anli_two = view.findViewById(R.id.tv_anli_two);
        LinearLayout ll_click = view.findViewById(R.id.ll_click);
        if (shenfen.equals("1")) {
            if (TextUtils.isEmpty(type) || !type.equals("1")) {
                messageList.addHeader(view);
            } else {
                getZiXunMessage();
            }
        }
        //律师顶部卡片点击事件
        ll_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LvShiDetailsActivity.class);
                intent.putExtra("lid", lid);
                intent.putExtra("userName", userName);
                intent.putExtra("types", 1);
                startActivity(intent);
            }
        });

    }

    //咨询发送卡片消息
    private void getZiXunMessage() {
        HttpUtil.getInstance().getApiService().setZiXunMessage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<ZiXunSendMessageBean>() {
                    @Override
                    public void onNextImpl(ZiXunSendMessageBean ziXunSendMessageBean) {

                        EMConversation conversation = EMClient.getInstance().chatManager()
                                .getConversation(lid);

                        if (ziXunSendMessageBean.getStatus() == 200) {
                            List<ZiXunSendMessageBean.ListBean> list = ziXunSendMessageBean.getList();
                            for (int i = 0; i < list.size(); i++) {
                                View fw_view = LayoutInflater.from(getContext()).inflate(R.layout.fuwuhao_header_view, null);
                                LinearLayout ll_suzhaung = fw_view.findViewById(R.id.ll_suzhaung);
                                ImageView ll_sz_icon = fw_view.findViewById(R.id.ll_sz_icon);
                                TextView tv_sz_title = fw_view.findViewById(R.id.tv_sz_title);
                                TextView tv_sz_ms = fw_view.findViewById(R.id.tv_sz_ms);
                                GlideManager.loadImageByUrl(getContext(), list.get(i).getImgurl(), ll_sz_icon);
                                tv_sz_title.setText(list.get(i).getTitle());
                                tv_sz_ms.setText(list.get(i).getContent());
                                int type = list.get(i).getType();
                                String url = list.get(i).getUrl();
                                //服务号诉状
                                ll_suzhaung.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        switch (type) {
                                            case 1:
                                                startActivity(new Intent(getActivity(), ActivityQiSuZhuang.class));
                                                break;
                                            case 2:
                                                if (!TextUtils.isEmpty(url)) {
                                                    Intent intent = new Intent(getActivity(), FwhMessageDetailsActivity.class);
                                                    intent.putExtra("url", url);
                                                    startActivity(intent);
                                                }
                                                break;
                                        }

                                    }
                                });
                                messageList.addHeader(fw_view);
                            }
                        } else {
                            Log.e(TAG, "onNextImpl: " + ziXunSendMessageBean.getStatus());
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e(errorMessage);
                    }
                });
    }

    @Override
    protected void registerExtendMenuItem() {
        super.registerExtendMenuItem();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CONTEXT_MENU) {
            switch (resultCode) {
                case ContextMenuActivity.RESULT_CODE_COPY: // copy
                    clipboard.setPrimaryClip(ClipData.newPlainText(null,
                            ((EMTextMessageBody) contextMenuMessage.getBody()).getMessage()));
                    break;
                case ContextMenuActivity.RESULT_CODE_DELETE: // delete
                    conversation.removeMessage(contextMenuMessage.getMsgId());
                    messageList.refresh();
                    // To delete the ding-type message native stored acked users.
                    EaseDingMessageHelper.get().delete(contextMenuMessage);
                    break;

                case ContextMenuActivity.RESULT_CODE_FORWARD: // forward
                   /* Intent intent = new Intent(getActivity(), ForwardMessageActivity.class);
                    intent.putExtra("forward_msg_id", contextMenuMessage.getMsgId());
                    startActivity(intent);*/
                    break;
                case ContextMenuActivity.RESULT_CODE_RECALL://recall
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                EMMessage msgNotification = EMMessage.createTxtSendMessage(" ", contextMenuMessage.getTo().toLowerCase());
                                EMTextMessageBody txtBody = new EMTextMessageBody(getResources().getString(R.string.msg_recall_by_self));
                                msgNotification.addBody(txtBody);
                                msgNotification.setMsgTime(contextMenuMessage.getMsgTime());
                                msgNotification.setLocalTime(contextMenuMessage.getMsgTime());
                                msgNotification.setAttribute(EaseConstant.MESSAGE_TYPE_RECALL, true);
                                msgNotification.setStatus(EMMessage.Status.SUCCESS);
                                EMClient.getInstance().chatManager().recallMessage(contextMenuMessage);
                                EMClient.getInstance().chatManager().saveMessage(msgNotification);
                                messageList.refresh();
                            } catch (final HyphenateException e) {
                                e.printStackTrace();
                                getActivity().runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();

                    // Delete group-ack data according to this message.
                    EaseDingMessageHelper.get().delete(contextMenuMessage);
                    break;

                default:
                    break;
            }
        }
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_SELECT_VIDEO: //send the video
                    if (data != null) {
                        int duration = data.getIntExtra("dur", 0);
                        String videoPath = data.getStringExtra("path");
                        File file = new File(PathUtil.getInstance().getImagePath(), "thvideo" + System.currentTimeMillis());
                        try {
                            FileOutputStream fos = new FileOutputStream(file);
                            Bitmap ThumbBitmap = ThumbnailUtils.createVideoThumbnail(videoPath, 3);
                            ThumbBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                            fos.close();
                            sendVideoMessage(videoPath, file.getAbsolutePath(), duration);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case REQUEST_CODE_SELECT_FILE: //send the file
                    if (data != null) {
                        Uri uri = data.getData();
                        if (uri != null) {
                            sendFileByUri(uri);
                        }
                    }
                    break;
                case REQUEST_CODE_SELECT_AT_USER:
                    if (data != null) {
                        String username = data.getStringExtra("username");
                        inputAtUsername(username, false);
                    }
                    break;
                default:
                    break;
            }
        }
        if (requestCode == REQUEST_CODE_GROUP_DETAIL) {
            switch (resultCode) {
                /*case GroupDetailsActivity.RESULT_CODE_SEND_GROUP_NOTIFICATION:
                    // Start the ding-type msg send ui.
                    EMLog.i(TAG, "Intent to the ding-msg send activity.");
                    Intent intent = new Intent(getActivity(), EaseDingMsgSendActivity.class);
                    intent.putExtra(EaseConstant.EXTRA_USER_ID, toChatUsername);
                    startActivityForResult(intent, REQUEST_CODE_DING_MSG);
                    break;*/
            }
        }
    }


    @Override
    public void onSetMessageAttributes(EMMessage message) {
        if (isRobot) {
            //set message extension
            message.setAttribute("em_robot_message", isRobot);
        }

        //        message.setAttribute(EaseConstant.MESSAGE_PAY_TYPE, "1");
        //        // 通过扩展属性，将userPic和userName发送出去。
        //        String userPic = SpUtil.getString(AppConstant.USER_PORTRAIT, "");
        //        message.setAttribute("hxid_avatar", userPic);
        //        String userName = SpUtil.getString(AppConstant.USERNAME, "");
        //        if (!TextUtils.isEmpty(userName)) {
        //            message.setAttribute("hxid_nickname", userName);
        //        }
        //        // 存入内存
        //        Map<String, EaseUser> contactList = DemoHelper.getInstance().getContactList();
        //        EaseUser easeUser = new EaseUser(message.getTo());
        //        List<PlannerHXBean> beanList = PlannerHelperDao.getInstance(App.getContext()).plannerSelect();
        //        for (int i = 0; i < beanList.size(); i++) {
        //            PlannerHXBean plannerHXBean = beanList.get(i);
        //            if (!TextUtils.isEmpty(plannerHXBean.getHx_username())) {
        //                String s = plannerHXBean.getHx_username().toLowerCase();
        //                if (message.getTo().equals(s)) {
        //                    easeUser.setAvatar(plannerHXBean.getUser_avatar());
        //                    easeUser.setNickname(plannerHXBean.getNike_name());
        //                }
        //            }
        //        }
        //        contactList.put(message.getTo(), easeUser);
        //        // 存入db
        //        UserDao dao = new UserDao(App.getContext());
        //        List<EaseUser> users = new ArrayList<EaseUser>();
        //        users.add(easeUser);
        //        dao.saveContactList(users);
        //
        //        DemoHelper.getInstance().getModel().setContactSynced(true);

    }

    @Override
    public void setSendMessage(String content) {
        HashMap<String, String> map = new HashMap<>();
        map.put("from", uid);
        map.put("lvshi", toChatUsername);
        map.put("msg", content);
        getSendMessage(map);
    }

    //发送消息
    private void getSendMessage(HashMap<String, String> map) {
        //        HttpUtil.getInstance().getApiService().setSendMessage(map)
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .as(RxLifecycleUtil.bindLifecycle(this))
        //                .subscribe(new BaseRxObserver<SendMessageBean>() {
        //                    @Override
        //                    public void onNextImpl(SendMessageBean sendMessageBean) {
        //                        if (sendMessageBean.getStatus() == 200) {
        //                            Log.e(TAG, "onNextImpl: " + sendMessageBean.getMsg());
        //                        } else {
        //                            Log.e(TAG, "onNextImpl: " + sendMessageBean.getMsg());
        //                        }
        //                    }
        //
        //                    @Override
        //                    public void onErrorImpl(String errorMessage) {
        //                        Logger.e(errorMessage);
        //                    }
        //                });
    }


    //加载律师卡片
    private void getLvShiCard(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setLvShiCard(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<LvShiCardBean>() {
                    @Override
                    public void onNextImpl(LvShiCardBean lvShiCardBean) {
                        Log.e("onNextImpl", "------" + "success");
                        onCardSuccess(lvShiCardBean);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e(errorMessage);
                    }
                });
    }

    private void onCardSuccess(LvShiCardBean lvShiCardBean) {
        if (lvShiCardBean != null && lvShiCardBean.getData() != null) {
            LvShiCardBean.DataBean data = lvShiCardBean.getData();
            GlideManager.loadImageByUrl(getContext(), data.getPicurl(), iv_head);
            String sex = data.getSex();
            tv_name.setText(data.getName());
            tv_lingyu.setText(data.getLingyu());
            tv_dengji.setText(data.getLvshitype());
            tv_xueli.setText(data.getXueli());
            tv_nianling.setText(data.getCyear());
            tv_jianjie.setText(data.getJianjie());
            List<LvShiCardBean.DataBean.AnliContentBean> anli_content = data.getAnli_content();
            if (anli_content != null) {
                tv_anli_one.setText(anli_content.get(0).getContent());
                tv_anli_two.setText(anli_content.get(1).getContent());
            }
            if (!TextUtils.isEmpty(sex)) {
                switch (sex) {
                    case "1":
                        iv_xing.setBackgroundResource(com.hyphenate.easeui.R.drawable.xingbie_boy);
                        break;
                    case "2":
                        iv_xing.setBackgroundResource(com.hyphenate.easeui.R.drawable.xingbie_gril);
                        break;
                }
            }
        }
    }

    public void setNike(String nikeName) {
        this.nikeName = nikeName;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public void onEnterToChatDetails() {

    }

    //点击头像跳转
    @Override
    public void onAvatarClick(String username) {
        //        Intent intent = new Intent();
        if (iKuozhanPayClickListener != null) {
            iKuozhanPayClickListener.iconClickListener();
        }
    }

    @Override
    public void onAvatarLongClick(String username) {
        inputAtUsername(username);
    }

    @Override
    public boolean onMessageBubbleClick(EMMessage message) {

        String type = message.getStringAttribute(EaseConstant.MESSAGE_PAY_TYPE, "");
        //        boolean ctype = message.getBooleanAttribute("ctype", false);
        if (!TextUtils.isEmpty(type)) {
            //            Toast.makeText(getContext(), "点击", Toast.LENGTH_SHORT).show();
            if (type.equals("1")) {
                if (iKuozhanPayClickListener != null) {
                    iKuozhanPayClickListener.kuoZhanPayClickListener(message);
                }
            }
        }
        String ctype = message.getStringAttribute("ctype", "");
        if (ctype.equals("4")) {
            if (iKuozhanPayClickListener != null) {
                iKuozhanPayClickListener.lvShiDetails();
            }
        }
        return false;
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        super.onCmdMessageReceived(messages);
    }

    @Override
    public void onMessageBubbleLongClick(EMMessage message) {


    }

    @Override
    public boolean onExtendMenuItemClick(int itemId, View view) {
        switch (itemId) {
            case ITEM_VIDEO:
                /*Intent intent = new Intent(getActivity(), ImageGridActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_VIDEO);*/
                break;
            case ITEM_FILE: //file
                selectFileFromLocal();
                break;
            case ITEM_VOICE_CALL:
                //startVoiceCall();
                break;
            case ITEM_VIDEO_CALL:
                // startVideoCall();
                break;
            case ITEM_CONFERENCE_CALL:
                //ConferenceActivity.startConferenceCall(getActivity(), toChatUsername);
                break;
            case ITEM_LIVE:
                //LiveActivity.startLive(getContext(), toChatUsername);
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * select file
     */
    protected void selectFileFromLocal() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("*/*");

        startActivityForResult(intent, REQUEST_CODE_SELECT_FILE);
    }

    @Override
    public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
        return new CustomChatRowProvider();
    }

    //    @Override
    //    public void isClick() {
    //        startVoiceCall();
    //    }

    private void startVoiceCall() {
        //        if (!EMClient.getInstance().isConnected()) {
        //            Toast.makeText(getActivity(), R.string.not_connect_to_server, Toast.LENGTH_SHORT).show();
        //        } else {
        //            startActivity(new Intent(getActivity(), VoiceCallActivity.class)
        //                    .putExtra("username", toChatUsername)
        //                    .putExtra("userNike", nikeName)
        //                    .putExtra("userAvatar", avatar)
        //                    .putExtra("isComingCall", false));
        //            // voiceCallBtn.setEnabled(false);
        //            inputMenu.hideExtendMenuContainer();
        //        }
    }

    /**
     * chat row provider
     */
    private final class CustomChatRowProvider implements EaseCustomChatRowProvider {
        @Override
        public int getCustomChatRowTypeCount() {
            //here the number is the message type in EMMessage::Type
            //which is used to count the number of different chat row
            return 14;
        }

        @Override
        public int getCustomChatRowType(EMMessage message) {
            if (message.getType() == EMMessage.Type.TXT) {
                //voice call
                if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, false)) {
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VOICE_CALL : MESSAGE_TYPE_SENT_VOICE_CALL;
                } else if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VIDEO_CALL, false)) {
                    //video call
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_VIDEO_CALL : MESSAGE_TYPE_SENT_VIDEO_CALL;
                }
                //messagee recall
                else if (message.getBooleanAttribute(EaseConstant.MESSAGE_TYPE_RECALL, false)) {
                    return MESSAGE_TYPE_RECALL;
                } else if (!"".equals(message.getStringAttribute(AppConstant.MSG_ATTR_CONF_ID, ""))) {
                    return MESSAGE_TYPE_CONFERENCE_INVITE;
                } else if (AppConstant.OP_INVITE.equals(message.getStringAttribute(AppConstant.EM_CONFERENCE_OP, ""))) {
                    return MESSAGE_TYPE_LIVE_INVITE;
                } /*else if (message.getBooleanAttribute("ctype", false)) {
                    return message.direct() == EMMessage.Direct.RECEIVE ? MESSAGE_TYPE_RECV_RICH_CARD : MESSAGE_TYPE_SENT_RICH_CARD;
                }*/
            }
            return 0;
        }

        @Override
        public EaseChatRowPresenter getCustomChatRow(EMMessage message, int position, BaseAdapter adapter) {
            if (message.getType() == EMMessage.Type.TXT) {
                // voice call or video call
                if (message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VOICE_CALL, false) ||
                        message.getBooleanAttribute(EaseConstant.MESSAGE_ATTR_IS_VIDEO_CALL, false)) {
                    EaseChatRowPresenter presenter = new EaseChatVoiceCallPresenter();
                    return presenter;
                }
                //recall message
                else if (message.getBooleanAttribute(EaseConstant.MESSAGE_TYPE_RECALL, false)) {
                    EaseChatRowPresenter presenter = new EaseChatRecallPresenter();
                    return presenter;
                } /*else if (message.getBooleanAttribute("ctype", false)) {
                    EaseChatRowCardPresenter presenter = new EaseChatRowCardPresenter();
                    return presenter;
                }*/

                /* else if (!"".equals(message.getStringAttribute(AppConstant.MSG_ATTR_CONF_ID,""))) {
                    return new ChatRowConferenceInvitePresenter();
                } else if (AppConstant.OP_INVITE.equals(message.getStringAttribute(AppConstant.EM_CONFERENCE_OP, ""))) {
                    return new ChatRowLivePresenter();
                }*/
            }
            return null;
        }

    }

    //定义接口回调扩展消息点击事件
    private IKuozhanPayClickListener iKuozhanPayClickListener;

    public void setIKuozhanPayClickListener(IKuozhanPayClickListener iKuozhanPayClickListener) {
        this.iKuozhanPayClickListener = iKuozhanPayClickListener;
    }

    public interface IKuozhanPayClickListener {
        void kuoZhanPayClickListener(EMMessage message);

        void iconClickListener();

        void lvShiDetails();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
