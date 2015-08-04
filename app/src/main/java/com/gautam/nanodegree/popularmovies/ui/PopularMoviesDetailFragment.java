package com.gautam.nanodegree.popularmovies.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.gautam.nanodegree.utils.NetworkUtil;
import com.gautam.nanodegree.utils.Utils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gautam on 09/07/15.
 */
public class PopularMoviesDetailFragment extends Fragment implements UpdateViewListener, ListView.OnTouchListener {

    private static Context mContext = null;
    private MoviesDataModel mMoviesDataModel = null;
    private MoviesDataModel mMoviesDataModel_trailers = null;
    private TextView mPopularMovieTitleTxtView, mPopularMovieReleaseDateTxtView, mPopularMovieRatingTxtView, mPopularMovieOverviewTxtView, mPopularMovieReviewTxtView = null;
    private ImageView mPopularMovieThumbnailImgView = null;
    private ListView mPopularMoviesTrailersListView = null;
    private ListView mPopularMoviesReviewsListView = null;
    private Button mMarkFavoriteBtn = null;
    private boolean mIsNetworkConnected = false;
    private ShareActionProvider mShareActionProvider;

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
        setHasOptionsMenu(true);
        Bundle mBundle = getArguments();
        if (mBundle != null) {
            mMoviesDataModel = mBundle.getParcelable(PopularMovieConstants.KEY_MOVIE_DATA_BUNDLE);

            if (NetworkUtil.getConnectivityStatus(mContext) == 0) {
                mIsNetworkConnected = false;
                Utils.getToast(mContext, mContext.getString(R.string.not_connected_to_internet_text)).show();
            } else {
                mIsNetworkConnected = true;
                new HttpRequestTaskController(mMoviesDataModel.getMovieId(), PopularMoviesDetailFragment.this).executeHttpRequest(PopularMovieConstants.REQUEST_TYPE_MOVIE_TRAILERS);
                new HttpRequestTaskController(mMoviesDataModel.getMovieId(), PopularMoviesDetailFragment.this).executeHttpRequest(PopularMovieConstants.REQUEST_TYPE_MOVIE_REVIEWS);
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_popular_movies_details, null);

        mPopularMovieTitleTxtView = (TextView) mRootView.findViewById(R.id.popularMovieTitleTxtViewId);
        mPopularMovieReleaseDateTxtView = (TextView) mRootView.findViewById(R.id.popularMovieReleaseDateTextViewId);
        mPopularMovieRatingTxtView = (TextView) mRootView.findViewById(R.id.popularMovieRatingTxtViewId);
        mPopularMovieOverviewTxtView = (TextView) mRootView.findViewById(R.id.popularMovieOverviewTextViewId);
        mPopularMovieReviewTxtView = (TextView) mRootView.findViewById(R.id.popularMovieReviewTxtId);
        mPopularMovieThumbnailImgView = (ImageView) mRootView.findViewById(R.id.popularMovieThumbnailImgViewId);
        mPopularMoviesTrailersListView = (ListView) mRootView.findViewById(R.id.movieTrailersListViewId);
        mPopularMoviesReviewsListView = (ListView) mRootView.findViewById(R.id.movieReviewsListViewId);
        mMarkFavoriteBtn = (Button) mRootView.findViewById(R.id.markFavoriteBtnId);

        if (!mIsNetworkConnected) {
            if (mMoviesDataModel.isMovieFavorite()) {
                mMarkFavoriteBtn.setEnabled(false);
                mMarkFavoriteBtn.setText(PopularMovieConstants.IS_YOUR_FAVORITE);
            } else {
                mMarkFavoriteBtn.setEnabled(true);
            }
        }
        mMarkFavoriteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save favorite
                saveFavoriteMovieToDb();
            }
        });

        mPopularMoviesTrailersListView.setOnTouchListener(this);
        mPopularMoviesReviewsListView.setOnTouchListener(this);

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
            showMovieDetails(mMoviesDataModel);
        }


        return mRootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        setHasOptionsMenu(false);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate menu resource file.
        inflater.inflate(R.menu.share_menu, menu);
        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);

        // Fetch and store ShareActionProvider
//        mShareActionProvider = (ShareActionProvider) item.getActionProvider();
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);

        // Connect the dots: give the ShareActionProvider its Share Intent
        if (mShareActionProvider != null && mMoviesDataModel != null) {
            Intent mShareIntent = new Intent(Intent.ACTION_VIEW);
            mShareIntent.setData(Uri.parse(PopularMovieConstants.YOUTUBE_VIDEO_URL + mMoviesDataModel.getMovieTrailerKey()));
            if (mShareActionProvider != null) {
                mShareActionProvider.setShareIntent(mShareIntent);
            }
            mShareActionProvider.setShareIntent(mShareIntent);
        }

    }

    public void showMovieDetails(MoviesDataModel mMoviesDataModel) {
        mPopularMovieTitleTxtView.setText(mMoviesDataModel.getMovieOriginalTitle());
        mPopularMovieReleaseDateTxtView.setText(mMoviesDataModel.getMovieReleaseDate());
        mPopularMovieRatingTxtView.setText(String.valueOf(mMoviesDataModel.getMovieVoteAverage()) + PopularMovieConstants.POPULAR_MOVIES_RATING_TOTAL);
        mPopularMovieOverviewTxtView.setText(mMoviesDataModel.getMovieOverview());

        String mPosterThumbnailUrlStr = NetworkConstants.MOVIE_POSTER_IMAGE_BASE_URL + NetworkConstants.MOVIE_POSTER_THUMBNAIL_PHONE_SIZE;
        Picasso.with(mContext).load(mPosterThumbnailUrlStr + mMoviesDataModel.getMoviePosterPath()).into(mPopularMovieThumbnailImgView);//.into(mTarget);
    }

    private void saveFavoriteMovieToDb() {

        Gson gson = new Gson();
        mMoviesDataModel.setIsMovieFavorite(true);
        String moviesDataObjJsonStr = gson.toJson(mMoviesDataModel);

        PopularMoviesDbWrapper.favoriteMovieInsertQuery(mContext, moviesDataObjJsonStr);

        mMarkFavoriteBtn.setEnabled(false);

    }

    private void updateReviewsList(Object data, boolean shouldUpdate) {
        if (shouldUpdate) {
            if (((ArrayList<MoviesDataModel>) data).size() == 0) {
                mPopularMovieReviewTxtView.setText(R.string.no_movie_reviews_yet);
                mPopularMoviesReviewsListView.setVisibility(View.GONE);
            } else {
                mPopularMoviesReviewsListView.setAdapter(new MovieReviewsAdapter(mContext, data));
            }
        } else {
            mPopularMovieReviewTxtView.setText(R.string.no_movie_reviews_yet);
            mPopularMoviesReviewsListView.setVisibility(View.GONE);
//            Utils.getToast(mContext, mContext.getString(R.string.http_error_msg_text)).show();
        }
    }

    private void updateTrailersList(Object data, boolean shouldUpdate) {
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

    /**
     * Called when a touch event is dispatched to a view. This allows listeners to
     * get a chance to respond before the target view.
     *
     * @param v     The view the touch event has been dispatched to.
     * @param event The MotionEvent object containing full information about
     *              the event.
     * @return True if the listener has consumed the event, false otherwise.
     */
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

    @Override
    public void updateView(Object data, boolean shouldUpdate, int requestType) {
        switch (requestType) {
            case PopularMovieConstants.REQUEST_TYPE_MOVIE_TRAILERS:
                updateTrailersList(data, shouldUpdate);
                break;
            case PopularMovieConstants.REQUEST_TYPE_MOVIE_REVIEWS:
                updateReviewsList(data, shouldUpdate);
                break;
        }
    }
}
