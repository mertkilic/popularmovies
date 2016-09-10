package com.mertkilic.popularmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class Movie implements Parcelable {

    int year;
    String title;
    String overview;
    Thumb thumb;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.year);
        dest.writeString(this.title);
        dest.writeParcelable(this.thumb, flags);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.year = in.readInt();
        this.title = in.readString();
        this.thumb = in.readParcelable(Thumb.class.getClassLoader());
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
