package com.nanodegree.projectzero;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nanodegree.projectzero.utils.Utils;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    private Button mSpotifyStreamerBtn, mSuperDuoBtn, mLibraryAppBtn, mBuildItBiggerAppBtn, mXyzReaderBtn, mCapstoneAppBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }


    private void initView() {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Walkway_UltraBold.ttf");

        TextView mHeaderTextView = (TextView) findViewById(R.id.headerTextViewId);
        mHeaderTextView.setTypeface(font);

        mSpotifyStreamerBtn = (Button) findViewById(R.id.spotifyStreamerAppBtnId);
        mSpotifyStreamerBtn.setOnClickListener(this);
        mSuperDuoBtn = (Button) findViewById(R.id.superDuoAppBtnId);
        mSuperDuoBtn.setOnClickListener(this);
        mLibraryAppBtn = (Button) findViewById(R.id.libraryAppBtnId);
        mLibraryAppBtn.setOnClickListener(this);
        mBuildItBiggerAppBtn = (Button) findViewById(R.id.buildItBiggerAppBtnId);
        mBuildItBiggerAppBtn.setOnClickListener(this);
        mXyzReaderBtn = (Button) findViewById(R.id.xyzReaderBtnId);
        mXyzReaderBtn.setOnClickListener(this);
        mCapstoneAppBtn = (Button) findViewById(R.id.capstoneAppVBtnId);
        mCapstoneAppBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mSpotifyStreamerBtn) {
            showToastMessage(R.string.spotify_streamer_app_toast_text);
        }
        if (v == mSuperDuoBtn) {
            showToastMessage(R.string.super_duo_app_toast_text);
        }
        if (v == mLibraryAppBtn) {
            showToastMessage(R.string.library_app_toast_text);
        }
        if (v == mBuildItBiggerAppBtn) {
            showToastMessage(R.string.build_it_bigger_app_toast_text);
        }
        if (v == mXyzReaderBtn) {
            showToastMessage(R.string.xyz_reader_app_toast_text);
        }
        if (v == mCapstoneAppBtn) {
            showToastMessage(R.string.capstone_app_toast_text);
        }
    }

    private void showToastMessage(int resId) {
        Utils.getToast(MainActivity.this, Utils.getResourceString(MainActivity.this, resId)).show();
    }

}
