package com.ytfu.yuntaifawu.ui.mseeage.adaper;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.mseeage.activity.HuiDaDetailsActivity;
import com.ytfu.yuntaifawu.ui.mseeage.bean.TaHuiDaBean;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2020/4/20
 * @Des Ta的回答adaper
 */
public class TaHuiDaAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<TaHuiDaBean.DataBean> dataBeans;

    public TaHuiDaAdaper(Context mContext) {
        this.mContext = mContext;
        dataBeans = new ArrayList<>();
    }

    public void setDataBeans(List<TaHuiDaBean.DataBean> dataBeans) {
        this.dataBeans = dataBeans;
        notifyDataSetChanged();
    }
    public void addDataBeans(List<TaHuiDaBean.DataBean> dataBeans) {
        if(!dataBeans.isEmpty()){
            this.dataBeans.addAll(dataBeans);
            notifyDataSetChanged();
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tahuida_recycle_item, parent, false);
        return new TaHuiDaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaHuiDaBean.DataBean dataBean = dataBeans.get(position);
        TaHuiDaViewHolder viewHolder = (TaHuiDaViewHolder) holder;
        if (!TextUtils.isEmpty(dataBean.getPicurl())) {
            GlideManager.loadImageByUrl(mContext, dataBean.getPicurl(), viewHolder.ivTaIcon);
        }
        viewHolder.tvHuidaName.setText(dataBean.getUname());
        viewHolder.tvHuidaTime.setText(dataBean.getDate());
        viewHolder.tvContent.setText(dataBean.getContent());
        viewHolder.tvLeixing.setText(dataBean.getCid());
        viewHolder.tvNum.setText(dataBean.getSum());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, HuiDaDetailsActivity.class);
                intent.putExtra("lid",dataBean.getLid());
                intent.putExtra("anid",dataBean.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeans.size();
    }

    public class TaHuiDaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_ta_icon)
        ImageView ivTaIcon;
        @BindView(R.id.tv_huida_name)
        TextView tvHuidaName;
        @BindView(R.id.tv_huida_time)
        TextView tvHuidaTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_leixing)
        TextView tvLeixing;
        @BindView(R.id.tv_num)
        TextView tvNum;
        public TaHuiDaViewHolder(@NonNull View itemView) {
            super(itemView);
           ButterKnife.bind(this,itemView);
        }

    }
}
