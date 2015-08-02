package com.gautam.nanodegree.popularmovies.constants;

import android.net.Uri;

public class PopularMoviesProviderConstants {

    // Authority string for content URIs
    public static String AUTHORITY = "app-portfolio-authority";

    public static final String UNSUPPORTED_URI = "Unsupported uri";

    public static class FavoriteMoviesTable {
        public static final String TABLE_URI_PATH = "favorite_movies";
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_URI_PATH); // Content URI
        public static final String TABLE = "favoriteMovies"; // Table name
        public static final String _ID = "_id"; // Primary key
        public static final String FAVORITE_MOVIES_ID = "favorite_movies_id"; // movie id
        public static final String FAVORITE_MOVIES_DATA = "favorite_movies_data"; // movie data obj
//		public static final String FAVORITE_MOVIES_THUMBNAIL_BITMAP = "favorite_movies_thumbnail"; // movie thumbnail
		public static final int QUERY_ID = 0;


	/*	private boolean mIsMovieAdult = false;
        private String mMovieBackdropPath = null;
		private String mMovieGenreIds = null;
		private int mMovieId = -1;
		private String mMovieOriginalLanguage = null;
		private String mMovieOriginalTitle = null;
		private String mMovieOverview = null;
		private String mMovieReleaseDate = null;
		private String mMoviePosterPath = null;
		private int mMoviePopularity = -1;
		private String mMovieTitle = null;
		private boolean mHasVideo = false;
		private int mMovieVoteAverage = -1;
		private int mMovieVoteCount = -1;
		private String mMovieTrailerId = null;
		private String mMovieTrailerIso = null;
		private String mMovieTrailerKey = null;
		private String mMovieTrailerName = null;
		private String mMovieTrailerSite = null;
		private int mMovieTrailerSize = -1;
		private String mMovieTrailerType = null;
*/

    }

}
