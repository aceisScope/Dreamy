package com.binghui.binghuiliu.dreamy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Created by binghuiliu on 15/11/2017.
 */

@AutoValue
public abstract class User implements Parcelable{
    public abstract String id();
    public abstract String name();
    public abstract String bio();
    public abstract String avatar_url();

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }
}
