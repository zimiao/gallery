package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
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

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.LogUtils.log;

public class MainActivity extends AppCompatActivity {

    private static final int CODE = 0;
    private RecyclerView mThumbnailContainer;
    private Button mButton;
    DevicePhotoLoader mLoader;
    ThumbnailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        init();
    }

    private void init() {
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log(this.getClass(), "clicking button");
            }
        });

        mThumbnailContainer = findViewById(R.id.thumbnail_container);

        mAdapter = new ThumbnailAdapter();
        mLoader = new DevicePhotoLoader(this, mAdapter);
        mThumbnailContainer.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(0, null, mLoader);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE:
                init();
        }
    }

    private class ThumbnailViewHolder extends RecyclerView.ViewHolder {
        ThumbnailView mThumbnailView;

        public ThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            mThumbnailView = (ThumbnailView) itemView;
            mThumbnailView.init();
        }
    }

    public class ThumbnailAdapter extends RecyclerView.Adapter<ThumbnailViewHolder> {
        private List<Uri> mImageUris;

        public ThumbnailAdapter() {
            mImageUris = new ArrayList<>();
        }

        @NonNull
        @Override
        public ThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            log(this.getClass(), "calling onCreateViewHodler");
            LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
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

}

