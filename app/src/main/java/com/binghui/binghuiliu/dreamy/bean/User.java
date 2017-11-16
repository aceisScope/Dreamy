package com.binghui.binghuiliu.dreamy.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by binghuiliu on 15/11/2017.
 */

public class User implements Parcelable {
    public String id;
    public String name;
    public String bio;
    public String avatar_url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.bio);
        dest.writeString(this.avatar_url);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.bio = in.readString();
        this.avatar_url = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
