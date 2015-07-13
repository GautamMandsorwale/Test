package com.gautam.nanodegree;

/**
 * Created by Gautam on 09/07/15.
 */
public interface FragmentChangeListener {

    int POPULAR_MOVIES_EVENT = 1;
    int POPULAR_MOVIES_DETAILS_EVENT = 2;

    void fragmentChangeEvent(int event, Object data);

}
