package com.gautam.nanodegree.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gautam.nanodegree.FragmentChangeListener;
import com.gautam.nanodegree.R;
import com.gautam.nanodegree.utils.Utils;

/**
 * Created by Gautam on 09/07/15.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentChangeListener mFragmentChangeListener = null;
    private Button mPopularMoviesBtn, mSuperDuoBtn, mLibraryAppBtn, mBuildItBiggerAppBtn, mXyzReaderBtn, mCapstoneAppBtn;
    private Typeface mTypeface = null;
    private static Context mContext = null;

    public static HomeFragment newInstance(Context context) {
        mContext = context;
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTypeface = Typeface.createFromAsset(mContext.getAssets(), "fonts/Walkway_UltraBold.ttf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_home_screen, null);

        TextView mHeaderTextView = (TextView) mRootView.findViewById(R.id.headerTextViewId);
        mHeaderTextView.setTypeface(mTypeface);

        mPopularMoviesBtn = (Button) mRootView.findViewById(R.id.popularMoviesAppBtnId);
        mPopularMoviesBtn.setOnClickListener(this);
        mSuperDuoBtn = (Button) mRootView.findViewById(R.id.superDuoAppBtnId);
        mSuperDuoBtn.setOnClickListener(this);
        mLibraryAppBtn = (Button) mRootView.findViewById(R.id.libraryAppBtnId);
        mLibraryAppBtn.setOnClickListener(this);
        mBuildItBiggerAppBtn = (Button) mRootView.findViewById(R.id.buildItBiggerAppBtnId);
        mBuildItBiggerAppBtn.setOnClickListener(this);
        mXyzReaderBtn = (Button) mRootView.findViewById(R.id.xyzReaderBtnId);
        mXyzReaderBtn.setOnClickListener(this);
        mCapstoneAppBtn = (Button) mRootView.findViewById(R.id.capstoneAppVBtnId);
        mCapstoneAppBtn.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mFragmentChangeListener = (FragmentChangeListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mFragmentChangeListener = null;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v == mPopularMoviesBtn) {
            mFragmentChangeListener.fragmentChangeEvent(FragmentChangeListener.POPULAR_MOVIES_EVENT, null);
        }
        if (v == mSuperDuoBtn) {
            Utils.getToast(mContext, Utils.getResourceString(mContext, R.string.super_duo_app_toast_text)).show();
        }
        if (v == mLibraryAppBtn) {
            Utils.getToast(mContext, Utils.getResourceString(mContext, R.string.library_app_toast_text)).show();
        }
        if (v == mBuildItBiggerAppBtn) {
            Utils.getToast(mContext, Utils.getResourceString(mContext, R.string.build_it_bigger_app_toast_text)).show();
        }
        if (v == mXyzReaderBtn) {
            Utils.getToast(mContext, Utils.getResourceString(mContext, R.string.xyz_reader_app_toast_text)).show();
        }
        if (v == mCapstoneAppBtn) {
            Utils.getToast(mContext, Utils.getResourceString(mContext, R.string.capstone_app_toast_text)).show();
        }
    }
}
