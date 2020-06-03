package com.ytfu.yuntaifawu.ui.mine.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Util;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.mine.audio.AudioController;
import com.ytfu.yuntaifawu.ui.mine.bean.MyLibraryBean;

import java.util.ArrayList;
import java.util.List;

public class MyAudioLibraryAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AudioController.AudioControlListener, AdapterView.OnItemClickListener {
    private Context mContext;
    private List<MyLibraryBean.ListBean> mList;
    private AudioController mAudioControl;

    public MyAudioLibraryAdaper(Context mContext, AudioController control) {
        this.mContext = mContext;
        this.mAudioControl = control;
        mList = new ArrayList<>();
    }

    public void setmList(List<MyLibraryBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_audio_library, parent, false);
        return new MyLibraryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyLibraryViewHolder viewHolder = (MyLibraryViewHolder) holder;
        MyLibraryBean.ListBean listBean = mList.get(position);
        viewHolder.tv_title_name.setText(listBean.getName());
        viewHolder.tv_date.setText(listBean.getDate());
        viewHolder.tv_script.setText(listBean.getDescript());
        viewHolder.timeBar.addListener(new TimeBar.OnScrubListener() {
            @Override
            public void onScrubStart(TimeBar timeBar, long position) {

            }

            @Override
            public void onScrubMove(TimeBar timeBar, long position) {
                if (viewHolder.tv_start_date != null) {
                    if(mAudioControl.getPosition() == mList.indexOf(listBean)){
                        viewHolder.tv_start_date.setText(Util.getStringForTime(mAudioControl.getFormatBuilder(),
                                mAudioControl.getFormatter(), position));
                    }
                }else{
                    timeBar.setPosition(0);
                }
            }

            @Override
            public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
                if (mAudioControl != null) {
                    if (mAudioControl.getPosition() == mList.indexOf(listBean)) {
                        mAudioControl.seekToTimeBarPosition(position);
                    } else {
                        timeBar.setPosition(0);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void setCurPositionTime(int position, long curPositionTime) {
//        if (getViewByPosition(getRecyclerView(), position,
//                R.id.exo_progress) != null) {
//            DefaultTimeBar timeBar = (DefaultTimeBar) getViewByPosition(getRecyclerView(), position,
//                    R.id.exo_progress);
//            timeBar.setPosition(curPositionTime);
//        }
    }

    @Override
    public void setDurationTime(int position, long durationTime) {

    }

    @Override
    public void setBufferedPositionTime(int position, long bufferedPosition) {

    }

    @Override
    public void setCurTimeString(int position, String curTimeString) {

    }

    @Override
    public void isPlay(int position, boolean isPlay) {

    }

    @Override
    public void setDurationTimeString(int position, String durationTimeString) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public class MyLibraryViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title_name, tv_date, tv_script, tv_start_date, tv_end_date;
        public ImageView iv_play;
        public DefaultTimeBar timeBar;

        public MyLibraryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title_name = itemView.findViewById(R.id.tv_title_name);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_script = itemView.findViewById(R.id.tv_script);
            iv_play = itemView.findViewById(R.id.iv_play);
            tv_start_date = itemView.findViewById(R.id.tv_start_date);
            timeBar = itemView.findViewById(R.id.exo_progress);
            tv_end_date = itemView.findViewById(R.id.tv_end_date);

        }
    }
}
