package com.ytfu.yuntaifawu.ui.mseeage.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class LvShiDetailsTaHuiDaAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<LvShiDetailsBean1.DataBean.AnswserBean> answserBeanList;

    public LvShiDetailsTaHuiDaAdaper(Context mContext) {
        this.mContext = mContext;
        answserBeanList = new ArrayList<>();
    }

    public void setAnswserBeanList(List<LvShiDetailsBean1.DataBean.AnswserBean> answserBeanList) {
        this.answserBeanList = answserBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.lvshi_details_tahuida_item, parent, false);
        return new HuiDaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LvShiDetailsBean1.DataBean.AnswserBean answserBean = answserBeanList.get(position);
        HuiDaViewHolder viewHolder = (HuiDaViewHolder) holder;
        if(answserBean!=null){
            viewHolder.tvHuidaCont.setText(answserBean.getContent());
            viewHolder.tvLiexing.setText(answserBean.getCid());
            viewHolder.tvHuidaTime.setText("发布时间:" + answserBean.getDate());
        }

    }

    @Override
    public int getItemCount() {
        return answserBeanList.size();
    }

    public class HuiDaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_huida_cont)
        TextView tvHuidaCont;
        @BindView(R.id.tv_liexing)
        TextView tvLiexing;
        @BindView(R.id.tv_huida_time)
        TextView tvHuidaTime;
        public HuiDaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
