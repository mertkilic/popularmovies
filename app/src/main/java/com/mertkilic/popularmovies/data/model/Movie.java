package com.mertkilic.popularmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
public class Movie implements Parcelable {

    public static final int TYPE_POPULAR = 0;
    public static final int TYPE_SEARCH  = 1;

    int year;
    int type = TYPE_POPULAR;
    String title;
    String overview;

    @JsonField(name = "images")
    Thumb image;

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
        dest.writeParcelable(this.image, flags);
    }

    protected Movie(Parcel in) {
        this.year = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.image = in.readParcelable(Thumb.class.getClassLoader());
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

    public Thumb getImage() {
        return image;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public Movie setType(int type) {
        this.type = type;
        return this;
    }
}
