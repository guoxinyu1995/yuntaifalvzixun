package com.ytfu.yuntaifawu.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

//    private IWXAPI iwxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        iwxapi = WXAPIFactory.createWXAPI(this, AppConstant.WX_APP_ID, true);
//        iwxapi.registerApp(AppConstant.WX_APP_ID);
        App.wxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        setIntent(intent);
        App.wxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //登录回调
        int errorcode = baseResp.errCode;
        Logger.e("errorcode", "---------" + errorcode);
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                String code = ((SendAuth.Resp) baseResp).code;
                Logger.d("onResp", code.toString() + "");
                EventBus.getDefault().post(new MessageEvent(AppConstant.WX_LOGIN, code));
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                ToastUtil.showToast("您已拒绝受权");
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                ToastUtil.showToast("您已取消登录");
                finish();
                break;
            default:
                finish();
                break;
        }
        finish();
    }
}
