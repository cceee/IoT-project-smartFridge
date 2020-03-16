package com.iot.myfridge.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.bumptech.glide.util.Util;
import com.iot.myfridge.data.CurrentGood;
import com.iot.myfridge.data.Good;
import com.iot.myfridge.data.HistoryGood;
import com.iot.myfridge.utils.DataUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // all static variables
    // database version
    private static final int DATABAE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "fridgeDB";

    // currentGoods table name
    private static final String TABLE_Goods = "Goods";

    // Goods table fields
    private static final String Goods_Cid = "id";
    private static final String Goods_Name = "name";
    private static final String Goods_Label = "label";
    private static final String Goods_Calorie = "calorie";
    private static final String Goods_Icon = "icon";

    // currentGoods table name
    private static final String TABLE_CurrentGoods = "Current_Goods";

    // currentGoods table fields
    private static final String CurrentGoods_Cid = "Cid";
    private static final String CurrentGoods_Name = "name";
    private static final String CurrentGoods_StoreDate = "storeDate";
    private static final String CurrentGoods_ExpireDate = "expireDate";
    private static final String CurrentGoods_Quantity = "quantity";
    private static final String CurrentGoods_Calories = "calories";
    private static final String CurrentGoods_Label = "label";
    // we can use name to find out calorie per quantity, and icon, label in TABLE_GOODS

    // historyGoods table name
    private static final String TABLE_HistoryGoods = "History_Goods";

    // historyGoods table fields
    private static final String HistoryGoods_Hid = "Hid";
    private static final String HistoryGoods_Name = "name";
    private static final String HistoryGoods_EatenDate = "eatenDate";
    private static final String HistoryGoods_Quantity = "quantity";
    private static final String HistoryGoods_Calories = "calories";
    private static final String HistoryGoods_Label = "label";
    // we can use name to find out calorie per quantity, and icon, label in TABLE_GOODS

    DataUtil dataUtil = new DataUtil(this);
    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABAE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String CREATE_GOODS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_Goods + "("
                + Goods_Cid + " INTEGER PRIMARY KEY,"
                + Goods_Name + " VARCHAR(30),"
                + Goods_Label + " VARCHAR(20),"
                + Goods_Calorie + " INTEGER,"
                + Goods_Icon + " INTEGER" + ")";
        sqLiteDatabase.execSQL(CREATE_GOODS_TABLE);

        String CREATE_CURRENT_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_CurrentGoods + "("
                + CurrentGoods_Cid + " INTEGER PRIMARY KEY,"
                + CurrentGoods_Name + " VARCHAR(30),"
                + CurrentGoods_StoreDate + " DATETIME,"
                + CurrentGoods_ExpireDate + " DATETIME, "
                + CurrentGoods_Quantity + " INTEGER,"
                + CurrentGoods_Calories + " INTEGER,"
                + CurrentGoods_Label + " VARCHAR(20)" + ")";
        sqLiteDatabase.execSQL(CREATE_CURRENT_TABLE);

        String CREATE_HISTORY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_HistoryGoods + "("
                + HistoryGoods_Hid + " INTEGER PRIMARY KEY,"
                + HistoryGoods_Name + " VARCHAR(30),"
                + HistoryGoods_EatenDate + " DATETIME,"
                + HistoryGoods_Quantity + " INTEGER,"
                + HistoryGoods_Calories + " DATETIME,"
                + HistoryGoods_Label + " VARCHAR(20)" + ")";
        sqLiteDatabase.execSQL(CREATE_HISTORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Goods);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CurrentGoods);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HistoryGoods);
        // Create tables again
        onCreate(db);
    }

    // getter from TABLE_Goods using name
    public String getIcon(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String icon = "none";
        //todo
        return icon;
    }

    // basic methods for current goods
    public void insertCurrentGood(CurrentGood good){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CurrentGoods_Cid, good.getCid());
        values.put(CurrentGoods_Name, good.getName());
        values.put(CurrentGoods_StoreDate,(new SimpleDateFormat("yyyy-MM-DD").format(good.getStoreDate())));
        values.put(CurrentGoods_ExpireDate,(new SimpleDateFormat("yyyy-MM-DD").format(good.getExpDate())));
        values.put(CurrentGoods_Quantity,good.getQuantity());
        values.put(CurrentGoods_Calories,good.getCalories());
        values.put(CurrentGoods_Label, good.getLabel());
        // Inserting Row
        db.insert(TABLE_CurrentGoods, null, values);
        db.close(); // Closing database connection
    }

    public void deleteAllCurrentGoods(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_CurrentGoods);
        db.close();
    }

    public List<CurrentGood> getCurrentGoodsByLabel(String label) throws ParseException{
        List<CurrentGood> goodsList = new ArrayList<CurrentGood>();
        // Select All Query
        String selectQuery = "";
        selectQuery = "SELECT  * FROM " + TABLE_CurrentGoods+" where label = "+ label;
        //Log.d("selProducts","got it");

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (cursor.moveToFirst()) {
            do {
                CurrentGood good = new CurrentGood(cursor.getString(0));
                good.setName(cursor.getString(1));
                //good.setStoreDate(dataUtil.parseDates(cursor.getString(2)));
                good.setStoreDate(cursor.getString(2));
                good.setExpDate(dataUtil.parseDates((cursor.getString(3))));
                good.setQuantity(Integer.parseInt(cursor.getString(4)));
                good.setCalories(Integer.parseInt(cursor.getString(5)));
                good.setLabel(cursor.getString(6));
                goodsList.add(good);

            } while (cursor.moveToNext());
        }
        return goodsList;
    }

    // basic method for history goods
    public void insertHistoryGood(HistoryGood good) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HistoryGoods_Hid, good.getHid());
        values.put(HistoryGoods_Name, good.getName());
        values.put(HistoryGoods_EatenDate, (new SimpleDateFormat("yyyy-MM-DD").format(good.getEatenDate())));
        values.put(HistoryGoods_Quantity, good.getQuantity());
        values.put(HistoryGoods_Calories, good.getCalories());
        values.put(HistoryGoods_Label, good.getLabel());
        // Inserting Row
        db.insert(TABLE_HistoryGoods, null, values);
        db.close(); // Closing database connection
    }

    public void deleteAllHistoryGoods(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_HistoryGoods);
        db.close();
    }

    public List<HistoryGood> getHistoryGoodsByLabel(String label) throws ParseException{
        List<HistoryGood> goodsList = new ArrayList<HistoryGood>();
        // Select All Query
        String selectQuery = "";
        selectQuery = "SELECT  * FROM " + TABLE_HistoryGoods+" where label = "+ label;
        //Log.d("selProducts","got it");

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (cursor.moveToFirst()) {
            do {
                HistoryGood good = new HistoryGood(cursor.getString(0));
                good.setName(cursor.getString(1));
                //good.setEatenDate(dataUtil.parseDates((cursor.getString(2))));
                good.setEatenDate(cursor.getString(2));
                good.setQuantity(Integer.parseInt(cursor.getString(3)));
                good.setCalories(Integer.parseInt(cursor.getString(4)));
                good.setLabel(cursor.getString(6));
                goodsList.add(good);

            } while (cursor.moveToNext());
        }
        return goodsList;
    }

}
