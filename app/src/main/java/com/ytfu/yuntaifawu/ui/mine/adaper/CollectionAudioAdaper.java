package com.ytfu.yuntaifawu.ui.mine.adaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityAudioDetails;
import com.ytfu.yuntaifawu.ui.mine.bean.CollectionListBean;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  音频列表适配器
*
*/
public class CollectionAudioAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<CollectionListBean.ListBean> mList;

    public CollectionAudioAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setList(List<CollectionListBean.ListBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void addList(List<CollectionListBean.ListBean> list) {
        if (!list.isEmpty()) {
            mList.addAll(list);
            notifyDataSetChanged();
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycel_view, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DataViewHolder viewHolder = (DataViewHolder) holder;
        CollectionListBean.ListBean listBean = mList.get(position);
//        Glide.with(mContext).load(listBean.getPost_img()).into(viewHolder.image);
        GlideManager.loadImageByUrl(mContext,listBean.getPost_img(),viewHolder.image);
        viewHolder.text_title.setText(listBean.getPost_title());
        viewHolder.text_excerpt.setText(listBean.getPost_excerpt());
        viewHolder.text_price.setText(listBean.getPost_price());
//        viewHolder.text_yuan_price.setText("原价" + "￥" + listBean.getPost_cost());
        viewHolder.text_yuan_price.setVisibility(View.GONE);
        viewHolder.text_count.setText("已有" + listBean.getOrder_count() + "人购买");
        viewHolder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityAudioDetails.class);
                intent.putExtra("id",listBean.getId());
                mContext.startActivity(intent);
                EventBus.getDefault().post(new MessageEvent(AppConstant.EVENT_BUS_AUDIODTEAILS_ID,listBean.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView text_title, text_excerpt, text_price, text_yuan_price, text_count;
        public RelativeLayout relative_layout;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            text_title = itemView.findViewById(R.id.text_title);
            text_excerpt = itemView.findViewById(R.id.text_excerpt);
            text_price = itemView.findViewById(R.id.text_price);
            text_yuan_price = itemView.findViewById(R.id.text_yuan_price);
            text_count = itemView.findViewById(R.id.text_count);
            relative_layout = itemView.findViewById(R.id.relative_layout);
        }
    }
}
