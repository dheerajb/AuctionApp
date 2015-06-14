package com.dheeraj.auctionapp.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.database.provider.AuctionContract;
import com.dheeraj.auctionapp.ui.loader.ImageLoader;

public class BidListCursorAdapter extends CursorAdapter {
    public ImageLoader mImageLoader;

    public BidListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        mImageLoader = new ImageLoader();
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.auction_list_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.firstLine);
        TextView description = (TextView) view.findViewById(R.id.secondLine);
        TextView running_price = (TextView)view.findViewById(R.id.running_price);
        TextView current_bid = (TextView)view.findViewById(R.id.current_bid);
        String state = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_STATUS));
        if(TextUtils.equals(state, "won")) {
            current_bid.setTextColor(0xFFFF6600);
        }
        ImageView imageView = (ImageView)view.findViewById(R.id.item_icon);
        String body = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_NAME));
        String item_description = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_DESCRIPTION));
        name.setText(body);
        description.setText(item_description);
        running_price.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_RUNNING_BID_PRICE)));
        mImageLoader.getImageBitmap(cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_IMAGE_PATH)), imageView);
    }
}
