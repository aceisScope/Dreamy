package com.binghui.binghuiliu.dreamy.ui;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.binghui.binghuiliu.dreamy.app.ApplicationModule;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.network.ApiManager;

import java.util.List;

import javax.inject.Inject;

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

    public ShotsPresenter (Application application) {
        this.mApplication = application;
    }

    public void getShotList() {
        mSubscription = ApiManager.getShotsList(mApplication)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Shot>>() {
                    @Override
                    public void call(List<Shot> shotList) {
                        for (Shot shot: shotList) {
                            Timber.d("Shot: %s", shot.title());
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
