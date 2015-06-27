package com.nanodegree.projectzero.popularmovies;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.nanodegree.projectzero.R;
import com.nanodegree.projectzero.popularmovies.network.HttpAsyncTask;
import com.nanodegree.projectzero.popularmovies.network.NetworkConstants;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gautam on 27/06/15.
 */
public class PopularMoviesMainActivity extends Activity implements IHttpRequest {

    private GridView mPopularMoviesGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popular_movies);

        initView();

        new HttpAsyncTask(PopularMoviesMainActivity.this).execute(NetworkConstants.MOVIES_HTTP_URL_POPULARITY_DESC + NetworkConstants.MOVIES_DB_API_KEY);
    }

    private void initView() {

        mPopularMoviesGridView = (GridView) findViewById(R.id.popularMoviesGridViewId);
        mPopularMoviesGridView.setAdapter(new PopularMoviesGridAdapter(PopularMoviesMainActivity.this));
    }


    private void parseHttpResponse(String response) {

        try {
            JSONObject mResponseJsonObject = new JSONObject(response);

            mResponseJsonObject.getJSONArray()

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void httpRequestSuccesful(String response) {

        parseHttpResponse(response);
    }

    @Override
    public void httpRequestFailed(String response) {

    }
}
