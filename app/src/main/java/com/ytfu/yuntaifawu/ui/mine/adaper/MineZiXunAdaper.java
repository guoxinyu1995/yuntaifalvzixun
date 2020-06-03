package com.ytfu.yuntaifawu.ui.mine.adaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.ui.LvShiMainActivity;
import com.ytfu.yuntaifawu.ui.MainActivity;
import com.ytfu.yuntaifawu.ui.mine.bean.MineZiXunBean;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.SpUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2020/5/19
 * @Des 我的咨询Adaper
 */
public class MineZiXunAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<MineZiXunBean.ListBean> listBeans;

    public MineZiXunAdaper(Context mContext) {
        this.mContext = mContext;
        listBeans = new ArrayList<>();
    }

    public void setListBeans(List<MineZiXunBean.ListBean> listBeans) {
        this.listBeans = listBeans;
        notifyDataSetChanged();
    }

    public void addListBeans(List<MineZiXunBean.ListBean> listBeans) {
        if (!listBeans.isEmpty()) {
            this.listBeans.addAll(listBeans);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.mine_zixun_recycleview_item, parent, false);
        return new MineZiXunViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MineZiXunBean.ListBean listBean = listBeans.get(position);
        MineZiXunViewHolder viewHolder = (MineZiXunViewHolder) holder;
        GlideManager.loadImageByUrl(mContext, listBean.getAvatar(), viewHolder.ivZixunIcon);
        viewHolder.tvZixunName.setText(listBean.getUser_login());
        viewHolder.tvZixunTime.setText(listBean.getConsult_date());
        viewHolder.tvZixunContent.setText(listBean.getConsult_content());
        viewHolder.tvZixunType.setText(listBean.getConsult_type());
        viewHolder.tvZixunNum.setText("已回复(" + listBean.getSum() + ")");
        if (listBean.getType() == 1) {
            viewHolder.tvZhifuleixing.setVisibility(View.GONE);
        } else if(listBean.getType() == 2){
            viewHolder.tvZhifuleixing.setVisibility(View.VISIBLE);
            viewHolder.tvZhifuleixing.setText("悬赏咨询");
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String shengfen = SpUtil.getString(AppConstant.SHENFEN, "1");
                if("1".equals(shengfen)){
                    Intent intent = new Intent(mContext, MainActivity.class);
                    intent.putExtra("frid",1);
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, LvShiMainActivity.class);
                    intent.putExtra("frid",1);
                    mContext.startActivity(intent);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class MineZiXunViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_zixun_icon)
        ImageView ivZixunIcon;
        @BindView(R.id.tv_zixun_name)
        TextView tvZixunName;
        @BindView(R.id.tv_zixun_time)
        TextView tvZixunTime;
        @BindView(R.id.tv_zixun_content)
        TextView tvZixunContent;
        @BindView(R.id.tv_zixun_type)
        TextView tvZixunType;
        @BindView(R.id.tv_zixun_num)
        TextView tvZixunNum;
        @BindView(R.id.tv_zhifuleixing)
        TextView tvZhifuleixing;

        public MineZiXunViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
