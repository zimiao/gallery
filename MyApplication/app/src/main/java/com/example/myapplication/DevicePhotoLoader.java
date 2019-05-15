package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.ImageColumns;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;

public class DevicePhotoLoader implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String DESC_SORT_ORDER = " DESC";
    private static final int LIMIT = 3;
    private final Context mContext;
    private MainActivity.ThumbnailAdapter mAdapter;

    public DevicePhotoLoader(Context context, MainActivity.ThumbnailAdapter adapter) {
        mContext = context;
        mAdapter = adapter;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        String[] projection = new String[]{
                Media._ID, ImageColumns.DATE_TAKEN
        };
        Uri contentUri = Media.EXTERNAL_CONTENT_URI;
        String sortOrderWithLimit = ImageColumns.DATE_TAKEN + DESC_SORT_ORDER + " limit " + LIMIT;
        CursorLoader loader =
                new CursorLoader(
                        mContext,
                        contentUri,
                        projection,
                        null /* selection clause */,
                        null /* selection arguments */,
                        sortOrderWithLimit);
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        if (!cursor.moveToFirst()) {
            return;
        }
        while (!cursor.isAfterLast()) {
            int columnIndex = cursor.getColumnIndexOrThrow(Media._ID);
            int dateTakenIndex = cursor.getColumnIndexOrThrow(ImageColumns.DATE_TAKEN);
            int imageId = cursor.getInt(columnIndex);
            Uri uri =
                    Uri.withAppendedPath(Media.EXTERNAL_CONTENT_URI, Integer.toString(imageId));
            Log.e("rachel", uri.toString());
            mAdapter.addUri(uri);
            cursor.moveToNext();
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
    }
}

