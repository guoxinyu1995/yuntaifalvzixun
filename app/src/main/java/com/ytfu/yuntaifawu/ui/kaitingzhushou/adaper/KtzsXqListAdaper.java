package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsXqListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/24
 * @Des 开庭助手详情适配器
 */
public class KtzsXqListAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<KtzsXqListBean.ListBean.SonBean> mList;

    public KtzsXqListAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setList(List<KtzsXqListBean.ListBean.SonBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ktzs_xq_list_item, parent, false);
        return new KtzsXqListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        KtzsXqListViewHolder viewHolder = (KtzsXqListViewHolder) holder;
        KtzsXqListBean.ListBean.SonBean sonBean = mList.get(position);
        String type = sonBean.getType();
        switch (type){
            case "1":
                //文本
                viewHolder.tvKtzsListText.setText(sonBean.getName());
                viewHolder.ivKtzsListDownload.setVisibility(View.GONE);
                viewHolder.ktzsXqListBz.setVisibility(View.INVISIBLE);
                break;
            case "2":
                //详情跳转
                viewHolder.tvKtzsListText.setText(sonBean.getName());
                viewHolder.ivKtzsListDownload.setImageResource(R.drawable.gengduo);
                viewHolder.ktzsXqListBz.setVisibility(View.INVISIBLE);
                viewHolder.rlKtzsListDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        if(sonBean.getName().equals("企业需另外提供")){
//                            Intent intent = new Intent(mContext, ActivityFirmlwtg.class);
//                            intent.putExtra("id",sonBean.getId());
//                            intent.putExtra("name",sonBean.getName());
//                            mContext.startActivity(intent);
//                        }else{
//                            Intent intent = new Intent(mContext, ActivityZhengJuQingDan.class);
//                            intent.putExtra("id",sonBean.getId());
//                            intent.putExtra("name",sonBean.getName());
//                            mContext.startActivity(intent);
//                        }
                        if(xqJumpListClickListener!=null){
                            xqJumpListClickListener.onXqListItemClickListener(sonBean.getName(),sonBean.getId());
                        }
                    }
                });
                break;
            case "3":
                //下载
                viewHolder.tvKtzsListText.setText(sonBean.getName());
                viewHolder.ivKtzsListDownload.setImageResource(R.drawable.ktzs_download);
                viewHolder.ktzsXqListBz.setVisibility(View.INVISIBLE);
                viewHolder.rlKtzsListDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(xqListClickListener!=null){
                            xqListClickListener.onXqListItemClickListener(sonBean.getUrl());
                        }
                    }
                });
                break;
            case "4":
                //下载带备注
                viewHolder.tvKtzsListText.setText(sonBean.getName());
                viewHolder.ivKtzsListDownload.setImageResource(R.drawable.ktzs_download);
                viewHolder.ktzsXqListBz.setText(sonBean.getMsg());
                viewHolder.rlKtzsListDownload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(xqListClickListener!=null){
                            xqListClickListener.onXqListItemClickListener(sonBean.getUrl());
                        }
                    }
                });
                break;
            case "5":
                //文本带备注
                viewHolder.tvKtzsListText.setText(sonBean.getName());
                viewHolder.ivKtzsListDownload.setVisibility(View.GONE);
                viewHolder.ktzsXqListBz.setText(sonBean.getMsg());
                break;
                default:
                    break;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class KtzsXqListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_ktzs)
        ImageView ivKtzs;
        @BindView(R.id.tv_ktzs_list_text)
        TextView tvKtzsListText;
        @BindView(R.id.iv_ktzs_list_download)
        ImageView ivKtzsListDownload;
        @BindView(R.id.ktzs_xq_list_bz)
        TextView ktzsXqListBz;
        @BindView(R.id.rl_ktzs_list_download)
        RelativeLayout rlKtzsListDownload;
        public KtzsXqListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
    //定义接口
    private IXqListClickListener xqListClickListener;
    public void setXqItemClickListener(IXqListClickListener xqListClickListener){
        this.xqListClickListener = xqListClickListener;
    }
    public interface IXqListClickListener{
        void onXqListItemClickListener(String url);
    }

    //定义接口
    private IXqJumpListClickListener xqJumpListClickListener;
    public void setXqJumpItemClickListener(IXqJumpListClickListener xqJumpListClickListener){
        this.xqJumpListClickListener = xqJumpListClickListener;
    }
    public interface IXqJumpListClickListener{
        void onXqListItemClickListener(String name,String id);
    }
}
