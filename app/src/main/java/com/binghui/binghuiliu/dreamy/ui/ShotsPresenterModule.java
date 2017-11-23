package com.binghui.binghuiliu.dreamy.ui;

import android.app.Application;

import com.binghui.binghuiliu.dreamy.app.ApplicationModule;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binghuiliu on 2017/11/17.
 */

@Module(includes = {ApplicationModule.class})
public class ShotsPresenterModule {
    @Provides
    ShotsPresenter providesShotsPresenter(Application application) {
        return new ShotsPresenter(application);
    }
}
