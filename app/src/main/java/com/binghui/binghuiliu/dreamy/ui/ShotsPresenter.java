package com.binghui.binghuiliu.dreamy.ui;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.binghui.binghuiliu.dreamy.app.ApplicationModule;
import com.binghui.binghuiliu.dreamy.bean.Images;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.bean.User;
import com.binghui.binghuiliu.dreamy.network.ApiManager;
import com.squareup.sqlbrite2.BriteDatabase;

import java.util.List;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class ShotsPresenter {
    private Application mApplication;
    private Subscription mSubscription;
    private BriteDatabase mBriteDatabase;

    public ShotsPresenter (Application application, BriteDatabase briteDatabase) {
        this.mApplication = application;
        this.mBriteDatabase = briteDatabase;
    }

    public void getShotList() {
        mSubscription = ApiManager.getShotsList(mApplication)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Shot>>() {
                    @Override
                    public void call(List<Shot> shotList) {
                        for (Shot shot: shotList) {
                            Timber.d("Shot: %s %s, image: %s", shot.id(), shot.title(), shot.images().normal());
                            mBriteDatabase.insert(Shot.TABLE, new Shot.ContentsBuilder()
                                    .id(shot.id())
                                    .title(shot.title())
                                    .description(shot.description())
                                    .build());
                            mBriteDatabase.insert(Images.TABLE, new Images.ContentsBuilder()
                                    .shotId(shot.id())
                                    .hidpi(shot.images().hidpi())
                                    .normal(shot.images().normal())
                                    .teaser(shot.images().teaser())
                                    .build());
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Timber.d("Dreamy_Failure: %s", throwable.getLocalizedMessage());
                    }
                });
    }

    public void attachView() {

    }

    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
