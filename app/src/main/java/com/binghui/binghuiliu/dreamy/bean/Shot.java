package com.binghui.binghuiliu.dreamy.bean;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by binghuiliu on 15/11/2017.
 */

@AutoValue
public abstract class Shot implements Parcelable{

    public static final String TABLE = "shot_table";

    public static final String ID = "_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    public abstract String id();
    public abstract String title();
    public abstract String description();
    public abstract Images images();
    public abstract List<String> tags();
    public abstract User user();

    public static TypeAdapter<Shot> typeAdapter(Gson gson) {
        return new AutoValue_Shot.GsonTypeAdapter(gson);
    }

    public static final class ContentsBuilder {
        private final ContentValues values = new ContentValues();

        public ContentsBuilder id(String id) {
            values.put(ID, Integer.parseInt(id));
            return this;
        }

        public ContentsBuilder title(String title) {
            values.put(TITLE, title);
            return this;
        }

        public ContentsBuilder description(String description) {
            values.put(DESCRIPTION, description);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}
