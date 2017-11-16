package com.binghui.binghuiliu.dreamy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by binghuiliu on 15/11/2017.
 */

public class Shot implements Parcelable {
    public String id;
    public String title;
    public String description;
    public Images images;
    public List<String> tags;
    public User user;

    public Shot() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeParcelable(this.images, flags);
        dest.writeStringList(this.tags);
        dest.writeParcelable(this.user, flags);
    }

    protected Shot(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.images = in.readParcelable(Images.class.getClassLoader());
        this.tags = in.createStringArrayList();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Shot> CREATOR = new Creator<Shot>() {
        @Override
        public Shot createFromParcel(Parcel source) {
            return new Shot(source);
        }

        @Override
        public Shot[] newArray(int size) {
            return new Shot[size];
        }
    };
}
