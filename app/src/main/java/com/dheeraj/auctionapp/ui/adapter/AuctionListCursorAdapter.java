package com.dheeraj.auctionapp.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dheeraj.auctionapp.database.provider.AuctionContract;
import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.ui.loader.ImageLoader;

public class AuctionListCursorAdapter extends CursorAdapter {
    public ImageLoader mImageLoader;

    public AuctionListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        mImageLoader = new ImageLoader();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.auction_list_item, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name = (TextView) view.findViewById(R.id.firstLine);
        TextView description = (TextView) view.findViewById(R.id.secondLine);
        TextView currentBid = (TextView)view.findViewById(R.id.running_price);
        ImageView imageView = (ImageView)view.findViewById(R.id.item_icon);
        String body = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_NAME));
        String priority = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_DESCRIPTION));
        name.setText(body);
        description.setText(priority);
        currentBid.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_BIDDING_PRICE)));
        mImageLoader.getImageBitmap(cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_IMAGE_PATH)), imageView);
    }
}
