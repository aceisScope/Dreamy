package com.binghui.binghuiliu.dreamy.bean;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcelable;

import com.binghui.binghuiliu.dreamy.local.Db;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import rx.functions.Func1;

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

    static Func1<Cursor, Images> MAPPER = new Func1<Cursor, Images>() {
        @Override
        public Images call(Cursor cursor) {
            String hidpi = Db.getString(cursor, Images.HIDPI);
            String normal = Db.getString(cursor, Images.NORMAL);
            String teaser = Db.getString(cursor, Images.TEASER);
            return new AutoValue_Images(hidpi, normal, teaser);
        }
    };
}
