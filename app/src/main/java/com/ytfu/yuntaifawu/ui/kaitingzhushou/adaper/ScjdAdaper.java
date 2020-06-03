package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ScjdBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/27
 * @Des 伤残鉴定适配器
 */
public class ScjdAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ScjdBean.ListBean> mList;

    public ScjdAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<ScjdBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_scjd_list, parent, false);
        return new ScjdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ScjdViewHolder viewHolder = (ScjdViewHolder) holder;
        ScjdBean.ListBean listBean = mList.get(position);
        String type = listBean.getType();
        switch (type) {
            case "1":
                viewHolder.tvScjdListText.setText(listBean.getName());
                viewHolder.scjdXqListBz.setVisibility(View.INVISIBLE);
                viewHolder.ivScjdListDownload.setVisibility(View.GONE);
                break;
            case "2":
                break;
            case "3":
                viewHolder.tvScjdListText.setText(listBean.getName());
                viewHolder.scjdXqListBz.setText(listBean.getNeirong());
                viewHolder.ivScjdListDownload.setVisibility(View.VISIBLE);
                viewHolder.rlScjdList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (scjdClickListener != null) {
                            scjdClickListener.onScjdItemClickListener(position,listBean.getUrl());
                        }
                    }
                });
                break;
            case "4":
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ScjdViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_scjd)
        ImageView ivScjd;
        @BindView(R.id.tv_scjd_list_text)
        TextView tvScjdListText;
        @BindView(R.id.iv_scjd_list_download)
        ImageView ivScjdListDownload;
        @BindView(R.id.scjd_xq_list_bz)
        TextView scjdXqListBz;
        @BindView(R.id.rl_scjd_list)
        RelativeLayout rlScjdList;

        public ScjdViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //定义接口
    private IScjdClickListener scjdClickListener;

    public void setScjdItemClickListener(IScjdClickListener scjdClickListener) {
        this.scjdClickListener = scjdClickListener;
    }

    public interface IScjdClickListener {
        void onScjdItemClickListener(int pos,String url);
    }
}
