package com.dheeraj.auctionapp.ui.fragments;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dheeraj.auctionapp.AuctionConstants;
import com.dheeraj.auctionapp.database.provider.AuctionContract;
import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.database.provider.AuctionProvider;
import com.dheeraj.auctionapp.ui.loader.ImageLoader;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";

    private String mItemName;
    private long mIndex;

    private OnFragmentInteractionListener mListener;

    private LinearLayout mLn;
    private TextView mName;
    private TextView mDescription;
    private TextView mSeller;
    private TextView mSalePrice;
    private TextView mNewBidPrice;
    private TextView mRunningBidPrice;
    private ImageView mImage;
    private Button mBidButton;
    private Button mCancelButton;
    private QueryHandler mQueryHandler;
    private static final int TOKEN_QUERY = 0;
    private static final int TOKEN_INSERT = 1;

    private int mItemID;
    private ImageLoader mImageLoader;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mQueryHandler = new QueryHandler(getActivity().getApplicationContext());
        mQueryHandler.startQuery(TOKEN_QUERY, null, AuctionProvider.CONTENT_URI_BIDITEMS, null,
                "_id = ?", new String[]{String.valueOf(mIndex)}, null);
        mImageLoader = new ImageLoader();
        ;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(String param1, int param2) {
        Fragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mItemName = getArguments().getString(ARG_PARAM1);
            mIndex = getArguments().getLong(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View detailView = inflater.inflate(R.layout.fragment_item_details, container, false);
        mLn = (LinearLayout) detailView.findViewById(R.id.auction_item_layout);

        mImage = (ImageView) detailView.findViewById(R.id.image1);
        mName = (TextView) detailView.findViewById(R.id.item_description);
        mDescription = (TextView) detailView.findViewById(R.id.item_description_details);
        mSeller = (TextView) detailView.findViewById(R.id.seller_name);
        mSalePrice = (TextView) detailView.findViewById(R.id.sale_price);
        mRunningBidPrice = (TextView) detailView.findViewById(R.id.running_bid);

        mNewBidPrice = (TextView) detailView.findViewById(R.id.new_bid_amount);
        mCancelButton = (Button) detailView.findViewById(R.id.cancel_button);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put(AuctionContract.AuctionItemTable.ITEM_STATUS, AuctionConstants.ITEM_STATE_ACTIVE);
                mQueryHandler.startUpdate(TOKEN_INSERT, null, AuctionProvider.CONTENT_URI_BIDITEMS, cv, "_id = ?", new String[]{String.valueOf(mItemID)});
                Toast.makeText(getActivity().getApplicationContext(), "You have withdrawn from bid", Toast.LENGTH_LONG).show();
            }
        });
        mBidButton = (Button) detailView.findViewById(R.id.bid_now_button);
        mBidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(mNewBidPrice.getText().toString()) < Integer.parseInt(mRunningBidPrice.getText().toString())) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please bid more than running bid", Toast.LENGTH_LONG).show();
                    return;
                }

                mRunningBidPrice.setText(mNewBidPrice.getText());
                ContentValues cv = new ContentValues();
                cv.put(AuctionContract.AuctionItemTable.ITEM_RUNNING_BID_PRICE, mNewBidPrice.getText().toString());
                cv.put(AuctionContract.AuctionItemTable.ITEM_STATUS, AuctionConstants.ITEM_STATE_BID);
                mQueryHandler.startUpdate(TOKEN_INSERT, null, AuctionProvider.CONTENT_URI_BIDITEMS, cv, "_id = ?", new String[]{String.valueOf(mItemID)});
                Toast.makeText(getActivity().getApplicationContext(), "New Bid Submitted", Toast.LENGTH_LONG).show();
            }
        });
        return detailView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            //throw new ClassCastException(activity.toString()
            //       + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

    private final class QueryHandler extends AsyncQueryHandler {

        public QueryHandler(Context context) {
            super(context.getContentResolver());
        }

        @Override
        protected void onUpdateComplete(int token, Object cookie, int result) {
            super.onUpdateComplete(token, cookie, result);
        }

        @Override
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            /*Cursor should not be null. If so we are in bad shape*/
            switch (token) {
                case TOKEN_QUERY:
                    if (cursor.getCount() != 0) {

                        while (cursor.moveToNext()) {
                            mItemID = cursor.getInt(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_ID));
                            mName.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_NAME)));
                            mDescription.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_DESCRIPTION)));
                            mSeller.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_SELLER)));
                            mSalePrice.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_SALE_PRICE)));
                            mImageLoader.getImageBitmap(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_IMAGE_PATH)), mImage);
                            mRunningBidPrice.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_RUNNING_BID_PRICE)));

                            String status = cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItemTable.ITEM_STATUS));
                            if(TextUtils.equals(status, AuctionConstants.ITEM_STATE_BID)) {
                                mCancelButton.setVisibility(View.VISIBLE);
                            }
                        }
                    }

            }
        }
    }
}
