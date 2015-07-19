package com.gautam.nanodegree.popularmovies.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.core.MoviesDataModel;

import java.util.ArrayList;

/**
 * Created by Gautam on 19/07/15.
 */
public class MovieTrailersAdapter extends BaseAdapter {
    private final Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<MoviesDataModel> mMoviesDataArrayList = null;

    public MovieTrailersAdapter(Context context, Object data) {
        this.mContext = context;
        this.mMoviesDataArrayList = (ArrayList<MoviesDataModel>) data;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if(mMoviesDataArrayList!=null){
            return mMoviesDataArrayList.size();
        }else{
            return 0;
        }


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

            convertView = mLayoutInflater.inflate(R.layout.popular_movie_trailers_list_row, null);
            mViewHolder.mMovieTrailerTxtView = (TextView) convertView.findViewById(R.id.movieTrailerNameTxtId);

            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mMovieTrailerTxtView.setText(mMoviesDataArrayList.get(position).getMovieTrailerName());

        return convertView;
    }

    public class ViewHolder {
        TextView mMovieTrailerTxtView;
    }
}
