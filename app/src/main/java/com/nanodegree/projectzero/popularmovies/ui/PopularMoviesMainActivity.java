package com.nanodegree.projectzero.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.nanodegree.projectzero.R;
import com.nanodegree.projectzero.popularmovies.constants.PopularMovieConstants;
import com.nanodegree.projectzero.popularmovies.core.HttpRequestTaskController;
import com.nanodegree.projectzero.popularmovies.core.MoviesDataModel;
import com.nanodegree.projectzero.popularmovies.core.UpdateViewListener;

/**
 * Created by Gautam on 27/06/15.
 */
public class PopularMoviesMainActivity extends ActionBarActivity implements UpdateViewListener {

    private GridView mPopularMoviesGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(PopularMovieConstants.POPULAR_MOVIES_ACTIVITY_TITLE);
        setContentView(R.layout.activity_popular_movies);

        initView();

        new HttpRequestTaskController(PopularMoviesMainActivity.this).executeHttpRequest(PopularMovieConstants.MOST_POPULAR);

    }

    private void initView() {

        mPopularMoviesGridView = (GridView) findViewById(R.id.popularMoviesGridViewId);
        mPopularMoviesGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MoviesDataModel mMoviesDataModel = (MoviesDataModel) parent.getItemAtPosition(position);
                Intent mIntent = new Intent(PopularMoviesMainActivity.this, PopularMovieDetailsActivity.class);
                mIntent.putExtra(PopularMovieConstants.KEY_MOVIE_DATA_BUNDLE, mMoviesDataModel);
                startActivity(mIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popularMoviesMenuId:
                new HttpRequestTaskController(PopularMoviesMainActivity.this).executeHttpRequest(PopularMovieConstants.MOST_POPULAR);
                return true;
            case R.id.highestRatedMoviesMenuId:
                new HttpRequestTaskController(PopularMoviesMainActivity.this).executeHttpRequest(PopularMovieConstants.HIGHEST_RATED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void updateView(Object data, boolean shouldUpdate) {
        if (shouldUpdate) {
            mPopularMoviesGridView.setAdapter(new PopularMoviesGridAdapter(PopularMoviesMainActivity.this, data));
        } else {
            Toast.makeText(this, "Problem", Toast.LENGTH_SHORT).show();
        }
    }
}
