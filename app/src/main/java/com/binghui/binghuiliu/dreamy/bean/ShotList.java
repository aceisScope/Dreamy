package com.binghui.binghuiliu.dreamy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class ShotList implements Parcelable {
    private List<Shot> shots;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.shots);
    }

    public ShotList() {
    }

    protected ShotList(Parcel in) {
        this.shots = in.createTypedArrayList(Shot.CREATOR);
    }

    public static final Parcelable.Creator<ShotList> CREATOR = new Parcelable.Creator<ShotList>() {
        @Override
        public ShotList createFromParcel(Parcel source) {
            return new ShotList(source);
        }

        @Override
        public ShotList[] newArray(int size) {
            return new ShotList[size];
        }
    };
}
