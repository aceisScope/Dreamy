package com.binghui.binghuiliu.dreamy.main;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.app.ApplicationModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Inject
    ShotsPresenter shotsPresenter;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.left_drawer)
    ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupComponent();

//        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
//        shotsPresenter.attachView(mainFragment);
//        shotsPresenter.getShotList();

        setupToolbar();
        setupDrawerToggle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        shotsPresenter.detachView();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    protected void setupComponent() {
        DaggerShotsPresenterComponent
                .builder()
                .applicationModule(new ApplicationModule(getApplication()))
                .shotsPresenterModule(new ShotsPresenterModule())
                .build()
                .inject(this);
    }

    protected void setupToolbar(){
        // These lines are needed to display the top-left hamburger button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        // This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

}
