package com.gautam.nanodegree;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.gautam.nanodegree.popularmovies.constants.PopularMovieConstants;
import com.gautam.nanodegree.popularmovies.db.PopularMoviesDbHelper;
import com.gautam.nanodegree.popularmovies.ui.PopularMoviesDetailFragment;
import com.gautam.nanodegree.popularmovies.ui.PopularMoviesFragment;
import com.gautam.nanodegree.ui.HomeFragment;
import com.gautam.nanodegree.utils.Utils;


public class MainActivity extends ActionBarActivity implements FragmentChangeListener {

    private FragmentManager mFragmentManager = null;
    private FragmentTransaction mFragmentTransaction = null;
    private PopularMoviesFragment mPopularMoviesFragment = null;
    private PopularMoviesDbHelper mPopularMoviesDbHelper = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(Utils.getResourceString(this, R.string.welcome_title_text));

        mPopularMoviesDbHelper = new PopularMoviesDbHelper(this);

        loadHomeFragment();
    }

    @Override
    public void onBackPressed() {

        if (!getResources().getBoolean(R.bool.isDeviceTypePhone)) {
            if (findViewById(R.id.popularMovieLayoutId).getVisibility() == View.VISIBLE) {
                findViewById(R.id.popularMovieLayoutId).setVisibility(View.GONE);
                changeHomeFragmentVisibility(true);
            } else {
                finish();
            }

        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void fragmentChangeEvent(int event, Object data) {

        switch (event) {
            case FragmentChangeListener.POPULAR_MOVIES_EVENT:
                loadPopularMoviesFragment();
                break;
            case FragmentChangeListener.POPULAR_MOVIES_DETAILS_EVENT:
                loadPopularMoviesDetailFragment(data);
                break;
        }

    }

    private void loadHomeFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        HomeFragment mHomeFragment = HomeFragment.newInstance(this);
        mFragmentTransaction.add(R.id.fragmentContainerId, mHomeFragment, null);
        mFragmentTransaction.commit();
    }

    private void loadPopularMoviesFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mPopularMoviesFragment = PopularMoviesFragment.newInstance(this);
        if (getResources().getBoolean(R.bool.isDeviceTypePhone)) {
            mFragmentTransaction.replace(R.id.fragmentContainerId, mPopularMoviesFragment, PopularMovieConstants.POPULAR_MOVIES_FRAGMENT_TAG);
            mFragmentTransaction.addToBackStack(null);
        } else {
            findViewById(R.id.popularMovieLayoutId).setVisibility(View.VISIBLE);
            changeHomeFragmentVisibility(false);
            mFragmentTransaction.replace(R.id.popularMovieFragmentContainerId, mPopularMoviesFragment, PopularMovieConstants.POPULAR_MOVIES_FRAGMENT_TAG);
        }
        mFragmentTransaction.commit();
    }

    private void loadPopularMoviesDetailFragment(Object data) {


        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        PopularMoviesDetailFragment mPopularMoviesDetailFragment = PopularMoviesDetailFragment.newInstance(this, data);
        if (getResources().getBoolean(R.bool.isDeviceTypePhone)) {
            mFragmentTransaction.add(R.id.fragmentContainerId, mPopularMoviesDetailFragment, PopularMovieConstants.POPULAR_MOVIES_DETAILS_FRAGMENT_TAG);
            mPopularMoviesDetailFragment.setTargetFragment(mPopularMoviesFragment, 1);
            mFragmentTransaction.addToBackStack(null);
        } else {
            findViewById(R.id.popularMovieLayoutId).setVisibility(View.VISIBLE);
            changeHomeFragmentVisibility(false);
            mFragmentTransaction.add(R.id.popularMovieDetailFragmentContainerId, mPopularMoviesDetailFragment, PopularMovieConstants.POPULAR_MOVIES_DETAILS_FRAGMENT_TAG);
        }

        mFragmentTransaction.commit();
    }


    public void changeHomeFragmentVisibility(boolean showView) {
        if (showView) {
            findViewById(R.id.fragmentContainerId).setVisibility(View.VISIBLE);
            setTitle(Utils.getResourceString(this, R.string.welcome_title_text));
        } else {
            findViewById(R.id.fragmentContainerId).setVisibility(View.GONE);
        }

    }
}
