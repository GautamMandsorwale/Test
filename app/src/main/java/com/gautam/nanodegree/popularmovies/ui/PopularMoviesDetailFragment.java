package com.gautam.nanodegree.popularmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.constants.NetworkConstants;
import com.gautam.nanodegree.popularmovies.constants.PopularMovieConstants;
import com.gautam.nanodegree.popularmovies.core.HttpRequestTaskController;
import com.gautam.nanodegree.popularmovies.core.MoviesDataModel;
import com.gautam.nanodegree.popularmovies.core.UpdateViewListener;
import com.gautam.nanodegree.popularmovies.db.PopularMoviesDbWrapper;
import com.gautam.nanodegree.utils.Utils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gautam on 09/07/15.
 */
public class PopularMoviesDetailFragment extends Fragment implements UpdateViewListener {

    private static Context mContext = null;
    private MoviesDataModel mMoviesDataModel = null;
    private MoviesDataModel mMoviesDataModel_trailers = null;
    private TextView mPopularMovieTitleTxtView, mPopularMovieReleaseDateTxtView, mPopularMovieRatingTxtView, mPopularMovieOverviewTxtView = null;
    private ImageView mPopularMovieThumbnailImgView = null;
    private ListView mPopularMoviesTrailersListView = null;
    private Button mMarkFavoriteBtn = null;


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
            new HttpRequestTaskController(mMoviesDataModel.getMovieId(), PopularMoviesDetailFragment.this).executeHttpRequest(PopularMovieConstants.REQUEST_TYPE_MOVIE_TRAILERS);
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
        mPopularMoviesTrailersListView = (ListView) mRootView.findViewById(R.id.movieTrailersListViewId);
        mMarkFavoriteBtn = (Button) mRootView.findViewById(R.id.markFavoriteBtnId);

        mMarkFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save favorite
                saveFavoriteMovieToDb();
            }
        });

        mPopularMoviesTrailersListView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        mPopularMoviesTrailersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                MoviesDataModel mMoviesDataModel = (MoviesDataModel) parent.getItemAtPosition(position);
                intent.setData(Uri.parse(PopularMovieConstants.YOUTUBE_VIDEO_URL + mMoviesDataModel.getMovieTrailerKey()));
                startActivity(intent);
            }
        });

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

    private void saveFavoriteMovieToDb() {

        Gson gson = new Gson();
        mMoviesDataModel.setIsMovieFavorite(true);
        String moviesDataObjJsonStr = gson.toJson(mMoviesDataModel);

        PopularMoviesDbWrapper.favoriteMovieInsertQuery(mContext, moviesDataObjJsonStr);

        mMarkFavoriteBtn.setEnabled(false);

    }

    @Override
    public void updateView(Object data, boolean shouldUpdate) {
        if (shouldUpdate) {
            mMoviesDataModel.setMovieTrailersArr((ArrayList<MoviesDataModel>) data);
            mPopularMoviesTrailersListView.setAdapter(new MovieTrailersAdapter(mContext, data));
            if (mMoviesDataModel.isMovieFavorite()) {
                mMarkFavoriteBtn.setEnabled(false);
                mMarkFavoriteBtn.setText(PopularMovieConstants.IS_YOUR_FAVORITE);
            } else {
                mMarkFavoriteBtn.setEnabled(true);
            }

        } else {
            Utils.getToast(mContext, mContext.getString(R.string.http_error_msg_text)).show();
        }
    }
}
