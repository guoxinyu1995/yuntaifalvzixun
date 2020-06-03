package com.ytfu.yuntaifawu.ui.mine.adaper;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shy.poi.word2html.BasicSet;
import com.shy.poi.word2html.WordUtils;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityPreview;
import com.ytfu.yuntaifawu.ui.mine.bean.MyLibraryBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/19
*
*  @Des  我的合同库
*
*/
public class MyCollectionLibraryAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MyLibraryBean.ListBean> mList;

    public MyCollectionLibraryAdaper(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<MyLibraryBean.ListBean> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_collect_library, parent, false);
        return new MyLibraryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyLibraryViewHolder viewHolder = (MyLibraryViewHolder) holder;
        MyLibraryBean.ListBean listBean = mList.get(position);
        viewHolder.tvTilte.setText(listBean.getName());
        viewHolder.tvYulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ActivityPreview.class);
                intent.putExtra("url", listBean.getUrl());
                mContext.startActivity(intent);
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                Uri content_url = Uri.parse(listBean.getUrl());
//                intent.setData(content_url);
//                mContext.startActivity(intent);
//                getIntents(listBean.getUrl());
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String sourceFilePath = listBean.getUrl();
//                        String htmlFilePath = Environment.getExternalStorageDirectory() + "/Pictures/html";
//                        String htmlFileName = "picTextDoc";
//                        BasicSet basicSet = new BasicSet(
//                                mContext,
//                                sourceFilePath,//源文件
//                                htmlFilePath,//保存后的文件路径
//                                htmlFileName);//保存后的文件名称
//                        //BasicSet 基础设置
//                        //可以修改标签样式等 具体看BasicSet的属性
//                        //如：取消网页自适应手机屏幕
//                        //String htmlBegin =
//                        //        "<!DOCTYPE html>" +
//                        //                "<html>" +
//                        //                "<head>" +
//                        //                "</head>" +
//                        //                "<body>";
//                        //basicSet.setHtmlBegin(htmlBegin);
//                        String htmlSavePath = WordUtils.getInstance(basicSet).word2html();
//                        //跳转到webview界面预览转换html文件
//                        Intent i = new Intent(mContext, ActivityPreview.class);
//                        i.putExtra("path", htmlSavePath);
//                        mContext.startActivity(i);
//                    }
//                }).start();
            }
        });
        viewHolder.tvXiazai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(libraryClickListener!=null){
                    libraryClickListener.libraryItemClickListener(listBean.getUrl());
                }
            }
        });
    }

    private void getIntents(String url) {
        Intent intent = new Intent();

        intent.setAction("android.intent.action.VIEW");

        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        Uri contentUri = Uri.parse(url);

        intent.setData(contentUri);

        if (hasbrowser(mContext, intent)) {

            intent.setComponent(new ComponentName("com.android.browser", "com.android.browser.BrowserActivity"));

            mContext.startActivity(intent);
        }
    }

    public boolean hasbrowser(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        for (ResolveInfo resolve : list) {
            if (resolve.activityInfo.packageName.contains("com.android.browser")) {
                return true;
            }
        }
        return false;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyLibraryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_xiazai)
        TextView tvXiazai;
        @BindView(R.id.tv_yulan)
        TextView tvYulan;
        @BindView(R.id.tv_tilte)
        TextView tvTilte;

        public MyLibraryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //定义接口
    private CollectionLibraryClickListener libraryClickListener;
    public void setLibraryClickListener(CollectionLibraryClickListener libraryClickListener){
        this.libraryClickListener = libraryClickListener;
    }
    public interface CollectionLibraryClickListener{
        void libraryItemClickListener(String url);
    }
}
