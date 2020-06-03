package com.hyphenate.easeui.widget.chatrow;

import android.widget.BaseAdapter;

import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.widget.presenter.EaseChatRowPresenter;

/**
 * 自定义chat row提供者
 *
 */
public interface EaseCustomChatRowProvider {
    /**
     * 获取多少种类型的自定义chatrow<br/>
     * 注意，每一种chatrow至少有两种type：发送type和接收type设置昵称，头像，和气泡
     * @return
     */
    int getCustomChatRowTypeCount(); 
    
    /**
     * 获取chatrow type，必须大于0, 从1开始有序排列
     * @return
     */
    int getCustomChatRowType(EMMessage message);
    
    /**
     * 根据给定message返回chat row
     * @return
     */
    EaseChatRowPresenter getCustomChatRow(EMMessage message, int position, BaseAdapter adapter);
    
}
