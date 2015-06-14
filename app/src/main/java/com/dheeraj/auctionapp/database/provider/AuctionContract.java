package com.dheeraj.auctionapp.database.provider;

import android.database.sqlite.SQLiteDatabase;

public abstract class AuctionContract {

    public static class AuctionItemTable {

        public static final String TABLE_NAME = "auction_item";
        public static final String ITEM_ID = "_id";
        public static final String ITEM_NAME = "item_name";
        public static final String ITEM_DESCRIPTION = "item_description";
        public static final String ITEM_SELLER = "item_seller";
        public static final String ITEM_SALE_PRICE = "sale_price";
        public static final String ITEM_BIDDING_PRICE = "bid_price";
        public static final String ITEM_IMAGE_PATH = "image_path";
        public static final String ITEM_STATUS = "status";
        public static final String ITEM_TIME_SPAN = "time_span";

        public static void createTable(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_NAME + " ( "
                    + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + ITEM_NAME + " TEXT, "
                    + ITEM_DESCRIPTION + " TEXT, "
                    + ITEM_SELLER + " TEXT, "
                    + ITEM_SALE_PRICE + " TEXT, "
                    + ITEM_BIDDING_PRICE + " TEXT, "
                    + ITEM_IMAGE_PATH + " TEXT, "
                    + ITEM_STATUS + " TEXT, "
                    + ITEM_TIME_SPAN + " TEXT "
                    + ");";
            db.execSQL(createTable);
            createDummyAuctionDataBase(db);
        }


        public static void createDummyAuctionDataBase(SQLiteDatabase db) {

            try {
                String data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'1', "
                        + "'Audi A8', "
                        + "'The Audi A8 is a four-door, full-size, luxury sedan car manufactured and marketed by the German automaker Audi since 1994', "
                        + "'Audi Studio', "
                        + "'1200', "
                        + "'1200', "
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
                        + "'1600', "
                        + "'1600', "
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
                        + "'1300', "
                        + "'1300', "
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
                        + "'1100', "
                        + "'1100', "
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
                        + "'900', "
                        + "'900', "
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
                        + "'850', "
                        + "'850', "
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
                        + "'990', "
                        + "'990', "
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
                        + "'800', "
                        + "'800', "
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
                        + "'1600', "
                        + "'2000', "
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
                        + "'1270', "
                        + "'1270', "
                        + "'Skoda.jpg', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static class UserBidTable {

        public static final String TABLE_NAME = "user_table";
        public static final String RECORD_ID = "_id";
        public static final String USER_NAME = "user_name";
        public static final String ITEM_ID = "item_id";
        public static final String ITEM_STATUS = "item_status";

        public static void createTable(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_NAME + " ( "
                    + RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + USER_NAME + " TEXT, "
                    + ITEM_ID + " TEXT, "
                    + ITEM_STATUS + " TEXT "
                    + ");";
            db.execSQL(createTable);
        }
    }

    public static class UserTable {

        public static final String TABLE_NAME = "credentials_table";
        public static final String RECORD_ID = "_id";
        public static final String USER_NAME = "user_name";
        public static final String USER_PASSWORD = "password";

        public static void createTable(SQLiteDatabase db) {
            String createTable = "CREATE TABLE " + TABLE_NAME + " ( "
                    + RECORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + USER_NAME + " TEXT, "
                    + USER_PASSWORD + " TEXT "
                    + ");";
            db.execSQL(createTable);
        }
    }
}


