package com.binghui.binghuiliu.dreamy.ui;

import com.binghui.binghuiliu.dreamy.BasePresenter;
import com.binghui.binghuiliu.dreamy.BaseView;
import com.binghui.binghuiliu.dreamy.bean.Shot;

import java.util.List;

/**
 * Created by binghuiliu on 28/11/2017.
 */

public interface ShotsContract {
    interface View extends BaseView {
        void  load(List<Shot> shotList);
    }

    interface Presenter extends BasePresenter<View>{
        void getShotList();
    }
}
