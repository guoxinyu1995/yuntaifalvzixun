package com.ytfu.yuntaifawu.ui.qisuzhuang.adaper;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.home.activity.AudioListActivity;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQszXinagqing;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszFenLeiBean;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QszFenLeiAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<QszFenLeiBean.ListBean> mList;
    private final int TYPE_SHUJU = 0;
    private final int TYPE_FOOTER = 1;
    private int mFooterCount = 1;
    public QszFenLeiAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<QszFenLeiBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position == mList.size()) {
//            return TYPE_SHUJU;
//        } else {
//            return TYPE_FOOTER;
//        }
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType != TYPE_FOOTER) {
//            View view = LayoutInflater.from(mContext).inflate(R.layout.item_fooder_qsz_view, parent, false);
//            return new FooderViewHolder(view);
//        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_qsz, parent, false);
            return new QszViewHolder(view);
//        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

//        if (getItemViewType(position) != TYPE_FOOTER) {
//            FooderViewHolder viewHolder = (FooderViewHolder) holder;
//        } else {
            QszFenLeiBean.ListBean listBean = mList.get(position);
            QszViewHolder viewHolder = (QszViewHolder) holder;
            GlideManager.loadImageByUrl(mContext, listBean.getImgx(), viewHolder.ivQsz);
            viewHolder.tvQsz.setText(listBean.getLabel());
            String href = listBean.getHref();
            viewHolder.rlQsz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(href)){
                        ToastUtil.showCenterToast("开发中，敬请期待");
                    }else {
                        Intent intent = new Intent(mContext, ActivityQszXinagqing.class);
                        intent.putExtra("id", listBean.getId());
                        intent.putExtra("label", listBean.getLabel());
                        intent.putExtra("href",listBean.getHref());
                        mContext.startActivity(intent);
                    }
                }
            });
//        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    //列表
        public class  QszViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_qsz)
        ImageView ivQsz;
        @BindView(R.id.tv_qsz)
        TextView tvQsz;
        @BindView(R.id.rl_qsz)
        RelativeLayout rlQsz;
        public QszViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //底部fooder
    public class FooderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_shou)
        ImageView ivShou;

        public FooderViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
