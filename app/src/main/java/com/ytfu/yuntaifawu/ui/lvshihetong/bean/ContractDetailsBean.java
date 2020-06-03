package com.ytfu.yuntaifawu.ui.lvshihetong.bean;

import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  合同详情bean
*
*/
public class ContractDetailsBean {

    /**
     * status : 1
     * buy : 0
     * shoucang : 0
     * list : {"id":"14793","download_count":"1465","descript":"暂无描述","title":"完成举证说明书（审理船舶碰撞案用） (2)","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg","price":"59","realprice":"29","contract_url":"https://www.yuntaifawu.com/data/upload/hetong/kaitingwenshu/2481kaitingsy/完成举证说明书（审理船舶碰撞案用） (2).docx","s_id_er":"2450"}
     * audio_list : [{"post_excerpt":"结婚登记程序产生的瑕疵有哪些？发生后如何救济？","post_img":"https://www.yuntaifawu.com/data/upload/","order_count":"1543","post_price":"39","post_cost":"99","post_title":"结婚程序登记中产生瑕疵后的救济途径","id":"23"},{"post_excerpt":"彩礼问题已经成为众多男女青年结婚的一大阻碍，因彩礼返还引起的纠纷在诉讼中也有一席之地。","post_img":"https://www.yuntaifawu.com/data/upload/","order_count":"1543","post_price":"39","post_cost":"99","post_title":"彩礼返还问题","id":"24"},{"post_excerpt":"夫妻共同财产制的本质是共同共有制，即夫妻双方对婚姻存续期间的夫妻共同财产享有平等的占有、使用、收益、管理和处分的权利。但是《&lt; 婚姻法 &gt; 的司法解释（三）》第 4 条规定，不离婚的情形下，有2种情况是可以请求分割共同财产的。","post_img":"https://www.yuntaifawu.com/data/upload/","order_count":"1573","post_price":"39","post_cost":"99","post_title":"不离婚情形下能否对夫妻共有财产请求分割？","id":"25"},{"post_excerpt":"如果单位要求员工有保密的义务，那么是否需要支付保密工资呢？对于保密工资，咱们国内法律法规以及很多地方政策法规都没有规定。","post_img":"https://www.yuntaifawu.com/data/upload/","order_count":"1454","post_price":"39","post_cost":"99","post_title":"要求员工保密,单位要付保密工资吗?","id":"26"},{"post_excerpt":"所谓的非婚生子女，也就是俗称的私生子、私生女，是指没有合法婚姻关系的男女所生育的子女，比如同居，比如婚前性行为，比如姘居，乃至包括强奸后所生的子女，这些都是在依法确立婚姻关系前或者婚外行为所产生的的子女。\r\n而非婚生子女认领，则是指通过法定程序使非婚生子女婚生化的法律行为。那么，这种法律行为是怎么操作的呢？","post_img":"https://www.yuntaifawu.com/data/upload/","order_count":"1196","post_price":"39","post_cost":"99","post_title":"非婚生子女认领之诉与亲子鉴定","id":"27"},{"post_excerpt":"夫妻之间房产赠与的问题比较复杂，特别是《司法解释（三）》出台以后，此类冲突案件比较常见，那么夫妻之间的房产赠与能不能撤销呢？","post_img":"https://www.yuntaifawu.com/data/upload/","order_count":"1174","post_price":"39","post_cost":"99","post_title":"夫妻之间的房产赠与能不能撤销","id":"28"}]
     * contract_list : [{"id":"10831","title":"对保全或先予执行裁定复议申请书（最高法院2016版） (2)","descript":"暂无描述","date":"2019-10-30 16:25:32","download_count":"1899","price":"59","realprice":"29","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg"},{"id":"12904","title":"海事拍卖船载货物申请书","descript":"暂无描述","date":"2019-10-30 17:36:31","download_count":"1899","price":"59","realprice":"29","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg"},{"id":"15275","title":"侦查阶段律师会见笔录（带说明）","descript":"暂无描述","date":"2019-11-06 11:15:44","download_count":"1898","price":"59","realprice":"19","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg"},{"id":"13224","title":"解除合同通知（破产程序） (2)","descript":"暂无描述","date":"2019-10-30 17:36:31","download_count":"1897","price":"59","realprice":"29","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg"},{"id":"13626","title":"民事起诉状（挂靠经营合同纠纷）","descript":"暂无描述","date":"2019-11-01 17:35:52","download_count":"1897","price":"59","realprice":"19","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg"},{"id":"13660","title":"民事起诉状（金融借款合同纠纷）","descript":"暂无描述","date":"2019-11-01 17:35:52","download_count":"1897","price":"59","realprice":"29","img":"https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg"}]
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
         * id : 14793
         * download_count : 1465
         * descript : 暂无描述
         * title : 完成举证说明书（审理船舶碰撞案用） (2)
         * img : https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg
         * price : 59
         * realprice : 29
         * contract_url : https://www.yuntaifawu.com/data/upload/hetong/kaitingwenshu/2481kaitingsy/完成举证说明书（审理船舶碰撞案用） (2).docx
         * s_id_er : 2450
         */

        private String id;
        private String download_count;
        private String descript;
        private String title;
        private String img;
        private String price;
        private String realprice;
        private String contract_url;
        private String s_id_er;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDownload_count() {
            return download_count;
        }

        public void setDownload_count(String download_count) {
            this.download_count = download_count;
        }

        public String getDescript() {
            return descript;
        }

        public void setDescript(String descript) {
            this.descript = descript;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
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

        public String getContract_url() {
            return contract_url;
        }

        public void setContract_url(String contract_url) {
            this.contract_url = contract_url;
        }

        public String getS_id_er() {
            return s_id_er;
        }

        public void setS_id_er(String s_id_er) {
            this.s_id_er = s_id_er;
        }
    }

    public static class AudioListBean {
        /**
         * post_excerpt : 结婚登记程序产生的瑕疵有哪些？发生后如何救济？
         * post_img : https://www.yuntaifawu.com/data/upload/
         * order_count : 1543
         * post_price : 39
         * post_cost : 99
         * post_title : 结婚程序登记中产生瑕疵后的救济途径
         * id : 23
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
         * id : 10831
         * title : 对保全或先予执行裁定复议申请书（最高法院2016版） (2)
         * descript : 暂无描述
         * date : 2019-10-30 16:25:32
         * download_count : 1899
         * price : 59
         * realprice : 29
         * img : https://www.yuntaifawu.com/data/upload/default/hetong_default.jpg
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
