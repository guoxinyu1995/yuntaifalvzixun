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
import com.ytfu.yuntaifawu.ui.mseeage.bean.PingJIaDetailsBean;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EvaluateDetailsAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<PingJIaDetailsBean.TalkdetailBean> beanList;

    public EvaluateDetailsAdaper(Context mContext) {
        this.mContext = mContext;
        beanList = new ArrayList<>();
    }

    public void setBeanList(List<PingJIaDetailsBean.TalkdetailBean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.evaluate_details_item, parent, false);
        return new EvaluateDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PingJIaDetailsBean.TalkdetailBean talkdetailBean = beanList.get(position);
        EvaluateDetailsViewHolder viewHolder = (EvaluateDetailsViewHolder) holder;
        GlideManager.loadImageByUrl(mContext,talkdetailBean.getPicurl(),viewHolder.ivHead);
        viewHolder.tvEvaluateName.setText(talkdetailBean.getNickname());
        viewHolder.tvEvaluateTime.setText(talkdetailBean.getYhaddtime());
        viewHolder.tvEvaluateContent.setText(talkdetailBean.getYhname());
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class EvaluateDetailsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_head)
        ImageView ivHead;
        @BindView(R.id.tv_evaluate_name)
        TextView tvEvaluateName;
        @BindView(R.id.tv_evaluate_time)
        TextView tvEvaluateTime;
        @BindView(R.id.tv_evaluate_content)
        TextView tvEvaluateContent;
        public EvaluateDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
