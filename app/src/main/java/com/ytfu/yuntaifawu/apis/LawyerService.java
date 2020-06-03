package com.ytfu.yuntaifawu.apis;

import com.ytfu.yuntaifawu.ui.chatroom.bean.AddMessageResponseBean;
import com.ytfu.yuntaifawu.ui.chatroom.bean.RoomLawyerCardInfoResponse;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;
import com.ytfu.yuntaifawu.ui.mine.bean.MineBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiCardBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LawyerService {

    @POST("my/get_my_content")
    Observable<MineBean> getPersonalData(@Query("uid") String uid);


    /**
     * 获取个人信息
     */
    @POST("my/information")
    @FormUrlEncoded
    Observable<InformationBean> getInformation(@Field("uid") String uid);

    /**
     * 获取个人信息
     */
    @POST("index/get_lvshi_card")
    @FormUrlEncoded
    Observable<RoomLawyerCardInfoResponse> getLawyerCardInfo(@Field("lid") String uid);


}
