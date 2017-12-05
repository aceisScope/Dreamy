package com.binghui.binghuiliu.dreamy.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.binghui.binghuiliu.dreamy.R;

import butterknife.ButterKnife;

/**
 * Created by binghuiliu on 04/12/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_cell, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
