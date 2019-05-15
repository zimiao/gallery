package com.example.myapplication;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.recyclerview.selection.SelectionTracker;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.LogUtils.log;

public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailViewHolder> {
    private List<Uri> mImageUris;

    public ThumbnailAdapter() {
        mImageUris = new ArrayList<>();
    }

    @NonNull
    @Override
    public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        log(this.getClass(), "calling onCreateViewHodler");
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.thumbnail_view, parent, false);
        return new ThumbnailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThumbnailViewHolder thumbnailViewHolder, int position) {
        log(this.getClass(),"calling onBindViewHodler");
        Uri uri = mImageUris.get(position);
        thumbnailViewHolder.mThumbnailView.mText.setText(uri.toString());
    }

    @Override
    public int getItemCount() {
        int count = mImageUris.size();
        log(this.getClass(), "calling getItemCount: " + count);
        return count;
    }

    public void addUri(Uri uri) {
        mImageUris.add(uri);
    }

    public void clearData() {
        mImageUris = new ArrayList<>();
    }
}

