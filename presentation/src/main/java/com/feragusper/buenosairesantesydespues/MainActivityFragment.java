package com.feragusper.buenosairesantesydespues;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.antes).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                int width = v.getLayoutParams().width;
                int height = v.getLayoutParams().height;

//                if (((x - width <= 20 && x - width > 0) || (width - x <= 20 && width - x > 0)) && (((y - height <= 20 && y - height > 0) || (height - y <= 20 && height - y > 0)))) {
                Log.d(">>", String.valueOf(event.getAction()));
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            break;
                        case MotionEvent.ACTION_MOVE:
                            Log.e(">>", "width:" + width + " height:" + height + " x:" + x + " y:" + y);
                            v.getLayoutParams().width = x;
//                            v.getLayoutParams().height = y;
                            v.requestLayout();
                            break;
                        case MotionEvent.ACTION_UP:
                            break;
                    }
//                }

                return true;
            }
        });
    }
}
