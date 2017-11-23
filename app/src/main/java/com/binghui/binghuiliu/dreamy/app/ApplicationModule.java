package com.binghui.binghuiliu.dreamy.app;

import android.app.Application;

import com.binghui.binghuiliu.dreamy.local.DbModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binghuiliu on 2017/11/17.
 */

@Module(includes = {DbModule.class})
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }
}
