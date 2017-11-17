package com.binghui.binghuiliu.dreamy.ui;

import dagger.Module;
import dagger.Provides;

/**
 * Created by binghuiliu on 2017/11/17.
 */

@Module
public class ShotsPresenterModule {
    @Provides
    ShotsPresenter providesShotsPresenter() {
        return new ShotsPresenter();
    }
}
