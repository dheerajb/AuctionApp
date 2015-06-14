package com.dheeraj.auctionapp.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.dheeraj.auctionapp.R;
import com.dheeraj.auctionapp.ui.fragments.AuctionListFragment;
import com.dheeraj.auctionapp.ui.fragments.AutoBotListFragment;
import com.dheeraj.auctionapp.ui.fragments.BidListFragment;
import com.dheeraj.auctionapp.ui.fragments.DetailsFragment;
import com.dheeraj.auctionapp.ui.fragments.SubmitItemsFragment;


public class AuctionMainActivity extends AppCompatActivity implements AuctionListFragment.OnAuctionListFragmentListener,
        BidListFragment.OnFragmentInteractionListener, SubmitItemsFragment.OnFragmentInteractionListener, AutoBotListFragment.OnAutobotListFragmentListener {

    private DrawerLayout mDrawerLayout;
    private AuctionListFragment mAuctionListFragment;
    private DetailsFragment mDetailsListFragment;
    private BidListFragment mBidListFragment;
    private SubmitItemsFragment mSubmitItemsFragment;
    private AutoBotListFragment mAutoBotListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        addFragment(mAuctionListFragment = new AuctionListFragment());

        getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Log.d("Dheeraj", "l");
            }
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {


                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.nav_home:
                                addFragment(mAuctionListFragment == null ? mAuctionListFragment = new AuctionListFragment() : mAuctionListFragment);
                                break;
                            case R.id.nav_play:
                                addFragment(mBidListFragment == null ? mBidListFragment = new BidListFragment() : mBidListFragment);
                                break;
                            case R.id.nav_submit:
                                addFragment(mSubmitItemsFragment == null ? mSubmitItemsFragment = new SubmitItemsFragment() : mSubmitItemsFragment);
                                break;
                            case R.id.nav_robo:
                                addFragment(mAutoBotListFragment == null ? mAutoBotListFragment = new AutoBotListFragment() : mAutoBotListFragment);
                                break;

                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    private void addFragment(android.app.Fragment fragment) {

        if (!fragment.isVisible()) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.popBackStack();
            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onSubmitFragmentInteraction(Uri uri) {
        addFragment(new AuctionListFragment());
    }

    @Override
    public void onAuctionListFragment(String value, long pos) {

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(R.id.container, DetailsFragment.newInstance("detailsFragment", (int) pos))
                .commit();

    }

    @Override
    public void onAutoBotListFragment(String value, long pos) {

    }

}