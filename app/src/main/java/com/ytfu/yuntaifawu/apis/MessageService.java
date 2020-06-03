package com.ytfu.yuntaifawu.apis;

import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.chatroom.bean.AddMessageResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatResponse;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HistoryRecordResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 消息相关的业务层代码实现
 */
public interface MessageService {


    /**
     * 同步消息到服务器
     * uid	是	string	用户id
     * lsid	是	string	律师id
     * content	是	string	发送内容（用urlencode加密）
     * type	是	string	1 用户发送 2 律师发送
     */
    @POST("Chat/addMsgs")
    Observable<AddMessageResponseBean> syncMessageToService(@Query("uid") String uid,
                                                            @Query("lsid") String lsid,
                                                            @Query(value = "content", encoded = true) String content,
                                                            @Query("type") String type
    );

    @POST("Chat/addMsgs")
    @FormUrlEncoded
    Observable<AddMessageResponseBean> lawyerSyncMessageToService(
            @Field("consultid") String consultId,
            @Field("uid") String uid,
            @Field("lsid") String lsid,
            @Field(value = "content", encoded = true) String content,
            @Field("type") String type
    );

    /**
     * 获取聊天消息历史记录
     */
    @GET("Newzixun/get_chat_news")
    Observable<HistoryRecordResponseBean> getHistoryRecord(@Query("uid") String uid,
                                                           @Query("lsid") String lsid,
                                                           @Query("source") int source
    );
    /**
     * 获取聊天消息历史记录
     */
    @GET("Newzixun/get_chat_news")
    Observable<HistoryChatResponse> userGetHistoryRecord(@Query("uid") String uid,
                                                           @Query("lsid") String lsid,
                                                           @Query("source") int source
    );

    /**
     * 获取聊天消息历史记录
     */
    @GET("Newzixun/get_chat_news")
    Observable<HistoryChatResponse> lawyerGetHistoryRecord(@Query("uid") String uid,
                                                           @Query("lsid") String lsid,
                                                           @Query("source") int source
    );


}
