package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.GsjdBean;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/27
 * @Des 工伤鉴定条目
 */
public class GsjdListAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<GsjdBean.ListBeanX.ListBean> mList;

    public GsjdListAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<GsjdBean.ListBeanX.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gsjd_item_list, parent, false);
        return new GsjdListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GsjdListViewHolder viewHolder = (GsjdListViewHolder) holder;
        GsjdBean.ListBeanX.ListBean listBean = mList.get(position);
        String type = listBean.getType();
        switch (type) {
            case "1":
                //文本
                viewHolder.tvGsjdListText.setText(listBean.getName());
                viewHolder.ivGsjdListDownload.setVisibility(View.GONE);
                viewHolder.gsjdXqListBz.setVisibility(View.INVISIBLE);
                break;
            case "2":
                //文本带邮件
                viewHolder.tvGsjdListText.setText(listBean.getName());
                viewHolder.ivGsjdListDownload.setVisibility(View.VISIBLE);
                viewHolder.gsjdXqListBz.setVisibility(View.INVISIBLE);
                viewHolder.rlGsjdList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(gsjdListClickListener!=null){
                            gsjdListClickListener.onGsjdListItemClickListener(position,listBean.getUrl());
                        }
                    }
                });
                break;
            case "3":
                //文本带备注带邮件
                viewHolder.tvGsjdListText.setText(listBean.getName());
                viewHolder.ivGsjdListDownload.setVisibility(View.VISIBLE);
                viewHolder.gsjdXqListBz.setText(listBean.getNeirong());
                viewHolder.rlGsjdList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(gsjdListClickListener!=null){
                            gsjdListClickListener.onGsjdListItemClickListener(position,listBean.getUrl());
                        }
                    }
                });
                break;
            case "4":
                //文本带备注
                viewHolder.tvGsjdListText.setText(listBean.getName());
                viewHolder.ivGsjdListDownload.setVisibility(View.GONE);
                viewHolder.gsjdXqListBz.setText(listBean.getNeirong());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GsjdListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_gsjd)
        ImageView ivGsjd;
        @BindView(R.id.tv_gsjd_list_text)
        TextView tvGsjdListText;
        @BindView(R.id.iv_gsjd_list_download)
        ImageView ivGsjdListDownload;
        @BindView(R.id.gsjd_xq_list_bz)
        TextView gsjdXqListBz;
        @BindView(R.id.rl_gsjd_list)
        RelativeLayout rlGsjdList;

        public GsjdListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //定义接口
    private IGsjdListClickListener gsjdListClickListener;

    public void setGsjdListItemClickListener(IGsjdListClickListener gsjdListClickListener) {
        this.gsjdListClickListener = gsjdListClickListener;
    }

    public interface IGsjdListClickListener {
        void onGsjdListItemClickListener(int pos,String url);
    }
}
