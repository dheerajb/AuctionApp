package com.dheeraj.auctionapp.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.database.provider.AuctionContract;
import com.dheeraj.auctionapp.ui.loader.ImageLoader;

import java.util.ArrayList;

public class AutoBotListCursorAdapter extends CursorAdapter {
    ArrayList<Integer> mIDList = new ArrayList<>();
    ArrayList<Integer> mPriceList = new ArrayList<>();

    ViewHolder mViewHolder;

    private class ViewHolder {
        CheckBox mCheckBox;
        int id;
        int price;
    }

    public AutoBotListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.autobot_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {

        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_icon);
        TextView name = (TextView) view.findViewById(R.id.firstLine);
        TextView description = (TextView) view.findViewById(R.id.secondLine);
        TextView currentBid = (TextView) view.findViewById(R.id.running_price);

        String body = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_NAME));
        String priority = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_DESCRIPTION));
        name.setText(body);
        description.setText(priority);
        final String itemBidPrice = cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_RUNNING_BID_PRICE));
        currentBid.setText(itemBidPrice);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewHolder holder = (ViewHolder)checkBox.getTag();
                if (checkBox.isChecked()) {

                    mIDList.add(holder.id);
                    mPriceList.add(holder.price);
                } else {
                    mIDList.remove(holder.id);
                    mPriceList.add(holder.price);
                }
            }
        });

        String status = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_STATUS));
        /*
        if(TextUtils.equals(status, AuctionConstants.ITEM_STATE_BID)) {
            checkBox.setChecked(true);
        }*/

        ViewHolder holder = new ViewHolder();
        holder.mCheckBox = checkBox;
        holder.id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_ID)));
        holder.price = Integer.parseInt(itemBidPrice);
        checkBox.setTag(holder);
        //checkBox.setTag(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_ID))));
        String url = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_IMAGE_PATH));
        Glide.with(context).load(url).centerCrop().into(imageView);
    }

    /*I know this is crude way. I should have used a parcelable Object*/
    public ArrayList<Integer> getSelectedItems() {
        return mIDList;
    }

    public ArrayList<Integer> getSelectedItemsPrice() {
        return mPriceList;
    }
}
