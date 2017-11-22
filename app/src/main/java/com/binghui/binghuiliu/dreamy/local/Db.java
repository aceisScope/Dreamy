package com.binghui.binghuiliu.dreamy.local;

import android.database.Cursor;

/**
 * Created by binghuiliu on 22/11/2017.
 */

public final class Db {
    public static String getString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columnName));
    }

    private Db() {
        throw new AssertionError("No instances.");
    }
}
