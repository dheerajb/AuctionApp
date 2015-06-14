package com.dheeraj.auctionapp.database.provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AuctionDatabaseHelper extends SQLiteOpenHelper
{
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "auctions.db";

    public AuctionDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Cursor c = sqLiteDatabase.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='auctions'", null);

        try {
            if (true) {

                /*Constructing a dummy Auctions db which we will use for our App*/
                AuctionContract.AuctionItemTable.createTable(sqLiteDatabase);
                AuctionContract.UserTable.createTable(sqLiteDatabase);

            }
        }catch(Exception e){

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS constants");
        onCreate(sqLiteDatabase);
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public void setWriteAheadLoggingEnabled(boolean enabled) {
        super.setWriteAheadLoggingEnabled(enabled);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
