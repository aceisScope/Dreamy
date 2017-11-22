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
public abstract class Images implements Parcelable {
    public abstract String hidpi();
    public abstract String normal();
    public abstract String teaser();

    public static TypeAdapter<Images> typeAdapter(Gson gson) {
        return new AutoValue_Images.GsonTypeAdapter(gson);
    }
}
