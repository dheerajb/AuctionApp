package com.dheeraj.auctionapp.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import com.dheeraj.auctionapp.AuctionContract;

public class AuctionProvider extends ContentProvider {
    private static final String AUTHORITY = "com.dheeraj.auctionapp.auctionProvider";

    private static final String BASE_PATH = "auction_items";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);

    private AuctionDatabaseHelper dbHelper = null;

    public AuctionProvider() {
    }


    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
        long id = sqlDB.insert(AuctionContract.AuctionItem.TABLE_NAME, null, values);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public boolean onCreate() {
        dbHelper = new AuctionDatabaseHelper(getContext());

        return ((dbHelper == null) ? false : true);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();

        int rowsDeleted = sqlDB.delete(AuctionContract.AuctionItem.TABLE_NAME, selection,
                selectionArgs);
        return rowsDeleted;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        queryBuilder.setTables(AuctionContract.AuctionItem.TABLE_NAME);


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsUpdated = db.update(AuctionContract.AuctionItem.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        return rowsUpdated;

    }
}
