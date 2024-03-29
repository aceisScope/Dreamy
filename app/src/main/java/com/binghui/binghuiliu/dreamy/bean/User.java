package com.binghui.binghuiliu.dreamy.bean;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.binghui.binghuiliu.dreamy.local.Db;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import io.reactivex.functions.Function;


/**
 * Created by binghuiliu on 15/11/2017.
 */

@AutoValue
public abstract class User implements Parcelable{

    public static final String TABLE = "user_table";

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String BIO = "bio";
    public static final String AVATAR_URL = "avatar_url";

    public abstract String id();
    public abstract String name();
    public abstract String bio();
    public abstract String avatar_url();

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }

    public static final class ContentsBuilder {
        private final ContentValues values = new ContentValues();

        public User.ContentsBuilder id(String id) {
            values.put(ID, Integer.parseInt(id));
            return this;
        }

        public User.ContentsBuilder name(String name) {
            values.put(NAME, name);
            return this;
        }

        public User.ContentsBuilder bio(String bio) {
            values.put(BIO, bio);
            return this;
        }

        public User.ContentsBuilder avatarURL(String avatar_url) {
            values.put(AVATAR_URL, avatar_url);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }

    static Function<Cursor, User> MAPPER = new Function<Cursor, User>() {
        @Override
        public User apply(Cursor cursor) {
            String id = Integer.toString(Db.getInt(cursor, User.ID));
            String name = Db.getString(cursor, User.NAME);
            String bio = Db.getString(cursor, User.BIO);
            String avatar_url = Db.getString(cursor, User.AVATAR_URL);
            return new AutoValue_User(id, name, bio, avatar_url);
        }
    };
}
