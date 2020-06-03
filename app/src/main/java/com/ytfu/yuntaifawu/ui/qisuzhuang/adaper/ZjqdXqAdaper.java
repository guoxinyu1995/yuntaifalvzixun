package com.ytfu.yuntaifawu.ui.qisuzhuang.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.ZjqdXqBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/12/18
 * @Des 证据清单详情Adaper
 */
public class ZjqdXqAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<ZjqdXqBean.FindBean.ZhengjulistBean> mList;

    public ZjqdXqAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<ZjqdXqBean.FindBean.ZhengjulistBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_zjqd_xq, parent, false);
        return new ZjqdXqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ZjqdXqViewHolder viewHolder = (ZjqdXqViewHolder) holder;
        ZjqdXqBean.FindBean.ZhengjulistBean zhengjulistBean = mList.get(position);
        viewHolder.tvName.setText(zhengjulistBean.getEvidence_name());
        viewHolder.tvEntory.setText(zhengjulistBean.getProve_fact());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class ZjqdXqViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_entory)
        TextView tvEntory;
        public ZjqdXqViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
