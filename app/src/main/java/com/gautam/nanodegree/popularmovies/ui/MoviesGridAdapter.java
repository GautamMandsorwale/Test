package com.gautam.nanodegree.popularmovies.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.constants.NetworkConstants;
import com.gautam.nanodegree.popularmovies.core.MoviesDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gautam on 27/06/15.
 */
public class MoviesGridAdapter extends BaseAdapter {
    private final LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<MoviesDataModel> mMoviesDataArrayList = null;

    public MoviesGridAdapter(Context context, Object data) {
        this.mContext = context;
        this.mMoviesDataArrayList = (ArrayList<MoviesDataModel>) data;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mMoviesDataArrayList.size();

    }

    public MoviesDataModel getItem(int position) {
        return mMoviesDataArrayList.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder mViewHolder = null;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes

            mViewHolder = new ViewHolder();

            convertView = mLayoutInflater.inflate(R.layout.popular_movies_grid_item, null);
            mViewHolder.mGridItemImgView = (ImageView) convertView.findViewById(R.id.popularMoviesGridItemImgViewId);

            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        String mPosterUrlStr = NetworkConstants.MOVIE_POSTER_IMAGE_BASE_URL + NetworkConstants.MOVIE_POSTER_IMAGE_PHONE_SIZE + mMoviesDataArrayList.get(position).getMoviePosterPath();
        Picasso.with(mContext).load(mPosterUrlStr).into(mViewHolder.mGridItemImgView);

        return convertView;
    }

    public class ViewHolder {
        ImageView mGridItemImgView;
    }
}