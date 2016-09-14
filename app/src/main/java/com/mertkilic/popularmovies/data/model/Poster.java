package com.mertkilic.popularmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

/**
 * Created by Mert Kilic on 11.9.2016.
 */
@JsonObject
public class Poster implements Parcelable {

    @JsonField
    Image poster;

    @JsonField
    Image thumb;

    public Poster() {
    }

    public Image getPoster() {
        return poster;
    }

    public Image getThumb() {
        return thumb;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.poster, flags);
        dest.writeParcelable(this.thumb, flags);
    }

    protected Poster(Parcel in) {
        this.poster = in.readParcelable(Image.class.getClassLoader());
        this.thumb = in.readParcelable(Image.class.getClassLoader());
    }

    public static final Creator<Poster> CREATOR = new Creator<Poster>() {
        @Override
        public Poster createFromParcel(Parcel source) {
            return new Poster(source);
        }

        @Override
        public Poster[] newArray(int size) {
            return new Poster[size];
        }
    };

    @JsonObject
    public static class Image implements Parcelable {
        @JsonField(name = "thumb")
        String posterUrl;

        @JsonField(name = "full")
        String thumbUrl;

        public Image() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.posterUrl);
            dest.writeString(this.thumbUrl);
        }

        protected Image(Parcel in) {
            this.posterUrl = in.readString();
            this.thumbUrl = in.readString();
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

        public String getPosterUrl() {
            return posterUrl;
        }

        public String getThumbUrl() {
            return thumbUrl;
        }
    }
}
