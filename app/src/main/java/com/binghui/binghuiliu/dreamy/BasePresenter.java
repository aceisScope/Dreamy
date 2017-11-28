package com.binghui.binghuiliu.dreamy;

import javax.annotation.Nonnull;

/**
 * Created by binghuiliu on 17/11/2017.
 */

public interface BasePresenter <T extends BaseView> {
    void attachView(@Nonnull T view);
    void detachView();
}
