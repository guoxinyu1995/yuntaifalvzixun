package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityDaiLiCi;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityOpenHelperDetails;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQszYlXq;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/27
 * @Des 开庭助手原告被告
 */
public class KtzsEntryContentAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<KtzsListBean.ListBeanX.ListBean> mList;

    public KtzsEntryContentAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setEntryList(List<KtzsListBean.ListBeanX.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ktzs_entry_content, parent, false);
        return new KtzsEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        KtzsEntryViewHolder viewHolder = (KtzsEntryViewHolder) holder;
        KtzsListBean.ListBeanX.ListBean listBean = mList.get(position);
        viewHolder.tvKtzsYuangao.setText("原告：" + listBean.getYuangao_name());
        viewHolder.tvKtzsBeigao.setText("被告：" + listBean.getBeigao_name());
        //进入代理词
        viewHolder.tvJinruDailici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityDaiLiCi.class);
                intent.putExtra("id", listBean.getId());
                mContext.startActivity(intent);
            }
        });
        //预览起诉张
        viewHolder.tvYlQsz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityQszYlXq.class);
//                intent.putExtra("url", listBean.getUrl());
                intent.putExtra("id", listBean.getId());
                mContext.startActivity(intent);
            }
        });
        //进入详情
        viewHolder.llKtzsXiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityOpenHelperDetails.class);
                intent.putExtra("id", listBean.getId());
                intent.putExtra("img_type", listBean.getImg_type());
                intent.putExtra("jump_type", listBean.getJump_type());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class KtzsEntryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_ktzs_yuangao)
        TextView tvKtzsYuangao;
        @BindView(R.id.tv_jinru_dailici)
        TextView tvJinruDailici;
        @BindView(R.id.tv_ktzs_beigao)
        TextView tvKtzsBeigao;
        @BindView(R.id.ll_ktzs_xiangqing)
        LinearLayout llKtzsXiangqing;
        @BindView(R.id.tv_yl_qsz)
        TextView tvYlQsz;
        public KtzsEntryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
