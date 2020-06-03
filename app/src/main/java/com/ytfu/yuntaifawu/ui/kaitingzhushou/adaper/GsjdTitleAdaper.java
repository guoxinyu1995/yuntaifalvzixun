package com.ytfu.yuntaifawu.ui.kaitingzhushou.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.GsjdBean;
import com.ytfu.yuntaifawu.utils.MyItemDecoration;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Auther gxy
 * @Date 2019/11/27
 * @Des 工伤鉴定title
 */
public class GsjdTitleAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<GsjdBean.ListBeanX> mList;

    public GsjdTitleAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<GsjdBean.ListBeanX> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_gsjd_title, parent, false);
        return new GsjdTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GsjdTitleViewHolder viewHolder = (GsjdTitleViewHolder) holder;
        GsjdBean.ListBeanX listBeanX = mList.get(position);
        viewHolder.tvGsjdTitle.setText(listBeanX.getName());
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        viewHolder.recycleGsjdItem.addItemDecoration(new MyItemDecoration2(0f,0f));
        viewHolder.recycleGsjdItem.setLayoutManager(layoutManager);
        GsjdListAdaper listAdaper = new GsjdListAdaper(mContext);
        viewHolder.recycleGsjdItem.setAdapter(listAdaper);
        if(listBeanX.getList() != null || !listBeanX.getList().isEmpty()){
            listAdaper.setmList(listBeanX.getList());
        }
        listAdaper.setGsjdListItemClickListener(new GsjdListAdaper.IGsjdListClickListener() {
            @Override
            public void onGsjdListItemClickListener(int pos,String url) {
                if(gsjdTitleClickListener!=null){
                    gsjdTitleClickListener.onGsjdTitleItemClickListener(position,url);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class GsjdTitleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_gsjd_title)
        TextView tvGsjdTitle;
        @BindView(R.id.recycle_gsjd_item)
        RecyclerView recycleGsjdItem;

        public GsjdTitleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //定义接口
    private IGsjdTitleClickListener gsjdTitleClickListener;
    public void setGsjdTitleItemClickListener(IGsjdTitleClickListener gsjdTitleClickListener){
        this.gsjdTitleClickListener = gsjdTitleClickListener;
    }
    public interface IGsjdTitleClickListener{
        void onGsjdTitleItemClickListener(int pos,String url);
    }
}
