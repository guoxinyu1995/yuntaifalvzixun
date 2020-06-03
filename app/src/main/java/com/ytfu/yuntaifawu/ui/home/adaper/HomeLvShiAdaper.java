package com.ytfu.yuntaifawu.ui.home.adaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.ui.home.bean.HomeLvShiBean;
import com.ytfu.yuntaifawu.ui.login.activity.LoginCodeActivity;
import com.ytfu.yuntaifawu.ui.mseeage.activity.LvShiDetailsActivity;
import com.ytfu.yuntaifawu.utils.DemoHelper;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2020/4/27
 * @Des 首页律师Adaper
 */
public class HomeLvShiAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<HomeLvShiBean.ListBean> beanList;

    public HomeLvShiAdaper(Context mContext) {
        this.mContext = mContext;
        beanList = new ArrayList<>();
    }

    public void setBeanList(List<HomeLvShiBean.ListBean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_recycle_lvshi_item, parent, false);
        return new HomeLvShiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeLvShiViewHolder viewHolder = (HomeLvShiViewHolder) holder;
        HomeLvShiBean.ListBean listBean = beanList.get(position);
        viewHolder.tvHomeName.setText(listBean.getName());
        viewHolder.tvHomeContent.setText(listBean.getJianjie());
        viewHolder.tvHomeType.setText(listBean.getTitle());
        GlideManager.loadImageByUrl(mContext, listBean.getPicurl(), viewHolder.ivHomeHead);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (App.getInstance().getLoginFlag() && DemoHelper.getInstance().isLoggedIn()) {
//                    Intent intent = new Intent(mContext, LvShiDetailsActivity.class);
//                    intent.putExtra("lid", listBean.getLid());
//                    intent.putExtra("userName", listBean.getName());
//                    intent.putExtra("types", 1);
//                    mContext.startActivity(intent);
//                } else {
//                    mContext.startActivity(new Intent(mContext, LoginCodeActivity.class));
//                }
                if (lawyerListClickListener != null) {
                    lawyerListClickListener.onClickListener();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class HomeLvShiViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home_head)
        ImageView ivHomeHead;
        @BindView(R.id.tv_home_name)
        TextView tvHomeName;
        @BindView(R.id.ll_yw)
        LinearLayout llYw;
        @BindView(R.id.tv_home_type)
        TextView tvHomeType;
        @BindView(R.id.tv_home_content)
        TextView tvHomeContent;

        public HomeLvShiViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private LawyerListClickListener lawyerListClickListener;

    public void setLawyerListClickListener(LawyerListClickListener lawyerListClickListener) {
        this.lawyerListClickListener = lawyerListClickListener;
    }

    public interface LawyerListClickListener {
        void onClickListener();
    }
}
