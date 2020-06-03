package com.hyphenate.easeui.widget.chatrow;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.R;

public class EaseChatRowCard extends EaseChatRow {

    private ImageView iv_xingbie;
    private ImageView iv_head;
    private TextView tv_name;
    private TextView tv_lingyu;
    private TextView tv_dengji;
    private TextView tv_xueli;
    private TextView tv_nianling;
    private TextView tv_jianjie;
    private TextView tv_anli_one;
    private TextView tv_anli_two;

    public EaseChatRowCard(Context context, EMMessage message, int position, BaseAdapter adapter) {
        super(context, message, position, adapter);
    }

    @Override
    protected void onInflateView() {
        inflater.inflate(message.direct() == EMMessage.Direct.RECEIVE ? R.layout.ease_row_received_card : R.layout.ease_row_send_card, this);
    }

    @Override
    protected void onFindViewById() {
        iv_xingbie = findViewById(R.id.iv_xing);
        iv_head = findViewById(R.id.iv_head);
        tv_name = findViewById(R.id.tv_name);
        tv_lingyu = findViewById(R.id.tv_lingyu);
        tv_dengji = findViewById(R.id.tv_dengji);
        tv_xueli = findViewById(R.id.tv_xueli);
        tv_nianling = findViewById(R.id.tv_nianling);
        tv_jianjie = findViewById(R.id.tv_jianjie);
        tv_anli_one = findViewById(R.id.tv_anli_one);
        tv_anli_two = findViewById(R.id.tv_anli_two);
    }

    @Override
    protected void onViewUpdate(EMMessage msg) {
//        userAvatarView.setVisibility(GONE);
//        timeStampView.setVisibility(GONE);
//        String picurlc = msg.getStringAttribute("picurlc", "");
//        Glide.with(this.getContext()).load(picurlc).into(iv_head);
//        String sex = msg.getStringAttribute("sex", "");
//        tv_name.setText(msg.getStringAttribute("name", ""));
//        tv_lingyu.setText(msg.getStringAttribute("lingyu", ""));
//        tv_dengji.setText(msg.getStringAttribute("lvshitype", ""));
//        tv_xueli.setText(msg.getStringAttribute("xueli", ""));
//        tv_nianling.setText(msg.getStringAttribute("cyear", ""));
//        tv_jianjie.setText(msg.getStringAttribute("jianjie", ""));
//        tv_anli_one.setText(msg.getStringAttribute("anli_one", ""));
//        tv_anli_two.setText(msg.getStringAttribute("anli_two", ""));
//        Log.v("message","------"+tv_name);
//        if (!TextUtils.isEmpty(sex)) {
//            switch (sex) {
//                case "1":
//                    iv_xingbie.setBackgroundResource(R.drawable.xingbie_boy);
//                    break;
//                case "2":
//                    iv_xingbie.setBackgroundResource(R.drawable.xingbie_gril);
//                    break;
//            }
//        }
    }

    @Override
    protected void onSetUpView() {
        userAvatarView.setVisibility(GONE);
        timeStampView.setVisibility(GONE);
        String picurlc = message.getStringAttribute("picurlc", "");
        Glide.with(this.getContext()).load(picurlc).into(iv_head);
        String sex = message.getStringAttribute("sex", "");
        tv_name.setText(message.getStringAttribute("name", ""));
        tv_lingyu.setText(message.getStringAttribute("lingyu", ""));
        tv_dengji.setText(message.getStringAttribute("lvshitype", ""));
        tv_xueli.setText(message.getStringAttribute("xueli", ""));
        tv_nianling.setText(message.getStringAttribute("cyear", ""));
        tv_jianjie.setText(message.getStringAttribute("jianjie", ""));
        tv_anli_one.setText(message.getStringAttribute("anli_one", ""));
        tv_anli_two.setText(message.getStringAttribute("anli_two", ""));
        Log.v("tv_name","------"+tv_name);
        if (!TextUtils.isEmpty(sex)) {
            switch (sex) {
                case "1":
                    iv_xingbie.setBackgroundResource(R.drawable.xingbie_boy);
                    break;
                case "2":
                    iv_xingbie.setBackgroundResource(R.drawable.xingbie_gril);
                    break;
            }
        }
    }
}
