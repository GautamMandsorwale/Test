package com.gautam.nanodegree.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Gautam on 27/06/15.
 */
public class Utils {

    public static String getResourceString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static Toast getToast(Context context, String toastMessage) {
        return Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT);
    }

}
