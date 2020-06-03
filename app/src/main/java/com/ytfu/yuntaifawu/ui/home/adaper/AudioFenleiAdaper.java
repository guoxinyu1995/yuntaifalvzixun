package com.ytfu.yuntaifawu.ui.home.adaper;

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
import com.ytfu.yuntaifawu.ui.home.activity.AudioListActivity;
import com.ytfu.yuntaifawu.ui.home.bean.AudioNavBean;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AudioFenleiAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<AudioNavBean.ListBean> mList;
    private final int TYPE_SHUJU = 0;
    private final int TYPE_FOOTER = 1;

    public AudioFenleiAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<AudioNavBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position != mList.size()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_SHUJU;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType != TYPE_FOOTER) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_fooder_view, parent, false);
            return new FooderViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_audio_fenlie, parent, false);
            return new AudisNavViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) != TYPE_FOOTER) {
            FooderViewHolder viewHolder = (FooderViewHolder) holder;
        } else {
            AudioNavBean.ListBean listBean = mList.get(position);
            AudisNavViewHolder viewHolder = (AudisNavViewHolder) holder;
            GlideManager.loadImageByUrl(mContext, listBean.getNav_img(), viewHolder.ivZixun);
            viewHolder.tvFlTitle.setText(listBean.getLabel());
            viewHolder.tvAudioZixunNum.setText("已有"+listBean.getCount()+"人咨询");
            viewHolder.rlGengduo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AudioListActivity.class);
                    intent.putExtra("id", listBean.getId());
                    intent.putExtra("label", listBean.getLabel());
                    intent.putExtra("navimg", listBean.getNav_img());
                    mContext.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    //列表
    public class AudisNavViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_zixun)
        ImageView ivZixun;
        @BindView(R.id.tv_fl_title)
        TextView tvFlTitle;
        @BindView(R.id.tv_come_in)
        TextView tvComeIn;
        @BindView(R.id.rl_gengduo)
        RelativeLayout rlGengduo;
        @BindView(R.id.tv_audio_zixun_num)
        TextView tvAudioZixunNum;
        public AudisNavViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //底部fooder
    public class FooderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_shou)
        ImageView ivShou;

        public FooderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
