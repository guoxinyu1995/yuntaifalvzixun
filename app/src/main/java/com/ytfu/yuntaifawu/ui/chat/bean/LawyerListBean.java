package com.ytfu.yuntaifawu.ui.chat.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import java.util.List;

@Keep
public class LawyerListBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"type":1,"nickName":"云台服务号","headerImage":"https://yuntaifawu.com/data/upload/admin/20200415/5e96dbc44e398.png","conversationId":"ls00","MessagesCount":0,"text":"","time":""}]
     */

    private String code;
    private String msg;
    private List<LawyerItemBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<LawyerItemBean> getData() {
        return data;
    }

    public void setData(List<LawyerItemBean> data) {
        this.data = data;
    }

    @Keep
    public static class LawyerItemBean implements Parcelable {
        /**
         * type : 1
         * nickName : 云台服务号
         * headerImage : https://yuntaifawu.com/data/upload/admin/20200415/5e96dbc44e398.png
         * conversationId : ls00
         * MessagesCount : 0
         * text :
         * time :
         * <p>
         * ,     :"23123123","time":1590398746},{"type":0,"nickName":"耿晨旭律师","headerImage":"https:\/\/yuntaifawu.com\/data\/upload\/admin\/20200421\/5e9e5f90745cf.png","conversationId":"ls08","MessagesCount":"0","text":"-走走D丢L已进入聊天页面","time":1589854114},{"type":0,"nickName":"高连宇律师","headerImage":"https:\/\/yuntaifawu.com\/data\/upload\/admin\/20200423\/5ea1274433ecb.png","conversationId":"ls11","MessagesCount":"0","text":"-走走D丢L已进入聊天页面","time":1589774485}]}
         */
        private int type;
        private String nickName;
        private String headerImage;
        private String conversationId;
        private int MessagesCount;
        private String text;
        private String time;


        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getHeaderImage() {
            return headerImage;
        }

        public void setHeaderImage(String headerImage) {
            this.headerImage = headerImage;
        }

        public String getConversationId() {
            return conversationId;
        }

        public void setConversationId(String conversationId) {
            this.conversationId = conversationId;
        }

        public int getMessagesCount() {
            return MessagesCount;
        }

        public void setMessagesCount(int MessagesCount) {
            this.MessagesCount = MessagesCount;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.type);
            dest.writeString(this.nickName);
            dest.writeString(this.headerImage);
            dest.writeString(this.conversationId);
            dest.writeInt(this.MessagesCount);
            dest.writeString(this.text);
            dest.writeString(this.time);
        }

        public LawyerItemBean() {
        }

        protected LawyerItemBean(Parcel in) {
            this.type = in.readInt();
            this.nickName = in.readString();
            this.headerImage = in.readString();
            this.conversationId = in.readString();
            this.MessagesCount = in.readInt();
            this.text = in.readString();
            this.time = in.readString();
        }

        public static final Parcelable.Creator<LawyerItemBean> CREATOR = new Parcelable.Creator<LawyerItemBean>() {
            @Override
            public LawyerItemBean createFromParcel(Parcel source) {
                return new LawyerItemBean(source);
            }

            @Override
            public LawyerItemBean[] newArray(int size) {
                return new LawyerItemBean[size];
            }
        };
    }

}
