package com.example.contact_book;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;



public class LauchActivity extends AppCompatActivity {

    private boolean isExt=false;
    private ImageView logo_1;
    private ImageView logo_2;
    private ImageView flash;
    private Animation.AnimationListener animationListener_logo= new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            rotate_scale_positive(logo_1);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Fade_out(logo_2);
            rotate_scale_reverse(logo_1);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    private Animation.AnimationListener animationListener_flash_out=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Flash_out(flash);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    private Animation.AnimationListener animationListener_flash_in=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Flash_in(flash);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };
    private Animation.AnimationListener animationListener_finish=new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            Log.d("My","Hello");
            startActivity(new Intent(LauchActivity.this,MainActivity.class));
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lauch);
        logo_1=(ImageView)findViewById(R.id.logo_1);
        logo_2=(ImageView)findViewById(R.id.logo_2);
        flash=(ImageView)findViewById(R.id.flash);

        flash.setVisibility(View.INVISIBLE);

        Fade_in(logo_2);
    }

    public void Fade_in(View view){
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f,1.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setAnimationListener(animationListener_logo);
        view.startAnimation(alphaAnimation);
    }

    public void Fade_out(View view){
        AlphaAnimation alphaAnimation=new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        alphaAnimation.setAnimationListener(animationListener_flash_in);
        view.startAnimation(alphaAnimation);
    }

    public void Flash_in(View view){
        Animation animation=AnimationUtils.loadAnimation(LauchActivity.this,R.anim.flash_in);
        animation.setFillAfter(true);
        animation.setAnimationListener(animationListener_flash_out);
        view.startAnimation(animation);
    }

    public void Flash_out(View view){
        Animation animation=AnimationUtils.loadAnimation(LauchActivity.this,R.anim.flash_out);
        animation.setAnimationListener(animationListener_finish);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    public void rotate_scale_positive(View view){
        Animation animation=AnimationUtils.loadAnimation(LauchActivity.this,R.anim.rotate_scale_positive);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }

    public void rotate_scale_reverse(View view){
        Animation animation=AnimationUtils.loadAnimation(LauchActivity.this,R.anim.rotate_scale_reverse);
        animation.setFillAfter(true);
        view.startAnimation(animation);
    }
}
