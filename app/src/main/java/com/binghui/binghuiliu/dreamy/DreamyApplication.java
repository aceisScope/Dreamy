package com.binghui.binghuiliu.dreamy;

import android.app.Application;
import android.content.Context;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class DreamyApplication extends Application {

    public static DreamyApplication get(Context context){
        return (DreamyApplication) context.getApplicationContext();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    private void setupGraph() {

    }

}
