package com.feragusper.buenosairesantesydespues.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.feragusper.buenosairesantesydespues.R;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Activity that shows the splash of the application.
 */
public class SplashActivity extends AppCompatActivity {

    // Set the duration of the splash screen
    private static final long SPLASH_SCREEN_DELAY = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            GifDrawable gifFromResource = new GifDrawable(getResources(), R.drawable.splash);
            ((GifImageView) findViewById(R.id.iv_splash)).setImageDrawable(gifFromResource);
            gifFromResource.setSpeed(new Float(0.5));
        } catch (IOException e) {
            Log.w(getClass().getSimpleName(), "An error ocured while trying to get the gif drawable", e);
        }

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // Start the next activity
                startActivity(HistoricalRecordListActivity.getCallingIntent(SplashActivity.this));
            }
        };

        // Simulate a long loading process on application startup.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }

}
