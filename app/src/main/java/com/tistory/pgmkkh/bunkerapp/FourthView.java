package com.tistory.pgmkkh.bunkerapp;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by Pgmkkh on 2017-12-20.
 */

public class FourthView extends AppCompatActivity{
    final String TAG = "AnimationTest";

    FrameLayout mFrame;
    ImageView mRocket;
    ImageView mFirework;

    int mScreenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourthview);

        mFrame = (FrameLayout)findViewById(R.id.activity_main);
        mFirework = (ImageView) findViewById(R.id.fire);
        mRocket = (ImageView) findViewById(R.id.rocket);
    }
    protected void onResume() {
        super.onResume();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        mScreenHeight = displaymetrics.heightPixels;

        startFireTweenAnimation();
        startRocketTweenAnimation();
    }
    private void startRocketTweenAnimation() {
        //트윈 애니메이션 세팅 xml 파일 객체에 연결
        Animation rocket_anim = AnimationUtils.loadAnimation(this, R.anim.rocket);
        //트윈 애니메이션이 연결된 객체를 애니메이션이 적용될 이미지가 있는 뷰에 연결하여 시작
        mRocket.startAnimation(rocket_anim);
    }
    private void startFireTweenAnimation() {
        Animation fire_anim = AnimationUtils.loadAnimation(this, R.anim.fire);
        mFirework.startAnimation(fire_anim);
        fire_anim.setAnimationListener(animationListener);
    }
    Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            Log.i(TAG, "onAnimationStart");
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Log.i(TAG, "onAnimationEnd");
            finish();
            startActivity(new Intent(getApplicationContext(), FourthView2.class));
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            Log.i(TAG, "onAnimationRepeat");
        }
    };
    Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {
            Log.i(TAG, "onAnimationStart");
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            Log.i(TAG, "onAnimationEnd");
        }

        @Override
        public void onAnimationCancel(Animator animator) {
            Log.i(TAG, "onAnimationCancel");
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
            Log.i(TAG, "onAnimationRepeat");
        }
    };
}
