package com.ytfu.yuntaifawu.ui.lvshiwenti.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2020/5/26
 * @Des 问题详情多少人沟通的头像adaper
 */
public class WenTiXqIconAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<String> list;
    private final int TYPE_SHUJU = 0;
    private final int TYPE_FOOTER = 1;

    public WenTiXqIconAdaper(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(list.size()>=9){
            if (position == 9) {
                //增加底部
                return TYPE_FOOTER;
            } else {
                //数据内容
                return TYPE_SHUJU;
            }
        }else{
            //数据内容
            return TYPE_SHUJU;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.wenti_xq_icon_shenglue, parent, false);
            return new FooderIconViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.wenti_xq_icon_item, parent, false);
            return new IconViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) {
            FooderIconViewHolder fooderIconViewHolder = (FooderIconViewHolder) holder;
        } else {
            IconViewHolder viewHolder = (IconViewHolder) holder;
            String imageUrl = list.get(position);
            GlideManager.loadCircleImage(mContext, imageUrl, viewHolder.ivWentiLvshi);
//        GlideManager.loadRadiusImage();
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() >= 9) {
            return 10;
        } else {
            return list.size();
        }
    }

    public class IconViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_wenti_lvshi)
        ImageView ivWentiLvshi;

        public IconViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FooderIconViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_wenti_lvshi)
        ImageView ivWentiLvshi;

        public FooderIconViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}