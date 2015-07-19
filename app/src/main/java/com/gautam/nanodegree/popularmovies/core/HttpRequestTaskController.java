package com.gautam.nanodegree.popularmovies.core;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.constants.NetworkConstants;
import com.gautam.nanodegree.popularmovies.constants.PopularMovieConstants;
import com.gautam.nanodegree.popularmovies.network.HttpRequestTask;
import com.gautam.nanodegree.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                new HttpRequestTask(requestType, this).execute(NetworkConstants.POPULAR_MOVIE_TRAILERS.replace("/id/", "/" + mBundleData+"/") + NetworkConstants.MOVIES_DB_API_KEY);
                break;
            default:
                dismissProgressDialog();
                break;
        }

    }

    private void parseMoviesResponse(String response) {
        JSONArray mMoviesJsonArray = null;
        JSONObject mResponseJsonObject = null;
        MoviesDataModel mMoviesDataModel = null;

        try {
            mResponseJsonObject = new JSONObject(response);
            mMoviesJsonArray = mResponseJsonObject.getJSONArray(PopularMovieConstants.KEY_MOVIE_RESULTS_ARRAY);
            if (mMoviesJsonArray != null) {
                mMoviesDataArrayList = new ArrayList<>();
                for (int i = 0; i < mMoviesJsonArray.length(); i++) {
                    JSONObject mTempJsonObject = mMoviesJsonArray.getJSONObject(i);
                    mMoviesDataModel = new MoviesDataModel();

                    mMoviesDataModel.setIsMovieAdult(mTempJsonObject.getBoolean(PopularMovieConstants.KEY_MOVIE_ADULT));
                    mMoviesDataModel.setMovieBackdropPath(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_BACKDROP_PATH));
                    mMoviesDataModel.setMovieGenreIds(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_GENRE_IDS));
                    mMoviesDataModel.setMovieId(mTempJsonObject.getInt(PopularMovieConstants.KEY_MOVIE_ID));
                    mMoviesDataModel.setMovieOriginalLanguage(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_ORIGINAL_LANGUAGE));
                    mMoviesDataModel.setMovieOriginalTitle(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_ORIGINAL_TITLE));
                    mMoviesDataModel.setMovieOverview(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_OVERVIEW));
                    mMoviesDataModel.setMovieReleaseDate(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_RELEASE_DATE));
                    mMoviesDataModel.setMoviePosterPath(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_POSTER_PATH));
                    mMoviesDataModel.setMoviePopularity(mTempJsonObject.getInt(PopularMovieConstants.KEY_MOVIE_POPULARITY));
                    mMoviesDataModel.setMovieTitle(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_TITLE));
                    mMoviesDataModel.setHasVideo(mTempJsonObject.getBoolean(PopularMovieConstants.KEY_MOVIE_VIDEO));
                    mMoviesDataModel.setMovieVoteAverage(mTempJsonObject.getInt(PopularMovieConstants.KEY_MOVIE_VOTE_AVERAGE));
                    mMoviesDataModel.setMovieVoteCount(mTempJsonObject.getInt(PopularMovieConstants.KEY_MOVIE_VOTE_COUNT));

                    mMoviesDataArrayList.add(mMoviesDataModel);
                }

            }


        } catch (JSONException e) {
            mUpdateViewListener.updateView(null, false);
            e.printStackTrace();
        }

        mUpdateViewListener.updateView(mMoviesDataArrayList, true);

    }

    private void parseMovieTrailersResponse(String response) {
        JSONArray mMoviesJsonArray = null;
        JSONObject mResponseJsonObject = null;
        MoviesDataModel mMoviesDataModel = null;

        try {
            mResponseJsonObject = new JSONObject(response);

//                {
//                    "id":211672, "results":[{
//                    "id":"54badb64c3a3684046001c73", "iso_639_1":"en", "key":"eisKxhjBnZ0", "name":
//                    "Minions Official Trailer #1 (2015) - Despicable Me Prequel HD", "site":
//                    "YouTube", "size":720, "type":"Trailer"
//                }
            mMoviesJsonArray = mResponseJsonObject.getJSONArray(PopularMovieConstants.KEY_MOVIE_RESULTS_ARRAY);
            if (mMoviesJsonArray != null) {
                mMoviesDataArrayList = new ArrayList<>();
                for (int i = 0; i < mMoviesJsonArray.length(); i++) {
                    JSONObject mTempJsonObject = mMoviesJsonArray.getJSONObject(i);
                    mMoviesDataModel = new MoviesDataModel();

                    mMoviesDataModel.setMovieTrailerId(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_TRAILER_ID));
                    mMoviesDataModel.setMovieTrailerIso(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_TRAILER_ISO));
                    mMoviesDataModel.setMovieTrailerKey(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_TRAILER_KEY));
                    mMoviesDataModel.setMovieTrailerName(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_TRAILER_NAME));
                    mMoviesDataModel.setMovieTrailerSite(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_TRAILER_SITE));
                    mMoviesDataModel.setMovieTrailerSize(mTempJsonObject.getInt(PopularMovieConstants.KEY_MOVIE_TRAILER_SIZE));
                    mMoviesDataModel.setMovieTrailerType(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_TRAILER_TYPE));

                    mMoviesDataArrayList.add(mMoviesDataModel);
                }

            }


        } catch (JSONException e) {
            mUpdateViewListener.updateView(null, false);
            e.printStackTrace();
        }

        mUpdateViewListener.updateView(mMoviesDataArrayList, true);

    }

    @Override
    public void httpRequestSuccessful(int requestType, String response) {
        dismissProgressDialog();

        switch (requestType) {
            case PopularMovieConstants.REQUEST_TYPE_MOVIE_TRAILERS:
                parseMovieTrailersResponse(response);
                break;
            case PopularMovieConstants.REQUEST_TYPE_MOST_POPULAR:
            case PopularMovieConstants.REQUEST_TYPE_HIGHEST_RATED:
                parseMoviesResponse(response);
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
