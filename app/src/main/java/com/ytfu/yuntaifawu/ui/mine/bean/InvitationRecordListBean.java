package com.ytfu.yuntaifawu.ui.mine.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/17
*
*  @Des  邀请记录bean
*
*/
public class InvitationRecordListBean {

    /**
     * list : [{"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/8gEn4a4hs2RjVCWHkZibzjH34FD7NpHvOSichm9iaQMPG5XKjibtZAOPaTzo4bp2hUicRPaUYk0nuxuKlPYvT4TjVOA/132","user_login":"郭先生，"},{"avatar":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLjcvw9bZq16K2LZYm2wQfByjdpANPqDPM8QGKIH9ciaeTicD2MpH36rad3LUcC3HdgLYBgCkWZEibag/132","user_login":"米饭"}]
     * count : 2
     * status : 1
     * referer :
     * state : success
     */

    private String count;
    private int status;
    private String referer;
    private String state;
    private List<ListBean> list;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {

        /**
         * avatar : http://thirdwx.qlogo.cn/mmopen/vi_32/8gEn4a4hs2RjVCWHkZibzjH34FD7NpHvOSichm9iaQMPG5XKjibtZAOPaTzo4bp2hUicRPaUYk0nuxuKlPYvT4TjVOA/132
         * user_login : 郭先生，
         */

        private String avatar;
        private String user_login;

        public ListBean(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }
    }
}
