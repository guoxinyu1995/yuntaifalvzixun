package com.ytfu.yuntaifawu.apis;

import com.ytfu.yuntaifawu.ui.consult.bean.MIanFeiFaBuBean;
import com.ytfu.yuntaifawu.ui.consult.bean.XuanshangFaBuAliBean;
import com.ytfu.yuntaifawu.ui.consult.bean.ZiXunNavBean;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserClassifyBean;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserListBean;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserSecondClassifyBean;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserDetailsBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioDetailsBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListerBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioNavBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioTopImageBean;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractDatalistBean;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractDetailsBean;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractListFirstBean;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractListSecondBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomeBannerBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomeLvShiBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomePingJIaBean;
import com.ytfu.yuntaifawu.ui.home.bean.RecommendListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.AddQszBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.DlcBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.DlcSendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.GsjdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsXqListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsXqTitleBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.QylxtgBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ScjdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SelectZhuShouBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ZjqdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ZjqdSendEmailBean;
import com.ytfu.yuntaifawu.ui.login.bean.CodeBean;
import com.ytfu.yuntaifawu.ui.login.bean.CodeLoginBean;
import com.ytfu.yuntaifawu.ui.login.bean.ForgectDosePwdBean;
import com.ytfu.yuntaifawu.ui.login.bean.ForgetSendBean;
import com.ytfu.yuntaifawu.ui.login.bean.LoginPwdBean;
import com.ytfu.yuntaifawu.ui.login.bean.WxLoginBean;
import com.ytfu.yuntaifawu.ui.lvshiwenti.bean.LvShiZiXunListBean;
import com.ytfu.yuntaifawu.ui.lvshiwenti.bean.WenTiXiangQingBean;
import com.ytfu.yuntaifawu.ui.lvshiwode.bean.LawyerInformationBean;
import com.ytfu.yuntaifawu.ui.lvshiwode.bean.LawyerRzUpdateBean;
import com.ytfu.yuntaifawu.ui.lvshiwode.bean.LawyreManagerBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.CollectionListBean;
import com.ytfu.yuntaifawu.ui.mine.bean.DownLordWordBean;
import com.ytfu.yuntaifawu.ui.mine.bean.GeQianBean;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;
import com.ytfu.yuntaifawu.ui.mine.bean.InvitationRecordListBean;
import com.ytfu.yuntaifawu.ui.mine.bean.LvShiRenZhengCommitBean;
import com.ytfu.yuntaifawu.ui.mine.bean.MineBean;
import com.ytfu.yuntaifawu.ui.mine.bean.MineZiXunBean;
import com.ytfu.yuntaifawu.ui.mine.bean.ModificationBean;
import com.ytfu.yuntaifawu.ui.mine.bean.MyLibraryBean;
import com.ytfu.yuntaifawu.ui.mine.bean.PhoneNumBdBean;
import com.ytfu.yuntaifawu.ui.mine.bean.PictureUploadBean;
import com.ytfu.yuntaifawu.ui.mine.bean.PurchaseRecordBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SendBdCodeBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetBirthdayBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetNameBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetPwdBean;
import com.ytfu.yuntaifawu.ui.mine.bean.ShangChangLingYuBean;
import com.ytfu.yuntaifawu.ui.mine.bean.ShenHeJInduBean;
import com.ytfu.yuntaifawu.ui.mine.bean.UpdataPwdBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.CheckWeixinBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ExChangeWeiXinBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.FirstMessageBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HuanXinLoginBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HuiDaDetailsBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiCardBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiDetailsBean1;
import com.ytfu.yuntaifawu.ui.mseeage.bean.PingJIaDetailsBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.SendMessageBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.SuccessAnLiBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.TaHuiDaBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ToCheckPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateClassBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.WhetherToPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ZiXunSendMessageBean;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.AddZjqdBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszDeleteBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszFenLeiBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszHistoryBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszXqFlBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.ZjqdXqBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.ZjqdXqSendEmailBean;
import com.ytfu.yuntaifawu.ui.register.bean.RegistBean;
import com.ytfu.yuntaifawu.ui.updatapk.UpDateApkBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
    //音频分类
    @POST("audio/get_nav")
    Observable<AudioNavBean> navTitle();

    //    //音频列表
//    @POST("audio/get_list")
//    Observable<ResultEntity<AudioListBean>> navAudioList(@Query("id") String id,
//                                                         @Query("page") int page,
//                                                         @Query("keyword") String keyword);
    //音频列表
    @POST("audio/get_list")
    Observable<AudioListBean> navAudioList(@Query("id") String id,
                                           @Query("page") int page,
                                           @Query("keyword") String keyword);


    //    //音频列表顶部图片
//    @POST("audio/audio_slide")
//    Observable<ResultEntity<AudioTopImageBean>> navTopImage();
    //音频列表顶部图片
    @POST("audio/audio_slide_type")
    Observable<AudioTopImageBean> navTopImage(@Query("id") String id);

    //    //首页顶部图片
//    @POST("index/slide")
//    Observable<ResultEntity<HomeBannerBean>> homeBanner();
    //首页顶部图片
    @POST("index/slide")
    Observable<HomeBannerBean> homeBanner();

    //    //推荐列表
//    @POST("index/tuijian")
//    Observable<ResultEntity<RecommendListBean>> recommendList();
    //推荐列表
    @POST("index/tuijian")
    Observable<RecommendListBean> recommendList();

    //音频详情
    @POST("audio/audio_info")
    @FormUrlEncoded
    Observable<AudioDetailsBean> audioDetails(@FieldMap Map<String, String> map);

    //合同详情
    @POST("contract/contract_info")
    @FormUrlEncoded
    Observable<ContractDetailsBean> contractDetails(@FieldMap Map<String, String> map);

    //获取验证码
    @POST("login/send_code")
    @FormUrlEncoded
    Observable<CodeBean> sendCode(@FieldMap Map<String, String> map);

    //验证码登录
    @POST("login/yzm_login1_2")
    @FormUrlEncoded
    Observable<CodeLoginBean> loginCode(@FieldMap Map<String, String> map);

    //注册
    @POST("do_register1_2")
    @FormUrlEncoded
    Observable<RegistBean> goRegist(@FieldMap Map<String, String> map);

    //账号密码登录
    @POST("login/pwd_login")
    @FormUrlEncoded
    Observable<LoginPwdBean> goLoginPwd(@FieldMap Map<String, String> map);

    //忘记密码发送验证码
    @POST("login/forget_send")
    @FormUrlEncoded
    Observable<ForgetSendBean> goForgectSend(@FieldMap Map<String, String> map);

    //忘记密码
    @POST("login/forget_dosetpwd")
    @FormUrlEncoded
    Observable<ForgectDosePwdBean> goForgectPwd(@FieldMap Map<String, String> map);

    //我的收藏
    @POST("my/store")
    @FormUrlEncoded
    Observable<CollectionListBean> getCollectionAudio(@FieldMap Map<String, String> map);

    //购买记录
    @POST("my/paylist")
    @FormUrlEncoded
    Observable<PurchaseRecordBean> getPurchaseRecord(@FieldMap Map<String, String> map);

    //邀请记录
    @POST("my/invite")
    @FormUrlEncoded
    Observable<InvitationRecordListBean> getInvitationRecord(@FieldMap Map<String, String> map);

    //我的音频合同库
    @POST("my/get_myfile")
    @FormUrlEncoded
    Observable<MyLibraryBean> getMyLibrary(@FieldMap Map<String, String> map);

    //我的首页
    @POST("my/get_my_content")
    @FormUrlEncoded
    Observable<MineBean> getMine(@FieldMap Map<String, String> map);

    //个人信息展示
    @POST("my/information")
    @FormUrlEncoded
    Observable<InformationBean> getInformation(@FieldMap Map<String, String> map);

    //收藏
    @POST("my/store_add")
    @FormUrlEncoded
    Observable<AudioShouCangBean> getShouCang(@FieldMap Map<String, String> map);

    //
    //取消收藏
    @POST("my/store_del")
    @FormUrlEncoded
    Observable<AudioShouCangBean> getShouCangdel(@FieldMap Map<String, String> map);

    //合同列表一级
    @POST("contract/get_nav")
    Observable<ContractListFirstBean> getListFirst();

    //合同列表二级
    @POST("contract/get_nav_son")
    Observable<ContractListSecondBean> getListSecond(@Query("id") String id);

    //合同列表二级
    @POST("contract/get_list")
    @FormUrlEncoded
    Observable<ContractDatalistBean> getDataList(@FieldMap Map<String, String> map);

    //文件下载
//http://yuntaifawu.com/api/my/get_fileflow
    @GET("my/get_fileflow")
    Observable<DownLordWordBean> getWord(@Url String url);

    //上传头像
    @Multipart
    @POST("my/avatar_edit")
    Observable<PictureUploadBean> getUpLoadImage(@Part("uid") RequestBody uid, @Part MultipartBody.Part body);

    //起诉状
    @POST("qisuzhuang/get_qisufenlei_list")
    Observable<QszFenLeiBean> getQsz();

    //起诉状列表
    @POST("qsz/get_list")
    @FormUrlEncoded
    Observable<QszHistoryBean> getQszHistory(@FieldMap Map<String, String> map);

    //起诉状详情分类
    @POST("zhushou/get_qisuzhuang_list")
    @FormUrlEncoded
    Observable<QszXqFlBean> getQszXqFl(@FieldMap Map<String, String> map);

    //修改个人信息
    @POST("my/information_save")
    @FormUrlEncoded
    Observable<ModificationBean> getModification(@FieldMap Map<String, String> map);

    //法律顾问一级分类
    @POST("adviser/get_adviser_classify")
    Observable<LegalAdviserClassifyBean> getFLGW();

    //法律顾问列表
    @POST("adviser/get_adviser_list")
    @FormUrlEncoded
    Observable<LegalAdviserListBean> getFlgwLb(@FieldMap Map<String, String> map);

    //法律顾问二级分类
    @POST("adviser/get_adviser_classify_er")
    Observable<LegalAdviserSecondClassifyBean> getFlgwSecondFl(@Query("id") String id);

    //法律顾问详情
    @POST("adviser/get_adviser_content")
    @FormUrlEncoded
    Observable<LegalAdviserDetailsBean> getFlgwXq(@FieldMap Map<String, String> map);

    //开庭助手列表
    @POST("Zhushou/get_zhushou_list")
    @FormUrlEncoded
    Observable<KtzsListBean> getKtzs(@FieldMap Map<String, String> map);

    //选择起诉状
    @POST("Zhushou/get_select_zhushou")
    @FormUrlEncoded
    Observable<SelectZhuShouBean> getSelectZhuShuo(@FieldMap Map<String, String> map);

    //添加起诉状
    @POST("zhushou/get_select_zhushou_post")
    @FormUrlEncoded
    Observable<AddQszBean> getAddQsz(@FieldMap Map<String, String> map);

    //开庭助手详情title
    @POST("Zhushou/top_name")
    Observable<KtzsXqTitleBean> getKtzsXqTitle(@Query("id") String id);

    //开庭助手详情列表
    @POST("Zhushou/app_list")
    @FormUrlEncoded
    Observable<KtzsXqListBean> getKtzsXqList(@FieldMap Map<String, String> map);

    //证据清单
    @POST("zhushou/get_evidence_list_fenlei")
    @FormUrlEncoded
    Observable<ZjqdBean> getZjqd(@FieldMap Map<String, String> map);

    //生成并发送邮箱
    @POST("zhushou/get_evidence_word")
    @FormUrlEncoded
    Observable<ZjqdSendEmailBean> getSendEmail(@FieldMap Map<String, String> map);

    //企业另需提供
    @POST("zhushou/get_company")
    Observable<QylxtgBean> getQylxtg();

    //伤残鉴定
    @POST("zhushou/get_shancan_list")
    Observable<ScjdBean> getScjd();

    //工伤鉴定
    @POST("zhushou/get_gongsahng_list")
    Observable<GsjdBean> getGsjd();

    //发送邮件
    @POST("bangding/get_send_word")
    @FormUrlEncoded
    Observable<SendEmailBean> getSendEmails(@FieldMap Map<String, String> map);

    //代理词
    @POST("zhushou/get_agent_details")
    @FormUrlEncoded
    Observable<DlcBean> getDlc(@FieldMap Map<String, String> map);

    //代理词发送邮箱
    @POST("zhushou/get_agent_word")
    @FormUrlEncoded
    Observable<DlcSendEmailBean> getDlcSendEmail(@FieldMap Map<String, String> map);

    //支付
    @POST("pay/buy")
    @FormUrlEncoded
    Observable<PayBean> getPay(@FieldMap Map<String, String> map);

    //证据清单列表页
    @POST("zhushou/get_evidence_list_order")
    @FormUrlEncoded
    Observable<AddZjqdBean> getAddZjqd(@FieldMap Map<String, String> map);

    //证据清单详情
    @POST("zhushou/get_evidence_content")
    @FormUrlEncoded
    Observable<ZjqdXqBean> getZjqdXq(@FieldMap Map<String, String> map);

    //证据清单详情购买
    @POST("zhushou/buy_evidence_content")
    @FormUrlEncoded
    Observable<ZjqdXqBean> getZjqdXqBuy(@FieldMap Map<String, String> map);


    //证据清单详情购买
    @POST("zhushou/get_evidence_word_fasong")
    @FormUrlEncoded
    Observable<ZjqdXqSendEmailBean> getZjqdXqSendEmail(@FieldMap Map<String, String> map);

    //删除起诉状
    @POST("qsz/delete")
    @FormUrlEncoded
    Observable<QszDeleteBean> getQszDel(@FieldMap Map<String, String> map);

    //微信登录 https://yuntaifawu.com/api/login/app_wx_login
    @POST("login/app_wx_login")
    @FormUrlEncoded
    Observable<WxLoginBean> getWxLogin(@FieldMap Map<String, String> map);

    //绑定邮箱https://yuntaifawu.com/api/index/get_banding_mail
    @POST("index/get_banding_mail")
    @FormUrlEncoded
    Observable<BdEmailBean> getBdEmail(@FieldMap Map<String, String> map);

    //微信支付 https://yuntaifawu.com/api/wxapppay/buy
    @POST("wxapppay/buy")
    @FormUrlEncoded
    Observable<WxPayBean> getWxPay(@FieldMap HashMap<String, String> map);

    //修改生日https://yuntaifawu.com/api/my/my_birthday_edit
    @POST("my/my_birthday_edit")
    @FormUrlEncoded
    Observable<SetBirthdayBean> getBirthday(@FieldMap HashMap<String, String> map);

    //修改昵称  https://yuntaifawu.com/api/my/my_name_edit
    @POST("my/my_name_edit")
    @FormUrlEncoded
    Observable<SetNameBean> getSetName(@FieldMap HashMap<String, String> map);

    //修改个性签名https://yuntaifawu.com/api/my/my_geqian_edit
    @POST("my/my_geqian_edit")
    @FormUrlEncoded
    Observable<GeQianBean> getGeQian(@FieldMap HashMap<String, String> map);

    //修改邮箱  https://yuntaifawu.com/api/my/my_email_edit
    @POST("my/my_email_edit")
    @FormUrlEncoded
    Observable<SetEmailBean> getEmail(@FieldMap HashMap<String, String> map);

    //修改密码 https://yuntaifawu.com/api/my/edit_pwd
    @POST("my/edit_pwd")
    @FormUrlEncoded
    Observable<UpdataPwdBean> getUpDataPwd(@FieldMap HashMap<String, String> map);

    //设置密码 https://yuntaifawu.com/api/my/set_pwd
    @POST("my/set_pwd")
    @FormUrlEncoded
    Observable<SetPwdBean> getSetPwd(@FieldMap HashMap<String, String> map);

    //绑定手机号发送验证码 https://yuntaifawu.com/api/bangding/app_send
    @POST("bangding/app_send")
    @FormUrlEncoded
    Observable<SendBdCodeBean> getSendCode(@FieldMap HashMap<String, String> map);

    //绑定手机号
    //https://yuntaifawu.com/api/bangding/app_bangding
    @POST("bangding/app_bangding")
    @FormUrlEncoded
    Observable<PhoneNumBdBean> getPhoneNum(@FieldMap HashMap<String, String> map);

    //apk更新  https://yuntaifawu.com/api/index/app_update
    @POST("index/app_update?xt=1")
    Observable<UpDateApkBean> checkUpdate();

    //上传听取音频时长  https://yuntaifawu.com/api/audio/audio_time_save
    @POST("audio/audio_time_save")
    @FormUrlEncoded
    Observable<AudioListerBean> getAudioListener(@FieldMap HashMap<String, String> map);

    //消息列表
    //有服务号的接口
    //http://yuntaifawu.com/api/index/get_lvshi_list
    //无服务好的接口
    //http://yuntaifawu.com/api/index/get_anzhuo_lvshi_list
    @POST("index/get_lvshi_list")
    Observable<ConversationBean> setConversationList();

    //第一次发送消息
    //http://yuntaifawu.com/api/index/get_lvshi_msg
    @POST("index/get_lvshi_msg")
    @FormUrlEncoded
    Observable<FirstMessageBean> setFirstMessage(@FieldMap HashMap<String, String> map);

    //老用户环信登录失败
    //http://yuntaifawu.com/api/index/hmregister
    @POST("index/hmregister")
    @FormUrlEncoded
    Observable<HuanXinLoginBean> setHxLogin(@FieldMap HashMap<String, String> map);

    //发消息
    //http://yuntaifawu.com/api/zixun/get_send_msg
    @POST("zixun/get_send_msg")
    @FormUrlEncoded
    Observable<SendMessageBean> setSendMessage(@FieldMap HashMap<String, String> map);

    //判断当前咨询是否支付
    //https://yuntaifawu.com/api/Huanxin/checkpay
    @GET("Huanxin/checkpay")
    Observable<ToCheckPayBean> setToPay(@Query("user_id") String user_id, @Query("lv_id") String lv_id, @Query("h_id") String h_id);

    //判断微信是否购买
    //https://yuntaifawu.com/api/huanxin/checkweixin
    @POST("huanxin/checkweixin")
    @FormUrlEncoded
    Observable<CheckWeixinBean> setCheckWeiXin(@FieldMap HashMap<String, String> map);

    //律师卡片
    //https://yuntaifawu.com/api/index/get_lvshi_card
    @POST("index/get_lvshi_card")
    @FormUrlEncoded
    Observable<LvShiCardBean> setLvShiCard(@FieldMap HashMap<String, String> map);

    //律师详情
    //http://yuntaifawu.com/api/index/get_lvshi
    @POST("index/get_lvshi")
    @FormUrlEncoded
    Observable<LvShiDetailsBean1> setLvShiDetails(@FieldMap HashMap<String, String> map);

    //点击顶部判断是否支付
    //https://yuntaifawu.com/api/huanxin/checkpay2
    @POST("huanxin/checkpay2")
    @FormUrlEncoded
    Observable<WhetherToPayBean> setWhetherToPay(@FieldMap HashMap<String, String> map);

    //律师回答
    //https://yuntaifawu.com/api/index/get_lvshi_huida
    @POST("index/get_lvshi_huida")
//    @FormUrlEncoded
    Observable<TaHuiDaBean> setTaHuiDa(@Query("lid") String lid, @Query("p") int p);

    @POST("index/get_lvshi_huida")
    @FormUrlEncoded
    Observable<HuiDaDetailsBean> setHuiDaDetails(@FieldMap HashMap<String, String> map);

    //律师获取用户评价列表接口
    //https://yuntaifawu.com/api/index/get_talk_list
    @POST("shop/talk_list")
    @FormUrlEncoded
    Observable<UserEvaluateBean> setUserEvaluate(@FieldMap HashMap<String, String> map);

    //分类
    //https://yuntaifawu.com/api/index/get_talk_class
    @POST("index/get_talk_class")
    @FormUrlEncoded
    Observable<UserEvaluateClassBean> setUserEvaluateClass(@FieldMap HashMap<String, String> map);

    //律师成功案例接口
    //https://yuntaifawu.com/api/index/lvshi_sucess
    @POST("index/lvshi_sucess")
    @FormUrlEncoded
    Observable<SuccessAnLiBean> setSuccessAnli(@FieldMap HashMap<String, String> map);

    //律师评价详情
    //https://yuntaifawu.com/api/index/get_lvshi_talkinfo
    @POST("index/get_lvshi_talkinfo")
    @FormUrlEncoded
    Observable<PingJIaDetailsBean> setEvaluateDetails(@FieldMap HashMap<String, String> map);

    //进入聊天页面发送提醒
    //http://yuntaifawu.com/api/index/get_qingqiu_xiaoxi
    @POST("index/get_qingqiu_xiaoxi")
    @FormUrlEncoded
    Observable<ResponseBody> setFirstNotice(@FieldMap HashMap<String, String> map);

    //交换微信
    //https://yuntaifawu.com/api/wxchange/get_chanage_weixin
    @POST("wxchange/get_change_weixin")
    @FormUrlEncoded
    Observable<ExChangeWeiXinBean> setExChangeWx(@FieldMap HashMap<String, String> map);

    //首页律师接口
    //http://yuntaifawu.com/api/index/get_tui_lvshi
    @POST("index/get_tui_lvshi")
    Observable<HomeLvShiBean> setHomeLvShi();

    //首页评价接口
    //http://yuntaifawu.com/api/index/get_index_pingjia
    @POST("index/get_index_pingjia")
    Observable<HomePingJIaBean> setHomePingJIa(@Query("page") int page);

    //服务号发送消息
    //http://yuntaifawu.com/api/zixun/get_fuwumsg_send
    @POST("zixun/get_fuwumsg_send")
    Observable<ZiXunSendMessageBean> setZiXunMessage();

    //  咨询获取分类接口 https://yuntaifawu.com/api/Consult/get_nav
    @POST("Consult/get_nav")
    Observable<ZiXunNavBean> getZiXunNav();

    //我的咨询   https://yuntaifawu.com/api/Consult/get_conuslt_list
    @POST("Consult/get_conuslt_list")
    @FormUrlEncoded
    Observable<MineZiXunBean> setMineZiXunList(@FieldMap HashMap<String, String> map);

    //发布咨询免费  https://yuntaifawu.com/api/Consult/post_consult_cntent
    @POST("Consult/post_consult_cntent")
    @FormUrlEncoded
    Observable<MIanFeiFaBuBean> setMianFeiFaBu(@FieldMap HashMap<String, String> map);

    //微信支付发布咨询  https://yuntaifawu.com/api/Wxapppay/buy
    @POST("Wxapppay/buy")
    @FormUrlEncoded
    Observable<WxPayBean> setXuanShangWatch(@FieldMap HashMap<String, String> map);

    //支付宝支付发布咨询  https://yuntaifawu.com/api/pay/buy
    @POST("pay/buy")
    @FormUrlEncoded
    Observable<XuanshangFaBuAliBean> setXuanShangAli(@FieldMap HashMap<String, String> map);

    //律师擅长领域 https://yuntaifawu.com/api/lvshi/lingyu
    @POST("lvshi/lingyu")
    Observable<ShangChangLingYuBean> setShangChangLingYu();

    //律师审核进度 https://yuntaifawu.com/api/lvshi/query
    @POST("lvshi/query")
    @FormUrlEncoded
    Observable<ShenHeJInduBean> setShengHeJindu(@FieldMap HashMap<String, String> map);

    //律师认证提交  https://yuntaifawu.com/api/lvshi/shenhe
    @Multipart
    @POST("lvshi/shenhe")
    Observable<LvShiRenZhengCommitBean> setLvShiRenZhengCommit(@PartMap Map<String, RequestBody> map);

    //律师端问题列表   https://yuntaifawu.com/api/Consult/get_conuslt_list
    @GET("Consult/get_conuslt_list")
    Observable<LvShiZiXunListBean> setLvShiZiXunList(@Query("page") int page);

    //律师端咨询问题详情接口 https://yuntaifawu.com/api/consult/get_conuslt_xiangqin
    @POST("consult/get_conuslt_xiangqin ")
    @FormUrlEncoded
    Observable<WenTiXiangQingBean> setWenTiXq(@FieldMap HashMap<String,String> map);

    //律师认证管理（获取名字和身份证号）
    //https://yuntaifawu.com/api/lvshi/lvshi_info
    @POST("lvshi/lvshi_info")
    @FormUrlEncoded
    Observable<LawyreManagerBean> setLawyerManager(@Field("uid") String uid);

    //律师认证信息查询
    //https://yuntaifawu.com/api/lvshi/shenhe_info
    @POST("lvshi/shenhe_info")
    @FormUrlEncoded
    Observable<LawyerInformationBean> setLawyerInfor(@Field("uid") String uid);

    //律师认证信息修改
    //https://yuntaifawu.com/api/lvshi/shenhe_edit
    @Multipart
    @POST("lvshi/shenhe_edit")
    Observable<LawyerRzUpdateBean> setLawyerRzUpDate(@PartMap Map<String, RequestBody> map);
}
