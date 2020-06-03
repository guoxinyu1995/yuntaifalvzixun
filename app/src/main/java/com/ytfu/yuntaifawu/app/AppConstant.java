package com.ytfu.yuntaifawu.app;

/**
 * @作者 gxy
 * @创建时间 2019/11/9
 * @描述 App系统常量
 */
public class AppConstant {
    /**
     * 日志开关
     */
//    public static boolean DEBUG = false;
    public static boolean DEBUG = true;
    /**
     * 友盟
     */
    public static final String UMENG_KEY = "5de76139570df34a4d000b8f";
    /**
     * bugly
     */
    public static final String BUGLY_ID = "6bbc6d272c";
    /**
     * 微信APP_ID
     * wxfc703d618ab01bad
     */
    public static final String WX_APP_ID = "wxfc703d618ab01bad";
    /**
     * Logger tag
     */
    public static final String LOGGER_TAG = "logger_ytfw";
    /**
     * 成功
     */
    public static final int CODE_SUCCESS = 1;
    public static final int STATUS_SUCCESS = 200;
    /**
     * 网络连接状态
     */
    public static String NETWORK = "network";
    /**
     * 分页 每页数量
     */
    public static final int MAXPERPAGE = 6;
    public static final int EVENT_BUS_DATAFRAGMENT = 101;
    public static final int EVENT_BUS_AUDIODTEAILS = 102;
    public static final int EVENT_BUS_AUDIODTEAILS_ID = 103;
    public static final int EVENT_BUS_ZJQD_TYPE_ID = 104;
    public static final int EVENT_BUS_WDSC_keyword = 105;
    /**
     * 未登录提示去登录
     */
    public static final String NOTLOGIN = "notLogin";
    /**
     * uid
     */
    public static final String UID = "uid";
    /**
     * 身份 1是用户 2是律师
     */
    public static final String SHENFEN = "shenfen";
    /**
     * 获取验证码倒计时 ms
     */
    public static final int GETCODEDURATION = 60000;
    /**
     * 新用户登录
     */
    public static final int LOGIN_NEW_USER = 2;
    /**
     * 老用户登录
     */
    public static final int LOGIN_OLD_USER = 1;
    /**
     * 发送失败
     */
    public static final int LOGIN_REEOR = 3;
    /**
     * 按两次返回键退出间隔时间
     */
    public static final int EXIT_INTERVAL_TIME = 2000;
    /**
     * 支付宝状态
     */
    public static final int SDK_PAY_FLAG = 1;

    public static final String TONGYI = "tongyi";

    /**
     * 退出登录
     */
    public static final int LOGOUT = 113;
    /**
     * 微信登录
     */
    public static final int WX_LOGIN = 111;
    /**
     * 微信支付
     */
    public static final int WX_PAY_SUCCESS = 112;

    //  疫情期间进入咨询首页提示
    public static final String YQ_SHENGMING = "shengming";

    //添加开庭助手
    public static final int KT_SUCCESS = 114;



    public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
    public static final String GROUP_USERNAME = "item_groups";
    public static final String CHAT_ROOM = "item_chatroom";
    public static final String ACCOUNT_REMOVED = "account_removed";
    public static final String ACCOUNT_CONFLICT = "conflict";
    public static final String ACCOUNT_FORBIDDEN = "user_forbidden";
    public static final String ACCOUNT_KICKED_BY_CHANGE_PASSWORD = "kicked_by_change_password";
    public static final String ACCOUNT_KICKED_BY_OTHER_DEVICE = "kicked_by_another_device";
    public static final String CHAT_ROBOT = "item_robots";
    public static final String ACTION_GROUP_CHANAGED = "action_group_changed";
    public static final String ACTION_CONTACT_CHANAGED = "action_contact_changed";

    public static final String EXTRA_CONFERENCE_ID = "confId";
    public static final String EXTRA_CONFERENCE_PASS = "password";
    public static final String EXTRA_CONFERENCE_INVITER = "inviter";
    public static final String EXTRA_CONFERENCE_IS_CREATOR = "is_creator";
    public static final String EXTRA_CONFERENCE_GROUP_ID = "group_id";

    public static final String MSG_ATTR_CONF_ID = "conferenceId";
    public static final String MSG_ATTR_CONF_PASS = EXTRA_CONFERENCE_PASS;
    public static final String MSG_ATTR_EXTENSION = "msg_extension";

    public static final String EM_CONFERENCE_OP = "em_conference_op";
    public static final String EM_CONFERENCE_ID = "em_conference_id";
    public static final String EM_CONFERENCE_PASSWORD = "em_conference_password";
    public static final String EM_CONFERENCE_TYPE = "em_conference_type";
    public static final String EM_MEMBER_NAME = "em_member_name";

    public static final String OP_INVITE = "invite";
    public static final String OP_REQUEST_TOBE_SPEAKER = "request_tobe_speaker";
    public static final String OP_REQUEST_TOBE_AUDIENCE = "request_tobe_audience";

    public static final int CHATTYPE_SINGLE = 1;
    public static final int CHATTYPE_GROUP = 2;
    public static final int CHATTYPE_CHATROOM = 3;
    /**
     * 用户环信昵称
     */
    public static final String USERHXNAME = "userhxname";
    public static final String EXTRA_USER_ID = "userId";
    /**
     * 用户昵称
     */
    public static final String USERNAME = "username";

    /**
     * 用户头像
     */
    public static final String USER_PORTRAIT = "portrait";
    public static final String EXTRA_CHAT_TYPE = "chatType";

    //律师认证
    public static final int LVSHI_RENZHENG_NAME = 301;
    public static final int LVSUO_RENZHENG_NAME = 302;
    public static final int LVSHI_RENZHENG_GRJJ = 303;
    public static final int LVSHI_RENZHENG_ADDRESS = 304;
    public static final int LVSHI_RENZHENG_SAHNGCHANGLINGYU = 305;

    //咨询列表进入会话列表页
    public static final int FRAGMENT_TYPE = 1;
}
