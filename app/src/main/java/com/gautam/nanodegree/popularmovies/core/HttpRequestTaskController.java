package com.gautam.nanodegree.popularmovies.core;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.gautam.nanodegree.popularmovies.constants.NetworkConstants;
import com.gautam.nanodegree.popularmovies.constants.PopularMovieConstants;
import com.gautam.nanodegree.popularmovies.network.HttpRequestTask;

import java.util.ArrayList;

/**
 * Created by Gautam on 28/06/15.
 */
public class HttpRequestTaskController implements HttpRequestStatusListener {

    private UpdateViewListener mUpdateViewListener = null;
    private ProgressDialog mProgressDialog = null;
    private Object mBundleData = null;
    private int mRequestType = -1;

    public HttpRequestTaskController(Fragment fragment) {
        this.mUpdateViewListener = (UpdateViewListener) fragment;
    }

    public HttpRequestTaskController(Object data, Fragment fragment) {
        this.mBundleData = data;
        this.mUpdateViewListener = (UpdateViewListener) fragment;
    }


    public void executeHttpRequest(int requestType) {

        /*if (requestType != PopularMovieConstants.REQUEST_TYPE_MOVIE_TRAILERS) {
            mProgressDialog = new ProgressDialog(mFragment.getActivity());
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(Utils.getResourceString(mFragment.getActivity(), R.string.please_wait_msg_text));
            mProgressDialog.show();

        }*/

        this.mRequestType = requestType;
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
            case PopularMovieConstants.REQUEST_TYPE_MOVIE_REVIEWS:
                new HttpRequestTask(requestType, this).execute(NetworkConstants.POPULAR_MOVIE_REVIEWS.replace("/id", "/" + mBundleData));
                break;
            default:
//                dismissProgressDialog();
                break;
        }

    }

    @Override
    public void httpRequestSuccessful(int requestType, String response) {
//        dismissProgressDialog();
        ArrayList<MoviesDataModel> mMoviesDataArrayList = null;
        switch (requestType) {
            case PopularMovieConstants.REQUEST_TYPE_MOST_POPULAR:
            case PopularMovieConstants.REQUEST_TYPE_HIGHEST_RATED:
                mMoviesDataArrayList = MovieJsonParser.parseMoviesResponse(response);
                break;
            case PopularMovieConstants.REQUEST_TYPE_MOVIE_REVIEWS:
                mMoviesDataArrayList = MovieJsonParser.parseMovieReviewsResponse(response);
                break;
            case PopularMovieConstants.REQUEST_TYPE_MOVIE_TRAILERS:
                mMoviesDataArrayList = MovieJsonParser.parseMovieTrailersResponse(response);
                break;
        }
        if (mMoviesDataArrayList != null) {
            mUpdateViewListener.updateView(mMoviesDataArrayList, true, mRequestType);
        } else {
            mUpdateViewListener.updateView(null, false, -1);
        }
    }

    @Override
    public void httpRequestFailed(String response) {
//        dismissProgressDialog();
        mUpdateViewListener.updateView(null, false, -1);

    }

    /*private void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }*/
}
