package com.ytfu.yuntaifawu.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBLvshiHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "lvshi.db";
    public static final int DB_VERSION = 1;
    public static final String TEBLE_NAME = "Lvshi";
    public static final String LVSHI_ID="lid";
    public static final String LVSHI_NAME = "name";
    public static final String LVSHI_PICURL="picurl";
    public static final String LVSHI_HUASHU = "huashu";
    public static final String LVSHI_TYPE= "type";
    private static SQLiteDatabase db;
    private SQLiteDatabase mDataBase = null;
    private static DBLvshiHelper instance;

    private DBLvshiHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 建表
        String sql = "create table " +
                TEBLE_NAME +"(" +
                LVSHI_ID + " varchar(225) primary key," +
                LVSHI_NAME + " varchar(225)," +
                LVSHI_PICURL+" varchar(225),"+
                LVSHI_HUASHU+" varchar(225),"+
                LVSHI_TYPE+" varchar(225)"
                + ")";
        db.execSQL(sql);
    }

    public static DBLvshiHelper getInstance(Context context){
        if(instance == null){
            synchronized (DBLvshiHelper.class){
                if(instance == null){
                    instance = new DBLvshiHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public synchronized void close() {
        if(mDataBase!=null){
            mDataBase.close();
        }
        super.close();
    }
    public SQLiteDatabase getDb() {
        if (db == null) {
            db = getWritableDatabase();
        }
        return db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
