package com.binghui.binghuiliu.dreamy.app;

import com.binghui.binghuiliu.dreamy.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by binghuiliu on 23/11/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
