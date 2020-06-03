package com.ytfu.yuntaifawu.ui.home.adaper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityAudioDetails;
import com.ytfu.yuntaifawu.ui.home.bean.AudioDetailsBean;
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
*  @Des  同类音频列表适配器
*
*/
public class AudioSimilarAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<AudioDetailsBean.AudioListBean> mList;

    public AudioSimilarAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setList(List<AudioDetailsBean.AudioListBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    public void addList(List<AudioDetailsBean.AudioListBean> list) {
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
        AudioDetailsBean.AudioListBean audioListBean = mList.get(position);
        Glide.with(mContext).load(audioListBean.getPost_img()).into(viewHolder.image);
        viewHolder.text_title.setText(audioListBean .getPost_title());
        viewHolder.text_excerpt.setText(audioListBean.getPost_excerpt());
        viewHolder.text_price.setText(audioListBean.getPost_price());
        viewHolder.text_yuan_price.setText("原价" + "¥" + audioListBean.getPost_cost());
        viewHolder.text_yuan_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.text_count.setText("已有" + audioListBean.getOrder_count() + "人咨询");
        viewHolder.relative_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityAudioDetails.class);
                intent.putExtra("id",audioListBean.getId());
                mContext.startActivity(intent);
                EventBus.getDefault().post(new MessageEvent(AppConstant.EVENT_BUS_AUDIODTEAILS_ID,audioListBean.getId()));
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
