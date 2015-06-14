package com.dheeraj.auctionapp.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class AuctionProvider extends ContentProvider {
    private static final String LOGTAG = Class.class.getSimpleName();
    private static final String AUTHORITY = "com.dheeraj.auctionapp.auctionProvider";

    private static final String BASE_PATH_BID_ITEMS = "auction_items";
    private static final String BASE_PATH_BIDS = "bids";
    private static final String BASE_PATH_CREDENTIALS = "credentials";

    public static final Uri CONTENT_URI_BIDITEMS = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH_BID_ITEMS);
    public static final Uri CONTENT_URI_BIDS = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH_BIDS);
    public static final Uri CONTENT_URI_CREDENTIALS = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH_CREDENTIALS);

    private AuctionDatabaseHelper dbHelper = null;

    public AuctionProvider() {
    }

    private static final int ITEMS_BASE = 0x0000;
    private static final int BID_BASE = 0x1000;
    private static final int USER_BASE = 0x2000;

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);

    static {

        sURIMatcher.addURI(AUTHORITY, BASE_PATH_BID_ITEMS, ITEMS_BASE);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH_BIDS, BID_BASE);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH_CREDENTIALS, USER_BASE);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new AuctionDatabaseHelper(getContext());

        return ((dbHelper == null) ? false : true);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();


        int match = 0;
        try {
            match = findMatch(uri, "query");
        } catch (IllegalArgumentException e) {

        }
        switch (match) {
            case ITEMS_BASE:
                queryBuilder.setTables(AuctionContract.AuctionItemTable.TABLE_NAME);
                break;
            case BID_BASE:
                queryBuilder.setTables(AuctionContract.UserBidTable.TABLE_NAME);
                break;
            case USER_BASE:
                queryBuilder.setTables(AuctionContract.UserTable.TABLE_NAME);
                break;
        }


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection,
                selectionArgs, null, null, sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        String tableName = null;
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int match = 0;
        try {
            match = findMatch(uri, "update");
        } catch (IllegalArgumentException e) {

        }
        switch (match) {
            case ITEMS_BASE:
                tableName = AuctionContract.AuctionItemTable.TABLE_NAME;
                break;
            case BID_BASE:
                break;
            case USER_BASE:
                break;
        }
        int rowsUpdated = db.update(tableName,
                values,
                selection,
                selectionArgs);
        return rowsUpdated;

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
        String tableName = null;

        Uri returnURI = null;
        int match = 0;
        try {
            match = findMatch(uri, "update");
        } catch (IllegalArgumentException e) {

        }
        switch (match) {
            case ITEMS_BASE:
                tableName = AuctionContract.AuctionItemTable.TABLE_NAME;
                break;
            case BID_BASE:
                tableName = AuctionContract.UserBidTable.TABLE_NAME;
                break;
            case USER_BASE:
                tableName = AuctionContract.UserTable.TABLE_NAME;
                break;
        }
        long id = sqlDB.insert(tableName, null, values);
        getContext().getContentResolver().notifyChange(uri, null);

        return null;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();

        int rowsDeleted = sqlDB.delete(AuctionContract.AuctionItemTable.TABLE_NAME, selection,
                selectionArgs);
        return rowsDeleted;
    }

    private static int findMatch(Uri uri, String methodName) {
        int match = sURIMatcher.match(uri);
        if (match < 0) {
            throw new IllegalArgumentException("Unknown uri: " + uri);
        } else {
            Log.v(LOGTAG, methodName + ": uri=" + uri + ", match is " + match);
        }
        return match;
    }
}
