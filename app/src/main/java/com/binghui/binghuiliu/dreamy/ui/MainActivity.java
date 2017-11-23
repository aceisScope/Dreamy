package com.binghui.binghuiliu.dreamy.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.app.DreamyApplication;
import com.squareup.sqlbrite.BriteDatabase;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    BriteDatabase briteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DreamyApplication.getComponent(getApplicationContext()).inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
