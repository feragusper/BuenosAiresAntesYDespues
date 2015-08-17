package com.feragusper.buenosairesantesydespues.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feragusper.buenosairesantesydespues.R;
import com.feragusper.buenosairesantesydespues.di.components.HistoricalRecordComponent;
import com.feragusper.buenosairesantesydespues.model.HistoricalRecordModel;
import com.feragusper.buenosairesantesydespues.presenter.HistoricalRecordDetailsPresenter;
import com.feragusper.buenosairesantesydespues.view.HistoricalRecordDetailsView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * @author Fernando.Perez
 * @since 0.1
 * <p>
 * Fragment that shows details of a certain historical record.
 */
public class HistoricalRecordDetailsFragment extends BaseFragment implements HistoricalRecordDetailsView {

    private static final String ARGUMENT_KEY_HISTORICAL_RECORD_ID = "com.feragusper.buenosairesantesydespues.ARGUMENT_HISTORICAL_RECORD_ID";
    private static final float PX_OFFSET_SLIDER = 25;

    private int historicalRecordId;

    @Inject
    HistoricalRecordDetailsPresenter historicalRecordDetailsPresenter;

    @InjectView(R.id.iv_historical_record_before)
    ImageView ivBefore;
    @InjectView(R.id.iv_historical_record_after)
    ImageView ivAfter;
    @InjectView(R.id.iv_slider)
    View slider;
    @InjectView(R.id.tv_historical_record_address)
    TextView address;
    @InjectView(R.id.tv_historical_record_description)
    TextView description;
    @InjectView(R.id.tv_historical_record_year)
    TextView year;
    @InjectView(R.id.tv_historical_record_neighborhood)
    TextView neighborhood;
    @InjectView(R.id.slider_container)
    View sliderContainer;
    @InjectView(R.id.iv_historical_record_share)
    View ivHistoricalRecordShare;
    private GoogleMap mMap;

    public HistoricalRecordDetailsFragment() {
        super();
    }

    public static HistoricalRecordDetailsFragment newInstance(String historicalRecordId) {
        HistoricalRecordDetailsFragment historicalRecordDetailsFragment = new HistoricalRecordDetailsFragment();

        Bundle argumentsBundle = new Bundle();
        argumentsBundle.putString(ARGUMENT_KEY_HISTORICAL_RECORD_ID, historicalRecordId);
        historicalRecordDetailsFragment.setArguments(argumentsBundle);

        return historicalRecordDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_historical_record_details, container, false);
        ButterKnife.inject(this, fragmentView);

        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMap = ((SupportMapFragment) ((AppCompatActivity) getActivity()).getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        ivBefore.setOnTouchListener(new BeforeAfterSliderTouchListener());
        ivAfter.setOnTouchListener(new BeforeAfterSliderTouchListener());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.historicalRecordDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.historicalRecordDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.historicalRecordDetailsPresenter.destroy();
    }

    private void initialize() {
        this.getComponent(HistoricalRecordComponent.class).inject(this);
        this.historicalRecordDetailsPresenter.setView(this);
        this.historicalRecordId = getArguments().getInt(ARGUMENT_KEY_HISTORICAL_RECORD_ID);
        this.historicalRecordDetailsPresenter.initialize(this.historicalRecordId);
    }

    @Override
    public void renderHistoricalRecord(final HistoricalRecordModel historicalRecord) {
        if (historicalRecord != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(historicalRecord.getTitle());
            Picasso.with(getContext()).load(historicalRecord.getImageURLBefore()).into(ivBefore, new Callback() {
                @Override
                public void onSuccess() {
                    // getResources().getDimension(R.dimen.iv_historical_record_before_width)
                    slider.setX(getResources().getDimension(R.dimen.iv_historical_record_before_width) - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PX_OFFSET_SLIDER, getResources().getDisplayMetrics()));
                    slider.requestLayout();
                    slider.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError() {
                    // Do nothing...
                }
            });
            Picasso.with(getContext()).load(historicalRecord.getImageURLAfter()).into(ivAfter);
            address.setText(historicalRecord.getAddress());
            description.setText(historicalRecord.getDescription());
            year.setText(historicalRecord.getYear());
            neighborhood.setText(historicalRecord.getNeighborhood());
            final LatLng latLng = new LatLng(historicalRecord.getLat(), historicalRecord.getLng());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(14.0f).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.moveCamera(cameraUpdate);
            ivHistoricalRecordShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_body, historicalRecord.getAddress(), historicalRecord.getShareURL()));
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject, historicalRecord.getTitle()));
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            });
        }
    }

    @Override
    public void showLoading() {
        // TODO Show a loading
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        // TODO Hide the loading
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        // TODO Show a retry
    }

    @Override
    public void hideRetry() {
        // TODO Hide the retry
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context getContext() {
        return getActivity().getApplicationContext();
    }

    /**
     * Loads all users.
     */
    private void loadUserDetails() {
        if (this.historicalRecordDetailsPresenter != null) {
            this.historicalRecordDetailsPresenter.initialize(this.historicalRecordId);
        }
    }

    private class BeforeAfterSliderTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int x = (int) event.getX();

            switch (event.getAction()) {
                case MotionEvent.ACTION_MOVE:
                    ivBefore.getLayoutParams().width = x;
                    slider.setX(x - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, PX_OFFSET_SLIDER, getResources().getDisplayMetrics()));
                    sliderContainer.requestLayout();
                    break;
            }

            return true;
        }
    }

//    @OnClick(R.id.bt_retry)
//    void onButtonRetryClick() {
//        HistoricalRecordDetailsFragment.this.loadUserDetails();
//    }
}
