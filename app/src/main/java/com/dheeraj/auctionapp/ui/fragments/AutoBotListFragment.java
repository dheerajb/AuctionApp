package com.dheeraj.auctionapp.ui.fragments;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.database.provider.AuctionContract;
import com.dheeraj.auctionapp.reciever.AutobotBroadcastReceiver;
import com.dheeraj.auctionapp.ui.adapter.AutoBotListCursorAdapter;

import java.util.ArrayList;

import static com.dheeraj.auctionapp.database.provider.AuctionProvider.CONTENT_URI_BIDITEMS;


public class AutoBotListFragment extends Fragment implements AbsListView.OnItemClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    private OnAutobotListFragmentListener mListener;

    private AbsListView mListView;

    private AutoBotListCursorAdapter mAdapter;

    private AlarmManager mgr = null;

    private Context mContext;

    private EditText mBidValueEditText;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AutoBotListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(0, null, this);
        mAdapter = new AutoBotListCursorAdapter(getActivity().getApplicationContext(), null);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_auto_bid, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(R.id.autobot_listview);
        mListView.setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        Button bidButton = (Button) view.findViewById(R.id.bid_now_button);
        bidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAutoBid(mAdapter.getSelectedItems());

            }
        });

        mBidValueEditText = (EditText) view.findViewById(R.id.new_bid_amount_text);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnAutobotListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnAutobotListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext = getActivity().getApplicationContext();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            mListener.onAutoBotListFragment("test", id);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        CursorLoader cursorLoader;
        cursorLoader = new CursorLoader(getActivity().getApplicationContext(), CONTENT_URI_BIDITEMS, null, AuctionContract.AuctionItemTable.ITEM_STATUS + "= ?", new String[]{"active"}, null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
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
    public interface OnAutobotListFragmentListener {
        public void onAutoBotListFragment(String value, long pos);
    }

    private void setAutoBid(ArrayList<Integer> list) {
        ArrayList<String> inc = new ArrayList<>();
        inc.add(mBidValueEditText.getText().toString());
        Intent intent = new Intent(mContext, AutobotBroadcastReceiver.class);

        intent.putIntegerArrayListExtra(AutobotBroadcastReceiver.AUTO_POLL_LIST, list);
        intent.putStringArrayListExtra(AutobotBroadcastReceiver.AUTO_BID_INCREMENT, inc);
        AutobotBroadcastReceiver.scheduleAlarms(mContext, intent);
    }
}
