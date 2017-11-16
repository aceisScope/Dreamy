package com.binghui.binghuiliu.dreamy;

import android.app.Application;

import com.binghui.binghuiliu.dreamy.network.DaggerNetworkComponent;
import com.binghui.binghuiliu.dreamy.network.NetworkComponent;
import com.binghui.binghuiliu.dreamy.network.NetworkModule;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class DreamyApplication extends Application {
    private NetworkComponent networkComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    private void setupGraph() {
        networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
    }

    public NetworkComponent getNetworkComponent() {
        return networkComponent;
    }
}
