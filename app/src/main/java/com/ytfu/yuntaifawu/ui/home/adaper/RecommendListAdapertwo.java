package com.ytfu.yuntaifawu.ui.home.adaper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityAudioDetails;
import com.ytfu.yuntaifawu.ui.home.bean.RecommendListBean;
import com.ytfu.yuntaifawu.ui.login.activity.LoginCodeActivity;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  推荐列表适配器
*
*/
public class RecommendListAdapertwo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<RecommendListBean.ListBean.HengfuBean> mList;

    public RecommendListAdapertwo(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }
    public void setmList(List<RecommendListBean.ListBean.HengfuBean> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycel_view, parent, false);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecommendViewHolder viewHolder = (RecommendViewHolder) holder;
        RecommendListBean.ListBean.HengfuBean hengfuBean = mList.get(position);
//        Glide.with(mContext).load(hengfuBean.getPost_img()).into(viewHolder.image);
        GlideManager.loadImageByUrl(mContext,hengfuBean.getPost_img(),viewHolder.image);
        viewHolder.text_title.setText(hengfuBean.getPost_title());
        viewHolder.text_excerpt.setText(hengfuBean.getPost_excerpt());
        viewHolder.text_price.setText(hengfuBean.getPost_price());
        viewHolder.text_yuan_price.setText("原价" + "¥" + hengfuBean.getPost_cost());
        viewHolder.text_yuan_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolder.text_count.setText("已有" + hengfuBean.getOrder_count() + "人咨询");
        //点击跳转到详情
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(App.getInstance().getLoginFlag()){
                    Intent intent = new Intent(mContext, ActivityAudioDetails.class);
                    intent.putExtra("id",hengfuBean.getId());
                    mContext.startActivity(intent);
                }else{
                    mContext.startActivity(new Intent(mContext, LoginCodeActivity.class));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

public class RecommendViewHolder extends RecyclerView.ViewHolder{
    public ImageView image;
    public TextView text_title, text_excerpt, text_price, text_yuan_price, text_count;
    public RelativeLayout relativeLayout;
    public RecommendViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        text_title = itemView.findViewById(R.id.text_title);
        text_excerpt = itemView.findViewById(R.id.text_excerpt);
        text_price = itemView.findViewById(R.id.text_price);
        text_yuan_price = itemView.findViewById(R.id.text_yuan_price);
        text_count = itemView.findViewById(R.id.text_count);
        relativeLayout = itemView.findViewById(R.id.relative_layout);
    }
}
}
