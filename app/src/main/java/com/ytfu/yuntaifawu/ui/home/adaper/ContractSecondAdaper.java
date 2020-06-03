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
import com.ytfu.yuntaifawu.ui.lvshihetong.activity.ActivityContractDataLiist;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractListSecondBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/18
 * @Des 合同列表二级
 */
public class ContractSecondAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<ContractListSecondBean.ListBean> mList;

    public ContractSecondAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<ContractListSecondBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contract_recycle_second, parent, false);
        return new SecondViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SecondViewHolder viewHolder = (SecondViewHolder) holder;
        ContractListSecondBean.ListBean listBean = mList.get(position);
        viewHolder.tvItem.setText(listBean.getLabel());
        viewHolder.layoutSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityContractDataLiist.class);
                intent.putExtra("id", listBean.getId());
                intent.putExtra("lable", listBean.getLabel());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SecondViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;
        @BindView(R.id.icon_more)
        ImageView iconMore;
        @BindView(R.id.layout_second)
        RelativeLayout layoutSecond;

        public SecondViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
