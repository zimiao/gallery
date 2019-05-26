package com.rachelzhang.gallery;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
  private Button mButton;
  private View mImage;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mButton = findViewById(R.id.button);
    mImage = findViewById(R.id.image);

    mButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
        // create the transition animation - the images in the layouts
        // of both activities are defined with android:transitionName="target"
        ActivityOptions options = ActivityOptions
            .makeSceneTransitionAnimation(MainActivity.this, mImage, "target");
        startActivity(intent, options.toBundle());
      }
    });
  }
}
