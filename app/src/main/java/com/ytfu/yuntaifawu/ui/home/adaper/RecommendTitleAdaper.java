package com.ytfu.yuntaifawu.ui.home.adaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.ui.home.activity.AudioListActivity;
import com.ytfu.yuntaifawu.ui.home.bean.RecommendListBean;
import com.ytfu.yuntaifawu.ui.login.activity.LoginCodeActivity;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2019/11/12
 * @Des 首页推荐列表title适配器
 */
public class RecommendTitleAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<RecommendListBean.ListBean> mList;

    public RecommendTitleAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<RecommendListBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycle_title, parent, false);
        return new RecommendTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecommendTitleViewHolder viewHolder = (RecommendTitleViewHolder) holder;
        RecommendListBean.ListBean listBean = mList.get(position);
//        Glide.with(mContext).load(recommendListBean.).into(viewHolder.title_item_icon);
        GlideManager.loadImageByUrl(mContext, listBean.getImg(), viewHolder.title_item_icon);
        viewHolder.text_item_title.setText(listBean.getLabel());
        //展示两条数据
        RecommendListAdapertwo listAdapertwo = new RecommendListAdapertwo(mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.recycle_two.setLayoutManager(linearLayoutManager);
        viewHolder.recycle_two.setAdapter(listAdapertwo);
        listAdapertwo.setmList(listBean.getHengfu());
        //展示三条数据
        RecommendListAdaperthree listAdaperthree = new RecommendListAdaperthree(mContext);
//        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
//        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
//        viewHolder.recycle_three.setLayoutManager(linearLayoutManager1);
        viewHolder.recycle_three.setLayoutManager(new GridLayoutManager(mContext, 3));
        viewHolder.recycle_three.setAdapter(listAdaperthree);
        listAdaperthree.setmList(listBean.getShufu());
        viewHolder.text_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(App.getInstance().getLoginFlag()){
                    Intent intent = new Intent(mContext, AudioListActivity.class);
                    intent.putExtra("id",listBean.getId());
                    intent.putExtra("label", listBean.getLabel());
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

    public class RecommendTitleViewHolder extends RecyclerView.ViewHolder {
        public ImageView title_item_icon;
        public TextView text_item_title, text_more;
        public RecyclerView recycle_two, recycle_three;

        public RecommendTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            title_item_icon = itemView.findViewById(R.id.title_item_icon);
            text_item_title = itemView.findViewById(R.id.text_item_title);
            text_more = itemView.findViewById(R.id.text_more);
            recycle_two = itemView.findViewById(R.id.recycle_two);
            recycle_three = itemView.findViewById(R.id.recycle_three);
        }
    }
}
