package com.example.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ThumbnailViewHolder extends RecyclerView.ViewHolder {
    ThumbnailView mThumbnailView;

    public ThumbnailViewHolder(@NonNull View itemView) {
        super(itemView);
        mThumbnailView = (ThumbnailView) itemView;
        mThumbnailView.init();
    }
}
