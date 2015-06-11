package com.dheeraj.auctionapp;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by DBhati on 09-Jun-15.
 */
public abstract class AuctionContract {
    public static class AuctionItem {

        public static final String TABLE_NAME = "auction_item";
        public static final String RECORD_ID = "_id";
        public static final String ITEM_NAME = "item_name";
        public static final String ITEM_DESCRIPTION = "item_description";
        public static final String ITEM_SELLER = "item_seller";
        public static final String ITEM__MIN_PRICE = "price";
        public static final String ITEM_IMAGE_PATH = "image_path";
        public static final String ITEM_STATUS = "status";
        public static final String ITEM_TIME_SPAN = "time_span";

        public static void createTable(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_NAME + " ( "
                    + RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ITEM_NAME + " TEXT, "
                    + ITEM_DESCRIPTION + " TEXT, "
                    + ITEM_SELLER + " TEXT, "
                    + ITEM__MIN_PRICE + " TEXT, "
                    + ITEM_IMAGE_PATH + " TEXT, "
                    + ITEM_STATUS + " TEXT, "
                    + ITEM_TIME_SPAN + " TEXT "
                    + ");";
            db.execSQL(createTable);
            createDummyDatabase(db);
        }

        public static void createDummyDatabase(SQLiteDatabase db) {

            try {
                String data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'1', "
                        + "'Audi A8', "
                        + "'The Audi A8 is a four-door, full-size, luxury sedan car manufactured and marketed by the German automaker Audi since 1994', "
                        + "'Audi Studio', "
                        + "'50000', "
                        + "'Audi.jpg', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'2', "
                        + "'Mercedes' , "
                        + "'An awesome car', "
                        + "'Mercedes Studio', "
                        + "'50000', "
                        + "'Mercedes.jpg', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'3', "
                        + "'Toyota Liva', "
                        + "'An awesome car', "
                        + "'Toyota Studio', "
                        + "'8000', "
                        + "'Toyota.jpg', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'4', "
                        + "'Lincoln', "
                        + "'An awesome car', "
                        + "'Audi Studio', "
                        + "'58000', "
                        + "'Lincoln.jpg', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'5', "
                        + "'Honda City', "
                        + "'An awesome car', "
                        + "'Honda Studio', "
                        + "'21000', "
                        + "'Honda.jpg', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'6', "
                        + "'Hyundai i10', "
                        + "'An awesome car', "
                        + "'Hyundai Studio', "
                        + "'11000', "
                        + "'Hyundai.jpg', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'7', "
                        + "'Suzuki', "
                        + "'An awesome car', "
                        + "'Suzuki Studio', "
                        + "'5000', "
                        + "'Suzuki.jpg', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'8', "
                        + "'Tata Zest', "
                        + "'An awesome car', "
                        + "'Tata Studio', "
                        + "'5000', "
                        + "'Tata.jpg', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'9', "
                        + "'Acura', "
                        + "'An awesome car', "
                        + "'Acura Studio', "
                        + "'5000', "
                        + "'Acura.jpg', "
                        + "'won', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'10', "
                        + "'Skoda Ocatvia', "
                        + "'An awesome car', "
                        + "'Skoda Studio', "
                        + "'15000', "
                        + "'Skoda.jpg', "
                        + "'bidding', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}

