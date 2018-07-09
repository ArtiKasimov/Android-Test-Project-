package com.example.arturkasymov.application_a;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "history.db";
    private static final String TABLE_NAME = "refs";

    private static final String KEY_ID = "id";
    private static final String KEY_REFERENCE = "reference";
    private static final String KEY_STATUS = "status";
    private static final String KEY_TIME = "time";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_REFERENCE + " TEXT,"
                + KEY_STATUS + " INTEGER, "
                + KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRecord(Re_cord re_cord) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_REFERENCE, re_cord.getReference());
        values.put(KEY_STATUS, re_cord.getStatus());
        values.put(KEY_TIME,re_cord.getTime());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Re_cord> getAllRecords(boolean sorting) {
        List<Re_cord> recordList = new ArrayList<Re_cord>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        if(sorting){
            cursor = db.query(TABLE_NAME,new String[]{KEY_ID,
                    KEY_REFERENCE, KEY_STATUS, KEY_TIME},null,null,null,null,KEY_STATUS);}
        else{
            cursor = db.rawQuery(selectQuery, null);
        }


        if (cursor.moveToFirst()) {
            do {
                Re_cord re_cord = new Re_cord(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)),
                        cursor.getString(3));
                recordList.add(re_cord);
            } while (cursor.moveToNext());
        }
        return recordList;
    }

    public Re_cord getRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_ID,
                        KEY_REFERENCE, KEY_STATUS, KEY_TIME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Re_cord re_cord = new Re_cord(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                cursor.getString(3));

        return re_cord;
    }

    public int getRecordCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    public int updateRecord(Re_cord re_cord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REFERENCE, re_cord.getReference());
        values.put(KEY_STATUS, re_cord.getStatus());
        values.put(KEY_TIME, re_cord.getTime());

        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(re_cord.getId())});
    }

    public void deleteRecord(Re_cord re_cord) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(re_cord.getId()) });
        db.close();
    }


    public static String getTableName() {
        return TABLE_NAME;
    }
    public static String getID() {
        return KEY_ID;
    }
}