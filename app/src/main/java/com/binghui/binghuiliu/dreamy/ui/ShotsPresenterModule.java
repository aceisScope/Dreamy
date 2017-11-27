package com.binghui.binghuiliu.dreamy.ui;

import android.app.Application;

import com.binghui.binghuiliu.dreamy.app.ApplicationModule;
import com.binghui.binghuiliu.dreamy.local.DbModule;
import com.squareup.sqlbrite2.BriteDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binghuiliu on 2017/11/17.
 */

@Module(includes = {ApplicationModule.class, DbModule.class})
public class ShotsPresenterModule {
    @Provides
    ShotsPresenter providesShotsPresenter(Application application, BriteDatabase briteDatabase) {
        return new ShotsPresenter(application, briteDatabase);
    }
}
