package com.nanodegree.projectzero.popularmovies.core;

/**
 * Created by Gautam on 28/06/15.
 */
public interface HttpRequestStatusListener {

    void httpRequestSuccesful(String response);

    void httpRequestFailed(String response);
}
