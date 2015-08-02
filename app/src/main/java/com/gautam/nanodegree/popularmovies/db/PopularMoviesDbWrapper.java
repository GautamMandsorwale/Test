package com.gautam.nanodegree.popularmovies.db;

import android.content.ContentValues;
import android.content.Context;

import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.constants.PopularMoviesProviderConstants.FavoriteMoviesTable;
import com.gautam.nanodegree.utils.Utils;


/**
 * The Class PopularMoviesDbWrapper.
 */
public class PopularMoviesDbWrapper {

    private static final String TAG = "PopularMoviesDbWrapper";

    public static void favoriteMovieInsertQuery(final Context context, final String moviesDataObjJsonStr) {
        final ContentValues values = new ContentValues();
        values.put(FavoriteMoviesTable.FAVORITE_MOVIES_DATA, moviesDataObjJsonStr);

        context.getContentResolver().insert(FavoriteMoviesTable.CONTENT_URI, values);
        Utils.getToast(context, context.getString(R.string.marked_as_favorite_movie_text)).show();
    }

}
