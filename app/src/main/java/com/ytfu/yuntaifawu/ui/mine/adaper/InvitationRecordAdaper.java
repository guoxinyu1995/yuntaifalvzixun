package com.ytfu.yuntaifawu.ui.mine.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.mine.bean.InvitationRecordListBean;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2019/11/17
 * @Des 邀请记录适配器
 */
public class InvitationRecordAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<InvitationRecordListBean.ListBean> mList;
    private final int TYPE_ONE = 1;
    private final int TYPE_TWO = 2;

    public InvitationRecordAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<InvitationRecordListBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mList.size() - 1) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ONE) {
            View view_num = LayoutInflater.from(mContext).inflate(R.layout.item_invitation_num, parent, false);
            return new AlreadyInvitedViewHolder(view_num);
        } else {
            View view_item = LayoutInflater.from(mContext).inflate(R.layout.item_invitation_record, parent, false);
            return new InvationRecordListItemViewHolder(view_item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        InvitationRecordListBean.ListBean listBean = mList.get(position);
        if (getItemViewType(position) == TYPE_ONE) {
            AlreadyInvitedViewHolder mHolder = (AlreadyInvitedViewHolder) holder;
            mHolder.text_pre_num.setText("已邀请" + listBean.getAvatar() + "人");
        } else if (getItemViewType(position) == TYPE_TWO) {
            InvationRecordListItemViewHolder iHolder = (InvationRecordListItemViewHolder) holder;
            GlideManager.loadCircleImage(mContext, listBean.getAvatar(), iHolder.header_icon);
            iHolder.tv_name.setText(listBean.getUser_login());
            iHolder.tv_state.setText("已邀请");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //邀请的条目
    public class InvationRecordListItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView header_icon;
        public TextView tv_name, tv_state;

        public InvationRecordListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            header_icon = itemView.findViewById(R.id.header_icon);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_state = itemView.findViewById(R.id.tv_state);
        }
    }

    //已邀请人数
    public class AlreadyInvitedViewHolder extends RecyclerView.ViewHolder {
        public TextView text_pre_num;

        public AlreadyInvitedViewHolder(@NonNull View itemView) {
            super(itemView);
            text_pre_num = itemView.findViewById(R.id.text_pre_num);
        }
    }
}
