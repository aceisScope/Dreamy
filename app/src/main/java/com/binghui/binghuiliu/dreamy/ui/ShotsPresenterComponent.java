package com.binghui.binghuiliu.dreamy.ui;

import com.binghui.binghuiliu.dreamy.app.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by binghuiliu on 16/11/2017.
 */
@Singleton
@Component(modules = {ShotsPresenterModule.class})
public interface ShotsPresenterComponent {
    void inject(MainActivity mainActivity);
}
