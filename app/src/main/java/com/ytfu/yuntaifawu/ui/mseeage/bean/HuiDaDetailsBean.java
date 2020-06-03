package com.ytfu.yuntaifawu.ui.mseeage.bean;
/**
*
*  @Auther  gxy
*
*  @Date    2020/4/21
*
*  @Des  问答详情bean
*
*/
public class HuiDaDetailsBean {

    /**
     * data : {"id":"2","lid":"ls01","cid":"个人劳动争议","uname":"我是一个小学生","content":"我是一个小学生","sum":"10","date":"2020-04-16 00:00:00"}
     * code : 200
     * status : success
     * referer :
     * state : success
     */

    private DataBean data;
    private int code;
    private String status;
    private String referer;
    private String state;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public static class DataBean {
        /**
         * id : 2
         * lid : ls01
         * cid : 个人劳动争议
         * uname : 我是一个小学生
         * content : 我是一个小学生
         * sum : 10
         * date : 2020-04-16 00:00:00
         */

        private String id;
        private String lid;
        private String cid;
        private String uname;
        private String content;
        private String sum;
        private String date;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLid() {
            return lid;
        }

        public void setLid(String lid) {
            this.lid = lid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
