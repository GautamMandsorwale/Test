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
        public static final int QUERY_ID = 0;

    }

}
