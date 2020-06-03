package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KtzsTitleListAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<KtzsListBean.ListBeanX> mList;

    public KtzsTitleListAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<KtzsListBean.ListBeanX> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ktzs_title, parent, false);
        return new KtzsTileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        KtzsTileViewHolder viewHolder = (KtzsTileViewHolder) holder;
        KtzsListBean.ListBeanX listBeanX = mList.get(position);
        viewHolder.tvKtzsTitle.setText(listBeanX.getLabel());
        KtzsEntryContentAdaper contentAdaper = new KtzsEntryContentAdaper(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        viewHolder.recycleItem.addItemDecoration(new MyItemDecoration2(0f, 0f));
        viewHolder.recycleItem.setLayoutManager(layoutManager);
        viewHolder.recycleItem.setAdapter(contentAdaper);
        if (listBeanX.getList() != null) {
            contentAdaper.setEntryList(listBeanX.getList());
        }
//        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mContext.startActivity(new Intent(mContext, ActivitySelectIndictment.class));
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class KtzsTileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_ktzs_title)
        TextView tvKtzsTitle;
        @BindView(R.id.recycle_item)
        RecyclerView recycleItem;

        public KtzsTileViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
