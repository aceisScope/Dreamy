package com.binghui.binghuiliu.dreamy.main;

import android.app.Application;

import com.binghui.binghuiliu.dreamy.bean.Images;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.local.DbQueryHelper;
import com.binghui.binghuiliu.dreamy.network.ApiManager;
import com.squareup.sqlbrite2.BriteDatabase;

import java.util.List;

import javax.annotation.Nonnull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class ShotsPresenter implements ShotsContract.Presenter {
    private ShotsContract.View view;

    private Application mApplication;
    private Disposable mNetworkDisposable;
    private BriteDatabase mBriteDatabase;

    public ShotsPresenter (Application application, BriteDatabase briteDatabase) {
        this.mApplication = application;
        this.mBriteDatabase = briteDatabase;
    }

    @Override
    public void getShotList() {
        mNetworkDisposable = ApiManager.getShotsList(mApplication)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Shot>>() {
                    @Override
                    public void accept(List<Shot> shotList) throws Exception {
                        for (Shot shot: shotList) {
                            Timber.d("Shot: %s %s, image: %s", shot.id(), shot.title(), shot.images().normal());
                            DbQueryHelper.insertShot(mBriteDatabase, shot);
                        }
                    }
                });

        Disposable disposable = DbQueryHelper.selectImagesByShotId(mBriteDatabase, "3917245")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Images>() {
                    @Override
                    public void accept(Images images) throws Exception {
                        Timber.d("Images Query: %s", images.hidpi());
                    }
                });
    }

    @Override
    public void attachView(@Nonnull ShotsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (mNetworkDisposable != null && !mNetworkDisposable.isDisposed()) {
            mNetworkDisposable.dispose();
        }
        view = null;
    }
}
