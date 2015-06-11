package com.dheeraj.auctionapp.ui;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dheeraj.auctionapp.AuctionContract;
import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.database.provider.AuctionProvider;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mIndex;

    private OnFragmentInteractionListener mListener;

    private TextView mName;
    private TextView mDescription;
    private TextView mSeller;
    private TextView mPrice;
    private QueryHandler mQueryHandler;
    private static final int TOKEN_GROUP = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mQueryHandler = new QueryHandler(getActivity().getApplicationContext());
        mQueryHandler.startQuery(TOKEN_GROUP, null, AuctionProvider.CONTENT_URI, null,
                null, null, null);
;    }

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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mIndex = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View detailView = inflater.inflate(R.layout.fragment_add_auction_item, container, false);

        mName = (TextView) detailView.findViewById(R.id.item_description);
        mDescription = (TextView) detailView.findViewById(R.id.item_description_details);
        mSeller = (TextView) detailView.findViewById(R.id.seller_name);
        mPrice = (TextView)detailView.findViewById(R.id.amount);
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
        protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
            switch (token) {
                case TOKEN_GROUP:
                    if(cursor.getCount() != 0){

                        while (cursor.moveToNext()) {
                            mName.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItem.ITEM_NAME)));
                            mDescription.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItem.ITEM_DESCRIPTION)));
                            mSeller.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItem.ITEM_SELLER)));
                            mPrice.setText(cursor.getString(cursor.getColumnIndex(AuctionContract.AuctionItem.ITEM__MIN_PRICE)));

                        }
                    }
                    break;

            }
        }
    }
}
