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
import com.ytfu.yuntaifawu.ui.lvshihetong.activity.ActivityContractDetails;
import com.ytfu.yuntaifawu.ui.home.bean.AudioDetailsBean;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  相关合同适配器
*
*/
public class RelevantContractAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<AudioDetailsBean.ContractListBean> mList;

    public RelevantContractAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }
  public void setmList(List<AudioDetailsBean.ContractListBean> mList){
        this.mList = mList;
        notifyDataSetChanged();
  }

  public void addmlist(List<AudioDetailsBean.ContractListBean> list){
            if(!list.isEmpty()){
                mList.addAll(list);
                notifyDataSetChanged();
            }
  }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_relevant_contract, parent, false);
        RelevationContractViewHolder contractViewHolder = new RelevationContractViewHolder(view);
        return contractViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RelevationContractViewHolder viewHolder = (RelevationContractViewHolder) holder;
        AudioDetailsBean.ContractListBean contractListBean = mList.get(position);
        GlideManager.loadImageByUrl(mContext,contractListBean.getImg(),viewHolder.contract_icon);
        viewHolder.text_title.setText(contractListBean.getTitle());
        viewHolder.text_count.setText("已有"+contractListBean.getDownload_count()+"人浏览");
       viewHolder.text_price.setVisibility(View.GONE);
        viewHolder.text_price.setText(contractListBean.getPrice());
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityContractDetails.class);
                intent.putExtra("id",contractListBean.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class RelevationContractViewHolder extends RecyclerView.ViewHolder{
        public ImageView contract_icon;
        public TextView text_title,text_count,text_price;
        public RelativeLayout layout;
        public RelevationContractViewHolder(@NonNull View itemView) {
            super(itemView);
            text_count = itemView.findViewById(R.id.text_count);
            text_title = itemView.findViewById(R.id.text_title);
            contract_icon = itemView.findViewById(R.id.contract_icon);
            text_price = itemView.findViewById(R.id.text_price);
            layout = itemView.findViewById(R.id.rl);
        }
    }
}
