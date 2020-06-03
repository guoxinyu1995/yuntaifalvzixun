package com.ytfu.yuntaifawu.ui.home.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2020/4/28
*
*  @Des 首页评价接口
*
*/
public class HomePingJIaBean {

    /**
     * status : 1
     * list : [{"yhaddtime":"2020-04-27 17:57:56","lvshi":"ls02","name":"满满意","nickname":"yt9621","name_lvshi":"丁彩燕律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:53:05","lvshi":"ls05","name":"好评，很满意！！谢谢！好评，很满意！！谢谢！！","nickname":"yt1074","name_lvshi":"方鹏鹏律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:44:58","lvshi":"ls06","name":"很满很满意","nickname":"yt8429","name_lvshi":"窦锦波律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:42:55","lvshi":"ls08","name":"非常非常非常非常好","nickname":"yt1664","name_lvshi":"耿晨旭律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:41:36","lvshi":"ls07","name":"回答很仔回答很仔细","nickname":"yt1427","name_lvshi":"曹海洲律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:34:25","lvshi":"ls10","name":"非常满意，回答的很仔细，问什么就回答什么！回复也很快。嘻非常满意，回答的很仔细，问什么就回答什么！回复也很快。嘻嘻","nickname":"yt6184","name_lvshi":"冯西科律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:23:26","lvshi":"ls08","name":"很满很满意","nickname":"yt7804","name_lvshi":"耿晨旭律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:21:24","lvshi":"ls10","name":"热情周热情周到","nickname":"yt7202","name_lvshi":"冯西科律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:14:02","lvshi":"ls04","name":"律师解说太到位了。人很好","nickname":"yt3482","name_lvshi":"郭稳波律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:13:40","lvshi":"ls06","name":"超级好评，解答详细律师人超级好","nickname":"yt3484","name_lvshi":"窦锦波律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 17:06:43","lvshi":"ls04","name":"非常细心耐心，真的太感谢非常细心耐心，真的太感谢了","nickname":"yt7816","name_lvshi":"郭稳波律师","url":"https://yuntaifawu.com/public/images/avatar.png"},{"yhaddtime":"2020-04-27 16:47:32","lvshi":"ls11","name":"很耐很耐心","nickname":"yt3867","name_lvshi":"高连宇律师","url":"https://yuntaifawu.com/public/images/avatar.png"}]
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private List<ListBean> list;

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
         * yhaddtime : 2020-04-27 17:57:56
         * lvshi : ls02
         * name : 满满意
         * nickname : yt9621
         * name_lvshi : 丁彩燕律师
         * url : https://yuntaifawu.com/public/images/avatar.png
         */

        private String yhaddtime;
        private String lvshi;
        private String name;
        private String nickname;
        private String name_lvshi;
        private String url;

        public String getYhaddtime() {
            return yhaddtime;
        }

        public void setYhaddtime(String yhaddtime) {
            this.yhaddtime = yhaddtime;
        }

        public String getLvshi() {
            return lvshi;
        }

        public void setLvshi(String lvshi) {
            this.lvshi = lvshi;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getName_lvshi() {
            return name_lvshi;
        }

        public void setName_lvshi(String name_lvshi) {
            this.name_lvshi = name_lvshi;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
