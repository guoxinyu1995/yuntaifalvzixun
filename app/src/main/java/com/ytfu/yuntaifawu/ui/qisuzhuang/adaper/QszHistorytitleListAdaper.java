package com.ytfu.yuntaifawu.ui.qisuzhuang.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszHistoryBean;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/12/17
 * @Des 起诉状历史Adaper
 */
public class QszHistorytitleListAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<QszHistoryBean.ListBeanX> mList;

    public QszHistorytitleListAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<QszHistoryBean.ListBeanX> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_qsz_history_title, parent, false);
        return new QszHistoryTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QszHistoryTitleViewHolder viewHolder = (QszHistoryTitleViewHolder) holder;
        QszHistoryBean.ListBeanX listBeanX = mList.get(position);
        viewHolder.tvQszTitle.setText(listBeanX.getLabel());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        viewHolder.recycleItem.addItemDecoration(new MyItemDecoration2(0f, 0f));
        viewHolder.recycleItem.setLayoutManager(layoutManager);
        QszHistoryEntryListAdaper entryListAdaper = new QszHistoryEntryListAdaper(mContext);
        viewHolder.recycleItem.setAdapter(entryListAdaper);
        viewHolder.recycleItem.setHasFixedSize(true);
        viewHolder.recycleItem.setNestedScrollingEnabled(false);
        if (listBeanX.getList() != null) {
            entryListAdaper.setmList(listBeanX.getList());
        }
        entryListAdaper.setOnItemClientListener(new QszHistoryEntryListAdaper.IQszHistortEntryItemClientListener() {
            @Override
            public void onQszHistoryItemListener(int position, String id) {
                if(itemClickListener!=null){
                    itemClickListener.onQszTitleItemClickListener(position,id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class QszHistoryTitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_qsz_title)
        TextView tvQszTitle;
        @BindView(R.id.recycle_item)
        RecyclerView recycleItem;

        public QszHistoryTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //定义接口
    private IQszTitleItemClickListener itemClickListener;

    public void setItemClickListener(IQszTitleItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface IQszTitleItemClickListener {
        void onQszTitleItemClickListener(int position, String id);
    }
}
