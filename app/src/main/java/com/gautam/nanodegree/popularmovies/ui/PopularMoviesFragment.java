package com.gautam.nanodegree.popularmovies.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.gautam.nanodegree.FragmentChangeListener;
import com.gautam.nanodegree.MainActivity;
import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.constants.PopularMovieConstants;
import com.gautam.nanodegree.popularmovies.core.HttpRequestTaskController;
import com.gautam.nanodegree.popularmovies.core.MoviesDataModel;
import com.gautam.nanodegree.popularmovies.core.UpdateViewListener;
import com.gautam.nanodegree.utils.Utils;

/**
 * Created by Gautam on 09/07/15.
 */
public class PopularMoviesFragment extends Fragment implements UpdateViewListener {

    private GridView mPopularMoviesGridView;
    private static Context mContext = null;
    private FragmentChangeListener mFragmentChangeListener = null;
    public static String mCurrentSortChoiceStr = null;

    public static PopularMoviesFragment newInstance(Context context) {
        mContext = context;
        return new PopularMoviesFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        new HttpRequestTaskController(PopularMoviesFragment.this).executeHttpRequest(PopularMovieConstants.MOST_POPULAR);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(mCurrentSortChoiceStr);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popularMoviesMenuId:
                if (mCurrentSortChoiceStr != null && !mCurrentSortChoiceStr.equalsIgnoreCase(Utils.getResourceString(mContext, R.string.popular_movies_title_text))) {
                    mCurrentSortChoiceStr = Utils.getResourceString(mContext, R.string.popular_movies_title_text);
                    getActivity().setTitle(mCurrentSortChoiceStr);
                    new HttpRequestTaskController(this).executeHttpRequest(PopularMovieConstants.MOST_POPULAR);
                }
                return true;
            case R.id.highestRatedMoviesMenuId:
                if (mCurrentSortChoiceStr != null && !mCurrentSortChoiceStr.equalsIgnoreCase(Utils.getResourceString(mContext, R.string.highest_rated_movies_title_text))) {
                    mCurrentSortChoiceStr = Utils.getResourceString(mContext, R.string.highest_rated_movies_title_text);
                    getActivity().setTitle(mCurrentSortChoiceStr);
                    new HttpRequestTaskController(this).executeHttpRequest(PopularMovieConstants.HIGHEST_RATED);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mRooView = inflater.inflate(R.layout.fragment_popular_movies, null);

        mPopularMoviesGridView = (GridView) mRooView.findViewById(R.id.popularMoviesGridViewId);
        mPopularMoviesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MoviesDataModel mMoviesDataModel = (MoviesDataModel) parent.getItemAtPosition(position);

                mFragmentChangeListener.fragmentChangeEvent(FragmentChangeListener.POPULAR_MOVIES_DETAILS_EVENT, mMoviesDataModel);
            }
        });

        mCurrentSortChoiceStr = Utils.getResourceString(mContext, R.string.popular_movies_title_text);
        getActivity().setTitle(mCurrentSortChoiceStr);

        return mRooView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mFragmentChangeListener = (FragmentChangeListener) activity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().setTitle(Utils.getResourceString(mContext, R.string.welcome_title_text));
        if (!getResources().getBoolean(R.bool.isDeviceTypePhone)) {
            ((MainActivity) getActivity()).changeHomeFragmentVisibility(true);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void updateView(Object data, boolean shouldUpdate) {
        if (shouldUpdate) {
            mPopularMoviesGridView.setAdapter(new PopularMoviesGridAdapter(mContext, data));
        } else {
            Utils.getToast(mContext, mContext.getString(R.string.http_error_msg_text)).show();
        }
    }
}
