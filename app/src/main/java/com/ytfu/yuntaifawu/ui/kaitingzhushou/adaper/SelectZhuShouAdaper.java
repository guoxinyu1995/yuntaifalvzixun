package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SelectZhuShouBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/12/17
 * @Des 选择起诉状的Adaper
 */
public class SelectZhuShouAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<SelectZhuShouBean.ListBean> mList;

    public SelectZhuShouAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<SelectZhuShouBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    /**
     * 某一个条目选中或者取消选中
     *
     * @param position
     */
    public void setItemCheck(int position, boolean isCheck) {
        mList.get(position).setIs_check(isCheck);
        notifyItemChanged(position);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_selectqsz, parent, false);
        return new SelectZhushouViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SelectZhushouViewHolder viewHolder = (SelectZhushouViewHolder) holder;
        SelectZhuShouBean.ListBean listBean = mList.get(position);
        viewHolder.tvAnyouTitle.setText("案由:"+listBean.getLabel());
        viewHolder.tvYuangaoName.setText("原告:"+listBean.getYuangao_name());
        viewHolder.tvBeigaoName.setText("被告:"+listBean.getBeigao_name());
        if (null != onItemClickListener) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!listBean.isIs_check()) {
                        //加入队列
                        viewHolder.ivZjqd.setSelected(true);
                        listBean.setIs_check(true);
                        onItemClickListener.onItemClickListener(position, listBean.isIs_check());
//                        viewHolder.itemView.setBackgroundColor(Color.parseColor("#e13b38"));
//                        viewHolder.tvAnyouTitle.setTextColor(Color.parseColor("#44A2f7"));
//                        viewHolder.tvBeigaoName.setTextColor(Color.parseColor("#44A2f7"));
//                        viewHolder.tvYuangaoName.setTextColor(Color.parseColor("#44A2f7"));
                    } else {
                        //从队列里删除
                        viewHolder.ivZjqd.setSelected(false);
                        listBean.setIs_check(false);
                        onItemClickListener.onItemClickListener(position, listBean.isIs_check());
//                        viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
//                        viewHolder.tvAnyouTitle.setTextColor(Color.parseColor("#303030"));
//                        viewHolder.tvBeigaoName.setTextColor(Color.parseColor("#303030"));
//                        viewHolder.tvYuangaoName.setTextColor(Color.parseColor("#303030"));
                    }
                }
            });

//            ((SelectZhushouViewHolder) holder).ivZjqd.setOnClickListener(v -> {
//                if (!listBean.isIs_check()) {
//                    //加入队列
//                    viewHolder.ivZjqd.setSelected(true);
//                    listBean.setIs_check(true);
//                    onItemClickListener.onItemClickListener(position, listBean.isIs_check());
////                    viewHolder.tvAnyouTitle.setTextColor(Color.parseColor("#44A2f7"));
////                    viewHolder.tvBeigaoName.setTextColor(Color.parseColor("#44A2f7"));
////                    viewHolder.tvYuangaoName.setTextColor(Color.parseColor("#44A2f7"));
//                } else {
//                    //从队列里删除
//                    viewHolder.ivZjqd.setSelected(false);
//                    listBean.setIs_check(false);
//                    onItemClickListener.onItemClickListener(position, listBean.isIs_check());
////                    viewHolder.tvAnyouTitle.setTextColor(Color.parseColor("#303030"));
////                    viewHolder.tvBeigaoName.setTextColor(Color.parseColor("#303030"));
////                    viewHolder.tvYuangaoName.setTextColor(Color.parseColor("#303030"));
//                }
//            });
        }
        if (listBean.isIs_check()) {
            viewHolder.ivZjqd.setSelected(true);
        } else {
            viewHolder.ivZjqd.setSelected(false);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class SelectZhushouViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_anyou_title)
        TextView tvAnyouTitle;
        @BindView(R.id.iv_zjqd)
        ImageView ivZjqd;
        @BindView(R.id.tv_yuangao_name)
        TextView tvYuangaoName;
        @BindView(R.id.tv_beigao_name)
        TextView tvBeigaoName;
        @BindView(R.id.rl_qsz)
        RelativeLayout rlQsz;
        public SelectZhushouViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public SelectQszOnItemClickListener onItemClickListener;
    public void setItemClickListener(SelectQszOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //接口回调设置点击事件
    public interface SelectQszOnItemClickListener {
        //点击事件
        void onItemClickListener(int position, boolean check);
    }
}
