package com.binghui.binghuiliu.dreamy.main;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.binghui.binghuiliu.dreamy.R;
import com.binghui.binghuiliu.dreamy.bean.Shot;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import timber.log.Timber;

/**
 * Created by binghuiliu on 04/12/2017.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<Shot> shotList = Collections.emptyList();

    private final RequestManager glide;
    private OnItemClickListener mClickListener;

    MainAdapter(RequestManager glide, OnItemClickListener clickListener) {
        this.glide = glide;
        this.mClickListener = clickListener;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_cell, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        // image
        String normalImageUrl = shotList.get(position).images().normal();
        if (normalImageUrl != null) {
            // glide can load gif automatically
            glide.load(normalImageUrl).into(holder.shotImageView);
        }

        // view
        holder.viewCountTextView.setText(shotList.get(position).viewsCount());

        // like
        holder.likeCountTextView.setText(shotList.get(position).likesCount());
    }

    @Override
    public int getItemCount() {
        return shotList.size();
    }

    public void setShotList(List<Shot> shotList) {
        this.shotList = shotList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.shot_image)
        ImageView shotImageView;

        @BindView(R.id.like_count_text)
        TextView likeCountTextView;

        @BindView(R.id.view_count_text)
        TextView viewCountTextView;

        @OnClick
        void onClick() {
            mClickListener.onItemClick(getAdapterPosition());
        }

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
