package com.ytfu.yuntaifawu.ui.mseeage.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiDetailsBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiDetailsBean1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LvShiDetailsPingJiaAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<LvShiDetailsBean1.DataBean.PinglunBean> pinglunBeanList;

    public LvShiDetailsPingJiaAdaper(Context mContext) {
        this.mContext = mContext;
        pinglunBeanList = new ArrayList<>();
    }
public void setPinglunBeanList(List<LvShiDetailsBean1.DataBean.PinglunBean> pinglunBeanList){
        this.pinglunBeanList = pinglunBeanList;
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.lvshi_details_user_pingjia_item, parent, false);
        return new PingJiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LvShiDetailsBean1.DataBean.PinglunBean pinglunBean = pinglunBeanList.get(position);
        PingJiaViewHolder viewHolder = (PingJiaViewHolder) holder;
        if(pinglunBean!=null){
            viewHolder.tvPingjiaCont.setText(pinglunBean.getName());
            viewHolder.tvPingjiaTime.setText(pinglunBean.getYhaddtime());
            viewHolder.tvPingjiaName.setText(pinglunBean.getNickname());
//            viewHolder.ivPingjia.setBackgroundResource(R.drawable.logo);
        }

    }

    @Override
    public int getItemCount() {
        return pinglunBeanList.size();
    }

    public class PingJiaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_pingjia)
        ImageView ivPingjia;
        @BindView(R.id.tv_pingjia_name)
        TextView tvPingjiaName;
        @BindView(R.id.tv_pingjia_time)
        TextView tvPingjiaTime;
        @BindView(R.id.tv_pingjia_cont)
        TextView tvPingjiaCont;
        public PingJiaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
