package com.feragusper.buenosairesantesydespues.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.feragusper.buenosairesantesydespues.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.InjectView;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Activity that shows the splash of the application.
 */
public class SplashActivity extends BaseActivity {

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 2500;

    @InjectView(R.id.iv_logo)
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Do noting
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                logo.setVisibility(View.VISIBLE);
                startActivity(HistoricalRecordListActivity.getCallingIntent(SplashActivity.this));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Do noting
            }
        });

        logo.startAnimation(fadeIn);
    }

    @Override
    protected int getContentViewResourceId() {
        return R.layout.activity_splash;
    }

}
