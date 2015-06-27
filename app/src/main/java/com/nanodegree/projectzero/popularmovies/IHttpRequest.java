package com.nanodegree.projectzero.popularmovies;

/**
 * Created by Gautam on 28/06/15.
 */
public interface IHttpRequest {

    public void httpRequestSuccesful(String response);

    public void httpRequestFailed(String response);
}
