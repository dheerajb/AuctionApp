package com.dheeraj.auctionapp.ui.fragments;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dheeraj.auctionapp.AuctionConstants;
import com.dheeraj.auctionapp.database.provider.AuctionContract;
import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.database.provider.AuctionProvider;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubmitItemsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubmitItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmitItemsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private TextView mName;
    private TextView mDescription;
    private TextView mSeller;
    private TextView mPrice;
    private Button mButton;
    private QueryHandler mQueryHandler;
    private static final int TOKEN_INSERT = 0;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubmitItemsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubmitItemsFragment newInstance(String param1, String param2) {
        SubmitItemsFragment fragment = new SubmitItemsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SubmitItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mQueryHandler = new QueryHandler(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View detailView = inflater.inflate(R.layout.fragment_submit_items, container, false);

        mName = (TextView) detailView.findViewById(R.id.item_name);
        mDescription = (TextView) detailView.findViewById(R.id.item_description_details_submit);
        mSeller = (TextView) detailView.findViewById(R.id.seller_name_submit);
        mPrice = (TextView)detailView.findViewById(R.id.offer_price);
        mButton = (Button)detailView.findViewById(R.id.submit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Insert new enteries to database*/
                ContentValues cv = new ContentValues();
                cv.put(AuctionContract.AuctionItemTable.ITEM_NAME, mName.getText().toString());
                cv.put(AuctionContract.AuctionItemTable.ITEM_DESCRIPTION, mDescription.getText().toString());
                cv.put(AuctionContract.AuctionItemTable.ITEM_SELLER, mSeller.getText().toString());
                cv.put(AuctionContract.AuctionItemTable.ITEM_IMAGE_PATH, "none");
                cv.put(AuctionContract.AuctionItemTable.ITEM_SALE_PRICE, mPrice.getText().toString());
                cv.put(AuctionContract.AuctionItemTable.ITEM_STATUS, AuctionConstants.ITEM_STATE_ACTIVE);
                cv.put(AuctionContract.AuctionItemTable.ITEM_TIME_SPAN, "24hr");
                mQueryHandler.startInsert(TOKEN_INSERT,null,AuctionProvider.CONTENT_URI_BIDITEMS, cv);
            }
        });
        return detailView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSubmitFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
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
        public void onSubmitFragmentInteraction(Uri uri);
    }
    private final class QueryHandler extends AsyncQueryHandler {

        public QueryHandler(Context context) {
            super(context.getContentResolver());
        }

        @Override
        protected void onInsertComplete(int token, Object cookie, Uri uri) {
            super.onInsertComplete(token, cookie, uri);
            switch (token) {
                case TOKEN_INSERT:
                    if (mListener != null) {
                        mListener.onSubmitFragmentInteraction(uri);
                    }
                    break;

            }
        }
    }
}
