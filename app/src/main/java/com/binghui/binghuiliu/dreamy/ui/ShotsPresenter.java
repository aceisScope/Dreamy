package com.binghui.binghuiliu.dreamy.ui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.network.ApiManager;

import java.util.List;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class ShotsPresenter {
    @Inject
    public ShotsPresenter () {
    }

    private Subscription mSubscription;

    public void getShotList() {
        mSubscription = ApiManager.getShotsList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Shot>>() {
                    @Override
                    public void call(List<Shot> shotList) {
                        Log.v("Dreamy_Success", shotList.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.v("Dreamy_Failure", throwable.getLocalizedMessage());
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
