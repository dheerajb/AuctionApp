package com.dheeraj.auctionapp;

import java.util.Map;
import java.util.Random;

/**
 * Created by Subham Tyagi,
 * on 15/Jun/2015,
 * 1:28 PM
 */
public class Cars {
    private static final Random RANDOM = new Random();
    public static String[] urls = {
            "http://images.askmen.com/top_10/cars/top-10-best-car-names_flash.jpg",
            "http://images.askmen.com/top_10/cars/top-10-best-car-names_10.jpg",
            "http://images.askmen.com/top_10/cars/top-10-best-car-names_9.jpg",
            "http://images.askmen.com/top_10/cars/top-10-best-car-names_8.jpg",
            "http://images.askmen.com/top_10/cars/top-10-best-car-names_7.jpg",
            "http://images.askmen.com/top_10/cars/top-10-best-car-names_6.jpg",
            "http://images.askmen.com/top_10/cars/1301606035_top-10-best-car-names_5.jpg",
            "http://images.askmen.com/top_10/cars/1301606035_top-10-best-car-names_4.jpg",
            "http://images.askmen.com/top_10/cars/top-10-best-car-names_3.jpg",
            "http://images.askmen.com/top_10/cars/top-10-best-car-names_2.jpg",
            "http://images.askmen.com/top_10/cars/top-10-best-car-names_1.jpg"

    };

    public static String getRandomCheeseDrawable() {
        return urls[RANDOM.nextInt(10)];
    }



}
