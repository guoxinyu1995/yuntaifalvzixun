package com.ytfu.yuntaifawu.ui.home.adaper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.ui.chatroom.activity.UserChatRoomActivity;
import com.ytfu.yuntaifawu.ui.home.bean.HomePingJIaBean;
import com.ytfu.yuntaifawu.ui.login.activity.LoginCodeActivity;
import com.ytfu.yuntaifawu.ui.mseeage.activity.ChatActivity;
import com.ytfu.yuntaifawu.ui.mseeage.activity.LvShiDetailsActivity;
import com.ytfu.yuntaifawu.utils.DemoHelper;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2020/4/28
 * @Des 首页用户评价adaper
 */
public class HomePingJiaAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<HomePingJIaBean.ListBean> pingJiaBeanList;

    public HomePingJiaAdaper(Context mContext) {
        this.mContext = mContext;
        pingJiaBeanList = new ArrayList<>();
    }

    public void setPingJiaBeanList(List<HomePingJIaBean.ListBean> pingJiaBeanList) {
        this.pingJiaBeanList = pingJiaBeanList;
        notifyDataSetChanged();
    }

    public void addPingJiaBeanList(List<HomePingJIaBean.ListBean> pingJiaBeanList) {
        if (!pingJiaBeanList.isEmpty()) {
            this.pingJiaBeanList.addAll(pingJiaBeanList);
            notifyDataSetChanged();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_pingjia_item, parent, false);
        return new HomePingJiaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomePingJIaBean.ListBean listBean = pingJiaBeanList.get(position);
        HomePingJiaViewHolder viewHolder = (HomePingJiaViewHolder) holder;
        GlideManager.loadImageByUrl(mContext, listBean.getUrl(), viewHolder.ivHomePingjia);
        viewHolder.tvHomePjName.setText(listBean.getNickname());
        viewHolder.tvHomePjTime.setText(listBean.getYhaddtime());
        viewHolder.tvHomePingjiaContent.setText(listBean.getName());
        viewHolder.tvPjGeiName.setText(listBean.getName_lvshi());
        viewHolder.tvPjGeiName.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (App.getInstance().getLoginFlag() && DemoHelper.getInstance().isLoggedIn()) {
////                Intent intent = new Intent(mContext, LvShiDetailsActivity.class);
////                intent.putExtra("lid", listBean.getLvshi());
////                intent.putExtra("userName", listBean.getName_lvshi());
////                intent.putExtra("types", 1);
////                mContext.startActivity(intent);
////            } else {
////                mContext.startActivity(new Intent(mContext, LoginCodeActivity.class));
////            }
                if (rateListClickListener != null) {
                    rateListClickListener.onClickListener();
                }
            }
        });
        viewHolder.tvZixun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (App.getInstance().getLoginFlag() && DemoHelper.getInstance().isLoggedIn()) {
//
//                    String toUserId = listBean.getLvshi();
//                    String toUserName = listBean.getName_lvshi();
//                    String toUserAvatar = listBean.getUrl();
//                    UserChatRoomActivity.start(mContext,
//                            false,false,
//                            toUserId, toUserName, toUserAvatar);
//                } else {
//                    mContext.startActivity(new Intent(mContext, LoginCodeActivity.class));
//                }
                if (rateListClickListener != null) {
                    rateListClickListener.onClickListener();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pingJiaBeanList.size();
    }


    public class HomePingJiaViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_home_pingjia)
        ImageView ivHomePingjia;
        @BindView(R.id.tv_home_pj_name)
        TextView tvHomePjName;
        @BindView(R.id.tv_home_pj_time)
        TextView tvHomePjTime;
        @BindView(R.id.tv_home_pingjia_content)
        TextView tvHomePingjiaContent;
        @BindView(R.id.tv_pingjia_gei)
        TextView tvPingjiaGei;
        @BindView(R.id.tv_pj_gei_name)
        TextView tvPjGeiName;
        @BindView(R.id.tv_zixun)
        TextView tvZixun;

        public HomePingJiaViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private RateListClickListener rateListClickListener;

    public void setRateListClickListener(RateListClickListener rateListClickListener) {
        this.rateListClickListener = rateListClickListener;
    }

    public interface RateListClickListener {
        void onClickListener();
    }
}
