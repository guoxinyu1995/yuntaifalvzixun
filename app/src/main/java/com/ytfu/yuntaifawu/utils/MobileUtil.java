package com.ytfu.yuntaifawu.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/4
*
*  @Des
*
*/
public class MobileUtil {

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186、166
         * 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3/5/7/8，其他位置的可以为0-9
         */
        // "[1]"代表第1位为数字1，"[3456789]"代表第二位可以为3、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        String telRegex = "[1][3456789]\\d{9}";

        return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
    }
    /**
     * 验证密码
     * */
    public static boolean isPwdNo(String pwd){
            String pwdRegex = "/^[\\w]{6,12}$/";
        return !TextUtils.isEmpty(pwd) && pwd.matches(pwdRegex);
    }
    /**
     * 验证码
     * */
    public static boolean isCode(String code){
        String codeRegex = "/\\d{6}/";
        return !TextUtils.isEmpty(code) && code.matches(codeRegex);
    }
//    /**
//     * 返回隐藏中间四位的手机号
//     *
//     * @return 185****5270
//     */
//    public static String getSecurityPhone(String phone) {
//        if (TextUtils.isEmpty(phone)) {
//            return "";
//        }
//        // $1、$2、……表示正则表达式里面第一个、第二个、……括号里面的匹配内容
//        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
//    }

//    /**
//     * 去掉手机号内除数字外的所有字符
//     *
//     * @param phoneNum 手机号 例：188 8888 8888
//     * @return 18888888888
//     */
//    public static String formatPhoneNum(String phoneNum) {
//        String regex = "(\\+86)|[^0-9]";
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher = pattern.matcher(phoneNum);
//        return matcher.replaceAll("");
//    }
//
//    /**
//     * 返回固定格式的手机号
//     *
//     * @return 133-3333-3333
//     */
//    public static String getFormatPhone(String phone) {
//        if (TextUtils.isEmpty(phone)) {
//            return "";
//        }
//        if (phone.length() < 11) {
//            return phone;
//        }
//
//        return phone.substring(0, 3) + "-"
//                + phone.substring(3, 7) + "-"
//                + phone.substring(7, 11);
//    }

//    /**
//     * 返回344格式的手机号
//     *
//     * @return 133 3333 3333
//     */
//    public static String getSeparatedPhone(String phone) {
//        if (TextUtils.isEmpty(phone)) {
//            return "";
//        }
//        if (phone.length() < 11) {
//            return phone;
//        }
//
//        return phone.substring(0, 3) + " "
//                + phone.substring(3, 7) + " "
//                + phone.substring(7, 11);
//    }

//    /**
//     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
//     *
//     * @param phoneNum 电话号码
//     */
//    public static void diallPhone(Context context, String phoneNum) {
//        Intent intent = new Intent(Intent.ACTION_DIAL);
//        Uri data = Uri.parse("tel:" + phoneNum);
//        intent.setData(data);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
//    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
//    @SuppressLint("MissingPermission")
//    public static void callPhone(final Context context, String phoneNum) {
//        final Intent intent = new Intent(Intent.ACTION_CALL);
//        Uri data = Uri.parse("tel:" + phoneNum);
//        intent.setData(data);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        // 请求权限
//        AndPermissionUtil.getInstance().requestPermissions(context, () -> {
//            // permission are allowed.
//            context.startActivity(intent);
//        }, Permission.CALL_PHONE);
//    }

    /**
     * 短信分享
     *
     * @param mContext
     * @param smstext  短信分享内容
     * @return
     */
    public static Boolean sendSms(Context mContext, String smstext) {
        Uri smsToUri = Uri.parse("smsto:");
        Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
        mIntent.putExtra("sms_body", smstext);
        mContext.startActivity(mIntent);
        return null;
    }

//    /**
//     * 邮件分享
//     *
//     * @param mContext
//     * @param title    邮件的标题
//     * @param text     邮件的内容
//     * @return
//     */
//    public static void sendMail(Context mContext, String title, String text) {
//        // 调用系统发邮件
//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        // 设置文本格式
//        emailIntent.setType("text/plain");
//        // 设置对方邮件地址
//        emailIntent.putExtra(Intent.EXTRA_EMAIL, "");
//        // 设置标题内容
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, title);
//        // 设置邮件文本内容
//        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
//        mContext.startActivity(Intent.createChooser(emailIntent, "Choose Email Client"));
//    }
}
