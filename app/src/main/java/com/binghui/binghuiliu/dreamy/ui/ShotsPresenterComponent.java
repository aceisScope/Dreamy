package com.binghui.binghuiliu.dreamy.ui;

import dagger.Component;

/**
 * Created by binghuiliu on 16/11/2017.
 */

@Component(modules = {ShotsPresenterModule.class})
public interface ShotsPresenterComponent {
    void inject(MainActivity mainActivity);
}
