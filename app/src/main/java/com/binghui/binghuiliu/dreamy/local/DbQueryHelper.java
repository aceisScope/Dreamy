package com.binghui.binghuiliu.dreamy.local;

import com.binghui.binghuiliu.dreamy.bean.Images;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.bean.User;
import com.squareup.sqlbrite2.BriteDatabase;

import io.reactivex.Observable;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;

/**
 * Created by binghuiliu on 24/11/2017.
 */

public class DbQueryHelper {

    private static final String SELECT_SHOT_BY_ID =
            "SELECT * FROM " + Shot.TABLE + " WHERE " + Shot.ID + " = ? ";
    private static final String SELECT_USER_BY_ID =
            "SELECT * FROM " + User.TABLE + " WHERE " + User.ID + " = ? ";
    private static final String SELECT_IMAGE_BY_SHOT_ID =
            "SELECT * FROM " + Images.TABLE + " WHERE " + Images.SHOT_ID + " = ? ";

    public static long insertShot(BriteDatabase briteDatabase, Shot shot) {
        briteDatabase.insert(User.TABLE, new User.ContentsBuilder()
                .id(shot.user().id())
                .name(shot.user().name())
                .bio(shot.user().bio())
                .avatarURL(shot.user().avatar_url())
                .build(), CONFLICT_IGNORE);
        briteDatabase.insert(Images.TABLE, new Images.ContentsBuilder()
                .shotId(shot.id())
                .hidpi(shot.images().hidpi())
                .normal(shot.images().normal())
                .teaser(shot.images().teaser())
                .build(), CONFLICT_IGNORE);
        long rowId = briteDatabase.insert(Shot.TABLE, new Shot.ContentsBuilder()
                .id(shot.id())
                .userId(shot.user().id())
                .title(shot.title())
                .description(shot.description())
                .build(), CONFLICT_IGNORE);

        return rowId;
    }

    public static Observable<Images> selectImagesByShotId(BriteDatabase briteDatabase, String shotId) {
        return briteDatabase.createQuery(Images.TABLE, SELECT_IMAGE_BY_SHOT_ID, shotId).map(Images.MAPPER);
    }
}
