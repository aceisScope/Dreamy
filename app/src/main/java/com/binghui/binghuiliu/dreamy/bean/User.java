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
public abstract class User implements Parcelable{

    // TODO: User 和 Shot 是多对多的数据关系， 此处需要修改

    public static final String TABLE = "user_table";

    public static final String SHOT_ID = "shot_id";
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

        public User.ContentsBuilder shotId(String shotId) {
            values.put(SHOT_ID, Integer.parseInt(shotId));
            return this;
        }

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
}
