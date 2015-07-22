package com.gautam.nanodegree.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Gautam on 22/07/15.
 */
public class NetworkUtil {

    private static int TYPE_WIFI = 1;
    private static int TYPE_MOBILE = 2;
    private static int TYPE_NOT_CONNECTED = 0;
    private static String mNetworkType = null;


    public static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    /*public static boolean getConnectivityStatusString(Context context) {
        boolean mConnectivityStatus = false;
        int conn = NetworkUtil.getConnectivityStatus(context);
        if (conn == NetworkUtil.TYPE_WIFI) {
            mNetworkType = "Wifi enabled";
            mConnectivityStatus = true;
        } else if (conn == NetworkUtil.TYPE_MOBILE) {
            mNetworkType = "Mobile data enabled";
            mConnectivityStatus = true;
        } else if (conn == NetworkUtil.TYPE_NOT_CONNECTED) {
            mNetworkType = "Not connected to Internet";
        }
        return mConnectivityStatus;
    }*/

    public static String getmNetworkType() {
        return mNetworkType;
    }

    public static void setmNetworkType(String mNetworkType) {
        NetworkUtil.mNetworkType = mNetworkType;
    }
}
