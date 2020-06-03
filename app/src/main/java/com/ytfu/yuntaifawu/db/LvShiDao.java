package com.ytfu.yuntaifawu.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;

import java.util.ArrayList;
import java.util.List;

public class LvShiDao {
    private static LvShiDao instance;
    private Context mContext;
    private ConversationBean.MsgBean msgBean;

    public LvShiDao(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 获取数据库实例
     */
    public static LvShiDao getInstance(Context context) {
        if (instance == null) {
            synchronized (LvShiDao.class) {
                if (instance == null) {
                    instance = new LvShiDao(context);
                }
            }
        }
        return instance;
    }

    public void lvShiAdd(String lid, String name, String picurl, String huashu, String type) {
        DBLvshiHelper dbLvshiHelper = DBLvshiHelper.getInstance(mContext);
        SQLiteDatabase db = dbLvshiHelper.getDb();
        if (!db.isOpen()) {
            db.beginTransaction();
            db.setTransactionSuccessful();
        }
        ContentValues values = new ContentValues();
        values.put("lid", lid);
        values.put("name", name);
        values.put("picurl", picurl);
        values.put("huashu", huashu);
        values.put("type", type);
        long num = db.replace(DBLvshiHelper.TEBLE_NAME, null, values);
        System.out.print("----------du" + num + "\n");
        values.clear();
    }

    public List<ConversationBean.MsgBean> lvshiSelect() {
        List<ConversationBean.MsgBean> beanList = new ArrayList<>();
        DBLvshiHelper dbLvshiHelper = DBLvshiHelper.getInstance(mContext);
        SQLiteDatabase db = dbLvshiHelper.getDb();
        Cursor cursor = db.query("Lvshi", null, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String lid = cursor.getString(cursor.getColumnIndex("lid"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String picurl = cursor.getString(cursor.getColumnIndex("picurl"));
                String huashu = cursor.getString(cursor.getColumnIndex("huashu"));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                msgBean = new ConversationBean.MsgBean(lid, name, picurl, huashu, type);
                beanList.add(msgBean);
            }
        }
        cursor.close();
        return beanList;
    }

    public ConversationBean.MsgBean selectById(String userId) {
        DBLvshiHelper dbLvshiHelper = DBLvshiHelper.getInstance(mContext);
        SQLiteDatabase db = dbLvshiHelper.getDb();
        Cursor cursor = db.query("Lvshi", null,
                "lid=?", new String[]{userId.toLowerCase()}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String lid = cursor.getString(cursor.getColumnIndex("lid"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String picurl = cursor.getString(cursor.getColumnIndex("picurl"));
                String huashu = cursor.getString(cursor.getColumnIndex("huashu"));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                msgBean = new ConversationBean.MsgBean(lid, name, picurl, huashu, type);
            }
            cursor.close();
        }
        return msgBean;
    }

    public ConversationBean.MsgBean lvshiSelectByID(String id) {
        DBLvshiHelper helper = DBLvshiHelper.getInstance(mContext);
        SQLiteDatabase db = helper.getDb();
        Cursor cursor = db.query("Lvshi", null, "lid='" + id + "'", null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String lid = cursor.getString(cursor.getColumnIndex("lid"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String picurl = cursor.getString(cursor.getColumnIndex("picurl"));
                String huashu = cursor.getString(cursor.getColumnIndex("huashu"));
                String type = cursor.getString(cursor.getColumnIndex("type"));
                msgBean = new ConversationBean.MsgBean(lid, name, picurl, huashu, type);
            }
        }
        cursor.close();
        return msgBean;
    }
}
