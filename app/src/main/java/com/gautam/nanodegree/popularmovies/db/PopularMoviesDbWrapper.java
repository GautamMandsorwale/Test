package com.gautam.nanodegree.popularmovies.db;

import android.content.ContentValues;
import android.content.Context;

import com.gautam.nanodegree.popularmovies.constants.PopularMoviesProviderConstants.FavoriteMoviesTable;


/**
 * The Class PopularMoviesDbWrapper.
 */
public class PopularMoviesDbWrapper {

    private static final String TAG = "PopularMoviesDbWrapper";

    private static Context mContext = null;

    public static void favoriteMovieInsertQuery(final Context context, final String moviesDataObjJsonStr) {
        final ContentValues values = new ContentValues();
        values.put(FavoriteMoviesTable.FAVORITE_MOVIES_DATA, moviesDataObjJsonStr);

        context.getContentResolver().insert(FavoriteMoviesTable.CONTENT_URI, values);

    }

/*
    public static void insertIntoTestCaseMetadataTable(final Context context) {
        mContext = context;

        performInsertQuery(23, "GlobalTimeOut", "300");
        performInsertQuery(23, "ReceivingSMSText", "00000000000000000000000000000000000000000000000000000000000000000000000000000000");
        performInsertQuery(23, "SendingSMSText", "11111111111111111111111111111111111111111111111111111111111111111111111111111111");
        performInsertQuery(23, "WaitTimeForSMSReceive", "120");
        performInsertQuery(23, "TargetPhoneNo", "1111111111");
        performInsertQuery(300, "GlobalTimeOut", "300");
        performInsertQuery(46, "GlobalTimeOut", "300");
        performInsertQuery(46, "EMBServerUrl", "http://108.229.9.53");
        performInsertQuery(46, "EMBServerULControlPort", "62122");
        performInsertQuery(46, "EMBServerULDataPort", "62123");
        performInsertQuery(46, "EMBServerDLPort", "62121");
        performInsertQuery(46, "EMBULPackets", "22");
        performInsertQuery(46, "EMBDLPackets", "33");
        performInsertQuery(46, "EMBPacketSize", "1376");
        performInsertQuery(46, "EMBDelay", "1");
        performInsertQuery(46, "EMBRetryCount", "3");
        performInsertQuery(48, "GlobalTimeOut", "300");
        performInsertQuery(48, "TargetPhoneNo", "0000000000");
        performInsertQuery(29, "GlobalTimeOut", "300");
        performInsertQuery(29, "HttpDownloadUrl", "http://108.229.65.59/FileGen30MB.bin");
        performInsertQuery(29, "HttpDownloadTimeSec", "10");
        performInsertQuery(29, "HttpDownloadNumThreads", "4");
        performInsertQuery(30, "GlobalTimeOut", "300");
        performInsertQuery(30, "HttpUploadUrl", "http://108.229.33.91/cgi-bin/upload.php");
        performInsertQuery(30, "HttpUploadNumThreads", "4");
        performInsertQuery(30, "HttpUploadTimeSec", "10");
        performInsertQuery(50, "GlobalTimeOut", "300");

    }

    *//**
     * Perform update query.
     *
     * @param context                 the context
     * @param _id                     the _id
     * @param testcase_metadata_key   the testcase_metadata_key
     * @param testcase_metadata_value the testcase_metadata_value
     * @return the int
     *//*
    public static int performUpdateQuery(final Context context, final int _id, final String testcase_metadata_key, final String testcase_metadata_value) {
        final ContentValues values = new ContentValues();
        values.put(TestCaseMetaDataTable.TESTCASE_ID, _id);
        values.put(TestCaseMetaDataTable.TESTCASE_METADATA_KEY, testcase_metadata_key);
        values.put(TestCaseMetaDataTable.TESTCASE_METADATA_VALUE, testcase_metadata_value);

        return context.getContentResolver().update(TestCaseMetaDataTable.CONTENT_URI, values, TestCaseMetaDataTable.TESTCASE_ID + " = ? AND " + TestCaseMetaDataTable.TESTCASE_METADATA_KEY + " = ?", new String[]{String.valueOf(_id), testcase_metadata_key});

    }*/
}
