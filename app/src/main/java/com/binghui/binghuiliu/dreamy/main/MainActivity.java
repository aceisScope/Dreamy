package com.binghui.binghuiliu.dreamy.main;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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

        setupToolbar();
        setupDrawerToggle();
        setupFragment(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shotsPresenter.detachView();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupComponent() {
        DaggerShotsPresenterComponent
                .builder()
                .applicationModule(new ApplicationModule(getApplication()))
                .shotsPresenterModule(new ShotsPresenterModule())
                .build()
                .inject(this);
    }

    private void setupToolbar(){
        // These lines are needed to display the top-left hamburger button
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupDrawerToggle(){
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        // This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }


    // Activity creates the Presenters and Views and connect them.
    private void setupFragment(int index) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainFragment mainFragment = new MainFragment();
        fragmentManager.beginTransaction().replace(R.id.content_frame, mainFragment).commit();
        shotsPresenter.attachView(mainFragment);
        shotsPresenter.getShotList();
    }
}
