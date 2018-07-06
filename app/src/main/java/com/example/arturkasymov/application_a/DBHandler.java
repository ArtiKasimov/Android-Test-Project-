package com.example.arturkasymov.application_a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "history.db";
    // Contacts table name
    private static final String TABLE_NAME = "references";

    //Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_REFERENCE = "reference";
    private static final String KEY_STATUS = "status";
    private static final String KEY_TIME = "time";

    public SQLiteDatabase db;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_REFERENCE + " TEXT, "
                + KEY_STATUS + " INTEGER, "
                + KEY_TIME + " INTEGER"
                + ");");
        //ContentValues contentValues = new ContentValues();
        //contentValues.put("id", 0);
        //contentValues.put("reference", "fsfsdfd");
        //contentValues.put("status",1);
        //contentValues.put("time", 0);
        //db.insert("position", null, contentValues);

        //contentValues.clear();
        //contentValues.put("id", 1);
        //contentValues.put("reference", "dsadadasdasdasfsfsdfd");
        //contentValues.put("status",1);
        //contentValues.put("time", 0);
        //db.insert("position", null, contentValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
// Creating tables again
        //       onCreate(db);
    }

    // Adding Re_cord
    public void addRe_cord(Re_cord record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REFERENCE, record.getReference());
        values.put(KEY_STATUS, record.getStatus());
        values.put(KEY_TIME, record.getTime());
        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }

    // Getting one Re_cord
    public Re_cord getRe_cord(int id) {
        //SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
        //                KEY_REFERENCE, KEY_STATUS, KEY_TIME }, KEY_ID + " = ?",
         //       new String[] { String.valueOf(id) }, null, null, null, null);



        //if (cursor != null)
        //    cursor.moveToFirst();
        //Re_cord record = new Re_cord(//Integer.parseInt(cursor.getString(0)),
        //        cursor.getString(1), Integer.parseInt(cursor.getString(2)),
        //        Integer.parseInt(cursor.getString(3)));
        Re_cord record = new Re_cord("fsfsadasd", 1, 0);
        // return shop
        return record;
    }

    // Getting All Re_cord
    public List<Re_cord> getAllRe_cords() {
        List<Re_cord> recordList = new ArrayList<Re_cord>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        //SQLiteDatabase db = this.getWritableDatabase();
        //SQLiteDatabase dbt = this.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_NAME, new String[]{KEY_REFERENCE,KEY_STATUS,KEY_TIME},null,null,null,null, null);
        // looping through all rows and adding to list
        /*if (cursor.moveToFirst()) {
            do {
                Re_cord record = new Re_cord(//Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3)));
                // Adding contact to list
                recordList.add(record);
            } while (cursor.moveToNext());
        }*/
        // return contact list
        return recordList;
    }

    // Getting Re_cord Count
    public int getRe_cordCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    // Updating a Re_cord
    public int updateRe_cord(Re_cord record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REFERENCE, record.getReference());
        values.put(KEY_STATUS, record.getStatus());
        values.put(KEY_TIME, record.getTime());
        // updating row
        return db.update(TABLE_NAME, values, KEY_ID + " = ?",
                new String[]{String.valueOf(record.getId())});
    }

    // Deleting a Re_cord
    public void deleteRe_cord(Re_cord record) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_ID + " = ?",
                new String[] { String.valueOf(record.getId()) });
        db.close();
    }

    public  void createDataBase(Context context){
        SQLiteDatabase db = context.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_REFERENCE + " TEXT,"
                + KEY_STATUS + " INTEGER," + KEY_TIME + " INTEGER)");
    }
}