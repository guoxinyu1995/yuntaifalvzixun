package com.ytfu.yuntaifawu.helper;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.utils.ToastUtil;


/**
 * @author fans
 * @date 2018/7/31
 * @description
 */
public class ResultCodeCheck {
    /**
     * 返回码检测
     */
    public static Boolean checkCode(int code, String message) {
//        String message = "";
//        switch (code) {
//            case AppConstant.CODE_SUCCESS:
//                result = true;
//                break;
//            case AppConstant.CODE_ERROR_INTERIOR:
//                message = "内部错误";
//                break;
//            case AppConstant.CODE_ERROR_UNKNOWN:
//                message = "未知错误";
//                break;
//            case AppConstant.CODE_ERROR_PARAM:
//                message = "参数错误";
//                break;
//            case AppConstant.CODE_ERROR_NO_LOGIN:
//                message = "请登录";
//                break;
//            case AppConstant.CODE_ERROR_04:
//                message = "请先获取验证码";
//                break;
//            case AppConstant.CODE_ERROR_05:
//                message = "无效的验证码";
//                break;
//            case AppConstant.CODE_ERROR_06:
//                message = "手机号已被注册";
//                break;
//            case AppConstant.CODE_ERROR_07:
//                message = "手机号未注册";
//                break;
//            case AppConstant.CODE_ERROR_08:
//                message = "请勿重复发送验证码";
//                break;
//            case AppConstant.CODE_ERROR_09:
//                message = "您发送验证码过于频繁,请稍后再试";
//                break;
//            case AppConstant.CODE_ERROR_10:
//                message = "今日验证码请求次数超限，请明天再试";
//                break;
//            case AppConstant.CODE_PASSWORD_ERROR:
//                message = "密码错误";
//                break;
//            default:
//                break;
//        }
        if (code == AppConstant.CODE_SUCCESS) {
            return true;
        }

        if (code < 100000) {
            ToastUtil.showToast(message);
        } else {
            Logger.e("code : " + code + "\nmessage : " + message);
        }

        return false;
    }

    /**
     * 返回码检测（验证登录过期）
     */
//    public static Boolean checkCode(Context context, int code, String message) {
//        if (code == AppConstant.CODE_SUCCESS) {
//            return true;
//        }
//
//        if (code == AppConstant.CODE_ERROR_NO_LOGIN) {
//            // 添加一个判断，防止有些页面重复执行‘请重新登录’操作，出现多个登录页
//            if (App.getInstance().getLoginFlag()) {
//                // 退出登录
//                LoginHelper.getInstance().logout(null);
//                ToastUtil.showLong("您的登录信息已过期，请重新登录");
//
//                Intent intent = new Intent(context, LoginCodeActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(AppConstant.NOTLOGIN, true);
//                context.startActivity(intent);
//            }
//        } else if (code < 100000) {
//            ToastUtil.showToast(message);
//        } else {
//            Logger.e("code : " + code + "\nmessage : " + message);
//        }
//
//        return false;
//    }
}
