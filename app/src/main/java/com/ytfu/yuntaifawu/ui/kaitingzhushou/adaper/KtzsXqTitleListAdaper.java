package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.custom.ParentRecyclerView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsXqListBean;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/24
 * @Des 开庭助手title详情适配器
 */
public class KtzsXqTitleListAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<KtzsXqListBean.ListBean> mList;

    public KtzsXqTitleListAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setList(List<KtzsXqListBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ktzs_xq_list_title, parent, false);
        return new KtzsXqListTitleViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        KtzsXqListTitleViewHolder  viewHolder = (KtzsXqListTitleViewHolder) holder;
        KtzsXqListBean.ListBean listBean = mList.get(position);
        viewHolder.tvKtzsXqListTitle.setText(position+1+"、"+listBean.getName());
        KtzsXqListAdaper xqListAdaper = new KtzsXqListAdaper(mContext);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        viewHolder.recycleKtzsListItem.addItemDecoration(new MyItemDecoration2(0f,0f));
        viewHolder.recycleKtzsListItem.setLayoutManager(layoutManager);
        viewHolder.recycleKtzsListItem.setAdapter(xqListAdaper);
        xqListAdaper.setList(listBean.getSon());
        xqListAdaper.setXqItemClickListener(new KtzsXqListAdaper.IXqListClickListener() {
            @Override
            public void onXqListItemClickListener(String url) {
                if(xqTitleClickListener !=null){
                    xqTitleClickListener.onXqTitleItemClickListenet(url);
                }
            }
        });
        xqListAdaper.setXqJumpItemClickListener(new KtzsXqListAdaper.IXqJumpListClickListener() {
            @Override
            public void onXqListItemClickListener(String name, String id) {
                if(xqJmTitleClickListener!=null){
                    xqJmTitleClickListener.onXqTitleItemClickListenet(name,id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class KtzsXqListTitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_ktzs_xq_list_title)
        TextView tvKtzsXqListTitle;
        @BindView(R.id.recycle_ktzs_list_item)
        RecyclerView recycleKtzsListItem;
        public KtzsXqListTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //定义接口
    private IXqTitleClickListener xqTitleClickListener;
    public void setXqItemClickListener(IXqTitleClickListener xqTitleClickListener){
        this.xqTitleClickListener = xqTitleClickListener;
    }
    public interface IXqTitleClickListener{
        void onXqTitleItemClickListenet(String url);
    }
    //定义接口
    private IXqJmTitleClickListener xqJmTitleClickListener;
    public void setXqJmItemClickListener(IXqJmTitleClickListener xqJmTitleClickListener){
        this.xqJmTitleClickListener = xqJmTitleClickListener;
    }
    public interface IXqJmTitleClickListener{
        void onXqTitleItemClickListenet(String name,String id);
    }
}
