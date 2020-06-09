package com.ytfu.yuntaifawu.ui.lvshiwenti.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.lawyer.chat.act.LawyerChatRoomActivity;
import com.ytfu.yuntaifawu.ui.lvshiwenti.adaper.WenTiXqIconAdaper;
import com.ytfu.yuntaifawu.ui.lvshiwenti.bean.WenTiXiangQingBean;
import com.ytfu.yuntaifawu.ui.lvshiwenti.presenter.WenTiXqPresenter;
import com.ytfu.yuntaifawu.ui.lvshiwenti.view.IWenTiXqView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/5/26
 * @Des
 */
public class WenTiXiangQIngAtivity extends BaseActivity<IWenTiXqView, WenTiXqPresenter> implements IWenTiXqView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.iv_wenti_xq)
    ImageView ivWentiXq;
    @BindView(R.id.tv_wenti_xq_name)
    TextView tvWentiXqName;
    @BindView(R.id.tv_wenti_xq_time)
    TextView tvWentiXqTime;
    @BindView(R.id.tv_wenti_xq_type)
    TextView tvWentiXqType;
    @BindView(R.id.tv_wenti_xq_price)
    TextView tvWentiXqPrice;
    @BindView(R.id.tv_wenti_xq_connect)
    TextView tvWentiXqConnect;
    @BindView(R.id.tv_wenti_xq_hf_num)
    TextView tvWentiXqHfNum;
    @BindView(R.id.tv_sum)
    TextView tvSum;
    @BindView(R.id.rl_wenti_icon)
    RecyclerView rlWentiIcon;
    @BindView(R.id.tv_liji_goutong)
    TextView tvLijiGoutong;
    private String id;
    private WenTiXqIconAdaper wenTiXqIconAdaper;
    private String hxuid;
    private String wentiid;
    private String user_login;
    private String avatar;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_wenti_xiangqing;
    }

    @Override
    protected WenTiXqPresenter createPresenter() {
        return new WenTiXqPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("咨询详情");
        id = getIntent().getStringExtra("id");
        GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        rlWentiIcon.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initData() {
        wenTiXqIconAdaper = new WenTiXqIconAdaper(this);
        rlWentiIcon.setAdapter(wenTiXqIconAdaper);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", id);
        getPresenter().getWenTiXq(map);
    }

    @OnClick({R.id.iv_fanhui, R.id.tv_liji_goutong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_liji_goutong:
                //                Intent intent = new Intent(WenTiXiangQIngAtivity.this, ChatActivity.class);
                //                intent.putExtra(AppConstant.EXTRA_USER_ID,hxuid);
                getHistoryRecord(hxuid);
                //                intent.putExtra("wentiid",wentiid);
                //                intent.putExtra("name",user_login);
                //                startActivity(intent);
                break;
        }
    }

    @Override
    public void onWenTiXqSuccess(WenTiXiangQingBean wenTiXiangQingBean) {
        if (wenTiXiangQingBean != null) {
            GlideManager.loadImageByUrl(this, wenTiXiangQingBean.getContent().getAvatar(), ivWentiXq);
            tvWentiXqName.setText(wenTiXiangQingBean.getContent().getUser_login());
            tvWentiXqTime.setText(wenTiXiangQingBean.getContent().getConsult_date());
            tvWentiXqType.setText(wenTiXiangQingBean.getContent().getConsult_type());

            double price;
            try {
                price = Double.parseDouble(wenTiXiangQingBean.getContent().getReward_price());
            } catch (NumberFormatException e) {
                price = 0.0;
            }
            if (price == 0.0) {
                tvWentiXqPrice.setVisibility(View.INVISIBLE);
            } else {
                tvWentiXqPrice.setVisibility(View.VISIBLE);
                tvWentiXqPrice.setText("悬赏" + wenTiXiangQingBean.getContent().getReward_price() + "元");
            }
            tvWentiXqConnect.setText(wenTiXiangQingBean.getContent().getConsult_content());
            tvWentiXqHfNum.setText("律师回复" + "(" + wenTiXiangQingBean.getContent().getCount() + ")");
            tvSum.setText(wenTiXiangQingBean.getContent().getCount() + "");
            hxuid = wenTiXiangQingBean.getContent().getUid();
            wentiid = wenTiXiangQingBean.getContent().getId();
            user_login = wenTiXiangQingBean.getContent().getUser_login();
            avatar = wenTiXiangQingBean.getContent().getAvatar();
            int count = wenTiXiangQingBean.getContent().getCount();
            List<String> shuzu = wenTiXiangQingBean.getContent().getShuzu();
            if (shuzu != null) {
                wenTiXqIconAdaper.setList(shuzu);
            }
        }
    }

    @Override
    public void onWenTiXqFiled(String error) {

    }


    /**
     * 从服务器获取你是聊天数据显示
     */
    private void getHistoryRecord(String hxuid) {
        String uid = SpUtil.getString(AppConstant.UID, "");
        String sourceStr = SpUtil.getString(AppConstant.SHENFEN, "1");
        int source;
        try {
            source = Integer.parseInt(sourceStr);
        } catch (NumberFormatException e) {
            source = 1;
        }

        String toUserId = hxuid;
        String toUserName = user_login;
        String toUserAvatar = avatar;
        String consultId = wentiid;
        LawyerChatRoomActivity.start(WenTiXiangQIngAtivity.this, false, toUserId, toUserName, toUserAvatar, consultId);


        //        HttpUtil.getInstance().getService(MessageService.class)
        //                .getHistoryRecord(hxuid,uid,source)
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .as(RxLifecycleUtil.bindLifecycle(this))
        //                .subscribe(new BaseRxObserver<HistoryRecordResponseBean>() {
        //                    @Override
        //                    public void onSubscribeImpl(Disposable d) {
        //                        super.onSubscribeImpl(d);
        //                        showWaitingDialog("", true);
        //                    }
        //
        //                    @Override
        //                    public void onNextImpl(HistoryRecordResponseBean bean) {
        //                        if ("200".equals(bean.getCode())) {
        //                            List<EMMessage> msgs = new ArrayList<>();
        //                            for (HistoryRecordResponseBean.DataBean item : bean.getData()) {
        //                                EMMessage message = null;
        //                                // 自己发送的
        //                                if (item.getDirection() == 0) {
        //                                    message = EMMessage.createTxtSendMessage(item.getBody().getText(), item.getTo());
        //                                    message.setDirection(EMMessage.Direct.SEND);
        //                                    message.setFrom(item.getFrom());
        //                                    message.setTo(item.getTo());
        //                                } else {//律师发送的
        //                                    message = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
        //                                    EMTextMessageBody body = new EMTextMessageBody(item.getBody().getText());
        //                                    message.addBody(body);
        //                                    message.setDirection(EMMessage.Direct.RECEIVE);
        //                                    message.setChatType(EMMessage.ChatType.GroupChat);
        //                                    message.setFrom(item.getFrom());
        //                                    Logger.e("form == " + item.getFrom());
        //                                    message.setTo(item.getTo());
        //                                }
        //                                //                                message.setFrom(item.getFrom());
        //                                //                                message.setTo(item.getTo());
        //
        //                                Logger.e("c id = " + message.conversationId() + ", from : " + item.getFrom() + ",, to : " + item.getTo());
        //                                //   EMMessage message = EMMessage.createReceiveMessage(EMMessage.Type.TXT);
        //                                message.setChatType(EMMessage.ChatType.Chat);
        //                                message.setStatus(EMMessage.Status.SUCCESS);
        //                                message.setUnread(false);
        //                                message.setDelivered(true);
        //                                message.setMsgId(item.getMessageId());
        //                                message.setMsgTime(item.getTimestamp() * 1000);
        //                                message.setLocalTime(item.getTimestamp() * 1000);
        //                                message.setChatType(EMMessage.ChatType.Chat);
        //                                message.setStatus(EMMessage.Status.SUCCESS);
        //                                message.setAcked(item.getIsRead() == 1);
        //                                message.setProgress(100);
        //
        //                                String conversationId = "";
        //                                if (message.direct() == EMMessage.Direct.RECEIVE) {
        //                                    conversationId = item.getFrom();
        //                                } else {
        //                                    conversationId = item.getTo();
        //                                }
        //                                EMClient.getInstance()
        //                                        .chatManager()
        //                                        .getConversation(conversationId, EMConversation.EMConversationType.GroupChat, true)
        //                                        .insertMessage(message);
        //                                //                                EMClient.getInstance()
        //                                //                                        .chatManager().saveMessage(message);
        //                            }
        //
        //                        }
        //
        //                        // ChatRoomActivity.start(getContext(), lawyerItemBean,bean.getData());
        //                        Intent intent = new Intent(WenTiXiangQIngAtivity.this, ChatActivity.class);
        //                        intent.putExtra("name", user_login);
        //                        intent.putExtra(AppConstant.EXTRA_USER_ID, hxuid);
        //                        intent.putExtra("picurl", avatar);
        //                        intent.putExtra("type", String.valueOf(2));
        //                        startActivity(intent);
        //                        hideWaitingDialog();
        //                    }
        //
        //                    @Override
        //                    public void onErrorImpl(String errorMessage) {
        //                        hideWaitingDialog();
        //                    }
        //                });
    }
}
