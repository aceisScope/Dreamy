package com.binghui.binghuiliu.dreamy.local;

import com.binghui.binghuiliu.dreamy.bean.Images;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.bean.User;
import com.squareup.sqlbrite2.BriteDatabase;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;

/**
 * Created by binghuiliu on 24/11/2017.
 */

public class DbQueryHelper {
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
}
