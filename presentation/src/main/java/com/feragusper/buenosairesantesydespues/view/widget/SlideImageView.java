package com.feragusper.buenosairesantesydespues.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.feragusper.buenosairesantesydespues.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Widget that shows two image view (one on top of the other) and it provides a slide bar to change the width of the top image.
 */
public class SlideImageView extends RelativeLayout {

    private static final float PX_OFFSET_SLIDER = 25;

    @InjectView(R.id.iv_1)
    ImageView image1;
    @InjectView(R.id.iv_2)
    ImageView image2;
    @InjectView(R.id.iv_slider)
    View slider;
    @InjectView(R.id.slider_container)
    View sliderContainer;

    public SlideImageView(Context context) {
        super(context);
        init();
    }

    public SlideImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SlideImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void reset() {
        Picasso.with(getContext()).cancelRequest(image1);
    }

    private void init() {
        inflate(getContext(), R.layout.view_slide_image_view, this);
        ButterKnife.inject(this, this);
        image2.setOnTouchListener(new BeforeAfterSliderTouchListener());
        image1.setOnTouchListener(new BeforeAfterSliderTouchListener());
    }

    public void setImageUrls(final String url1, final String url2, final ImageLoadCallback imageLoadCallback) {
        Picasso.with(getContext()).load(url1).placeholder(R.drawable.loading).fit().into(image1, new Callback() {
            @Override
            public void onSuccess() {
                Picasso.with(getContext()).load(url2).resize(image1.getWidth(), image1.getHeight()).into(image2, new Callback() {
                    @Override
                    public void onSuccess() {
                        slider.setX(getResources().getDimension(R.dimen.iv_slide_image_overlay_default_width) - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PX_OFFSET_SLIDER, getResources().getDisplayMetrics()));
                        slider.requestLayout();
                        slider.setVisibility(View.VISIBLE);
                        imageLoadCallback.onImageLoadSuccess();
                    }

                    @Override
                    public void onError() {
                        Log.e(this.getClass().getSimpleName(), "There was an error trying to load image url2 = " + url2);
                    }
                });
            }

            @Override
            public void onError() {
                Log.e(this.getClass().getSimpleName(), "There was an error trying to load image url1 = " + url1);
            }
        });
    }

    private class BeforeAfterSliderTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x = (int) event.getX();

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    if (x >= 0 && x <= image1.getWidth()) {
                        image2.getLayoutParams().width = x;
                        slider.setX(x - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PX_OFFSET_SLIDER, getResources().getDisplayMetrics()));
                        sliderContainer.requestLayout();
                    }
                    break;
                case MotionEvent.ACTION_DOWN:
                    // Disallow ScrollView to intercept touch events.
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    break;

                case MotionEvent.ACTION_UP:
                    // Allow ScrollView to intercept touch events.
                    v.getParent().requestDisallowInterceptTouchEvent(false);
                    break;
            }

            return true;
        }
    }
}
