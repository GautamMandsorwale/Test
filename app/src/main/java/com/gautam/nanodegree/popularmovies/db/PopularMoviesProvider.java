package com.gautam.nanodegree.popularmovies.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import com.gautam.nanodegree.popularmovies.constants.PopularMoviesProviderConstants;
import com.gautam.nanodegree.popularmovies.constants.PopularMoviesProviderConstants.FavoriteMoviesTable;

import java.io.FileNotFoundException;

public class PopularMoviesProvider extends ContentProvider {

    private SQLiteOpenHelper mOpenHelper;
    private final static String TAG = "PopularMoviesProvider";
    private static final UriMatcher sURLMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    private static final int URI_FAVORITE_MOVIES = 0;

    static {
        sURLMatcher.addURI(PopularMoviesProviderConstants.AUTHORITY, FavoriteMoviesTable.TABLE_URI_PATH, URI_FAVORITE_MOVIES);
    }

    @Override
    public int delete(final Uri uri, final String selection, final String[] selectionArgs) {
        final int match = sURLMatcher.match(uri);
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        int id;
        switch (match) {
            case URI_FAVORITE_MOVIES: {
                id = db.delete(FavoriteMoviesTable.TABLE, selection, selectionArgs);
                break;
            }

            default:
                throw new UnsupportedOperationException(PopularMoviesProviderConstants.UNSUPPORTED_URI);
        }
        return id;
    }

    @Override
    public String getType(final Uri uri) {
        return null;
    }

    @Override
    public Uri insert(final Uri uri, final ContentValues contentValues) {
        final int match = sURLMatcher.match(uri);
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final Uri insertUri = null;
        switch (match) {
            case URI_FAVORITE_MOVIES: {
                db.insert(FavoriteMoviesTable.TABLE, null, contentValues);
                break;
            }
            default:
                throw new UnsupportedOperationException(PopularMoviesProviderConstants.UNSUPPORTED_URI);
        }
        return insertUri;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = PopularMoviesDbHelper.getInstance(getContext());

        return false;
    }

    @Override
    public Cursor query(final Uri uri, final String[] projection, final String selection, final String[] selectionArgs, final String sortOrder) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();

        Cursor cursor = null;
        final int match = sURLMatcher.match(uri);
        switch (match) {
            case URI_FAVORITE_MOVIES: {

                cursor = db.query(FavoriteMoviesTable.TABLE, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            }

            default:
                throw new UnsupportedOperationException(PopularMoviesProviderConstants.UNSUPPORTED_URI);
        }
        return cursor;
    }

    @Override
    public int update(final Uri uri, final ContentValues contentValues, final String selection, final String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getReadableDatabase();
        final int match = sURLMatcher.match(uri);
        int rowsUpdated = 0;
        switch (match) {

            case URI_FAVORITE_MOVIES: {
                rowsUpdated = db.update(FavoriteMoviesTable.TABLE, contentValues, selection, selectionArgs);
                break;
            }
            default:
                throw new UnsupportedOperationException(PopularMoviesProviderConstants.UNSUPPORTED_URI);
        }
        return rowsUpdated;
    }

    @Override
    public ParcelFileDescriptor openFile(final Uri uri, final String mode) throws FileNotFoundException {
        final ParcelFileDescriptor fd = openFileHelper(uri, mode);
        return fd;
    }
}