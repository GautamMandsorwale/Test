package com.gautam.nanodegree.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

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

    public static byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    public static Bitmap getBitmapFromBytes(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
