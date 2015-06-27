package com.nanodegree.projectzero.popularmovies.core;

/**
 * Created by Gautam on 28/06/15.
 */
public interface HttpRequestStatusListener {

    public void httpRequestSuccesful(String response);

    public void httpRequestFailed(String response);
}
