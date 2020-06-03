package com.ytfu.yuntaifawu.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;

public class PayDialorUtil {
    private static PayDialorUtil dialorUtil = null;

    public static PayDialorUtil getInstance() {
        if (dialorUtil == null) {
            synchronized (PayHelper.class) {
                if (dialorUtil == null) {
                    dialorUtil = new PayDialorUtil();
                }
            }
        }
        return dialorUtil;
    }

    private PayDialorUtil() {

    }

    /**
     * 带有确认取消按钮的自定义dialog
     *
     * @param context 上下文
     *
     */
    public static void showPayDialog(Context context, OnButtonClickListener onButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        View view = View.inflate(context, R.layout.pay_view_alert_dialor, null);
        ImageView iv_guanbi = view.findViewById(R.id.iv_guanbi);
        LinearLayout pay_wx = view.findViewById(R.id.pay_wx);
        LinearLayout pay_zfb = view.findViewById(R.id.pay_zfb);

        pay_zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onPayZhiFuBaoButtonClick(alertDialog);
            }
        });
        pay_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onPayWeiXinButtonClick(alertDialog);
            }
        });
        iv_guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onGuanbiButtonClick(alertDialog);
            }
        });
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);

    }

    /*///////
    public static void showPayDialog1(Context context, OnButtonClickListener onButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        View view = View.inflate(context, R.layout.pay_view_alert_dialor1, null);
        ImageView iv_guanbi = view.findViewById(R.id.iv_guanbi);
        LinearLayout ll_pay_weichat = view.findViewById(R.id.ll_pay_weichat);
        LinearLayout ll_pay_zhifubao = view.findViewById(R.id.ll_pay_zhifubao);

        pay_zfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onPayZhiFuBaoButtonClick(alertDialog);
            }
        });
        pay_wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onPayWeiXinButtonClick(alertDialog);
            }
        });
        iv_guanbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onGuanbiButtonClick(alertDialog);
            }
        });
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);

    }
*/



    private static OnButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    /**
     * 按钮点击回调接口
     */
    public interface OnButtonClickListener {
        /**
         * 微信支付按钮点击回调方法
         *
         * @param dialog 当前 AlertDialog，传入它是为了在调用的地方对 dialog 做操作，比如 dismiss()
         *               也可以在该工具类中直接  dismiss() 掉，就不用将 AlertDialog 对象传出去了
         */
        void onPayWeiXinButtonClick(AlertDialog dialog);
        /**
         * 支付宝按钮点击回调方法
         *
         * @param dialog 当前AlertDialog
         */
        void onPayZhiFuBaoButtonClick(AlertDialog dialog);
        /**
         * 关闭按钮
         * */
        void onGuanbiButtonClick(AlertDialog dialog);
    }
}
