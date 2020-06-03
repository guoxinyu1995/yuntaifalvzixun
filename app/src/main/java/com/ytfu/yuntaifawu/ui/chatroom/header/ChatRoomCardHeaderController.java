package com.ytfu.yuntaifawu.ui.chatroom.header;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.lxj.xpopup.util.XPopupUtils;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.chatroom.bean.RoomLawyerCardInfoResponse;
import com.ytfu.yuntaifawu.ui.mseeage.activity.LvShiDetailsActivity;
import com.ytfu.yuntaifawu.utils.header.base.BaseHeaderController;

import java.util.List;

public class ChatRoomCardHeaderController extends BaseHeaderController<RoomLawyerCardInfoResponse.LawyerCardInfo> {

    public ChatRoomCardHeaderController(Context mContext) {
        super(mContext);
    }

    @Override
    protected View onCreateHeaderView() {
        return inflateView(R.layout.header_room_lawyer_info);
    }

    @Override
    public void render(RoomLawyerCardInfoResponse.LawyerCardInfo data) {
        super.render(data);
        //头像
        String avatarUrl = data.getPicurl();
        ImageView ivAvatar = findHeaderViewById(R.id.iv_lawyer_card_avatar);
        RoundedCorners rc = new RoundedCorners(XPopupUtils.dp2px(mContext, 4));
        RequestOptions options = RequestOptions.bitmapTransform(rc)
                .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                .fallback(R.drawable.touxiang) //url为空的时候,显示的图片
                .error(R.drawable.touxiang);//图片加载失败后，显示的图片
        Glide.with(mContext).load(avatarUrl)
                .apply(options)
                .into(ivAvatar);
        //姓名
        setVIewText(R.id.tv_lawyer_card_name, data.getName());
        //领域
        setVIewText(R.id.tv_lawyer_card_scopes, data.getLingyu());
        //级别
        setVIewText(R.id.tv_lawyer_card_level, data.getLvshitype());
        //学历
        setVIewText(R.id.tv_lawyer_card_education, data.getXueli());
        //年龄
        setVIewText(R.id.tv_lawyer_card_age, data.getCyear());
        //简介
        setVIewText(R.id.tv_lawyer_card_summary, data.getJianjie());
        //案例
        List<RoomLawyerCardInfoResponse.LawyerCardInfo.AnliContentBean> caseContent = data.getAnli_content();
        LinearLayout ll_lawyer_card_cases = findHeaderViewById(R.id.ll_lawyer_card_cases);
        ll_lawyer_card_cases.removeAllViews();
        if (null == caseContent) {
            findHeaderViewById(R.id.ll_lawyer_card_cases_root).setVisibility(View.GONE);
        } else {
            findHeaderViewById(R.id.ll_lawyer_card_cases_root).setVisibility(View.VISIBLE);
            //动态创建案例并添加
            for (int i = 0; i < caseContent.size(); i++) {
                RoomLawyerCardInfoResponse.LawyerCardInfo.AnliContentBean caseBean = caseContent.get(i);
                if (i == 0) {
                    LinearLayout.LayoutParams params =
                            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT);
                    TextView tv = createItemCaseView(caseBean.getContent());
                    ll_lawyer_card_cases.addView(tv, params);
                } else if (i == 1) {
                    LinearLayout.LayoutParams lineParams =
                            new LinearLayout.LayoutParams(XPopupUtils.dp2px(mContext, 0.5F),
                                    XPopupUtils.dp2px(mContext, 17F));
                    lineParams.leftMargin = XPopupUtils.dp2px(mContext, 10F);
                    lineParams.topMargin = XPopupUtils.dp2px(mContext, 2F);
                    lineParams.bottomMargin = XPopupUtils.dp2px(mContext, 2F);
                    View line = new View(mContext);
                    line.setBackgroundColor(Color.parseColor("#CCCCCC"));
                    ll_lawyer_card_cases.addView(line, lineParams);


                    LinearLayout.LayoutParams params =
                            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT);
                    TextView tv = createItemCaseView(caseBean.getContent());
                    ll_lawyer_card_cases.addView(tv, params);
                } else {
                    break;
                }

            }
        }

        getHeaderView().setOnClickListener(v -> {
            Intent intent = new Intent(mContext, LvShiDetailsActivity.class);
            intent.putExtra("lid", data.getLid());
            intent.putExtra("userName", data.getName());
            intent.putExtra("types", 1);
            mContext.startActivity(intent);
        });
    }

    //===Desc:=================================================================


    private TextView createItemCaseView(String content) {
        TextView tv = new TextView(mContext);
        Drawable drawable = mContext.getDrawable(R.drawable.anli);
        tv.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        tv.setCompoundDrawablePadding(XPopupUtils.dp2px(mContext, 10));
        tv.setEllipsize(TextUtils.TruncateAt.END);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setLines(1);
        tv.setMaxLines(1);
        tv.setTextColor(Color.parseColor("#666666"));
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        tv.setText(content);
        return tv;
    }

}
