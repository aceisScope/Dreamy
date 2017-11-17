package com.binghui.binghuiliu.dreamy.ui;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binghuiliu on 2017/11/17.
 */

@Module
public class ShotsPresenterModule {
    @Provides
    ShotsPresenter providesShotsPresenter(Application application) {
        return new ShotsPresenter(application);
    }
}
