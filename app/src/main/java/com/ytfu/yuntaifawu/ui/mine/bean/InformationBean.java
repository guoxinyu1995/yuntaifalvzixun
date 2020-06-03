package com.ytfu.yuntaifawu.ui.mine.bean;

public class InformationBean {

    /**
     * mobile_status : 1
     * pwd_status : 1
     * status : 1
     * user : {"id":"1055","user_login":"丢丢丢2","user_email":"1667854327@qq.com","sex":"0","birthday":"1995-12-07","signature":"斗地主丢丢丢","avatar":"https://yuntaifawu.com/data/upload/avatar/2019-12-25//15772413901265.jpg","mobile":"17084548998"}
     * referer :
     * state : success
     */

    private int mobile_status;
    private int pwd_status;
    private int status;
    private UserBean user;
    private String referer;
    private String state;

    public int getMobile_status() {
        return mobile_status;
    }

    public void setMobile_status(int mobile_status) {
        this.mobile_status = mobile_status;
    }

    public int getPwd_status() {
        return pwd_status;
    }

    public void setPwd_status(int pwd_status) {
        this.pwd_status = pwd_status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
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

    public static class UserBean {
        /**
         * id : 1055
         * user_login : 丢丢丢2
         * user_email : 1667854327@qq.com
         * sex : 0
         * birthday : 1995-12-07
         * signature : 斗地主丢丢丢
         * avatar : https://yuntaifawu.com/data/upload/avatar/2019-12-25//15772413901265.jpg
         * mobile : 17084548998
         */

        private String id;
        private String user_login;
        private String user_email;
        private String sex;
        private String birthday;
        private String signature;
        private String avatar;
        private String mobile;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
