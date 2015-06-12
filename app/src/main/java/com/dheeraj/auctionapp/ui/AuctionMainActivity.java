package com.dheeraj.auctionapp.ui;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.app.FragmentManager;
import android.widget.Toast;

import com.dheeraj.auctionapp.R;


public class AuctionMainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, AuctionListFragment.OnAuctionListFragmentListener,
        BidListFragment.OnFragmentInteractionListener, SubmitItemsFragment.OnFragmentInteractionListener, LoginFragment.OnLoginFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private boolean mLoggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        if (mLoggedIn == false) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new LoginFragment())
                    .commit();
            return;
        }
        switch (position) {

            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new AuctionListFragment())
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new BidListFragment())
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new SubmitItemsFragment())
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.auction_items_list);
                break;
            case 2:
                mTitle = getString(R.string.bid_items_list);
                break;
            case 3:
                mTitle = getString(R.string.submit_items);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.auction_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public void onAuctionListFragment(String value, long pos) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, DetailsFragment.newInstance("detailsFragment", (int)pos)).addToBackStack(null)
                .commit();

    }

    @Override
    public void onLoginFragmentInteraction(boolean status) {
        mLoggedIn = status;
        FragmentManager fragmentManager = getFragmentManager();
        if (status == true) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new AuctionListFragment())
                    .commit();
        } else {

            Toast.makeText(getApplicationContext(),"Please check your login details", Toast.LENGTH_SHORT).show();
        }
    }
}