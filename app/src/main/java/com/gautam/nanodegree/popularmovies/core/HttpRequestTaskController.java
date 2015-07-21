package com.gautam.nanodegree.popularmovies.core;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.constants.NetworkConstants;
import com.gautam.nanodegree.popularmovies.constants.PopularMovieConstants;
import com.gautam.nanodegree.popularmovies.network.HttpRequestTask;
import com.gautam.nanodegree.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Gautam on 28/06/15.
 */
public class HttpRequestTaskController implements HttpRequestStatusListener {

    private ArrayList<MoviesDataModel> mMoviesDataArrayList = null;
    private UpdateViewListener mUpdateViewListener = null;
    private ProgressDialog mProgressDialog = null;
    private Fragment mFragment = null;
    private Object mBundleData = null;

    public HttpRequestTaskController(Fragment fragment) {
        this.mFragment = fragment;
        this.mUpdateViewListener = (UpdateViewListener) fragment;
    }

    public HttpRequestTaskController(Object data, Fragment fragment) {
        this.mFragment = fragment;
        this.mBundleData = data;
        this.mUpdateViewListener = (UpdateViewListener) fragment;
    }


    public void executeHttpRequest(int requestType) {

        if (requestType != PopularMovieConstants.REQUEST_TYPE_MOVIE_TRAILERS) {
            mProgressDialog = new ProgressDialog(mFragment.getActivity());
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(Utils.getResourceString(mFragment.getActivity(), R.string.please_wait_msg_text));
            mProgressDialog.show();

        }

        switch (requestType) {
            case PopularMovieConstants.REQUEST_TYPE_MOST_POPULAR:
                new HttpRequestTask(requestType, this).execute(NetworkConstants.MOVIES_HTTP_URL_SORT_POPULARITY_DESC + NetworkConstants.MOVIES_DB_API_KEY);
                break;
            case PopularMovieConstants.REQUEST_TYPE_HIGHEST_RATED:
                new HttpRequestTask(requestType, this).execute(NetworkConstants.MOVIES_HTTP_URL_SORT_HIGHEST_RATED_DESC + NetworkConstants.MOVIES_DB_API_KEY);
                break;
            case PopularMovieConstants.REQUEST_TYPE_MOVIE_TRAILERS:
                new HttpRequestTask(requestType, this).execute(NetworkConstants.POPULAR_MOVIE_TRAILERS.replace("/id/", "/" + mBundleData + "/") + NetworkConstants.MOVIES_DB_API_KEY);
                break;
            default:
                dismissProgressDialog();
                break;
        }

    }

    @Override
    public void httpRequestSuccessful(int requestType, String response) {
        dismissProgressDialog();

        switch (requestType) {
            case PopularMovieConstants.REQUEST_TYPE_MOVIE_TRAILERS:
                mMoviesDataArrayList = MovieJsonParser.parseMovieTrailersResponse(response);
                if (mMoviesDataArrayList != null) {
                    mUpdateViewListener.updateView(mMoviesDataArrayList, true);
                } else {
                    mUpdateViewListener.updateView(null, false);
                }
                break;
            case PopularMovieConstants.REQUEST_TYPE_MOST_POPULAR:
            case PopularMovieConstants.REQUEST_TYPE_HIGHEST_RATED:
                mMoviesDataArrayList = MovieJsonParser.parseMoviesResponse(response);
                if (mMoviesDataArrayList != null) {
                    mUpdateViewListener.updateView(mMoviesDataArrayList, true);
                } else {
                    mUpdateViewListener.updateView(null, false);
                }
                break;
        }
    }

    @Override
    public void httpRequestFailed(String response) {
        dismissProgressDialog();
        mUpdateViewListener.updateView(null, false);

    }

    private void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
