package com.ytfu.yuntaifawu.ui.mseeage.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateBean1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserEvaluateAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<UserEvaluateBean.ShujuBean> beanList;

    public UserEvaluateAdaper(Context mContext) {
        this.mContext = mContext;
        beanList = new ArrayList<>();
    }

    public void setBeanList(List<UserEvaluateBean.ShujuBean> beanList) {
        this.beanList = beanList;
        notifyDataSetChanged();
    }
    public void addBeanList(List<UserEvaluateBean.ShujuBean> beanList) {
        if(!beanList.isEmpty()){}
        this.beanList.addAll(beanList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user_evaluate, parent, false);
        return new UserEvaluateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserEvaluateBean.ShujuBean shujuBean = beanList.get(position);
        UserEvaluateViewHolder viewHolder = (UserEvaluateViewHolder) holder;
        viewHolder.ivTaIcon.setBackgroundResource(R.drawable.logo);
        viewHolder.tvHuidaName.setText(shujuBean.getNickname());
        viewHolder.tvHuidaTime.setText(shujuBean.getYhaddtime());
        viewHolder.tvContent.setText(shujuBean.getName());
        viewHolder.tvLeixing.setText(shujuBean.getCid());
        viewHolder.tvNum.setText(shujuBean.getCount());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iUserEvaluateClientListener != null) {
                    iUserEvaluateClientListener.userEvaluateListener(shujuBean.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class UserEvaluateViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_ta_icon)
        ImageView ivTaIcon;
        @BindView(R.id.tv_huida_name)
        TextView tvHuidaName;
        @BindView(R.id.tv_huida_time)
        TextView tvHuidaTime;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_leixing)
        TextView tvLeixing;
        @BindView(R.id.tv_num)
        TextView tvNum;

        public UserEvaluateViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private IUserEvaluateClientListener iUserEvaluateClientListener;

    public void setiUserEvaluateClientListener(IUserEvaluateClientListener iUserEvaluateClientListener) {
        this.iUserEvaluateClientListener = iUserEvaluateClientListener;
    }

    public interface IUserEvaluateClientListener {
        void userEvaluateListener(String id);
    }
}
