package com.binghui.binghuiliu.dreamy.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.binghui.binghuiliu.dreamy.bean.Shot;

/**
 * Created by binghuiliu on 22/11/2017.
 */

public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    private static final String CREATE_SHOT = ""
            + "CREATE TABLE " + Shot.TABLE + "("
            + Shot.ID + " INTEGER NOT NULL PRIMARY KEY,"
            + Shot.TITLE + " TEXT NOT NULL,"
            + Shot.DESCRIPTION + " TEXT NOT NULL"
            + ")";

    public DbOpenHelper(Context context) {
        super(context, "shots.db", null /* factory */, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SHOT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
