package com.dheeraj.auctionapp.reciever;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import com.dheeraj.auctionapp.AuctionConstants;
import com.dheeraj.auctionapp.database.provider.AuctionContract;
import com.dheeraj.auctionapp.database.provider.AuctionProvider;

import java.util.ArrayList;

//TODO: Will handle restoring Alarm on Boot later

public class AutobotBroadcastReceiver extends WakefulBroadcastReceiver {
    public static final String AUTO_POLL_LIST = "auto_poll_list";
    public static final String AUTO_BID_INCREMENT = "bid_increment";

    private static final int PERIOD = 30000; // 1 Minute
    private static final int INITIAL_DELAY = 5000; // 5 seconds

    @Override
    public void onReceive(Context ctxt, Intent intent) {

        if (intent.getAction() == null) {

            ArrayList<Integer> selectedIdList = intent.getIntegerArrayListExtra(AUTO_POLL_LIST);
            ArrayList<Integer> price = intent.getIntegerArrayListExtra(AUTO_BID_INCREMENT);

            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(ctxt);
            int increment = sp.getInt(AuctionConstants.PREF_AUTBOT_INCREMENT, 0);
            int count = 0;
            ArrayList<Integer> inc = new ArrayList<>();
            for (Integer itemPrice : price) {
                inc.add(increment + price.get(count++));
            }

            int index = 0;
                /*Lets set bids on these items*/
            for (int value : selectedIdList) {
                /*Will later move this to IntentService. as I am running out of time right now*/
                ContentResolver cr = ctxt.getContentResolver();
                ContentValues cp = new ContentValues();
                cp.put(AuctionContract.AuctionItemTable.ITEM_RUNNING_BID_PRICE, inc.get(index));
                cp.put(AuctionContract.AuctionItemTable.ITEM_STATUS, AuctionConstants.ITEM_STATE_BID);
                cr.update(AuctionProvider.CONTENT_URI_BIDITEMS, cp, "_id = ?", new String[]{String.valueOf(selectedIdList.get(index++))});
            }
            //cancelAlarms(ctxt, intent);

            Intent newIntent = new Intent(ctxt, AutobotBroadcastReceiver.class);
            newIntent.putIntegerArrayListExtra(AutobotBroadcastReceiver.AUTO_POLL_LIST, selectedIdList);
            newIntent.putIntegerArrayListExtra(AutobotBroadcastReceiver.AUTO_BID_INCREMENT, inc);
            scheduleAlarms(ctxt, newIntent);
        }
    }

    public static void scheduleAlarms(Context ctxt, Intent intent) {

        //cancelAlarms(ctxt , intent);

        AlarmManager mgr =
                (AlarmManager) ctxt.getSystemService(Context.ALARM_SERVICE);

        PendingIntent pi = getPendingIntent(ctxt, intent);

        mgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        PERIOD, pi);

        Toast.makeText(ctxt, "New Auto Bid set", Toast.LENGTH_SHORT).show();

    }

    private static void cancelAlarms(Context ctxt, Intent intent) {

        AlarmManager mgr =
                (AlarmManager) ctxt.getSystemService(Context.ALARM_SERVICE);

        mgr.cancel(getPendingIntent(ctxt, intent));

    }

    private static PendingIntent getPendingIntent(Context ctxt, Intent intent) {

        return PendingIntent.getBroadcast(ctxt, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
