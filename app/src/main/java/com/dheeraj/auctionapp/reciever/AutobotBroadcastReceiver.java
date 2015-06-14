package com.dheeraj.auctionapp.reciever;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;
import android.widget.Toast;

import com.dheeraj.auctionapp.database.provider.AuctionConstants;
import com.dheeraj.auctionapp.database.provider.AuctionContract;
import com.dheeraj.auctionapp.database.provider.AuctionProvider;

import java.util.ArrayList;

//TODO: Will handle restoring Alarm on Boot later

public class AutobotBroadcastReceiver extends WakefulBroadcastReceiver {
    public static final String AUTO_POLL_LIST = "auto_poll_list";
    public static final String AUTO_BID_INCREMENT= "bid_increment";

    private static final int PERIOD = 30000; //900000; // 15 minutes
    private static final int INITIAL_DELAY = 5000; // 5 seconds

    @Override
    public void onReceive(Context ctxt, Intent intent) {
        if (intent.getAction() == null) {

            ArrayList<Integer> selectedIdList = intent.getIntegerArrayListExtra(AUTO_POLL_LIST);
            ArrayList<String> incrementValue = intent.getStringArrayListExtra(AUTO_BID_INCREMENT);
            int index = 0;
                /*Lets set bids on these items*/
            for (int value : selectedIdList) {
                /*Will later move this to IntentService. as I am running out of time right now*/
                ContentResolver cr = ctxt.getContentResolver();
                ContentValues cp = new ContentValues();
                cp.put(AuctionContract.AuctionItemTable.ITEM_BIDDING_PRICE, String.valueOf(incrementValue.get(0)));
                cp.put(AuctionContract.AuctionItemTable.ITEM_STATUS, AuctionConstants.ITEM_STATE_BID);
                cr.update(AuctionProvider.CONTENT_URI_BIDITEMS, cp, "_id = ?", new String[]{String.valueOf(selectedIdList.get(index++))});
            }
        }
    }

    public static void scheduleAlarms(Context ctxt , Intent intent) {
        cancelAlarms(ctxt, intent);

        AlarmManager mgr=
                (AlarmManager)ctxt.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pi = PendingIntent.getBroadcast(ctxt, 0, intent, 0);

        mgr.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + INITIAL_DELAY,
                PERIOD, pi);

        Toast.makeText(ctxt, "New Auto Bid set", Toast.LENGTH_LONG).show();

    }

    public static void cancelAlarms(Context ctxt , Intent intent) {
        AlarmManager mgr=
                (AlarmManager)ctxt.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pi = PendingIntent.getBroadcast(ctxt, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mgr.cancel(pi);

    }
}
