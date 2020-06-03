package com.ytfu.yuntaifawu.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.ytfu.yuntaifawu.app.AppConstant;


/**
 * @author 007
 * @date 2018/6/29
 * @description 手机网络状态变化检测广播
 */
public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if ((ConnectivityManager.CONNECTIVITY_ACTION).equals(intent.getAction())) {
            // 当前网络类型
            int mNetWorkState = SpUtil.getInteger(AppConstant.NETWORK, NetworkUtil.NETWORK_WIFI);
            // 变化后网络类型
            int netWorkState = NetworkUtil.getNetWorkState(context);
            SpUtil.putInteger(AppConstant.NETWORK, netWorkState);

            if (mNetWorkState != netWorkState) {
                String message = "";
                switch (netWorkState) {
                    case NetworkUtil.NETWORK_WIFI:
                        message = "wifi已连接";
                        break;
                    case NetworkUtil.NETWORK_MOBILE:
                        message = "移动网络已连接";
                        break;
                    case NetworkUtil.NETWORK_NONE:
                        message = "网络连接断开";
                        break;
                    default:
                        break;
                }
                ToastUtil.showToast(message);

                if (null != mNetworkEvent) {
                    mNetworkEvent.onNetworkChange(netWorkState);
                }
            }
        }
    }

    private NetworkEvent mNetworkEvent;

    public void setNetworkEvent(NetworkEvent networkEvent) {
        this.mNetworkEvent = networkEvent;
    }

    /**
     * 自定义接口
     */
    public interface NetworkEvent {
        void onNetworkChange(int netWorkState);
    }
}
