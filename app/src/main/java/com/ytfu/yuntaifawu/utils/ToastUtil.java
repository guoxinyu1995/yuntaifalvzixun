package com.ytfu.yuntaifawu.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.ytfu.yuntaifawu.app.App;

/**
 * @author fans
 * @date 2018/6/13
 * @description 显示单例的吐司，能连续快速弹的吐司
 */
public class ToastUtil {
    private static Toast toast, toastLong, toastCenter,toastDelete;

    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(App.getContext(), text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }

    public static void showLong(String text) {
        if (toastLong == null) {
            toastLong = Toast.makeText(App.getContext(), text, Toast.LENGTH_LONG);
        }
        toastLong.setText(text);
        toastLong.show();
    }

    /**
     * 屏幕中间toast
     */
    public static void showCenterToast(String text) {
        if (toastCenter == null) {
            toastCenter = Toast.makeText(App.getContext(), text, Toast.LENGTH_LONG);
            toastCenter.setGravity(Gravity.CENTER, 0, 0);
        }
        toastCenter.setText(text);
        toastCenter.show();
    }

    /**
     * 删除成功toast
     */
//    public static void showDeleteToast(ActivityZhiYeJiGou activity, String text){
//        if(toastDelete==null){
//            toastDelete=new Toast(App.getContext());
//        }
//        toastDelete.setDuration(Toast.LENGTH_LONG);
//        toastDelete.setGravity(Gravity.CENTER,0,0);
//        LayoutInflater inflater = activity.getLayoutInflater();
//        View toastLayout = inflater.inflate(R.layout.toast_image_delete, null);
//        TextView txtToast = toastLayout.findViewById(R.id.toast_text);
//        ImageView imageToast=toastLayout.findViewById(R.id.toast_image);
//        toastDelete.setView(toastLayout);
//        txtToast.setText(text);
//        imageToast.setImageResource(R.drawable.start_page_center);
//        toastDelete.show();
//    }
}
