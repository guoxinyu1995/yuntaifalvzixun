package com.ytfu.yuntaifawu.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI wxapi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        wxapi = WXAPIFactory.createWXAPI(this, AppConstant.WX_APP_ID);
//        wxapi.handleIntent(getIntent(), this);
        App.wxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        App.wxapi.handleIntent(intent, this);
//        wxapi.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Logger.e("code = " + baseResp.errCode + "\nstr = " + baseResp.errStr);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            if (baseResp.errCode == 0) {
//                // 支付成功
//                EventBus.getDefault().post(new MessageEvent(AppConstant.WX_PAY_SUCCESS));
//            } else if(baseResp.errCode == -1){
//                ToastUtil.showCenterToast("支付失败！");
//            }
            switch (baseResp.errCode) {
                case 0:
                    // 支付成功
                    EventBus.getDefault().post(new MessageEvent(AppConstant.WX_PAY_SUCCESS));
                    finish();
                    break;
                case -1:
                    ToastUtil.showCenterToast("支付失败！");
                    finish();
                    break;
                case -2:
                    ToastUtil.showCenterToast("支付已取消");
                    finish();
                    break;
                default:
                    break;
            }
        }
        finish();
    }
}
