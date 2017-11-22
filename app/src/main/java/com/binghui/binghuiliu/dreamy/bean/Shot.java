package com.binghui.binghuiliu.dreamy.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

/**
 * Created by binghuiliu on 15/11/2017.
 */

@AutoValue
public abstract class Shot implements Parcelable{
    public abstract String id();
    public abstract String title();
    public abstract String description();
    public abstract Images images();
    public abstract List<String> tags();
    public abstract User user();

    public static TypeAdapter<Shot> typeAdapter(Gson gson) {
        return new AutoValue_Shot.GsonTypeAdapter(gson);
    }
}
