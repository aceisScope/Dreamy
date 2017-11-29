package com.binghui.binghuiliu.dreamy.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.local.DbQueryHelper;

import java.util.List;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by binghuiliu on 29/11/2017.
 */

public class MainFragment extends Fragment implements ShotsContract.View {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void load(List<Shot> shotList) {
        for (Shot shot: shotList) {
            Timber.d("Shot in Fragment: %s %s, image: %s", shot.id(), shot.title(), shot.images().normal());
        }
    }
}
