package com.gautam.nanodegree.popularmovies.core;

import com.gautam.nanodegree.popularmovies.constants.PopularMovieConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gautam on 22/07/15.
 */
public class MovieJsonParser {

    private static ArrayList<MoviesDataModel> mMoviesDataArrayList;

    public static ArrayList<MoviesDataModel> parseMoviesResponse(String response) {
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
            e.printStackTrace();
            return null;
        }

        return mMoviesDataArrayList;

    }

    public static ArrayList<MoviesDataModel> parseMovieTrailersResponse(String response) {
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
            e.printStackTrace();
            return null;
        }

        return mMoviesDataArrayList;

    }

}
