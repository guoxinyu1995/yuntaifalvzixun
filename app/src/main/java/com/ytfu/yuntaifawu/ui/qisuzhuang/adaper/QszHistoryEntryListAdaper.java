package com.ytfu.yuntaifawu.ui.qisuzhuang.adaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper.GsjdListAdaper;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQszXqClassify;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszHistoryBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/12/17
 * @Des 起诉状历史Adaper
 */
public class QszHistoryEntryListAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<QszHistoryBean.ListBeanX.ListBean> mList;

    public QszHistoryEntryListAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<QszHistoryBean.ListBeanX.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_qsz_entry_content, parent, false);
        return new QszHistoryEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QszHistoryEntryViewHolder viewHolder = (QszHistoryEntryViewHolder) holder;
        QszHistoryBean.ListBeanX.ListBean listBean = mList.get(position);
        viewHolder.tvQszYuangao.setText("原告: " + listBean.getYuangao_name());
        viewHolder.tvQszBeigao.setText("被告: " + listBean.getBeigao_name());
        viewHolder.llKtzsXiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityQszXqClassify.class);
                intent.putExtra("id",listBean.getId());
                mContext.startActivity(intent);
            }
        });
        viewHolder.qszIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClientListener!=null){
                    itemClientListener.onQszHistoryItemListener(position,listBean.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class QszHistoryEntryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_qsz_yuangao)
        TextView tvQszYuangao;
        @BindView(R.id.qsz_iv_delete)
        ImageView qszIvDelete;
        @BindView(R.id.tv_qsz_beigao)
        TextView tvQszBeigao;
        @BindView(R.id.ll_ktzs_xiangqing)
        LinearLayout llKtzsXiangqing;

        public QszHistoryEntryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //定义接口
    private IQszHistortEntryItemClientListener itemClientListener;
    public void setOnItemClientListener(IQszHistortEntryItemClientListener itemClientListener){
        this.itemClientListener = itemClientListener;
    }
    public interface IQszHistortEntryItemClientListener{
        void onQszHistoryItemListener(int position,String id);
    }
}
