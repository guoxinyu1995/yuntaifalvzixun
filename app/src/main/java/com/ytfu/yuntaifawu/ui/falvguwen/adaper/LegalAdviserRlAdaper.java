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
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviserDetailsBuy;
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviserDetailsDowLoad;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/22
 * @Des 法律顾问入职离职Adaper
 */
public class LegalAdviserRlAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<LegalAdviserListBean.ListBean> mList;

    public LegalAdviserRlAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setList(List<LegalAdviserListBean.ListBean> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_flgw, parent, false);
        return new FlgwLieBiaoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        LegalAdviserListBean.ListBean listBean = mList.get(position);
        FlgwLieBiaoViewHolder viewHolder = (FlgwLieBiaoViewHolder) holder;
        viewHolder.tvFlgwName.setText(listBean.getTitle());
        int buy = listBean.getBuy();
        viewHolder.rlFlgw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buy == 1){
                    Intent intent = new Intent(mContext, ActivityLegalAdviserDetailsDowLoad.class);
                    intent.putExtra("id", listBean.getId());
                    mContext.startActivity(intent);
                }else if(buy == 0){
                    Intent intent = new Intent(mContext, ActivityLegalAdviserDetailsBuy.class);
                    intent.putExtra("id", listBean.getId());
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class FlgwLieBiaoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_flgw_yd)
        ImageView ivFlgwYd;
        @BindView(R.id.tv_flgw_name)
        TextView tvFlgwName;
        @BindView(R.id.rl_flgw)
        RelativeLayout rlFlgw;
        public FlgwLieBiaoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
