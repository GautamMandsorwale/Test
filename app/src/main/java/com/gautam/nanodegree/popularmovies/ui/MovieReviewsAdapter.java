package com.gautam.nanodegree.popularmovies.ui;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gautam.nanodegree.R;
import com.gautam.nanodegree.popularmovies.core.MoviesDataModel;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Gautam on 19/07/15.
 */
public class MovieReviewsAdapter extends BaseAdapter {
    private final Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<MoviesDataModel> mMoviesDataArrayList = null;

    public MovieReviewsAdapter(Context context, Object data) {
        this.mContext = context;
        this.mMoviesDataArrayList = (ArrayList<MoviesDataModel>) data;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        if (mMoviesDataArrayList != null) {
            return mMoviesDataArrayList.size();
        } else {
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

        ViewHolder mViewHolder;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes

            convertView = mLayoutInflater.inflate(R.layout.popular_movie_reviews_list_row, null);

            mViewHolder = new ViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mMovieReviewerTxtView.setText(Html.fromHtml("<b><i>" + mContext.getString(R.string.movie_reviewer_name_text) + "</i></b>") + mMoviesDataArrayList.get(position).getMovieReviewAuthor());
        mViewHolder.mMovieReviewTxtView.setText(Html.fromHtml(mContext.getString(R.string.movie_review_text)) + mMoviesDataArrayList.get(position).getMovieReviewContent());

        return convertView;
    }

    public class ViewHolder {
        @Bind(R.id.movieReviewerNameTxtId)
        TextView mMovieReviewerTxtView;
        @Bind(R.id.movieReviewTxtId)
        TextView mMovieReviewTxtView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
