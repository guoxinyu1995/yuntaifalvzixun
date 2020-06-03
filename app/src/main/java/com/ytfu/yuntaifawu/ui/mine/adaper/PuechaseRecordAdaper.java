package com.ytfu.yuntaifawu.ui.mine.adaper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.falvguwen.activity.ActivityLegalAdviserDetailsDowLoad;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityAudioDetails;
import com.ytfu.yuntaifawu.ui.lvshihetong.activity.ActivityContractDetails;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.activity.ActivityDaiLiCi;
import com.ytfu.yuntaifawu.ui.mine.bean.PurchaseRecordBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityZjqdXq;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2019/11/20
 * @Des 购买记录
 */
public class PuechaseRecordAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<PurchaseRecordBean.ListBean> mList;

    public PuechaseRecordAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<PurchaseRecordBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_purchass_record, parent, false);
        return new PurchaseRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PurchaseRecordViewHolder viewHolder = (PurchaseRecordViewHolder) holder;
        PurchaseRecordBean.ListBean listBean = mList.get(position);
        viewHolder.tv_order_id.setText("订单号:" + listBean.getOrder_id());
        String type = listBean.getType();
        viewHolder.ty_type_name.setText(type);
        int type_id = listBean.getType_id();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (type_id) {
                    case 1:
                        //音频
                        ToastUtil.showToast("音频");
                        Intent intentAudio = new Intent(mContext, ActivityAudioDetails.class);
                        intentAudio.putExtra("id",listBean.getAn_id());
                        mContext.startActivity(intentAudio);
                        break;
                    case 2:
                        //合同
                        ToastUtil.showToast("合同");
                        Intent intentContract = new Intent(mContext, ActivityContractDetails.class);
                        intentContract.putExtra("id",listBean.getAn_id());
                        mContext.startActivity(intentContract);
                        break;
                    case 7:
                        //法律顾问
                        ToastUtil.showToast("法律顾问");
                        Intent intentFlgw = new Intent(mContext, ActivityLegalAdviserDetailsDowLoad.class);
                        intentFlgw.putExtra("id",listBean.getAn_id());
                        mContext.startActivity(intentFlgw);
                        break;
                    case 8:
                        //代理词
                        ToastUtil.showToast("代理词");
                        Intent intentDlc = new Intent(mContext, ActivityDaiLiCi.class);
                        intentDlc.putExtra("id",listBean.getAn_id());
                        mContext.startActivity(intentDlc);
                        break;
                    case 10:
                        //证据清单
                        ToastUtil.showToast("证据清单");
                        Intent intentZjqd = new Intent(mContext, ActivityZjqdXq.class);
                        intentZjqd.putExtra("types",3);
                        intentZjqd.putExtra("zhengjuid",listBean.getAn_id());
                        mContext.startActivity(intentZjqd);
                        break;
                    default:
                        break;
                }
            }
        });


        viewHolder.tv_title.setText(listBean.getOrder_name());
        viewHolder.tv_time.setText(listBean.getO_date());
        viewHolder.tv_price.setText("￥" + listBean.getOrder_price());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PurchaseRecordViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_order_id, ty_type_name, tv_title, tv_time, tv_price;

        public PurchaseRecordViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_order_id = itemView.findViewById(R.id.tv_order_id);
            ty_type_name = itemView.findViewById(R.id.ty_type_name);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_price = itemView.findViewById(R.id.tv_price);
        }
    }
}
