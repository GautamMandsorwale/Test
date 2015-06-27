package com.nanodegree.projectzero.popularmovies.core;

import android.content.Context;
import android.os.Bundle;

import com.nanodegree.projectzero.popularmovies.constants.PopularMovieConstants;
import com.nanodegree.projectzero.popularmovies.constants.NetworkConstants;
import com.nanodegree.projectzero.popularmovies.network.HttpRequestTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gautam on 28/06/15.
 */
public class HttpRequestTaskController implements HttpRequestStatusListener {

    private ArrayList<MoviesDataModel> mMoviesDataArrayList = null;
    UpdateViewListener mUpdateViewListener = null;


    public HttpRequestTaskController(Context context) {
        this.mUpdateViewListener = (UpdateViewListener) context;
    }


    public void executeHttpRequest(int filterParameter) {

        switch (filterParameter) {
            case PopularMovieConstants.MOST_POPULAR:
                new HttpRequestTask(this).execute(NetworkConstants.MOVIES_HTTP_URL_POPULARITY_DESC + NetworkConstants.MOVIES_DB_API_KEY);
                break;
            case PopularMovieConstants.HIGHEST_RATED:
                break;
            case PopularMovieConstants.FAVOURITES_COLLECTION:
                break;
            default:
                break;
        }

    }

    private void parseHttpResponse(String response) {
        JSONArray mMoviesJsonArray = null;
        JSONObject mResponseJsonObject = null;
        MoviesDataModel mMoviesDataModel = null;

        try {
            mResponseJsonObject = new JSONObject(response);
            if (mResponseJsonObject != null) {
                mMoviesJsonArray = mResponseJsonObject.getJSONArray(PopularMovieConstants.KEY_MOVIES_ARRAY);
                if (mMoviesJsonArray != null) {
                    mMoviesDataArrayList = new ArrayList<>();
                    for (int i = 0; i < mMoviesJsonArray.length(); i++) {
                        JSONObject mTempJsonObject = mMoviesJsonArray.getJSONObject(i);
                        mMoviesDataModel = new MoviesDataModel();

                        mMoviesDataModel.setIsMovieAdult(mTempJsonObject.getBoolean(PopularMovieConstants.KEY_MOVIE_ADULT));
                        mMoviesDataModel.setMovieBackdropPath(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_BACKDROP_PATH));
                        mMoviesDataModel.setMovieGenreIds(mTempJsonObject.getJSONArray(PopularMovieConstants.KEY_MOVIE_GENRE_IDS));
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

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        mUpdateViewListener.updateView(mMoviesDataArrayList, true);

    }

    @Override
    public void httpRequestSuccesful(String response) {
        parseHttpResponse(response);
    }

    @Override
    public void httpRequestFailed(String response) {
        mUpdateViewListener.updateView(null, false);
    }
}
