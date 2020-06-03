package com.ytfu.yuntaifawu.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DialorUtil {
    private static DialorUtil dialorUtil = null;

    public static DialorUtil getInstance() {
        if (dialorUtil == null) {
            synchronized (PayHelper.class) {
                if (dialorUtil == null) {
                    dialorUtil = new DialorUtil();
                }
            }
        }
        return dialorUtil;
    }

    private DialorUtil() {

    }

    /**
     * 带有确认取消按钮的自定义dialog
     *
     * @param context 上下文
     * @param message 显示的信息
     */
    public static void showConfirmDialog(Context context, String message, OnButtonClickListener onButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        View view = View.inflate(context, R.layout.view_alert_dialog_confirm, null);
        TextView tvMsg = view.findViewById(R.id.tv_message_dialog);
        TextView tvCancel = view.findViewById(R.id.tv_cancel_dialog);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm_dialog);

        tvMsg.setText(message);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onNegativeButtonClick(alertDialog);
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onPositiveButtonClick(alertDialog);
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

    //只有确定按钮
    public static void showImAlertDialog(Context context, String message, OnButtonClickListener onButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View view = View.inflate(context, R.layout.chat_im_dialor, null);
        TextView tv_neirong = view.findViewById(R.id.tv_conton);
        tv_neirong.setText(message);
        TextView tv_queding = view.findViewById(R.id.tv_queding);
        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onNegativeButtonClick(alertDialog);
            }
        });
        //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
//        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);
    }

    //文本长按
    public static void txteviewSelectDialog(Context context, String message, OnCopyClickListener onCopyClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View view = View.inflate(context, R.layout.text_longclien_dialor, null);
        TextView tv_neirong = view.findViewById(R.id.tv_conton);
        tv_neirong.setText(message);
        TextView tv_queding = view.findViewById(R.id.tv_queding);
        tv_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCopyClickListener.onCopyClick(alertDialog,tv_neirong);
            }
        });
        //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
//        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);
    }

    public static void showExChangeWxDialog(Context context, String message, OnButtonClickListener onButtonClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        View view = View.inflate(context, R.layout.exchange_wx_dialor, null);
        TextView tv_neirong = view.findViewById(R.id.tv_conton);
        tv_neirong.setText(message);
        TextView tv_confirm_dialog = view.findViewById(R.id.tv_confirm_dialog);
        TextView tv_cancel_dialog = view.findViewById(R.id.tv_cancel_dialog);
        tv_confirm_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onPositiveButtonClick(alertDialog);

            }
        });
        tv_cancel_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClickListener.onNegativeButtonClick(alertDialog);
            }
        });
        //只用下面这一行弹出对话框时需要点击输入框才能弹出软键盘
//        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);
    }

    private static OnButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    /**
     * 按钮点击回调接口
     */
    public interface OnButtonClickListener {
        /**
         * 确定按钮点击回调方法
         *
         * @param dialog 当前 AlertDialog，传入它是为了在调用的地方对 dialog 做操作，比如 dismiss()
         *               也可以在该工具类中直接  dismiss() 掉，就不用将 AlertDialog 对象传出去了
         */
        void onPositiveButtonClick(AlertDialog dialog);

        /**
         * 取消按钮点击回调方法
         *
         * @param dialog 当前AlertDialog
         */
        void onNegativeButtonClick(AlertDialog dialog);
    }


    //定义复制接口
    private static OnCopyClickListener onCopyClickListener;

    public void setonCopyClickListener(OnCopyClickListener onCopyClickListener) {
        this.onCopyClickListener = onCopyClickListener;
    }
    public interface OnCopyClickListener{
        void onCopyClick(AlertDialog dialog,TextView textView);
    }
}
