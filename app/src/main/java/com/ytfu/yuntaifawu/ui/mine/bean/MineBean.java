package com.ytfu.yuntaifawu.ui.mine.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/17
*
*  @Des 我的首页
*
*/
public class MineBean {

    /**
     * find : {"user_nicename":"17084548998","avatar":"https://yuntaifawu.com/data/upload/avatar/2019-12-25//15772413901265.jpg","qsz_count":"9","audio_count":"128","contract_count":"65"}
     * status : 200
     * referer :
     * state : success
     */

    private FindBean find;
    private int status;
    private String referer;
    private String state;

    public FindBean getFind() {
        return find;
    }

    public void setFind(FindBean find) {
        this.find = find;
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

    public static class FindBean {
        /**
         * user_nicename : 17084548998
         * avatar : https://yuntaifawu.com/data/upload/avatar/2019-12-25//15772413901265.jpg
         * qsz_count : 9
         * audio_count : 128
         * contract_count : 65
         */

        private String user_login;
        private String avatar;
        private String qsz_count;
        private String audio_count;
        private String contract_count;
        private String zixun;
        private String shenfen;

        public String getShenfen() {
            return shenfen;
        }

        public void setShenfen(String shenfen) {
            this.shenfen = shenfen;
        }

        public String getZixun() {
            return zixun;
        }

        public void setZixun(String zixun) {
            this.zixun = zixun;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_nicename(String user_nicename) {
            this.user_login = user_nicename;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getQsz_count() {
            return qsz_count;
        }

        public void setQsz_count(String qsz_count) {
            this.qsz_count = qsz_count;
        }

        public String getAudio_count() {
            return audio_count;
        }

        public void setAudio_count(String audio_count) {
            this.audio_count = audio_count;
        }

        public String getContract_count() {
            return contract_count;
        }

        public void setContract_count(String contract_count) {
            this.contract_count = contract_count;
        }
    }
}
