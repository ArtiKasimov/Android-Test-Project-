package com.misha.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.text.TextUtils;

import com.example.arturkasymov.application_a.DBHandler;

public class MyContentProvider extends ContentProvider {

    private static final String AUTHORITY =
            "com.misha.database.provider.MyContentProvider";
    private static final String PRODUCTS_TABLE = "refs";
    private static final int PRODUCTS = 1;
    private static final int PRODUCTS_ID = 2;
    private DBHandler dbHandler;

    private static final UriMatcher sURIMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE, PRODUCTS);
        sURIMatcher.addURI(AUTHORITY, PRODUCTS_TABLE + "/#",
                PRODUCTS_ID);
    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        dbHandler = new DBHandler(getContext());
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = dbHandler.getWritableDatabase();
        int rowsDeleted = 0;

        switch (uriType) {
            case PRODUCTS:
                rowsDeleted = sqlDB.delete(DBHandler.getTableName(),
                        selection,
                        selectionArgs);
                break;

            case PRODUCTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(DBHandler.getTableName(),
                            DBHandler.getID() + "=" + id,
                            null);
                } else {
                    rowsDeleted = sqlDB.delete(DBHandler.getTableName(),
                            DBHandler.getID() + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        return new String(); // not needed
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = dbHandler.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case PRODUCTS:
                id = sqlDB.insert(DBHandler.getTableName(),
                        null, values);
                break;
        }
        //getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(PRODUCTS_TABLE + "/" + id);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DBHandler.getTableName());

        int uriType = sURIMatcher.match(uri);

        switch (uriType) {
            case PRODUCTS_ID:
                queryBuilder.appendWhere(DBHandler.getID() + "="
                        + uri.getLastPathSegment());
                break;
            case PRODUCTS:
                break;
        }

        Cursor cursor = queryBuilder.query(dbHandler.getReadableDatabase(),
                projection, selection, selectionArgs, null, null,
                sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),
                uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = dbHandler.getWritableDatabase();
        int rowsUpdated = 0;

        switch (uriType) {
            case PRODUCTS:
                rowsUpdated =
                        sqlDB.update(DBHandler.getTableName(),
                                values,
                                selection,
                                selectionArgs);
                break;
            case PRODUCTS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =
                            sqlDB.update(DBHandler.getTableName(),
                                    values,
                                    DBHandler.getID() + "=" + id,
                                    null);
                } else {
                    rowsUpdated =
                            sqlDB.update(DBHandler.getTableName(),
                                    values,
                                    DBHandler.getID() + "=" + id
                                            + " and "
                                            + selection,
                                    selectionArgs);
                }
                break;
        }
        //getContext().getContentResolver().notifyChange(uri, null);// massage need only after deleting
        return rowsUpdated;
    }
}
