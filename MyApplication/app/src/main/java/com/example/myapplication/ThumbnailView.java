package com.example.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ThumbnailView extends FrameLayout {
    View mThumbnail;
    View mImage;
    View mGreyCircle;
    View mBlueCircle;
    boolean mIsClicked;
    TextView mText;

    public ThumbnailView(Context context) {
        super(context);
    }

    public ThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ThumbnailView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void init() {
        mThumbnail = findViewById(R.id.thumbnail);
        mImage = findViewById(R.id.image);
        mGreyCircle = findViewById(R.id.grey_circle);
        mBlueCircle = findViewById(R.id.blue_circle);
        mText = findViewById(R.id.text);
        final AnimatorSet animateClicked = new AnimatorSet();
        ObjectAnimator shrinkImageX = ObjectAnimator.ofFloat(mImage, "scaleX", 0.8f);
        ObjectAnimator shrinkImageY = ObjectAnimator.ofFloat(mImage, "scaleY", 0.8f);
        ObjectAnimator hideGreyCircle = ObjectAnimator.ofFloat(mGreyCircle, "alpha", 0.0f);
        ObjectAnimator showBlueCircle = ObjectAnimator.ofFloat(mBlueCircle, "alpha", 1.0f);
        animateClicked.play(shrinkImageX).with(shrinkImageY).with(hideGreyCircle).with(showBlueCircle);
        animateClicked.setDuration(100);

        final AnimatorSet animateUnClicked = new AnimatorSet();
        ObjectAnimator expandImageX = ObjectAnimator.ofFloat(mImage, "scaleX", 1.0f);
        ObjectAnimator expandImageY = ObjectAnimator.ofFloat(mImage, "scaleY", 1.0f);
        ObjectAnimator showGreyCircle = ObjectAnimator.ofFloat(mGreyCircle, "alpha", 1.0f);
        ObjectAnimator hideBlueCircle = ObjectAnimator.ofFloat(mBlueCircle, "alpha", 0.0f);
        animateUnClicked.play(expandImageX).with(expandImageY).with(showGreyCircle).with(hideBlueCircle);
        animateUnClicked.setDuration(100);

        mThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsClicked) {
                    // unselected -> selected
                    animateUnClicked.start();
                    mIsClicked = false;
                } else {
                    animateClicked.start();
                    mIsClicked = true;
                }
            }
        });
    }
}
