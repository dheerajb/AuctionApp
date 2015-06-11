package com.dheeraj.auctionapp.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dheeraj.auctionapp.AuctionContract;
import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.ui.loader.ImageLoader;

/**
 * Created by DBhati on 10-Jun-15.
 */
public class AuctionListCursorAdapter extends CursorAdapter {
    public ImageLoader mImageLoader;

    public AuctionListCursorAdapter(Context context, Cursor cursor) {
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
        // Find fields to populate in inflated template
        TextView name = (TextView) view.findViewById(R.id.firstLine);
        TextView description = (TextView) view.findViewById(R.id.secondLine);
        ImageView imageView = (ImageView)view.findViewById(R.id.item_icon);
        // Extract properties from cursor
        String body = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItem.ITEM_NAME));
        String priority = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItem.ITEM_DESCRIPTION));
        // Populate fields with extracted properties
        name.setText(body);
        description.setText(priority);
        mImageLoader.getImageBitmap(cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItem.ITEM_IMAGE_PATH)), imageView);
    }
}
