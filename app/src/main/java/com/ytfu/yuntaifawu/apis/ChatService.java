package com.ytfu.yuntaifawu.apis;

import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioNavBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 律师相关的网络请求
 */
public interface ChatService {
    /**
     * 获取律师聊天列表
     */
    @GET("Newzixun/chat_list")
    Observable<LawyerListBean> getLawyerChatList(@Query("uid") String uid);
}
