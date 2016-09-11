package com.mertkilic.popularmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@JsonObject
public class Thumb implements Parcelable {

    @JsonField
    String full;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.full);
    }

    public Thumb() {
    }

    protected Thumb(Parcel in) {
        this.full = in.readString();
    }

    public static final Parcelable.Creator<Thumb> CREATOR = new Parcelable.Creator<Thumb>() {
        @Override
        public Thumb createFromParcel(Parcel source) {
            return new Thumb(source);
        }

        @Override
        public Thumb[] newArray(int size) {
            return new Thumb[size];
        }
    };

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }
}
