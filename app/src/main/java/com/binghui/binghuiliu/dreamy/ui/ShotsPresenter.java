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

import javax.annotation.Nonnull;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static android.database.sqlite.SQLiteDatabase.CONFLICT_IGNORE;

/**
 * Created by binghuiliu on 16/11/2017.
 */

public class ShotsPresenter implements ShotsContract.Presenter {
    private ShotsContract.View view;

    private Application mApplication;
    private Subscription mSubscription;
    private BriteDatabase mBriteDatabase;

    public ShotsPresenter (Application application, BriteDatabase briteDatabase) {
        this.mApplication = application;
        this.mBriteDatabase = briteDatabase;
    }

    @Override
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
                                    .userId(shot.user().id())
                                    .title(shot.title())
                                    .description(shot.description())
                                    .build(), CONFLICT_IGNORE);
                            mBriteDatabase.insert(Images.TABLE, new Images.ContentsBuilder()
                                    .shotId(shot.id())
                                    .hidpi(shot.images().hidpi())
                                    .normal(shot.images().normal())
                                    .teaser(shot.images().teaser())
                                    .build(), CONFLICT_IGNORE);
                            mBriteDatabase.insert(User.TABLE, new User.ContentsBuilder()
                                    .id(shot.user().id())
                                    .name(shot.user().name())
                                    .bio(shot.user().bio())
                                    .avatarURL(shot.user().avatar_url())
                                    .build(), CONFLICT_IGNORE);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Timber.d("Dreamy_Failure: %s", throwable.getLocalizedMessage());
                    }
                });
    }

    @Override
    public void attachView(@Nonnull ShotsContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        view = null;
    }
}
