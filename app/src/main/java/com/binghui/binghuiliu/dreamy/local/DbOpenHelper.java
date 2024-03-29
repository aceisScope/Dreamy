package com.binghui.binghuiliu.dreamy.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.binghui.binghuiliu.dreamy.bean.Images;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.bean.User;

/**
 * Created by binghuiliu on 22/11/2017.
 */

// TODO: Create a TAG table and a relationship table for tags and shots
public class DbOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    private static final String CREATE_USER = ""
            + "CREATE TABLE " + User.TABLE + "("
            + User.ID + " INTEGER NOT NULL PRIMARY KEY,"
            + User.NAME + " TEXT NOT NULL,"
            + User.BIO + " TEXT NOT NULL,"
            + User.AVATAR_URL + " TEXT NOT NULL"
            + ")";

    private static final String CREATE_SHOT = ""
            + "CREATE TABLE " + Shot.TABLE + "("
            + Shot.ID + " INTEGER NOT NULL PRIMARY KEY,"
            + Shot.USER_ID + " INTEGER NOT NULL REFERENCES " + User.TABLE + "(" + User.ID + "),"
            + Shot.TITLE + " TEXT NOT NULL,"
            + Shot.DESCRIPTION + " TEXT NOT NULL"
            + ")";

    private static final String CREATE_IMAGES = ""
            + "CREATE TABLE " + Images.TABLE + "("
            + Images.SHOT_ID + " INTEGER NOT NULL PRIMARY KEY,"
            + Images.HIDPI + " TEXT NOT NULL,"
            + Images.NORMAL + " TEXT NOT NULL,"
            + Images.TEASER + " TEXT NOT NULL"
            + ")";


    public DbOpenHelper(Context context) {
        super(context, "shots.db", null /* factory */, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_SHOT);
        sqLiteDatabase.execSQL(CREATE_IMAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
