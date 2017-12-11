package com.binghui.binghuiliu.dreamy.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.binghui.binghuiliu.dreamy.util.notification.NotificationHelper;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by binghuiliu on 29/11/2017.
 */

public class MainFragment extends Fragment implements ShotsContract.View, MainAdapter.OnItemClickListener {

    private MainAdapter mainAdapter;

    @BindView(R.id.shot_recycler_view)
    RecyclerView shotRecyclerView;

    @BindInt(R.integer.column_count)
    int columnCount;

    @BindView(R.id.notification_button)
    Button notificationButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);

        initRecyclerView();

        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationHelper notificationHelper = new NotificationHelper(getActivity());
                notificationHelper.notify(101, "Dreamy", "Test!");
            }
        });

        return view;
    }

    private void initRecyclerView() {
        mainAdapter = new MainAdapter(Glide.with(this), this);
        shotRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), columnCount));
        shotRecyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void load(List<Shot> shotList) {
        mainAdapter.setShotList(shotList);
    }

    @Override
    public void onItemClick(int position) {

    }
}
