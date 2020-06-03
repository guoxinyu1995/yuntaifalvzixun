package com.ytfu.yuntaifawu.ui.lawyer.wallet.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

@Keep
public class WalletResponseBean {

    /**
     * code : 200
     * msg : 查询成功
     * data : {"BindingStatus":1,"TrueName":"赵秦瑞","Account":"15911097723","Balance":"0"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {

        /**
         * BindingStatus : 1
         * TrueName : Lee
         * Account : lb291700351@live.cn
         * Balance : 0
         * Notice : 1.近3日内赚取的金额不可提现
         * 2.提现到账时间预计5-7个工作日
         * 3.提现时自动扣除50%平台手续费
         */

        private int BindingStatus;
        private String TrueName;
        private String Account;
        private String Balance;
        private String Notice;

        public int getBindingStatus() {
            return BindingStatus;
        }

        public void setBindingStatus(int BindingStatus) {
            this.BindingStatus = BindingStatus;
        }

        public String getTrueName() {
            return TrueName;
        }

        public void setTrueName(String TrueName) {
            this.TrueName = TrueName;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public String getBalance() {
            return Balance;
        }

        public void setBalance(String Balance) {
            this.Balance = Balance;
        }

        public String getNotice() {
            return Notice;
        }

        public void setNotice(String Notice) {
            this.Notice = Notice;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.BindingStatus);
            dest.writeString(this.TrueName);
            dest.writeString(this.Account);
            dest.writeString(this.Balance);
            dest.writeString(this.Notice);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.BindingStatus = in.readInt();
            this.TrueName = in.readString();
            this.Account = in.readString();
            this.Balance = in.readString();
            this.Notice = in.readString();
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
