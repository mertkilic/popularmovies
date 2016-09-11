package com.mertkilic.popularmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.List;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class Movie implements Parcelable {

    int year;
    String title;
    String overview;

    @JsonField
    List<Thumb> images;

    public Movie() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.year);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeTypedList(this.images);
    }

    protected Movie(Parcel in) {
        this.year = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.images = in.createTypedArrayList(Thumb.CREATOR);
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
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
