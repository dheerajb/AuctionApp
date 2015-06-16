package com.dheeraj.auctionapp.database.provider;

import android.database.sqlite.SQLiteDatabase;

import com.dheeraj.auctionapp.Cars;

public abstract class AuctionContract {

    public static class AuctionItemTable {

        public static final String TABLE_NAME = "auction_item";
        public static final String ITEM_ID = "_id";
        public static final String ITEM_NAME = "item_name";
        public static final String ITEM_DESCRIPTION = "item_description";
        public static final String ITEM_SELLER = "item_seller";
        public static final String ITEM_SALE_PRICE = "sale_price";
        public static final String ITEM_RUNNING_BID_PRICE = "bid_price";
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
                    + ITEM_RUNNING_BID_PRICE + " TEXT, "
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
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Audi Studio', "
                        + "'1200', "
                        + "'1200', "
                        + "'" + Cars.urls[0] + "', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'2', "
                        + "'Mercedes' , "
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Mercedes Studio', "
                        + "'1600', "
                        + "'1600', "
                        + "'" + Cars.urls[1] + "', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'3', "
                        + "'Toyota Liva', "
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Toyota Studio', "
                        + "'1300', "
                        + "'1300', "
                        + "'" + Cars.urls[2] + "', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'4', "
                        + "'Lincoln', "
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Audi Studio', "
                        + "'1100', "
                        + "'1100', "
                        + "'" + Cars.urls[3] + "', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'5', "
                        + "'Honda City', "
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Honda Studio', "
                        + "'900', "
                        + "'900', "
                        + "'" + Cars.urls[4] + "', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'6', "
                        + "'Hyundai i10', "
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Hyundai Studio', "
                        + "'850', "
                        + "'850', "
                        + "'" + Cars.urls[5] + "', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'7', "
                        + "'Suzuki', "
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Suzuki Studio', "
                        + "'990', "
                        + "'990', "
                        + "'" + Cars.urls[6] + "', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'8', "
                        + "'Tata Zest', "
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Tata Studio', "
                        + "'800', "
                        + "'800', "
                        + "'" + Cars.urls[7] + "', "
                        + "'active', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'9', "
                        + "'Acura', "
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Acura Studio', "
                        + "'1600', "
                        + "'2000', "
                        + "'" + Cars.urls[8] + "', "
                        + "'won', "
                        + "'48' "
                        + ");";
                db.execSQL(data);

                data = "INSERT INTO " + TABLE_NAME + " VALUES" + " ( "
                        + "'10', "
                        + "'Skoda Ocatvia', "
                        + "'Exterior Looks amazing.\n" +
                        "\n" +
                        "Interior (Features, Space & Comfort) Excellent comfort with amazing features.\n" +
                        "\n" +
                        "Engine Performance, Fuel Economy and Gearbox Fuel economy is great, but engine performance could have been better.\n" +
                        "\n" +
                        "Ride Quality & Handling Very good.\n" +
                        "\n" +
                        "Final Words I would definetely recomend this car for my friends; Especially, the features and drive comfort it offers for the price.\n" +
                        "\n" +
                        "Areas of improvement Having said that, it does not match the service quality or spare parts quality of HONDA or TOYOTA.', "
                        + "'Skoda Studio', "
                        + "'1270', "
                        + "'1270', "
                        + "'" + Cars.urls[9] + "', "
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


