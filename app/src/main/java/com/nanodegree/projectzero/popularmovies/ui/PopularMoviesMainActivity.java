package com.nanodegree.projectzero.popularmovies.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.nanodegree.projectzero.R;
import com.nanodegree.projectzero.popularmovies.constants.PopularMovieConstants;
import com.nanodegree.projectzero.popularmovies.core.HttpRequestTaskController;
import com.nanodegree.projectzero.popularmovies.core.UpdateViewListener;

/**
 * Created by Gautam on 27/06/15.
 */
public class PopularMoviesMainActivity extends Activity implements UpdateViewListener{

    private GridView mPopularMoviesGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popular_movies);

        initView();

        new HttpRequestTaskController(PopularMoviesMainActivity.this).executeHttpRequest(PopularMovieConstants.MOST_POPULAR);

    }

    private void initView() {

        mPopularMoviesGridView = (GridView) findViewById(R.id.popularMoviesGridViewId);
    }

    @Override
    public void updateView(Object data, boolean shouldUpdate) {
        if(shouldUpdate) {
            mPopularMoviesGridView.setAdapter(new PopularMoviesGridAdapter(PopularMoviesMainActivity.this, data));
        }
        else{
            Toast.makeText(this,"Problem",Toast.LENGTH_SHORT).show();
        }
    }
}
