package com.ytfu.yuntaifawu.ui.mseeage.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * 服务器响应的历史消息的JavaBean封装
 */
public class HistoryRecordResponseBean {


    /**
     * code : 200
     * msg : 查询成功
     * data : [{"messageId":"10141","direction":0,"from":"4286","to":"ls00","timestamp":1589963290,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"你好Hello"},"ext":{}},{"messageId":"10142","direction":0,"from":"4286","to":"ls00","timestamp":1589963510,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"你好Hello"},"ext":{}},{"messageId":"10143","direction":0,"from":"4286","to":"ls00","timestamp":1589964230,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"你好 Hello"},"ext":{}},{"messageId":"10144","direction":0,"from":"4286","to":"ls00","timestamp":1589964236,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"你好 Hello"},"ext":{}},{"messageId":"10145","direction":0,"from":"4286","to":"ls00","timestamp":1589964900,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"123"},"ext":{}},{"messageId":"10146","direction":0,"from":"4286","to":"ls00","timestamp":1589964957,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"99997645"},"ext":{}},{"messageId":"10147","direction":0,"from":"4286","to":"ls00","timestamp":1589964962,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"999976456664"},"ext":{}},{"messageId":"10148","direction":0,"from":"4286","to":"ls00","timestamp":1589964993,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"444"},"ext":{}},{"messageId":"10149","direction":0,"from":"4286","to":"ls00","timestamp":1589965000,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"44456455"},"ext":{}},{"messageId":"10150","direction":0,"from":"4286","to":"ls00","timestamp":1589965139,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"999"},"ext":{}},{"messageId":"10151","direction":0,"from":"4286","to":"ls00","timestamp":1589965146,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"999"},"ext":{}},{"messageId":"10152","direction":0,"from":"4286","to":"ls00","timestamp":1589965290,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"9"},"ext":{}},{"messageId":"10153","direction":0,"from":"4286","to":"ls00","timestamp":1589965298,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"你好Hello"},"ext":{}},{"messageId":"10154","direction":0,"from":"4286","to":"ls00","timestamp":1589965807,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"1"},"ext":{}},{"messageId":"10155","direction":0,"from":"4286","to":"ls00","timestamp":1589965899,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"33"},"ext":{}},{"messageId":"10157","direction":0,"from":"4286","to":"ls00","timestamp":1589969827,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"123"},"ext":{}},{"messageId":"10158","direction":0,"from":"4286","to":"ls00","timestamp":1589969840,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"11"},"ext":{}},{"messageId":"10159","direction":0,"from":"4286","to":"ls00","timestamp":1589969977,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"999"},"ext":{}},{"messageId":"10160","direction":0,"from":"4286","to":"ls00","timestamp":1589971174,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"123456"},"ext":{}},{"messageId":"10161","direction":0,"from":"4286","to":"ls00","timestamp":1589971191,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"你好Hello"},"ext":{}},{"messageId":"10162","direction":0,"from":"4286","to":"ls00","timestamp":1589971228,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"123"},"ext":{}},{"messageId":"10163","direction":0,"from":"4286","to":"ls00","timestamp":1589971296,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"963"},"ext":{}},{"messageId":"10164","direction":0,"from":"4286","to":"ls00","timestamp":1589971409,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"9"},"ext":{}},{"messageId":"10207","direction":0,"from":"4286","to":"ls00","timestamp":1590114839,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"99"},"ext":{}},{"messageId":"10208","direction":0,"from":"4286","to":"ls00","timestamp":1590114907,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"9643"},"ext":{}},{"messageId":"10209","direction":0,"from":"4286","to":"ls00","timestamp":1590115281,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"789"},"ext":{}},{"messageId":"10210","direction":0,"from":"4286","to":"ls00","timestamp":1590116123,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"Hello"},"ext":{}},{"messageId":"10211","direction":0,"from":"4286","to":"ls00","timestamp":1590116248,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"345"},"ext":{}},{"messageId":"10212","direction":0,"from":"4286","to":"ls00","timestamp":1590116410,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"999"},"ext":{}},{"messageId":"10213","direction":0,"from":"4286","to":"ls00","timestamp":1590116410,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"999"},"ext":{}},{"messageId":"10216","direction":0,"from":"4286","to":"ls00","timestamp":1590119096,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"123"},"ext":{}},{"messageId":"10217","direction":0,"from":"4286","to":"ls00","timestamp":1590119096,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"123"},"ext":{}},{"messageId":"10219","direction":0,"from":"4286","to":"ls00","timestamp":1590122715,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"123"},"ext":{}},{"messageId":"10220","direction":0,"from":"4286","to":"ls00","timestamp":1590122715,"chatType":0,"status":2,"isRead":1,"body":{"type":1,"text":"123"},"ext":{}}]
     */

    private String code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable, MultiItemEntity {


        /**
         * messageId : 10141
         * direction : 0
         * from : 4286
         * to : ls00
         * timestamp : 1589963290
         * chatType : 0
         * status : 2
         * isRead : 1
         * body : {"type":1,"text":"你好Hello"}
         * ext : {}
         */

        private String messageId;
        private int direction;
        private String from;
        private String to;
        private long timestamp;
        private int chatType;
        private int status;
        private int isRead;
        private BodyBean body;
        private ExtBean ext;

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getChatType() {
            return chatType;
        }

        public void setChatType(int chatType) {
            this.chatType = chatType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }

        public BodyBean getBody() {
            return body;
        }

        public void setBody(BodyBean body) {
            this.body = body;
        }

        public ExtBean getExt() {
            return ext;
        }

        public void setExt(ExtBean ext) {
            this.ext = ext;
        }

        @Override
        public int getItemType() {
            return getDirection();
        }

        public static class BodyBean implements Parcelable {


            /**
             * type : 1
             * text : 你好Hello
             */

            private int type;
            private String text;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.type);
                dest.writeString(this.text);
            }

            public BodyBean() {
            }

            protected BodyBean(Parcel in) {
                this.type = in.readInt();
                this.text = in.readString();
            }

            public static final Creator<BodyBean> CREATOR = new Creator<BodyBean>() {
                @Override
                public BodyBean createFromParcel(Parcel source) {
                    return new BodyBean(source);
                }

                @Override
                public BodyBean[] newArray(int size) {
                    return new BodyBean[size];
                }
            };
        }

        public static class ExtBean implements Parcelable {

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
            }

            public ExtBean() {
            }

            protected ExtBean(Parcel in) {
            }

            public static final Creator<ExtBean> CREATOR = new Creator<ExtBean>() {
                @Override
                public ExtBean createFromParcel(Parcel source) {
                    return new ExtBean(source);
                }

                @Override
                public ExtBean[] newArray(int size) {
                    return new ExtBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.messageId);
            dest.writeInt(this.direction);
            dest.writeString(this.from);
            dest.writeString(this.to);
            dest.writeLong(this.timestamp);
            dest.writeInt(this.chatType);
            dest.writeInt(this.status);
            dest.writeInt(this.isRead);
            dest.writeParcelable(this.body, flags);
            dest.writeParcelable(this.ext, flags);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.messageId = in.readString();
            this.direction = in.readInt();
            this.from = in.readString();
            this.to = in.readString();
            this.timestamp = in.readLong();
            this.chatType = in.readInt();
            this.status = in.readInt();
            this.isRead = in.readInt();
            this.body = in.readParcelable(BodyBean.class.getClassLoader());
            this.ext = in.readParcelable(ExtBean.class.getClassLoader());
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
