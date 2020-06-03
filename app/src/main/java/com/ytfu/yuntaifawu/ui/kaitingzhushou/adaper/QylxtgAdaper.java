package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.QylxtgBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/27
 * @Des 企业另外提供
 */
public class QylxtgAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<QylxtgBean.ListBean> mList;

    public QylxtgAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<QylxtgBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_qylwtg, parent, false);
        return new QylwtgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        QylwtgViewHolder viewHolder = (QylwtgViewHolder) holder;
        QylxtgBean.ListBean listBean = mList.get(position);
        String type = listBean.getType();
        switch (type) {
            case "1":
                viewHolder.tvQylwtgName.setText(listBean.getName());
                viewHolder.ivSendQylwtg.setVisibility(View.GONE);
                break;
            case "2":
                viewHolder.tvQylwtgName.setText(listBean.getName());
                viewHolder.ivSendQylwtg.setVisibility(View.VISIBLE);
                viewHolder.llQylxtg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (qylxtgClickListener != null) {
                            qylxtgClickListener.onQylxtgItemClickListener(listBean.getUrl());
                        }
                    }
                });
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class QylwtgViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_qylwtg_name)
        TextView tvQylwtgName;
        @BindView(R.id.iv_send_qylwtg)
        ImageView ivSendQylwtg;
        @BindView(R.id.ll_qylxtg)
        LinearLayout llQylxtg;

        public QylwtgViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //定义接口
    private IQylxtgClickListener qylxtgClickListener;

    public void setQylxtgItemClickListener(IQylxtgClickListener qylxtgClickListener) {
        this.qylxtgClickListener = qylxtgClickListener;
    }

    public interface IQylxtgClickListener {
        void onQylxtgItemClickListener(String url);
    }
}
