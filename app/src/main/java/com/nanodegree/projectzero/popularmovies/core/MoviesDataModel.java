package com.nanodegree.projectzero.popularmovies.core;

import org.json.JSONArray;

/**
 * Created by Gautam on 28/06/15.
 */
public class MoviesDataModel {

    private boolean mIsMovieAdult = false;
    private String mMovieBackdropPath = null;
    private JSONArray mMovieGenreIds = null;
    private int mMovieId = -1;
    private String mMovieOriginalLanguage = null;
    private String mMovieOriginalTitle = null;
    private String mMovieOverview = null;
    private String mMovieReleaseDate = null;
    private String mMoviePosterPath = null;
    private int mMoviePopularity = -1;
    private String mMovieTitle = null;
    private boolean mHasVideo = false;
    private int mMovieVoteAverage = -1;
    private int mMovieVoteCount = -1;


    public boolean isIsMovieAdult() {
        return mIsMovieAdult;
    }

    public void setIsMovieAdult(boolean mIsMovieAdult) {
        this.mIsMovieAdult = mIsMovieAdult;
    }

    public String getMovieBackdropPath() {
        return mMovieBackdropPath;
    }

    public void setMovieBackdropPath(String mMovieBackdropPath) {
        this.mMovieBackdropPath = mMovieBackdropPath;
    }

    public JSONArray getMovieGenreIds() {
        return mMovieGenreIds;
    }

    public void setMovieGenreIds(JSONArray mMovieGenreIds) {
        this.mMovieGenreIds = mMovieGenreIds;
    }

    public int getMovieId() {
        return mMovieId;
    }

    public void setMovieId(int mMovieId) {
        this.mMovieId = mMovieId;
    }

    public String getMovieOriginalLanguage() {
        return mMovieOriginalLanguage;
    }

    public void setMovieOriginalLanguage(String mMovieOriginalLanguage) {
        this.mMovieOriginalLanguage = mMovieOriginalLanguage;
    }

    public String getMovieOriginalTitle() {
        return mMovieOriginalTitle;
    }

    public void setMovieOriginalTitle(String mMovieOriginalTitle) {
        this.mMovieOriginalTitle = mMovieOriginalTitle;
    }

    public String getMovieOverview() {
        return mMovieOverview;
    }

    public void setMovieOverview(String mMovieOverview) {
        this.mMovieOverview = mMovieOverview;
    }

    public String getMovieReleaseDate() {
        return mMovieReleaseDate;
    }

    public void setMovieReleaseDate(String mMovieReleaseDate) {
        this.mMovieReleaseDate = mMovieReleaseDate;
    }

    public String getMoviePosterPath() {
        return mMoviePosterPath;
    }

    public void setMoviePosterPath(String mMoviePosterPath) {
        this.mMoviePosterPath = mMoviePosterPath;
    }

    public int getMoviePopularity() {
        return mMoviePopularity;
    }

    public void setMoviePopularity(int mMoviePopularity) {
        this.mMoviePopularity = mMoviePopularity;
    }

    public String getMovieTitle() {
        return mMovieTitle;
    }

    public void setMovieTitle(String mMovieTitle) {
        this.mMovieTitle = mMovieTitle;
    }

    public boolean isHasVideo() {
        return mHasVideo;
    }

    public void setHasVideo(boolean mHasVideo) {
        this.mHasVideo = mHasVideo;
    }

    public int getMovieVoteAverage() {
        return mMovieVoteAverage;
    }

    public void setMovieVoteAverage(int mMovieVoteAverage) {
        this.mMovieVoteAverage = mMovieVoteAverage;
    }

    public int getMovieVoteCount() {
        return mMovieVoteCount;
    }

    public void setMovieVoteCount(int mMovieVoteCount) {
        this.mMovieVoteCount = mMovieVoteCount;
    }

}
