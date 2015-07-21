package com.gautam.nanodegree.popularmovies.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gautam.nanodegree.popularmovies.constants.PopularMoviesProviderConstants.FavoriteMoviesTable;

public class PopularMoviesDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "PopularMoviesDbHelper";
    public static final String DATABASE_NAME = "popular_movies.db";

    static final int DB_VERSION_1 = 1;
    static final int DB_VERSION_CURRENT = DB_VERSION_1;

    private static PopularMoviesDbHelper mPopularMoviesDbHelperInstance = null;

    public PopularMoviesDbHelper(final Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION_CURRENT);
    }

    public static synchronized PopularMoviesDbHelper getInstance(final Context context) {
        if (mPopularMoviesDbHelperInstance == null) {
            mPopularMoviesDbHelperInstance = new PopularMoviesDbHelper(context);
        }
        return mPopularMoviesDbHelperInstance;
    }

    @Override
    public void onCreate(final SQLiteDatabase sqliteDb) {
        createFavoriteMoviesTable(sqliteDb);

    }

    @Override
    public void onUpgrade(final SQLiteDatabase db, final int oldVersion, final int newVersion) {
        Log.v(TAG, "Enter onUpgrade. oldVersion=" + oldVersion + "; newVersion=" + newVersion);
    }

    public static void resetInstance() {
        mPopularMoviesDbHelperInstance = null;
    }

    private void createFavoriteMoviesTable(final SQLiteDatabase sqliteDb) {
        sqliteDb.execSQL("CREATE TABLE " + FavoriteMoviesTable.TABLE + " (" + FavoriteMoviesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + FavoriteMoviesTable.FAVORITE_MOVIES_ID + " INTEGER," + FavoriteMoviesTable.FAVORITE_MOVIES_DATA + " TEXT);");

    }

}
