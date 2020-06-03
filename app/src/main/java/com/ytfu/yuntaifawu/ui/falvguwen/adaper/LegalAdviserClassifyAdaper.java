package com.ytfu.yuntaifawu.ui.falvguwen.adaper;

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
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviserLizhiRuzhiList;
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviserZaiZhiList;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserClassifyBean;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/22
 * @Des 法律顾问一级分类
 */
public class LegalAdviserClassifyAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<LegalAdviserClassifyBean.ListBean> mList;
    private final int TYPE_SHUJU = 0;
    private final int TYPE_FOOTER = 1;
    private final String RL = "0";
    private final String ZZ = "1";

    public LegalAdviserClassifyAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<LegalAdviserClassifyBean.ListBean> mList) {
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
            return new FlGwNavViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) != TYPE_FOOTER) {
            FooderViewHolder viewHolder = (FooderViewHolder) holder;
        } else {
            LegalAdviserClassifyBean.ListBean listBean = mList.get(position);
            FlGwNavViewHolder viewHolder = (FlGwNavViewHolder) holder;
            GlideManager.loadImageByUrl(mContext, listBean.getImg(), viewHolder.ivZixun);
            viewHolder.tvFlTitle.setText(listBean.getLabel());
            viewHolder.tvAudioZixunNum.setText("已有"+listBean.getCount()+"人购买");
            viewHolder.rlGengduo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listBean.getType().equals(RL)) {
                        Intent intent = new Intent(mContext, ActivityLegalAdviserLizhiRuzhiList.class);
                        intent.putExtra("id", listBean.getId());
                        intent.putExtra("label", listBean.getLabel());
                        mContext.startActivity(intent);
                    } else if (listBean.getType().equals(ZZ)) {
                        Intent intent = new Intent(mContext, ActivityLegalAdviserZaiZhiList.class);
                        intent.putExtra("id", listBean.getId());
                        intent.putExtra("label", listBean.getLabel());
                        mContext.startActivity(intent);
                    }

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    //列表
    public class FlGwNavViewHolder extends RecyclerView.ViewHolder {
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
        public FlGwNavViewHolder(@NonNull View itemView) {
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
