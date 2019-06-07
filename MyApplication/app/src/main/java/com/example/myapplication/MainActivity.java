package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.myapplication.LogUtils.log;

public class MainActivity extends AppCompatActivity {

    private static final int CODE = 0;
    private RecyclerView mThumbnailRecyclerView;
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
                Log.e("rachel", "clicking button");
//                mThumbnailRecyclerView.removeViewAt(0);

                mAdapter.upload(1);
            }
        });

        mThumbnailRecyclerView = findViewById(R.id.thumbnail_container);

        mAdapter = new ThumbnailAdapter(this, null);
        mLoader = new DevicePhotoLoader(this, mAdapter);
        mThumbnailRecyclerView.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(0, null, mLoader);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE:
                init();
        }
    }
}

