package com.ytfu.yuntaifawu.ui.chatroom.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.im.EmChatManager;
import com.ytfu.yuntaifawu.im.OnMessageStatusCallback;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.chatroom.p.ChatMessagePresenter;
import com.ytfu.yuntaifawu.ui.chatroom.v.IChatMessageView;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HistoryRecordResponseBean;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.ytfu.yuntaifawu.ui.mseeage.bean.HistoryRecordResponseBean.*;

/**
 * //发送逻辑处理,  首先见文本数据提交到自己服务器,服务器响应成功之后,在使用环信IM发送数据给对方
 */
public class ChatRoomFragment extends BaseFragment<IChatMessageView, ChatMessagePresenter> implements IChatMessageView {

    @BindView(R.id.rv_room_content)
    RecyclerView rv_room_content;
    @BindView(R.id.et_room_input)
    EditText et_room_input;

    private Handler mHandler = new Handler();


    private static final String KEY_LAWYER_ITEM_BEAN = "KEY_LAWYER_ITEM_BEAN";

    public static ChatRoomFragment newInstance(LawyerListBean.LawyerItemBean lawyerItem, List<HistoryRecordResponseBean.DataBean> list) {
        Bundle args = new Bundle();
        args.putParcelable(KEY_LAWYER_ITEM_BEAN, lawyerItem);
        args.putParcelableArrayList("KEY", new ArrayList<>(list));
        ChatRoomFragment fragment = new ChatRoomFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_chat_room;
    }

    @Override
    protected ChatMessagePresenter createPresenter() {
        return new ChatMessagePresenter();
    }

    @Override
    protected void initView(View rootView) {

    }

    private BaseQuickAdapter<String, BaseViewHolder> adapter;

    @Override
    protected void initData() {
        rv_room_content.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(android.R.id.text1, item);
            }
        };
        rv_room_content.setAdapter(adapter);


        adapter.setUpFetchEnable(true);
        List<String> data = new ArrayList<>();
        ArrayList<HistoryRecordResponseBean.DataBean> list = getArguments().getParcelableArrayList("KEY");
        for (HistoryRecordResponseBean.DataBean item : list) {
            data.add(item.getBody().getText());
        }
        for (int i = 0; i < list.size(); i++) {
            adapter.addData(0, data.get(i));
        }
        rv_room_content.scrollToPosition(list.size() - 1);
        // rv_room_content.smoothScrollToPosition(list.size() - 1);
        hideLoading();
        //adapter.setNewData(list);
        adapter.setUpFetchEnable(false);


        et_room_input.setImeOptions(EditorInfo.IME_ACTION_SEND);
        //设置inputType
        et_room_input.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);//属于重要说明的第二条
        et_room_input.setSingleLine(false);//属于重要说明的第四条
        et_room_input.setMaxLines(3);

        et_room_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //非空效验
                    String msg = et_room_input.getText().toString().trim();
                    if (TextUtils.isEmpty(msg)) {
                        ToastUtil.showToast("请输入发送内容");
                        et_room_input.setText("");
                        return false;
                    }

                    Bundle bundle = getArguments();
                    if (null == bundle) {
                        ToastUtil.showToast("用用程序出错,请稍后重试");
                        return false;
                    }
                    LawyerListBean.LawyerItemBean lawyerItem = bundle.getParcelable(KEY_LAWYER_ITEM_BEAN);
                    if (null == lawyerItem) {
                        ToastUtil.showToast("用用程序出错,请稍后重试");
                        return false;
                    }
                    String uid = SpUtil.getString(AppConstant.UID, "");
                    String lsid = lawyerItem.getConversationId();
                    int type = 1;
                    mPresenter.syncMessageToService(uid, lsid, msg, type);

                    return true;
                }
                return false;
            }
        });
    }


    //===Desc:=================================================================

    @Override
    public void onSyncMessageToSuccess(String sendMessage) {
        //同步数据成功,使用环信发送消息
        Bundle bundle = getArguments();
        if (null == bundle) {
            ToastUtil.showToast("用用程序出错,请稍后重试");
            return;
        }
        LawyerListBean.LawyerItemBean lawyerItem = bundle.getParcelable(KEY_LAWYER_ITEM_BEAN);
        if (null == lawyerItem) {
            ToastUtil.showToast("用用程序出错,请稍后重试");
            return;
        }
        String lsid = lawyerItem.getConversationId();

        EmChatManager.getInstance().sendTxt(sendMessage, lsid, new OnMessageStatusCallback() {
            @Override
            public void onSuccess() {
                mHandler.post(() -> {
                    //清空输入框  添加到列表显示
                    et_room_input.setText("");
                    adapter.addData(sendMessage);
                    rv_room_content.smoothScrollToPosition(adapter.getData().size() - 1);
                });
            }

            @Override
            public void onError(int code, String msg) {

            }
        });


    }

    @Override
    public void onSyncMessageToFail(String errorMsg) {
    }

    @Override
    public void onHistoricalMessageSuccess() {

    }

    @Override
    public void onHistoricalMessageFail() {

    }
}
