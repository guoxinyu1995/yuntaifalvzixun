package com.ytfu.yuntaifawu.ui.falvguwen.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/22
*
*  @Des  法律顾问列表bean
*
*/
public class LegalAdviserListBean {

    /**
     * list : [{"id":"39","title":" (新版)解除终止劳动合同证明书"},{"id":"40","title":"催告上班通知书"},{"id":"41","title":"催告通知书"},{"id":"42","title":" 单位单方解除劳动关系通知"},{"id":"43","title":" 解除(终止)劳动合同通知书范本"},{"id":"44","title":"解除劳动关系通知"},{"id":"45","title":" 解除劳动关系通知书"}]
     * status : 200
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private String msg;
    private List<ListBean> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
         * id : 39
         * title :  (新版)解除终止劳动合同证明书
         */

        private String id;
        private String title;
        private int buy;

        public int getBuy() {
            return buy;
        }

        public void setBuy(int buy) {
            this.buy = buy;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
