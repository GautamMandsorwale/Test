package com.nanodegree.projectzero.popularmovies.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nanodegree.projectzero.R;
import com.nanodegree.projectzero.popularmovies.constants.NetworkConstants;
import com.nanodegree.projectzero.popularmovies.core.MoviesDataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gautam on 27/06/15.
 */
public class PopularMoviesGridAdapter extends BaseAdapter {
    private final LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<MoviesDataModel> mMoviesDataArrayList = null;

    public PopularMoviesGridAdapter(Context context, Object data) {
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
            mViewHolder.mGridItemImgView = (ImageView) convertView.findViewById(R.id.mPopularMoviesGridItemImgViewId);

            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        String mPosterUrlStr = NetworkConstants.MOVIE_POSTER_IMAGE_BASE_URL + NetworkConstants.MOVIE_POSTER_IMAGE_PHONE_SIZE + mMoviesDataArrayList.get(position).getMoviePosterPath();
       Picasso.with(mContext).load(mPosterUrlStr).placeholder(R.drawable.abc_spinner_mtrl_am_alpha).into(mViewHolder.mGridItemImgView);

//        Picasso.with(mContext).load("https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg").placeholder(R.drawable.sample_5).into(mViewHolder.mGridItemImgView);
//        mViewHolder.mGridItemImgView.setImageResource(mThumbIds[position]);
        return convertView;
    }

    public class ViewHolder {
        ImageView mGridItemImgView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5,
            R.drawable.sample_5, R.drawable.sample_5
    };
}