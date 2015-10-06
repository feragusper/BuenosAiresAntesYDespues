package com.feragusper.buenosairesantesydespues.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.feragusper.buenosairesantesydespues.navigation.Navigator;
import com.feragusper.buenosairesantesydespues.presenter.HistoricalRecordDetailsPresenter;
import com.feragusper.buenosairesantesydespues.view.HistoricalRecordDetailsView;
import com.feragusper.buenosairesantesydespues.view.widget.SlideImageView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

    private int historicalRecordId;

    @Inject
    HistoricalRecordDetailsPresenter historicalRecordDetailsPresenter;

    @InjectView(R.id.siv_before_after)
    SlideImageView slideImageView;
    @InjectView(R.id.tv_historical_record_address)
    TextView address;
    @InjectView(R.id.tv_historical_record_description)
    TextView description;
    @InjectView(R.id.tv_historical_record_year_neighborhood)
    TextView yearAndNeighborhood;
    @InjectView(R.id.iv_historical_record_share)
    View ivHistoricalRecordShare;
    @InjectView(R.id.tv_credits)
    TextView credits;

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

    @SuppressWarnings("ConstantConditions")
    @Override
    public void renderHistoricalRecord(final HistoricalRecordModel historicalRecord) {
        if (historicalRecord != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(historicalRecord.getTitle());

            slideImageView.setImageUrls(historicalRecord.getImageURLAfter(), historicalRecord.getImageURLBefore());
            address.setText(historicalRecord.getAddress());

            if (historicalRecord.getDescription() != null && !historicalRecord.getDescription().isEmpty()) {
                description.setText(historicalRecord.getDescription());
                description.setVisibility(View.VISIBLE);
            }

            credits.setText(historicalRecord.getCredits());

            if ((historicalRecord.getYear() != null && !historicalRecord.getYear().isEmpty()) || (historicalRecord.getNeighborhood() != null && !historicalRecord.getNeighborhood().isEmpty())) {
                yearAndNeighborhood.setText(historicalRecord.getYear() + " - " + historicalRecord.getNeighborhood());
                yearAndNeighborhood.setVisibility(View.VISIBLE);
            }

            final LatLng latLng = new LatLng(historicalRecord.getLat(), historicalRecord.getLng());
            mMap.addMarker(new MarkerOptions().position(latLng).title(historicalRecord.getAddress()).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_off)));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(14.0f).build();
            CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
            mMap.moveCamera(cameraUpdate);

            ivHistoricalRecordShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateToShare(historicalRecord);
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

    private void navigateToShare(HistoricalRecordModel historicalRecord) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getContext().getString(R.string.share_body, historicalRecord.getAddress(), historicalRecord.getShareURL()));
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, getContext().getString(R.string.share_subject, historicalRecord.getTitle()));
        sendIntent.setType("text/plain");
        getActivity().startActivity(sendIntent);
    }
}
