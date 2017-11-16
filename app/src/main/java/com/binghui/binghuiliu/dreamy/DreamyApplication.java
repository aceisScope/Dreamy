package com.binghui.binghuiliu.dreamy;

import android.app.Application;
import android.content.Context;

import com.binghui.binghuiliu.dreamy.network.DaggerNetworkComponent;
import com.binghui.binghuiliu.dreamy.network.NetworkComponent;
import com.binghui.binghuiliu.dreamy.network.NetworkModule;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class DreamyApplication extends Application {
    private NetworkComponent networkComponent;

    public static DreamyApplication get(Context context){
        return (DreamyApplication) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    private void setupGraph() {
        networkComponent = DaggerNetworkComponent.builder().networkModule(new NetworkModule()).build();
    }

    public NetworkComponent component() {
        return networkComponent;
    }
}
