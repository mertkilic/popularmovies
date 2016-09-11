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
    Image poster;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.poster, flags);
    }

    public Thumb() {
    }

    protected Thumb(Parcel in) {
        this.poster = in.readParcelable(Image.class.getClassLoader());
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

    public String getThumbUrl() {
        return poster.url;
    }

    @JsonObject
    static class Image implements Parcelable {
        @JsonField(name = "thumb")
        String url;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
        }

        public Image() {
        }

        protected Image(Parcel in) {
            this.url = in.readString();
        }

        public static final Creator<Image> CREATOR = new Creator<Image>() {
            @Override
            public Image createFromParcel(Parcel source) {
                return new Image(source);
            }

            @Override
            public Image[] newArray(int size) {
                return new Image[size];
            }
        };
    }
}
