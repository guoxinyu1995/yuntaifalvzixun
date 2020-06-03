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

public class LvShiDetailsSuccessAnliAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<LvShiDetailsBean1.DataBean.AnliContentBean> anliContentBeans;

    public LvShiDetailsSuccessAnliAdaper(Context mContext) {
        this.mContext = mContext;
        anliContentBeans = new ArrayList<>();
    }

    public void setAnliContentBeans(List<LvShiDetailsBean1.DataBean.AnliContentBean> anliContentBeans) {
        this.anliContentBeans = anliContentBeans;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.lvshi_details_success_anli_item, parent, false);
        return new SuccessAnLiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LvShiDetailsBean1.DataBean.AnliContentBean anliContentBean = anliContentBeans.get(position);
        SuccessAnLiViewHolder viewHolder = (SuccessAnLiViewHolder) holder;
        if (anliContentBean != null) {
            viewHolder.tvItemLiexing.setText(anliContentBean.getCid());
            viewHolder.tvItemAnli.setText(anliContentBean.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return anliContentBeans.size();
    }


    public class SuccessAnLiViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item_liexing)
        TextView tvItemLiexing;
        @BindView(R.id.tv_item_anli)
        TextView tvItemAnli;

        public SuccessAnLiViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
