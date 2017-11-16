package com.binghui.binghuiliu.dreamy.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by binghuiliu on 15/11/2017.
 */

public class Images implements Parcelable {
    public String hidpi;
    public String normal;
    public String teaser;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.hidpi);
        dest.writeString(this.normal);
        dest.writeString(this.teaser);
    }

    public Images() {
    }

    protected Images(Parcel in) {
        this.hidpi = in.readString();
        this.normal = in.readString();
        this.teaser = in.readString();
    }

    public static final Parcelable.Creator<Images> CREATOR = new Parcelable.Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };
}
