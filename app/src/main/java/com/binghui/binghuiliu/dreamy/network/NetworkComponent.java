package com.binghui.binghuiliu.dreamy.network;

import com.binghui.binghuiliu.dreamy.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by binghuiliu on 15/11/2017.
 */

@Singleton
@Component(modules = {ApiModule.class})
public interface NetworkComponent {
    void inject(MainActivity mainActivity);
}
