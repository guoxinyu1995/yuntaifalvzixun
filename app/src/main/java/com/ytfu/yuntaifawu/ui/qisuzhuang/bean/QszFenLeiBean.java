package com.ytfu.yuntaifawu.ui.qisuzhuang.bean;

import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/20
*
*  @Des  起诉状分类bean
*
*/
public class QszFenLeiBean {


    /**
     * list : [{"label":"婚姻家事","id":"1","icon":"icon-ziyuan","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/hunyinjiashi@2x.png"},{"label":"劳动人事","id":"2","icon":"icon-laodongrenshi","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/icon-ldrs@2x.png"},{"label":"工伤鉴定","id":"3","icon":"icon-gongshangjiandingbiaozhun","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/gongshangjiandingbiaozhun@2x.png"},{"label":"民间借贷","id":"4","icon":"icon-minjianjiedai","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/minjianjiedai-3@2x.png"},{"label":"合同纠纷","id":"5","icon":"icon-2","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/hetongjiufen@2x.png"},{"label":"交通事故","id":"6","icon":"icon-traffic-accident","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/jiaotongshigu-2@2x.png"},{"label":"建设事故","id":"7","icon":"icon-jianshegongcheng","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/jianshegongcheng@2x.png"},{"label":"医疗纠纷","id":"8","icon":"icon-iconyiliaojiufen","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/iconyiliaojiufen@2x.png"},{"label":"知识产权","id":"9","icon":"icon-zhishichanquan-copy","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/zhishichanquan@2x.png"},{"label":"房地产纠纷","id":"10","icon":"icon-fangdichan","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/fangdichan@2x.png"},{"label":"强制执行申请书","id":"11","icon":"icon-qianshutikuanshenqingshu","img":"http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/qiangzhizhixingshenqingshu@2x.png"}]
     * status : 1
     * referer :
     * state : success
     */

    private int status;
    private String referer;
    private String state;
    private int rand;
    private List<ListBean> list;

    public int getRand() {
        return rand;
    }

    public void setRand(int rand) {
        this.rand = rand;
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
         * label : 婚姻家事
         * id : 1
         * icon : icon-ziyuan
         * img : http://yuntaifawu.com//themes/simplebootx_mobile/Public/falv/icon/hunyinjiashi@2x.png
         */

        private String label;
        private String id;
        private String icon;
        private String img;
        private String href;
        private String imgx;

        public String getImgx() {
            return imgx;
        }

        public void setImgx(String imgx) {
            this.imgx = imgx;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
