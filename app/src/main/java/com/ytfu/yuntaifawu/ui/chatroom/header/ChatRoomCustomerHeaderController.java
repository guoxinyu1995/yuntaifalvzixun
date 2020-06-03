package com.ytfu.yuntaifawu.ui.chatroom.header;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.mseeage.activity.FwhMessageDetailsActivity;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ZiXunSendMessageBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQiSuZhuang;
import com.ytfu.yuntaifawu.utils.header.base.BaseHeaderController;
import com.ytfu.yuntaifawu.utils.view.RatioImageView;

import java.util.List;

public class ChatRoomCustomerHeaderController extends BaseHeaderController<List<ZiXunSendMessageBean.ListBean>> {

    public ChatRoomCustomerHeaderController(Context mContext) {
        super(mContext);
    }

    @Override
    protected View onCreateHeaderView() {
        return inflateView(R.layout.header_room_lawyer_customer);

    }

    @Override
    public void render(List<ZiXunSendMessageBean.ListBean> data) {
        super.render(data);
        LinearLayout ll = findHeaderViewById(R.id.ll_root);
        ll.removeAllViews();
        for (ZiXunSendMessageBean.ListBean item : data) {
            View itemView = inflateView(R.layout.item_header_room_lawyer_customer);
            RatioImageView ivPic = itemView.findViewById(R.id.rv_item_header_pic);
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.icon_seat)
                    .error(R.drawable.icon_seat)
                    // 设置缓存
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

            Glide.with(mContext)
                    .load(item.getImgurl())
                    .apply(options)
                    .into(ivPic);
            TextView tvTitle = itemView.findViewById(R.id.tv_item_header_title);
            tvTitle.setText(item.getTitle());

            TextView tvDesc = itemView.findViewById(R.id.tv_item_header_desc);
            tvDesc.setText(item.getContent());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int type = item.getType();
                    String url = item.getUrl();
                    switch (type) {
                        case 1:
                            mContext.startActivity(new Intent(mContext, ActivityQiSuZhuang.class));
                            break;
                        case 2:
                            if (!TextUtils.isEmpty(url)) {
                                Intent intent = new Intent(mContext, FwhMessageDetailsActivity.class);
                                intent.putExtra("url", url);
                                mContext.startActivity(intent);
                            }
                            break;
                    }
                }
            });

            ll.addView(itemView);
        }


    }
}
