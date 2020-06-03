package com.ytfu.yuntaifawu.ui.qisuzhuang.adaper;

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
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityDaiLiCi;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityZhengJuQingDan;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQszYlXq;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityZjqdAddList;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszXqFlBean;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QszXqFenleiAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<QszXqFlBean.ListBean> mList;
    private final int TYPE_SHUJU = 0;
    private final int TYPE_FOOTER = 1;

    public QszXqFenleiAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<QszXqFlBean.ListBean> mList) {
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
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_qsz_xq_classify, parent, false);
            return new QszXqFlViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) != TYPE_FOOTER) {
            FooderViewHolder viewHolder = (FooderViewHolder) holder;
        } else {
            QszXqFlViewHolder viewHolder = (QszXqFlViewHolder) holder;
            QszXqFlBean.ListBean listBean = mList.get(position);
            GlideManager.loadImageByUrl(mContext, listBean.getImg(), viewHolder.ivZixun);
            viewHolder.tvFlTitle.setText(listBean.getName());
            int buy = listBean.getBuy();
            int type = listBean.getType();
            if(type == 1){
                viewHolder.tvAudioZixunNum.setText("已有"+listBean.getCount()+"人代写");
            }else{
                viewHolder.tvAudioZixunNum.setText("已有"+listBean.getCount()+"人购买");
            }
            viewHolder.rlGengduo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (type) {
                        case 1:
                            //进入起诉状详情
                            Intent intent = new Intent(mContext, ActivityQszYlXq.class);
                            intent.putExtra("id", listBean.getId());
                            intent.putExtra("", listBean.getName());
                            mContext.startActivity(intent);
                            break;
                        case 2:
                            //进入代理词
                            if (buy == 0) {
                                //未购买
                                Intent intent1 = new Intent(mContext, ActivityDaiLiCi.class);
                                intent1.putExtra("id", listBean.getId());
                                mContext.startActivity(intent1);
                            } else if (buy == 1) {
                                //购买
                                Intent intent2 = new Intent(mContext, ActivityDaiLiCi.class);
                                intent2.putExtra("id", listBean.getId());
                                mContext.startActivity(intent2);
                            }
                            break;
                        case 3:
                            //进入证据清单
                            if (buy == 0) {
                                //未购买
                                Intent intent3 = new Intent(mContext, ActivityZhengJuQingDan.class);
                                intent3.putExtra("id", listBean.getId());
                                intent3.putExtra("name", listBean.getName());
                                mContext.startActivity(intent3);
                            } else if (buy == 1) {
                                //购买
                                Intent intent4 = new Intent(mContext, ActivityZjqdAddList.class);
                                intent4.putExtra("id", listBean.getId());
                                intent4.putExtra("name", listBean.getName());
                                mContext.startActivity(intent4);
                            }
                            break;
                        default:
                            break;
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
    public class QszXqFlViewHolder extends RecyclerView.ViewHolder {
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
        public QszXqFlViewHolder(@NonNull View itemView) {
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
