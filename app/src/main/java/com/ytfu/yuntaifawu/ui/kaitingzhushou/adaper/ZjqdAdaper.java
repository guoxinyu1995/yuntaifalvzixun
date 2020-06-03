package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ZjqdBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/26
 * @Des 证据清单适配器
 */
public class ZjqdAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ZjqdBean.ListBean> mList;
    public RecyclerViewOnItemClickListener onItemClickListener;
    public ZjqdAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();

    }


    public void setmList(List<ZjqdBean.ListBean> mList) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_zjqd, parent, false);
        return new ZjqdViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ZjqdBean.ListBean listBean = mList.get(position);
        ZjqdViewHolder viewHolder = (ZjqdViewHolder) holder;
        viewHolder.tvZjqdName.setText(listBean.getEvidence_name());
        if (null != onItemClickListener) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!listBean.isIs_check()) {
                        //加入队列
                        viewHolder.ivZjqd.setSelected(true);
                        listBean.setIs_check(true);
                        onItemClickListener.onItemClickListener(position, listBean.isIs_check());
                    } else {
                        //从队列里删除
                        viewHolder.ivZjqd.setSelected(false);
                        listBean.setIs_check(false);
                        onItemClickListener.onItemClickListener(position, listBean.isIs_check());
                    }
                }
            });

//            ((ZjqdViewHolder) holder).ivZjqd.setOnClickListener(v -> {
//                if (!listBean.isIs_check()) {
//                    //加入队列
//                    viewHolder.ivZjqd.setSelected(true);
//                    listBean.setIs_check(true);
//                    onItemClickListener.onItemClickListener(position, listBean.isIs_check());
//                } else {
//                    //从队列里删除
//                    viewHolder.ivZjqd.setSelected(false);
//                    listBean.setIs_check(false);
//                    onItemClickListener.onItemClickListener(position, listBean.isIs_check());
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

    public class ZjqdViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_zjqd)
        ImageView ivZjqd;
        @BindView(R.id.tv_zjqd_name)
        TextView tvZjqdName;

        public ZjqdViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //接口回调设置点击事件
    public interface RecyclerViewOnItemClickListener {
        //点击事件
        void onItemClickListener(int position, boolean check);
    }

}
