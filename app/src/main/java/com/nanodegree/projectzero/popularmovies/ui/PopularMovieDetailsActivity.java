package com.nanodegree.projectzero.popularmovies.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanodegree.projectzero.R;
import com.nanodegree.projectzero.popularmovies.constants.NetworkConstants;
import com.nanodegree.projectzero.popularmovies.constants.PopularMovieConstants;
import com.nanodegree.projectzero.popularmovies.core.MoviesDataModel;
import com.squareup.picasso.Picasso;

/**
 * Created by Gautam on 28/06/15.
 */
public class PopularMovieDetailsActivity extends ActionBarActivity {

    private MoviesDataModel mMoviesDataModel = null;
    private TextView mPopularMovieTitleTxtView, mPopularMovieReleaseDateTxtView, mPopularMovieRatingTxtView, mPopularMovieOverviewTxtView = null;
    private ImageView mPopularMovieThumbnailImgView = null;
    private String mPosterThumbnailUrlStr = NetworkConstants.MOVIE_POSTER_IMAGE_BASE_URL + NetworkConstants.MOVIE_POSTER_THUMBNAIL_PHONE_SIZE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(PopularMovieConstants.POPULAR_MOVIES_DETAIL_ACTIVITY_TITLE);
        setContentView(R.layout.activity_popular_movie_details);

        Bundle mBundle = this.getIntent().getExtras();
        if (mBundle != null) {
            mMoviesDataModel = mBundle.getParcelable(PopularMovieConstants.KEY_MOVIE_DATA_BUNDLE);

            initView();
        }
    }

    private void initView() {
        mPopularMovieTitleTxtView = (TextView) findViewById(R.id.popularMovieTitleTxtViewId);
        mPopularMovieReleaseDateTxtView = (TextView) findViewById(R.id.popularMovieReleaseDateTextViewId);
        mPopularMovieRatingTxtView = (TextView) findViewById(R.id.popularMovieRatingTxtViewId);
        mPopularMovieOverviewTxtView = (TextView) findViewById(R.id.popularMovieOverviewTextViewId);

        mPopularMovieThumbnailImgView = (ImageView) findViewById(R.id.PopularMovieThumbnailImgViewId);

        mPopularMovieTitleTxtView.setText(mMoviesDataModel.getMovieOriginalTitle());
        mPopularMovieReleaseDateTxtView.setText(mMoviesDataModel.getMovieReleaseDate());
        mPopularMovieRatingTxtView.setText(String.valueOf(mMoviesDataModel.getMovieVoteAverage()) + PopularMovieConstants.POPULAR_MOVIES_RATING_TOTAL);
        mPopularMovieOverviewTxtView.setText(mMoviesDataModel.getMovieOverview());


        Picasso.with(PopularMovieDetailsActivity.this).load(mPosterThumbnailUrlStr + mMoviesDataModel.getMoviePosterPath()).into(mPopularMovieThumbnailImgView);
    }
}
