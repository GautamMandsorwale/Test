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
        JSONArray mMoviesJsonArray;
        JSONObject mResponseJsonObject;
        MoviesDataModel mMoviesDataModel;

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
        JSONArray mMoviesJsonArray;
        JSONObject mResponseJsonObject;
        MoviesDataModel mMoviesDataModel;

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

    public static ArrayList<MoviesDataModel> parseMovieReviewsResponse(String response) {

        /*{"adult":false,"backdrop_path":"/c1OSRvorPXvGtFka7mgV6Jcw6jd.jpg","belongs_to_collection":{"id":9485,"name":"The Fast and the Furious Collection","poster_path":"/uv63yAGg1zETAs1XQsOQpava87l.jpg","backdrop_path":"/z5A5W3WYJc3UVEWljSGwdjDgQ0j.jpg"},"budget":190000000,"genres":[{"id":28,"name":"Action"},{"id":12,"name":"Adventure"},{"id":80,"name":"Crime"},{"id":10749,"name":"Romance"},{"id":53,"name":"Thriller"}],"homepage":"http://www.furious7.com/","id":168259,"imdb_id":"tt2820852","original_language":"en","original_title":"Furious 7","overview":"Dominic and his crew thought they left the criminal mercenary life behind. They defeated an international terrorist named Owen Shaw and went their seperate ways. But now, Shaw's brother, Deckard Shaw is out killing the crew one by one for revenge. Worse, a Somalian terrorist called Jakarde, and a shady government official called \"Mr. Nobody\" are both competing to steal a computer terrorism program called God's Eye, that can turn any technological device into a weapon. Torretto must reconvene with his team to stop Shaw and retrieve the God's Eye program while caught in a power struggle between terrorist and the United States government","popularity":24.236576,"poster_path":"/dCgm7efXDmiABSdWDHBDBx2jwmn.jpg","production_companies":[{"name":"Universal Pictures","id":33},{"name":"Relativity Media","id":7295}],"production_countries":[{"iso_3166_1":"US","name":"United States of America"}],"release_date":"2015-04-03","revenue":1506249360,"runtime":140,"spoken_languages":[{"iso_639_1":"en","name":"English"}],"status":"Released","tagline":"Vengeance Hits Home","title":"Furious 7","video":false,"vote_average":7.6,"vote_count":1288,
        "reviews":{"page":1,
        "results":[{"id":"553ba24592514138a90067c6","author":"Kenechukwu","content":"Revenge is so so cold...... action packed, thrilling and suspense filled but still some bits of make believe tricks here and there. \r\nHow did Hobbs know that there's a need for the drone to be brought down right after leaving the hospital bed? how did he know where the drone was at the moment when he left with the ambulance? How did Deckard Shaw know both that Toretto and his crew will be both on the route of Afghanistan and also when they were at Abu Dhabi? \r\nIts a little bit odd and out of place for the director and script editor.... \r\nAs for the next upcoming part in the franchise, i think Mark Wahlberg should be considered for a part in the rest of Fast and furious franchise..... Cheers ! ! !","url":"http://j.mp/1Dvxesh"}],"total_pages":1,"total_results":1}}*/

        JSONArray mMovieReviewJsonArray;
        JSONObject mResponseJsonObject;
        MoviesDataModel mMoviesDataModel;

        try {
            mResponseJsonObject = new JSONObject(response);
            mMovieReviewJsonArray = mResponseJsonObject.getJSONObject(PopularMovieConstants.KEY_MOVIE_REVIEWS).getJSONArray(PopularMovieConstants.KEY_MOVIE_RESULTS_ARRAY);
            if (mMovieReviewJsonArray != null) {
                mMoviesDataArrayList = new ArrayList<>();
                for (int i = 0; i < mMovieReviewJsonArray.length(); i++) {
                    JSONObject mTempJsonObject = mMovieReviewJsonArray.getJSONObject(i);
                    mMoviesDataModel = new MoviesDataModel();

                    mMoviesDataModel.setMovieReviewAuthor(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_REVIEW_AUTHOR));
                    mMoviesDataModel.setMovieReviewContent(mTempJsonObject.getString(PopularMovieConstants.KEY_MOVIE_REVIEW_CONTENT));

                    mMoviesDataArrayList.add(mMoviesDataModel);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return mMoviesDataArrayList;
    }

}
