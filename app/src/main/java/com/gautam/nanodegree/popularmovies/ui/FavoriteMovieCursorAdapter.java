package com.gautam.nanodegree.popularmovies.ui;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;

import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.constants.NetworkConstants;
import com.gautam.nanodegree.popularmovies.constants.PopularMoviesProviderConstants.FavoriteMoviesTable;
import com.gautam.nanodegree.popularmovies.core.MoviesDataModel;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Gautam on 22/07/15.
 */
public class FavoriteMovieCursorAdapter extends CursorAdapter {
    private LayoutInflater mLayoutInflater = null;
    private Context mContext;


    public FavoriteMovieCursorAdapter(Context context) {
        super(context, null, 0);
        mContext = context;
        // Stores inflater for use later
        mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * Makes a new view to hold the data pointed to by cursor.
     *
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflates the list item layout.
        final View mConvertView = mLayoutInflater.inflate(R.layout.popular_movies_grid_item,
                parent, false);

        // Creates a new ViewHolder in which to store handles to each view
        // resource. This
        // allows bindView() to retrieve stored references instead of calling
        // findViewById for
        // each instance of the layout.
        final ViewHolder mViewHolder = new ViewHolder(mConvertView);

        // Stores the resourceHolder instance in mConvertView. This makes
        // resourceHolder
        // available to bindView and other methods that receive a handle to the
        // item view.
        mConvertView.setTag(mViewHolder);

        return mConvertView;
    }

    /**
     * Bind an existing view to the data pointed to by cursor
     *
     * @param view    Existing view, returned earlier by newView
     * @param context Interface to application's global information
     * @param cursor  The cursor from which to get the data. The cursor is already
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
// Gets handles to individual view resources
        final ViewHolder mViewHolder = (ViewHolder) view.getTag();

        String mFavoriteMovieStr = cursor.getString(cursor.getColumnIndex(FavoriteMoviesTable.FAVORITE_MOVIES_DATA));

        Gson gson = new Gson();
        MoviesDataModel mMoviesDataModel = gson.fromJson(mFavoriteMovieStr, MoviesDataModel.class);
        String mPosterUrlStr = NetworkConstants.MOVIE_POSTER_IMAGE_BASE_URL + NetworkConstants.MOVIE_POSTER_IMAGE_PHONE_SIZE + mMoviesDataModel.getMoviePosterPath();
        Picasso.with(mContext).load(mPosterUrlStr).into(mViewHolder.mGridItemImgView);

    }

    /**
     * Overrides swapCursor to move the new Cursor into the CursorAdapter.
     */
    @Override
    public Cursor swapCursor(Cursor newCursor) {
        return super.swapCursor(newCursor);
    }

    /**
     * An override of getCount that simplifies accessing the Cursor. If the
     * Cursor is null, getCount returns zero. As a result, no test for Cursor ==
     * null is needed.
     */
    @Override
    public int getCount() {
        if (getCursor() == null) {
            return 0;
        }
        return super.getCount();
    }

    /**
     * A class that defines fields for each resource ID in the list item layout.
     * This allows ConversationSearchAdapter.newView() to store the IDs once, when it
     * inflates the layout, instead of calling findViewById in each iteration of
     * bindView.
     */
    class ViewHolder {
        @Bind(R.id.popularMoviesGridItemImgViewId)
        ImageView mGridItemImgView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
