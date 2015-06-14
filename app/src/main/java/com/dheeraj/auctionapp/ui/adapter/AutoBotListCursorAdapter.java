package com.dheeraj.auctionapp.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.database.provider.AuctionConstants;
import com.dheeraj.auctionapp.database.provider.AuctionContract;
import com.dheeraj.auctionapp.ui.loader.ImageLoader;

import java.util.ArrayList;

public class AutoBotListCursorAdapter extends CursorAdapter {
    public ImageLoader mImageLoader;
    ArrayList<Integer> mIDList = new ArrayList<>();
    ViewHolder mViewHolder;

    private class ViewHolder {
        CheckBox mCheckBox;
    }

    public AutoBotListCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        mImageLoader = new ImageLoader();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.autobot_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        TextView name = (TextView) view.findViewById(R.id.firstLine);
        TextView description = (TextView) view.findViewById(R.id.secondLine);
        TextView currentBid = (TextView) view.findViewById(R.id.running_price);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_icon);
        String body = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_NAME));
        String priority = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_DESCRIPTION));
        name.setText(body);
        description.setText(priority);
        currentBid.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_BIDDING_PRICE)));
        mImageLoader.getImageBitmap(cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_IMAGE_PATH)), imageView);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    mIDList.add((int) checkBox.getTag());
                } else {
                    mIDList.remove(checkBox.getTag());
                }
            }
        });

        String status = cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_STATUS));

        if(TextUtils.equals(status, AuctionConstants.ITEM_STATE_BID)) {
            checkBox.setChecked(true);
        }
        checkBox.setTag(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(AuctionContract.AuctionItemTable.ITEM_ID))));
    }

    public ArrayList<Integer> getSelectedItems() {
        return mIDList;
    }
}
