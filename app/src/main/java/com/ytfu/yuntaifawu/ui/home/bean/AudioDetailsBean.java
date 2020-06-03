package com.ytfu.yuntaifawu.ui.home.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  音频详情bean
*
*/
public class AudioDetailsBean {

    /**
     * status : 1
     * buy : 0
     * shoucang : 0
     * list : {"id":"243","order_count":"0","post_content":"<p style=\"white-space: normal; text-indent: 2em;\">上一讲当中说到了关于经济补偿金的问题，今天继续这块内容，首先是劳动合同终止有没有经济补偿的问题？<span style=\"text-indent: 2em;\">根据劳动合同法第四十六条第五款规定，除用人单位维持或者提高劳动合同约定条件续订劳动合同，劳动者不同意续订的情形外，依照本法第四十四条第一项规定终止固定期限劳动合同的，用人单位需要向劳动者支付经济补偿。<\/span><\/p><p><br/><\/p>","post_title":"劳动合同终止情形（三）","post_excerpt":"劳动合同终止有没有经济补偿？","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191010/5d9eed3fbd5b4.png","post_price":"19","post_cost":"99","post_audio":"https://www.yuntaifawu.com/data/upload/default/20191108/5dc5279eedf78.mp3","post_parent":"2392"}
     * audio_list : [{"post_excerpt":"如果单位要求员工有保密的义务，那么是否需要支付保密工资呢？对于保密工资，咱们国内法律法规以及很多地方政策法规都没有规定。","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191018/5da9884b1a659.png","order_count":"1454","post_price":"39","post_cost":"99","post_title":"要求员工保密,单位要付保密工资吗?","id":"26"},{"post_excerpt":"保密协议核心条款以及关键要点主要有八个方面","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191018/5da9884b1a659.png","order_count":"1210","post_price":"39","post_cost":"99","post_title":"保密协议的核心条款及关键要点有哪些?","id":"30"},{"post_excerpt":"病假和医疗期的区别，有哪些享受条件，长短因素以及申请病假，单位有没有绝对的审批权等","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191018/5da9884b1a659.png","order_count":"1191","post_price":"39","post_cost":"99","post_title":"病假、医疗期的享受条件及风险防范风险","id":"36"},{"post_excerpt":"\u201c称职\u201d\u201c基本称职\u201d、\u201c不称职\u201d使用上的法律风险以及防范方法","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191018/5da9884b1a659.png","order_count":"1386","post_price":"39","post_cost":"99","post_title":"\u201c不称职\u201d等同于不能胜任工作吗?","id":"37"},{"post_excerpt":"对于实行不定时工作制的员工能否主张加班费？很多人认为不需要，那么，实际上是否要支付加班费呢?","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191018/5da9884b1a659.png","order_count":"1588","post_price":"39","post_cost":"99","post_title":"不定时工作制，能否主张加班费?","id":"38"},{"post_excerpt":"对于解除患病劳动者是有法律依据的，但需满足一定的条件且具体操作过程中需要几个注意点。","post_img":"https://www.yuntaifawu.com/data/upload/admin/20191018/5da9884b1a659.png","order_count":"1285","post_price":"39","post_cost":"99","post_title":"能否解除患病员工，如何操作","id":"39"}]
     * contract_list : [{"id":"772","title":"劳动争议仲裁立案材料清单（劳动者申请仲裁）.docx","descript":"暂无描述","date":"2019-09-18 14:48:40","download_count":"1189","price":"59","realprice":"19","img":"https://www.yuntaifawu.com/data/upload/admin/20191021/5dad0de21f142.png"},{"id":"773","title":"劳动争议仲裁立案材料清单（用人单位申请仲裁）.docx","descript":"暂无描述","date":"2019-09-18 14:48:40","download_count":"1679","price":"59","realprice":"29","img":"https://www.yuntaifawu.com/data/upload/admin/20191021/5dad0de21f142.png"},{"id":"4481","title":"公司劳动争议调解委员会组织及工作条例","descript":"暂无描述","date":"2019-09-30 17:04:11","download_count":"1517","price":"59","realprice":"19","img":"https://www.yuntaifawu.com/data/upload/admin/20191021/5dad0a546f63c.png"},{"id":"4495","title":"行政复议答辩状（劳动争议）","descript":"暂无描述","date":"2019-09-30 17:04:11","download_count":"1545","price":"59","realprice":"19","img":"https://www.yuntaifawu.com/data/upload/admin/20191021/5dad0a546f63c.png"},{"id":"4496","title":"和解协议（劳动争议仲裁_诉讼中）","descript":"暂无描述","date":"2019-09-30 17:04:11","download_count":"1166","price":"59","realprice":"19","img":"https://www.yuntaifawu.com/data/upload/admin/20191021/5dad0a546f63c.png"},{"id":"4995","title":"劳动争议案件代理词（格式模板）","descript":"暂无描述","date":"2019-10-09 17:06:47","download_count":"1000","price":"59","realprice":"19","img":"https://www.yuntaifawu.com/data/upload/admin/20191021/5dad0a546f63c.png"}]
     * referer :
     * state : success
     */

    private int status;
    private int buy;
    private int shoucang;
    private ListBean list;
    private String referer;
    private String state;
    private List<AudioListBean> audio_list;
    private List<ContractListBean> contract_list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBuy() {
        return buy;
    }

    public void setBuy(int buy) {
        this.buy = buy;
    }

    public int getShoucang() {
        return shoucang;
    }

    public void setShoucang(int shoucang) {
        this.shoucang = shoucang;
    }

    public ListBean getList() {
        return list;
    }

    public void setList(ListBean list) {
        this.list = list;
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

    public List<AudioListBean> getAudio_list() {
        return audio_list;
    }

    public void setAudio_list(List<AudioListBean> audio_list) {
        this.audio_list = audio_list;
    }

    public List<ContractListBean> getContract_list() {
        return contract_list;
    }

    public void setContract_list(List<ContractListBean> contract_list) {
        this.contract_list = contract_list;
    }

    public static class ListBean {
        /**
         * id : 243
         * order_count : 0
         * post_content : <p style="white-space: normal; text-indent: 2em;">上一讲当中说到了关于经济补偿金的问题，今天继续这块内容，首先是劳动合同终止有没有经济补偿的问题？<span style="text-indent: 2em;">根据劳动合同法第四十六条第五款规定，除用人单位维持或者提高劳动合同约定条件续订劳动合同，劳动者不同意续订的情形外，依照本法第四十四条第一项规定终止固定期限劳动合同的，用人单位需要向劳动者支付经济补偿。</span></p><p><br/></p>
         * post_title : 劳动合同终止情形（三）
         * post_excerpt : 劳动合同终止有没有经济补偿？
         * post_img : https://www.yuntaifawu.com/data/upload/admin/20191010/5d9eed3fbd5b4.png
         * post_price : 19
         * post_cost : 99
         * post_audio : https://www.yuntaifawu.com/data/upload/default/20191108/5dc5279eedf78.mp3
         * post_parent : 2392
         */

        private String id;
        private String order_count;
        private String post_content;
        private String post_title;
        private String post_excerpt;
        private String post_img;
        private String post_price;
        private String post_cost;
        private String post_audio;
        private String post_parent;
        private String post_yuanwen;
        //律师简介
        private String lvshi_name;
        private String lvshi_img;
        private String lvshi_descript;

        String startTime;
        String endTime;
        boolean playStatus;
        long duration;

        public String getLvshi_descript() {
            return lvshi_descript;
        }

        public void setLvshi_descript(String lvshi_descript) {
            this.lvshi_descript = lvshi_descript;
        }

        public String getLvshi_name() {
            return lvshi_name;
        }

        public void setLvshi_name(String lvshi_name) {
            this.lvshi_name = lvshi_name;
        }

        public String getLvshi_img() {
            return lvshi_img;
        }

        public void setLvshi_img(String lvshi_img) {
            this.lvshi_img = lvshi_img;
        }


        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public boolean isPlayStatus() {
            return playStatus;
        }

        public void setPlayStatus(boolean playStatus) {
            this.playStatus = playStatus;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        public String getPost_yuanwen() {
            return post_yuanwen;
        }

        public void setPost_yuanwen(String post_yuanwen) {
            this.post_yuanwen = post_yuanwen;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }

        public String getPost_content() {
            return post_content;
        }

        public void setPost_content(String post_content) {
            this.post_content = post_content;
        }

        public String getPost_title() {
            return post_title;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public String getPost_excerpt() {
            return post_excerpt;
        }

        public void setPost_excerpt(String post_excerpt) {
            this.post_excerpt = post_excerpt;
        }

        public String getPost_img() {
            return post_img;
        }

        public void setPost_img(String post_img) {
            this.post_img = post_img;
        }

        public String getPost_price() {
            return post_price;
        }

        public void setPost_price(String post_price) {
            this.post_price = post_price;
        }

        public String getPost_cost() {
            return post_cost;
        }

        public void setPost_cost(String post_cost) {
            this.post_cost = post_cost;
        }

        public String getPost_audio() {
            return post_audio;
        }

        public void setPost_audio(String post_audio) {
            this.post_audio = post_audio;
        }

        public String getPost_parent() {
            return post_parent;
        }

        public void setPost_parent(String post_parent) {
            this.post_parent = post_parent;
        }
    }

    public static class AudioListBean {
        /**
         * post_excerpt : 如果单位要求员工有保密的义务，那么是否需要支付保密工资呢？对于保密工资，咱们国内法律法规以及很多地方政策法规都没有规定。
         * post_img : https://www.yuntaifawu.com/data/upload/admin/20191018/5da9884b1a659.png
         * order_count : 1454
         * post_price : 39
         * post_cost : 99
         * post_title : 要求员工保密,单位要付保密工资吗?
         * id : 26
         */

        private String post_excerpt;
        private String post_img;
        private String order_count;
        private String post_price;
        private String post_cost;
        private String post_title;
        private String id;

        public String getPost_excerpt() {
            return post_excerpt;
        }

        public void setPost_excerpt(String post_excerpt) {
            this.post_excerpt = post_excerpt;
        }

        public String getPost_img() {
            return post_img;
        }

        public void setPost_img(String post_img) {
            this.post_img = post_img;
        }

        public String getOrder_count() {
            return order_count;
        }

        public void setOrder_count(String order_count) {
            this.order_count = order_count;
        }

        public String getPost_price() {
            return post_price;
        }

        public void setPost_price(String post_price) {
            this.post_price = post_price;
        }

        public String getPost_cost() {
            return post_cost;
        }

        public void setPost_cost(String post_cost) {
            this.post_cost = post_cost;
        }

        public String getPost_title() {
            return post_title;
        }

        public void setPost_title(String post_title) {
            this.post_title = post_title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class ContractListBean {
        /**
         * id : 772
         * title : 劳动争议仲裁立案材料清单（劳动者申请仲裁）.docx
         * descript : 暂无描述
         * date : 2019-09-18 14:48:40
         * download_count : 1189
         * price : 59
         * realprice : 19
         * img : https://www.yuntaifawu.com/data/upload/admin/20191021/5dad0de21f142.png
         */

        private String id;
        private String title;
        private String descript;
        private String date;
        private String download_count;
        private String price;
        private String realprice;
        private String img;

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

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDownload_count() {
            return download_count;
        }

        public void setDownload_count(String download_count) {
            this.download_count = download_count;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRealprice() {
            return realprice;
        }

        public void setRealprice(String realprice) {
            this.realprice = realprice;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
