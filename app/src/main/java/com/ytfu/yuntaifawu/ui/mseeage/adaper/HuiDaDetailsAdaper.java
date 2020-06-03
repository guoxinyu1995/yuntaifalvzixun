package com.ytfu.yuntaifawu.ui.mseeage.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;

import java.util.ArrayList;
import java.util.List;

public class HuiDaDetailsAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<String> list;

    public HuiDaDetailsAdaper(Context mContext) {
        this.mContext = mContext;
        list = new ArrayList<>();
    }

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.huida_details_item, parent, false);
        return new HuiDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HuiDetailsViewHolder extends RecyclerView.ViewHolder {

        public HuiDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
