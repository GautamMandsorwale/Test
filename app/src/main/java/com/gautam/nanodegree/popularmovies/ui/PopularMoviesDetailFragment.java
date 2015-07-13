package com.gautam.nanodegree.popularmovies.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.constants.NetworkConstants;
import com.gautam.nanodegree.popularmovies.constants.PopularMovieConstants;
import com.gautam.nanodegree.popularmovies.core.MoviesDataModel;
import com.gautam.nanodegree.utils.Utils;
import com.squareup.picasso.Picasso;

/**
 * Created by Gautam on 09/07/15.
 */
public class PopularMoviesDetailFragment extends Fragment {

    private static Context mContext = null;
    private MoviesDataModel mMoviesDataModel = null;
    private TextView mPopularMovieTitleTxtView, mPopularMovieReleaseDateTxtView, mPopularMovieRatingTxtView, mPopularMovieOverviewTxtView = null;
    private ImageView mPopularMovieThumbnailImgView = null;


    public static PopularMoviesDetailFragment newInstance(Context context, Object data) {
        mContext = context;

        Bundle mBundle = new Bundle();
        mBundle.putParcelable(PopularMovieConstants.KEY_MOVIE_DATA_BUNDLE, (MoviesDataModel) data);
        PopularMoviesDetailFragment mPopularMoviesDetailFragment = new PopularMoviesDetailFragment();
        mPopularMoviesDetailFragment.setArguments(mBundle);
        return mPopularMoviesDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle mBundle = getArguments();
        if (mBundle != null) {
            mMoviesDataModel = mBundle.getParcelable(PopularMovieConstants.KEY_MOVIE_DATA_BUNDLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_popular_movies_details, null);

        mPopularMovieTitleTxtView = (TextView) mRootView.findViewById(R.id.popularMovieTitleTxtViewId);
        mPopularMovieReleaseDateTxtView = (TextView) mRootView.findViewById(R.id.popularMovieReleaseDateTextViewId);
        mPopularMovieRatingTxtView = (TextView) mRootView.findViewById(R.id.popularMovieRatingTxtViewId);
        mPopularMovieOverviewTxtView = (TextView) mRootView.findViewById(R.id.popularMovieOverviewTextViewId);
        mPopularMovieThumbnailImgView = (ImageView) mRootView.findViewById(R.id.popularMovieThumbnailImgViewId);


        if (mMoviesDataModel != null) {
            showMovieDetais(mMoviesDataModel);
        }


        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (getResources().getBoolean(R.bool.isDeviceTypePhone)) {
            getTargetFragment().setMenuVisibility(true);
            getActivity().setTitle(PopularMoviesFragment.mCurrentSortChoiceStr);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getResources().getBoolean(R.bool.isDeviceTypePhone)) {
            getTargetFragment().setMenuVisibility(false);
            getActivity().setTitle(Utils.getResourceString(mContext, R.string.movie_details_title_text));
        }
    }

    public void showMovieDetais(MoviesDataModel mMoviesDataModel) {
        mPopularMovieTitleTxtView.setText(mMoviesDataModel.getMovieOriginalTitle());
        mPopularMovieReleaseDateTxtView.setText(mMoviesDataModel.getMovieReleaseDate());
        mPopularMovieRatingTxtView.setText(String.valueOf(mMoviesDataModel.getMovieVoteAverage()) + PopularMovieConstants.POPULAR_MOVIES_RATING_TOTAL);
        mPopularMovieOverviewTxtView.setText(mMoviesDataModel.getMovieOverview());


        String mPosterThumbnailUrlStr = NetworkConstants.MOVIE_POSTER_IMAGE_BASE_URL + NetworkConstants.MOVIE_POSTER_THUMBNAIL_PHONE_SIZE;
        Picasso.with(mContext).load(mPosterThumbnailUrlStr + mMoviesDataModel.getMoviePosterPath()).into(mPopularMovieThumbnailImgView);
    }
}
