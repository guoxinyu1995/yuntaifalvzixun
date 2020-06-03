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
*  @Des 推荐列表适配器
*
*/
public class RecommendListAdaperthree extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<RecommendListBean.ListBean.ShufuBean> mList;

    public RecommendListAdaperthree(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<RecommendListBean.ListBean.ShufuBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycel_recommend_three, parent, false);
        return new RecommendViewHolderT(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecommendViewHolderT viewHolderT = (RecommendViewHolderT) holder;
        RecommendListBean.ListBean.ShufuBean shufuBean = mList.get(position);
//        Glide.with(mContext).load(shufuBean.getPost_img()).into(viewHolderT.icon);
        GlideManager.loadImageByUrl(mContext,shufuBean.getPost_img(),viewHolderT.icon);
        viewHolderT.text_title.setText(shufuBean.getPost_title());
        viewHolderT.text_price.setText("¥" + shufuBean.getPost_price());
        viewHolderT.text_yuan_price.setText("原价" + "¥" + shufuBean.getPost_cost());
        viewHolderT.text_yuan_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        viewHolderT.text_count.setText("已有" + shufuBean.getOrder_count() + "人咨询");

        viewHolderT.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(App.getInstance().getLoginFlag()){
                    Intent intent = new Intent(mContext, ActivityAudioDetails.class);
                    intent.putExtra("id",shufuBean.getId());
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

    public class RecommendViewHolderT extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView text_title, text_count, text_price, text_yuan_price;
        public RelativeLayout linearLayout;
        public RecommendViewHolderT(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.icon);
            text_title = itemView.findViewById(R.id.text_title);
            text_count = itemView.findViewById(R.id.text_count);
            text_price = itemView.findViewById(R.id.text_price);
            text_yuan_price = itemView.findViewById(R.id.text_yuan_price);
            linearLayout = itemView.findViewById(R.id.linear_layout);
        }
    }
}
