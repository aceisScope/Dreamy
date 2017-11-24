package com.binghui.binghuiliu.dreamy.bean;

import android.content.ContentValues;
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

    public static final String TABLE = "image_table";

    public static final String SHOT_ID = "shot_id";
    public static final String HIDPI = "hidpi";
    public static final String NORMAL = "normal";
    public static final String TEASER = "teaser";

    public abstract String hidpi();
    public abstract String normal();
    public abstract String teaser();

    public static TypeAdapter<Images> typeAdapter(Gson gson) {
        return new AutoValue_Images.GsonTypeAdapter(gson);
    }

    public static final class ContentsBuilder {
        private final ContentValues values = new ContentValues();

        public Images.ContentsBuilder shotId(String shotId) {
            values.put(SHOT_ID, Integer.parseInt(shotId));
            return this;
        }

        public Images.ContentsBuilder hidpi(String hidpi) {
            values.put(HIDPI, hidpi);
            return this;
        }

        public Images.ContentsBuilder normal(String normal) {
            values.put(NORMAL, normal);
            return this;
        }

        public Images.ContentsBuilder teaser(String teaser) {
            values.put(TEASER, teaser);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
