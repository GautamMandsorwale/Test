package com.gautam.nanodegree.popularmovies.core;

/**
 * Created by Gautam on 28/06/15.
 */
public interface HttpRequestStatusListener {

    void httpRequestSuccessful(int requestType, String response);

    void httpRequestFailed(String response);
}
