package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.DlcBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/12/2
 * @Des 代理词标题适配器
 */
public class DlcTileAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<DlcBean.ArrBean> mList;
    public DlcTileAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<DlcBean.ArrBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dlc_title, parent, false);
        return new DlcTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        DlcBean.ArrBean arrBean = mList.get(position);
        DlcTitleViewHolder viewHolder = (DlcTitleViewHolder) holder;
        viewHolder.tvDlcTitle.setText(arrBean.getAgent_name());
        if(!TextUtils.isEmpty(arrBean.getAgent_content())){
            viewHolder.tvDlcNr.setText(Html.fromHtml(arrBean.getAgent_content()));
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class DlcTitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_dlc_title)
        TextView tvDlcTitle;
        @BindView(R.id.tv_dlc_nr)
        TextView tvDlcNr;
        public DlcTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class DlcNrViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_dlc_nr)
        TextView tvDlcNierong;
        public DlcNrViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
