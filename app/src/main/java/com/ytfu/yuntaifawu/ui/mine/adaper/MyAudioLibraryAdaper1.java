package com.ytfu.yuntaifawu.ui.mine.adaper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.exoplayer2.ui.DefaultTimeBar;
import com.google.android.exoplayer2.ui.TimeBar;
import com.google.android.exoplayer2.util.Util;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.home.activity.ActivityAudioDetails;
import com.ytfu.yuntaifawu.ui.mine.audio.AudioController;
import com.ytfu.yuntaifawu.ui.mine.bean.MyLibraryBean;

import java.util.List;

public class MyAudioLibraryAdaper1
        extends BaseQuickAdapter<MyLibraryBean.ListBean, BaseViewHolder>
        implements AudioController.AudioControlListener, OnItemChildClickListener, LoadMoreModule {
    private AudioController mAudioControl;
    private boolean playNClickIsSame;
    private Context mContext;

    public MyAudioLibraryAdaper1(Context context, AudioController controller, @Nullable List<MyLibraryBean.ListBean> data) {
        super(R.layout.item_my_audio_library, data);
        this.mContext = context;
        mAudioControl = controller;
        mAudioControl.setOnAudioControlListener(this);
        setOnItemChildClickListener(this);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyLibraryBean.ListBean item) {
        TextView tv_title_name = helper.getView(R.id.tv_title_name);
        TextView tv_date = helper.getView(R.id.tv_date);
        TextView tv_script = helper.getView(R.id.tv_script);
        ImageView iv_play = helper.getView(R.id.iv_play);
        ImageView iv_pause = helper.getView(R.id.iv_pause);
        TextView tv_start_date = helper.getView(R.id.tv_start_date);
        DefaultTimeBar timeBar = helper.getView(R.id.exo_progress);
        TextView tv_end_date = helper.getView(R.id.tv_end_date);
        RelativeLayout rl_audio = helper.getView(R.id.rl_audio);
        if (item.isPlayStatus()) {
            iv_play.setVisibility(View.INVISIBLE);
            iv_pause.setVisibility(View.VISIBLE);
        } else {
            iv_play.setVisibility(View.VISIBLE);
            iv_pause.setVisibility(View.INVISIBLE);
        }

//        helper.addOnClickListener(R.id.iv_play);
//        helper.addOnClickListener(R.id.iv_pause);

        tv_start_date.setText("00:00");
        tv_end_date.setText("00:00");
        tv_title_name.setText(item.getName());
        tv_date.setText(item.getDate());
        tv_script.setText(item.getDescript());
        timeBar.setDuration(item.getDuration());
        timeBar.setPosition(0L);
        timeBar.addListener(new TimeBar.OnScrubListener() {
            @Override
            public void onScrubStart(TimeBar timeBar, long position) {

            }

            @Override
            public void onScrubMove(TimeBar timeBar, long position) {
                if (tv_start_date != null) {
                    if (mAudioControl.getPosition() == getData().indexOf(item)) {
                        tv_start_date.setText(Util.getStringForTime(mAudioControl.getFormatBuilder(),
                                mAudioControl.getFormatter(), position));
                    } else {
                        timeBar.setPosition(0);
                    }
                }
            }

            @Override
            public void onScrubStop(TimeBar timeBar, long position, boolean canceled) {
                if (mAudioControl != null) {
                    if (mAudioControl.getPosition() == getData().indexOf(item)) {
                        mAudioControl.seekToTimeBarPosition(position);
                    } else {
                        timeBar.setPosition(0);
                    }
                }
            }
        });
        rl_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtil.showToast(item.getYid());
                Intent intent = new Intent(mContext, ActivityAudioDetails.class);
                intent.putExtra("id", item.getYid());
                mContext.startActivity(intent);
                if (playNClickIsSame) {
                    mAudioControl.onPause();
                }
            }
        });
    }

    @Override
    public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
        playNClickIsSame = playNClickIsSame(mAudioControl.getPosition(), position);
        switch (view.getId()) {
            case R.id.iv_play:
                initStatus(mAudioControl.getPosition(), position);
                mAudioControl.onPrepare(getData().get(position).getUrl());
                mAudioControl.onStart(position);
                break;
            case R.id.iv_pause:
                if (playNClickIsSame) {
                    mAudioControl.onPause();
                }
                break;
        }
    }

    private void initStatus(int position, int position1) {
        MyLibraryBean.ListBean listBean = getData().get(position);
        listBean.setPlayStatus(false);
        listBean.setStartTime("00:00");
        if (position >= ((LinearLayoutManager) getRecyclerView().getLayoutManager())
                .findFirstVisibleItemPosition()
                && position <= ((LinearLayoutManager) getRecyclerView().getLayoutManager())
                .findLastVisibleItemPosition()) {
            if (getViewByPosition(position,
                    R.id.exo_progress) != null) {
                DefaultTimeBar timeBar = (DefaultTimeBar) getViewByPosition(
                        position,
                        R.id.exo_progress);
                timeBar.setPosition(0);
                timeBar.setBufferedPosition(0);
            }
            if (getViewByPosition(position,
                    R.id.tv_start_date) != null) {
                TextView startTime = (TextView) getViewByPosition(position,
                        R.id.tv_start_date);
                startTime.setText(listBean.getStartTime());
            }
            if (getViewByPosition(position, R.id.iv_play) != null) {
                ImageView oldplay = (ImageView) getViewByPosition(position,
                        R.id.iv_play);
                oldplay.setVisibility(View.VISIBLE);
            }
            if (getViewByPosition(position,
                    R.id.iv_pause) != null) {
                ImageView oldpause = (ImageView) getViewByPosition(position,
                        R.id.iv_pause);
                oldpause.setVisibility(View.INVISIBLE);
            }
            if (getViewByPosition(position1, R.id.iv_play) != null) {
                ImageView newplay = (ImageView) getViewByPosition(position1,
                        R.id.iv_play);
                newplay.setVisibility(View.INVISIBLE);
            }
            if (getViewByPosition(position1, R.id.iv_pause) != null) {
                ImageView onewpause = (ImageView) getViewByPosition(position1,
                        R.id.iv_pause);
                onewpause.setVisibility(View.VISIBLE);
            }
        } else {
            notifyItemChanged(position);
        }
    }

    private boolean playNClickIsSame(int position, int position1) {
        return position == position1 ? true : false;
    }

    @Override
    public void setCurPositionTime(int position, long curPositionTime) {
        if (getViewByPosition(position,
                R.id.exo_progress) != null) {
            DefaultTimeBar timeBar = (DefaultTimeBar) getViewByPosition(position,
                    R.id.exo_progress);
            timeBar.setPosition(curPositionTime);
        }
    }

    @Override
    public void setDurationTime(int position, long durationTime) {
        if (getViewByPosition(position, R.id.exo_progress) != null) {
            DefaultTimeBar timeBar = (DefaultTimeBar) getViewByPosition(position,
                    R.id.exo_progress);
            timeBar.setDuration(durationTime);
        }
    }

    @Override
    public void setBufferedPositionTime(int position, long bufferedPosition) {
        if (getViewByPosition(position,
                R.id.exo_progress) != null) {
            DefaultTimeBar timeBar = (DefaultTimeBar) getViewByPosition(position,
                    R.id.exo_progress);
            timeBar.setBufferedPosition(bufferedPosition);
        }
    }

    @Override
    public void setCurTimeString(int position, String curTimeString) {
        if (getViewByPosition(position, R.id.tv_start_date) != null) {
            TextView startTime = (TextView) getViewByPosition(position,
                    R.id.tv_start_date);
            startTime.setText(curTimeString);
        }
        MyLibraryBean.ListBean listBean = getData().get(position);
        listBean.setStartTime(curTimeString);
    }

    @Override
    public void isPlay(int position, boolean isPlay) {
        MyLibraryBean.ListBean listBean = getData().get(position);
        listBean.setPlayStatus(isPlay);
        if (getViewByPosition(position, R.id.iv_play) != null
                && getViewByPosition(position, R.id.iv_pause) != null) {
            ImageView play = (ImageView) getViewByPosition(position, R.id.iv_play);
            ImageView pause = (ImageView) getViewByPosition(position,
                    R.id.iv_pause);
            if (isPlay) {
                if (play != null) {
                    play.setVisibility(View.INVISIBLE);
                }
                if (pause != null) {
                    pause.setVisibility(View.VISIBLE);
                }


            } else {
                if (play != null) {
                    play.setVisibility(View.VISIBLE);
                }
                if (pause != null) {
                    pause.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public void setDurationTimeString(int position, String durationTimeString) {
        if (getViewByPosition(position,
                R.id.tv_end_date) != null) {
            TextView endTime = (TextView) getViewByPosition(position,
                    R.id.tv_end_date);
            endTime.setText(durationTimeString);
        }
        MyLibraryBean.ListBean listBean = getData().get(position);
        listBean.setEndTime(durationTimeString);
    }


}
