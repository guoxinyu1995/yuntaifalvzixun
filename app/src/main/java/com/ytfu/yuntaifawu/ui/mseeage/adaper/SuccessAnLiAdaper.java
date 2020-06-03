package com.ytfu.yuntaifawu.ui.mseeage.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.mseeage.bean.SuccessAnLiBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuccessAnLiAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<SuccessAnLiBean.ShujuBean> shujuBeanList;

    public SuccessAnLiAdaper(Context mContext) {
        this.mContext = mContext;
        shujuBeanList = new ArrayList<>();
    }

    public void setShujuBeanList(List<SuccessAnLiBean.ShujuBean> shujuBeanList) {
        this.shujuBeanList = shujuBeanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_success_anli, parent, false);
        return new SuccessAnLiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SuccessAnLiBean.ShujuBean shujuBean = shujuBeanList.get(position);
        SuccessAnLiViewHolder viewHolder = (SuccessAnLiViewHolder) holder;
        viewHolder.tvContent.setText(shujuBean.getContent());
        viewHolder.tvTime.setText(shujuBean.getAnli_date());
    }

    @Override
    public int getItemCount() {
        return shujuBeanList.size();
    }

    public class SuccessAnLiViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public SuccessAnLiViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
