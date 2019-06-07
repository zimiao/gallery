package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThumbnailAdapter extends CursorRecyclerViewAdapter<ThumbnailViewHolder>{
    public ThumbnailAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(ThumbnailViewHolder viewHolder, Cursor cursor) {
        int columnIndex = cursor.getColumnIndexOrThrow(Media._ID);
        int imageId = cursor.getInt(columnIndex);
        Uri uri =
                Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, Integer.toString(imageId));
        Log.e("rachel1", uri.toString());
        viewHolder.mThumbnailView.mText.setText(uri.toString());
    }

    @NonNull
    @Override
    public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.thumbnail_view, parent, false);
        return new ThumbnailViewHolder(view);
    }

    public void upload(int index, int imageId) {
        notifyItemRemoved(index);
        super.updateSkipIds(imageId);
    }

}
