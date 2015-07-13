package com.gautam.nanodegree.popularmovies.core;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gautam on 28/06/15.
 */
public class MoviesDataModel implements Parcelable {

    private boolean mIsMovieAdult = false;
    private String mMovieBackdropPath = null;
    private String mMovieGenreIds = null;
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


    public MoviesDataModel() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(mIsMovieAdult ? (byte) 1 : (byte) 0);
        dest.writeString(this.mMovieBackdropPath);
        dest.writeString(this.mMovieGenreIds);
        dest.writeInt(this.mMovieId);
        dest.writeString(this.mMovieOriginalLanguage);
        dest.writeString(this.mMovieOriginalTitle);
        dest.writeString(this.mMovieOverview);
        dest.writeString(this.mMovieReleaseDate);
        dest.writeString(this.mMoviePosterPath);
        dest.writeInt(this.mMoviePopularity);
        dest.writeString(this.mMovieTitle);
        dest.writeByte(mHasVideo ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mMovieVoteAverage);
        dest.writeInt(this.mMovieVoteCount);
    }

    private MoviesDataModel(Parcel in) {
        this.mIsMovieAdult = in.readByte() != 0;
        this.mMovieBackdropPath = in.readString();
        this.mMovieGenreIds = in.readString();
        this.mMovieId = in.readInt();
        this.mMovieOriginalLanguage = in.readString();
        this.mMovieOriginalTitle = in.readString();
        this.mMovieOverview = in.readString();
        this.mMovieReleaseDate = in.readString();
        this.mMoviePosterPath = in.readString();
        this.mMoviePopularity = in.readInt();
        this.mMovieTitle = in.readString();
        this.mHasVideo = in.readByte() != 0;
        this.mMovieVoteAverage = in.readInt();
        this.mMovieVoteCount = in.readInt();
    }

    public static final Creator<MoviesDataModel> CREATOR = new Creator<MoviesDataModel>() {
        public MoviesDataModel createFromParcel(Parcel source) {
            return new MoviesDataModel(source);
        }

        public MoviesDataModel[] newArray(int size) {
            return new MoviesDataModel[size];
        }
    };


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

    public String getMovieGenreIds() {
        return mMovieGenreIds;
    }

    public void setMovieGenreIds(String mMovieGenreIds) {
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
