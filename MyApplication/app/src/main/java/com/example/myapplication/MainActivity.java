package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CODE = 0;
    private RecyclerView mThumbnailContainer;
    private Button mButton;
    DevicePhotoLoader mLoader;
    ThumbnailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("rachel", "calling onCreateaaa");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CODE
            );
            return;
        }

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete some views.
                mThumbnailContainer.removeViewAt(0);
                // restart loader again for more views
                getSupportLoaderManager().restartLoader(0, null, mLoader);

            }
        });

        mThumbnailContainer = findViewById(R.id.thumbnail_container);
//        mThumbnailContainer.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Uri> arrayList = new ArrayList<>();
        arrayList.add(Uri.parse("content://media/external/images/media/9999"));
        mAdapter = new ThumbnailAdapter(arrayList);
        mLoader = new DevicePhotoLoader(this, mAdapter);
        mThumbnailContainer.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(0, null, mLoader);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE:
                getSupportLoaderManager().initLoader(0, null, mLoader);
        }
    }

    private class ThumbnailViewHolder extends RecyclerView.ViewHolder {
        ThumbnailView mThumbnailView;

        public ThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            mThumbnailView = (ThumbnailView) itemView;
            mThumbnailView.init();
            Log.e("rachel", "calling ViewHolder constructor");
        }
    }

    public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailViewHolder> {
        private List<Uri> mImageUris;

        public ThumbnailAdapter(List<Uri> uris) {
            mImageUris = uris;
        }

        @NonNull
        @Override
        public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.e("rachel", "calling onCreateViewHodler");
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
            View view = layoutInflater.inflate(R.layout.thumbnail_view, parent, false);
            return new ThumbnailViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ThumbnailViewHolder thumbnailViewHolder, int position) {
            Log.e("rachel", "calling onBindViewHodler");
            Uri uri = mImageUris.get(position);
            thumbnailViewHolder.mThumbnailView.mText.setText(uri.toString());
        }

        @Override
        public int getItemCount() {
            Log.e("rachel", "calling getItemCount");
            return mImageUris.size();
        }

        public void addUri(Uri uri) {
            mImageUris.add(uri);
        }
    }
}

