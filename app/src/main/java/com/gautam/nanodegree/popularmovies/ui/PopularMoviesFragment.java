package com.gautam.nanodegree.popularmovies.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
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
import com.gautam.nanodegree.popularmovies.constants.PopularMoviesProviderConstants.FavoriteMoviesTable;
import com.gautam.nanodegree.popularmovies.core.HttpRequestTaskController;
import com.gautam.nanodegree.popularmovies.core.MoviesDataModel;
import com.gautam.nanodegree.popularmovies.core.UpdateViewListener;
import com.gautam.nanodegree.utils.NetworkUtil;
import com.gautam.nanodegree.utils.Utils;
import com.google.gson.Gson;

/**
 * Created by Gautam on 09/07/15.
 */
public class PopularMoviesFragment extends Fragment implements UpdateViewListener, LoaderManager.LoaderCallbacks<Cursor> {

    private GridView mPopularMoviesGridView;
    private static Context mContext = null;
    private FragmentChangeListener mFragmentChangeListener = null;
    public static String mCurrentSortChoiceStr = null;
    private FavoriteMovieCursorAdapter mFavoriteMovieCursorAdapter = null;
    private ProgressDialog mProgressDialog = null;

    public static PopularMoviesFragment newInstance(Context context) {
        mContext = context;
        return new PopularMoviesFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (NetworkUtil.getConnectivityStatus(mContext) == 0) {
            Utils.getToast(mContext, mContext.getString(R.string.not_connected_to_internet_text)).show();
            handleNoInternetConnection();
        } else {
            mCurrentSortChoiceStr = Utils.getResourceString(mContext, R.string.popular_movies_title_text);
            handleSortChoice(PopularMovieConstants.REQUEST_TYPE_MOST_POPULAR);
//            getActivity().setTitle(mCurrentSortChoiceStr);
//            new HttpRequestTaskController(PopularMoviesFragment.this).executeHttpRequest(PopularMovieConstants.REQUEST_TYPE_MOST_POPULAR);
        }
    }

    private void handleNoInternetConnection() {
        fetchFavoriteMoviesFromDb();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(mCurrentSortChoiceStr);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.sort_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (NetworkUtil.getConnectivityStatus(mContext) == 0) {
            if (item.getItemId() == R.id.favoriteMoviesMenuId) {
                if (canSort(Utils.getResourceString(mContext, R.string.favorite_movies_title_text))) {
                    handleNoInternetConnection();
                }
                return true;
            } else {
                Utils.getToast(mContext, mContext.getString(R.string.not_connected_to_internet_text)).show();
                return false;
            }
        } else {
            switch (item.getItemId()) {
                case R.id.popularMoviesMenuId:
                    if (canSort(Utils.getResourceString(mContext, R.string.popular_movies_title_text))) {
                        mCurrentSortChoiceStr = Utils.getResourceString(mContext, R.string.popular_movies_title_text);
                        handleSortChoice(PopularMovieConstants.REQUEST_TYPE_MOST_POPULAR);
//                        new HttpRequestTaskController(this).executeHttpRequest(PopularMovieConstants.REQUEST_TYPE_MOST_POPULAR);
                    }
                    return true;
                case R.id.highestRatedMoviesMenuId:
                    if (canSort(Utils.getResourceString(mContext, R.string.highest_rated_movies_title_text))) {
                        mCurrentSortChoiceStr = Utils.getResourceString(mContext, R.string.highest_rated_movies_title_text);
                        handleSortChoice(PopularMovieConstants.REQUEST_TYPE_HIGHEST_RATED);
//                        new HttpRequestTaskController(this).executeHttpRequest(PopularMovieConstants.REQUEST_TYPE_HIGHEST_RATED);
                    }
                    return true;
                case R.id.favoriteMoviesMenuId:
                    if (canSort(Utils.getResourceString(mContext, R.string.favorite_movies_title_text))) {
                        mCurrentSortChoiceStr = Utils.getResourceString(mContext, R.string.favorite_movies_title_text);
                        getActivity().setTitle(mCurrentSortChoiceStr);

                        fetchFavoriteMoviesFromDb();
                    }
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }

    private void handleSortChoice(int sortChoice) {
        getActivity().setTitle(mCurrentSortChoiceStr);
        showProgressDialog();
        new HttpRequestTaskController(this).executeHttpRequest(sortChoice);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View mRooView = inflater.inflate(R.layout.fragment_popular_movies, null);

        mPopularMoviesGridView = (GridView) mRooView.findViewById(R.id.popularMoviesGridViewId);
        mPopularMoviesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                handleGridItemClick(parent, position);
            }
        });

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

    private boolean canSort(String sortChoice) {

        boolean mCanSort = false;

        if (mCurrentSortChoiceStr == null) {
            mCanSort = false;
        } else if (!mCurrentSortChoiceStr.equalsIgnoreCase(sortChoice)) {
            mCanSort = true;
        }
        return mCanSort;
    }

    private void showProgressDialog() {
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage(Utils.getResourceString(mContext, R.string.please_wait_msg_text));
        mProgressDialog.show();
    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }


    private void handleGridItemClick(AdapterView<?> parent, int position) {

        MoviesDataModel mMoviesDataModel = null;
        if (mPopularMoviesGridView.getAdapter() instanceof MoviesGridAdapter) {
            mMoviesDataModel = (MoviesDataModel) parent.getItemAtPosition(position);

        } else if (mPopularMoviesGridView.getAdapter() instanceof FavoriteMovieCursorAdapter) {
            final Cursor mCursor = mFavoriteMovieCursorAdapter.getCursor();
            mCursor.moveToPosition(position);
            String mFavoriteMovieStr = mCursor.getString(mCursor.getColumnIndex(FavoriteMoviesTable.FAVORITE_MOVIES_DATA));
            Gson gson = new Gson();
            mMoviesDataModel = gson.fromJson(mFavoriteMovieStr, MoviesDataModel.class);
        }

        mFragmentChangeListener.fragmentChangeEvent(FragmentChangeListener.POPULAR_MOVIES_DETAILS_EVENT, mMoviesDataModel);
    }

    private void fetchFavoriteMoviesFromDb() {

        Loader mLoader = getLoaderManager().getLoader(FavoriteMoviesTable.QUERY_ID);
        if (mLoader == null) {
            getLoaderManager().initLoader(FavoriteMoviesTable.QUERY_ID, null, PopularMoviesFragment.this);
        } else {
            getLoaderManager().restartLoader(FavoriteMoviesTable.QUERY_ID, null, PopularMoviesFragment.this);
        }
        mFavoriteMovieCursorAdapter = new FavoriteMovieCursorAdapter(mContext);
    }

    @Override
    public void updateView(Object data, boolean shouldUpdate, int requestType) {
        dismissProgressDialog();
        if (shouldUpdate) {
            mPopularMoviesGridView.setAdapter(new MoviesGridAdapter(mContext, data));
        } else {
            mCurrentSortChoiceStr = "";
            Utils.getToast(mContext, mContext.getString(R.string.http_error_msg_text)).show();
        }
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        final String[] PROJECTION = {

                FavoriteMoviesTable._ID,
                FavoriteMoviesTable.FAVORITE_MOVIES_ID,
                FavoriteMoviesTable.FAVORITE_MOVIES_DATA
        };
        return new CursorLoader(mContext, FavoriteMoviesTable.CONTENT_URI, PROJECTION, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (loader.getId() == FavoriteMoviesTable.QUERY_ID) {
            if (cursor.getCount() < 0) {
                Utils.getToast(mContext, mContext.getString(R.string.no_favorite_movies_text));
            } else {
                mFavoriteMovieCursorAdapter.swapCursor(cursor);
                mPopularMoviesGridView.setAdapter(mFavoriteMovieCursorAdapter);
                mCurrentSortChoiceStr = Utils.getResourceString(mContext, R.string.favorite_movies_title_text);
                getActivity().setTitle(mCurrentSortChoiceStr);
            }

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (loader.getId() == FavoriteMoviesTable.QUERY_ID) {
            mFavoriteMovieCursorAdapter.swapCursor(null);
            mPopularMoviesGridView.setAdapter(mFavoriteMovieCursorAdapter);
        }
    }

}
