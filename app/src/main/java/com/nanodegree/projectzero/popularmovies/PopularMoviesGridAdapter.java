package com.nanodegree.projectzero.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nanodegree.projectzero.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Gautam on 27/06/15.
 */
public class PopularMoviesGridAdapter extends BaseAdapter {
    private final LayoutInflater mLayoutInflater;
    private Context mContext;

    public PopularMoviesGridAdapter(Context c) {
        mContext = c;

        mLayoutInflater = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
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

        Picasso.with(mContext).load("https://cms-assets.tutsplus.com/uploads/users/21/posts/19431/featured_image/CodeFeature.jpg").placeholder(R.drawable.sample_5).into(mViewHolder.mGridItemImgView);
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